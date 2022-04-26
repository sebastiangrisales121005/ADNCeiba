package com.ceiba.domain.service

import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.entity.Car
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.repository.ParkingEntranceExitRepository
import javax.inject.Inject

class ParkingEntranceExitService @Inject constructor(private val parkingRepository: ParkingEntranceExitRepository) {

    suspend fun enterVehicle(parking: ParkingEntranceExit): Long? {
        parking.validateEnterLicensePlate()
        validateMotorCycle(parking)
        validateCar(parking)

        return parkingRepository.enterVehicle(parking)
    }

    suspend fun outVehicle(parking: ParkingEntranceExit): Int? {
        return parkingRepository.outVehicle(parking)
    }

    suspend fun calculateAmountParking(licensePlate: String, endTime: String): ParkingEntranceExit {
        val parkingUpdate = parkingRepository.calculateAmountParking(licensePlate, endTime)
        parkingUpdate?.vehicle?.calculateTotalForVehicle(parkingUpdate)
        parkingUpdate?.let { return it }
            ?: kotlin.run { throw ParkingException("Error en el cálculo") }

    }

    private suspend fun validateMotorCycle(parking: ParkingEntranceExit) {
        if (parking.vehicle.javaClass == Motorcycle::class.java) {
            parking.vehicle.validate(parkingRepository.getCountMotorcycleParking())
        }
    }

    private suspend fun validateCar(parking: ParkingEntranceExit) {
        if (parking.vehicle.javaClass == Car::class.java) {
            parking.vehicle.validate(parkingRepository.getCountCarParking())
        }
    }

}