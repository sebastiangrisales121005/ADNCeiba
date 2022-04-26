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
        validateCountVehicle(parking)

        return parkingRepository.enterVehicle(parking)
    }

    suspend fun outVehicle(licensePlate: String): Int? {
        return parkingRepository.outVehicle(licensePlate)
    }

    suspend fun calculateAmountParking(licensePlate: String, endTime: String): ParkingEntranceExit {
        val parkingUpdate = parkingRepository.calculateAmountParking(licensePlate, endTime)
        parkingUpdate?.vehicle?.calculateTotalForVehicle(parkingUpdate)
        parkingUpdate?.let { return it }
            ?: kotlin.run { throw ParkingException("Error en el cálculo") }

    }

    private suspend fun validateCountVehicle(parking: ParkingEntranceExit) {
        parking.vehicle.validate(parkingRepository.getCountVehicleParking(parking.vehicle.javaClass.simpleName))
    }

}