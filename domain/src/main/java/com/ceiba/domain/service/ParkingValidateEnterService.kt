package com.ceiba.domain.service

import com.ceiba.domain.aggregate.ParkingValidateEnter
import com.ceiba.domain.entity.Car
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.repository.ParkingValidateEnterRepository
import javax.inject.Inject

class ParkingValidateEnterService @Inject constructor(private val parkingRepository: ParkingValidateEnterRepository) {

    suspend fun enterVehicle(parking: ParkingValidateEnter): Long? {
        parking.validateEnterLicensePlate()
        validateMotorCycle(parking)
        validateCar(parking)

        return parkingRepository.enterVehicle(parking)
    }

    suspend fun deleteVehicle(parking: ParkingValidateEnter): Int? {
        return parkingRepository.deleteVehicle(parking)
    }

    suspend fun calculateAmountParking(licensePlate: String, endTime: String): ParkingValidateEnter? {
        return parkingRepository.calculateAmountParking(licensePlate, endTime)
    }

    private suspend fun validateMotorCycle(parking: ParkingValidateEnter) {
        if (parking.vehicle.javaClass == Motorcycle::class.java) {
            parking.vehicle.validate(parkingRepository.getCountMotorcycleParking())
        }
    }

    private suspend fun validateCar(parking: ParkingValidateEnter) {
        if (parking.vehicle.javaClass == Car::class.java) {
            parking.vehicle.validate(parkingRepository.getCountCarParking())
        }
    }

}