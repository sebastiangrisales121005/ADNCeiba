package com.ceiba.domain.service

import com.ceiba.domain.aggregate.ParkingValidateEnter
import com.ceiba.domain.entity.Car
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.repository.ParkingRepository
import javax.inject.Inject

class ParkingService @Inject constructor(parkingRepository: ParkingRepository) {
    private var parkingRepository: ParkingRepository = parkingRepository

    suspend fun enterVehicle(parking: ParkingValidateEnter): Long? {
        parking.validateEnterLicensePlate()
        validateMotorCycle(parking)
        validateCar(parking)

        return parkingRepository.enterVehicle(parking)
    }

    suspend fun deleteVehicle(parking: ParkingValidateEnter): Int? {
        return parkingRepository.deleteVehicle(parking)
    }

    suspend fun calculateAmountParking(parking: ParkingValidateEnter): ParkingValidateEnter? {
        return parkingRepository.calculateAmountParking(parking)
    }

    private suspend fun validateMotorCycle(parking: ParkingValidateEnter) {
        val motorcycle = Motorcycle(parking.vehicle.licensePlate, parking.vehicle.vehicleType,
            parking.vehicle.cylinderCapacity)
        motorcycle.validate(parkingRepository.getCountMotorcycleParking())
    }

    private suspend fun validateCar(parking: ParkingValidateEnter) {
        val car = Car(parking.vehicle.licensePlate, parking.vehicle.vehicleType,
            parking.vehicle.cylinderCapacity)
        car.validate(parkingRepository.getCountCarParking())
    }

}