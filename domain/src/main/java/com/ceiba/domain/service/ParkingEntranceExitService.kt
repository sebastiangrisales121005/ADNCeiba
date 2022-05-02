package com.ceiba.domain.service

import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.repository.ParkingEntranceExitRepository
import com.ceiba.domain.repository.VehicleEnterRepository
import javax.inject.Inject

class ParkingEntranceExitService @Inject constructor(private val parkingRepository: ParkingEntranceExitRepository) {

    suspend fun enterVehicle(parking: ParkingEntranceExit, vehicleEnterRepository: VehicleEnterRepository): Long? {
        parking.validateEnterLicensePlate()
        validateCountVehicle(parking)
        val id = validateVehicleState(parking.vehicle.licensePlate)

        return id?.let { return it } ?: kotlin.run { return parkingRepository.enterVehicle(parking, vehicleEnterRepository) }
    }

    suspend fun outVehicle(licensePlate: String): Int? = parkingRepository.outVehicle(licensePlate)


    suspend fun calculateAmountParking(licensePlate: String, endTime: String): ParkingEntranceExit {
        val parkingUpdate = parkingRepository.calculateAmountParking(licensePlate, endTime)
        parkingUpdate?.vehicle?.calculateTotalForVehicle(parkingUpdate)
        parkingUpdate?.let { return it }
            ?: kotlin.run { throw ParkingException(CALCULATE_ERROR) }

    }

    private suspend fun validateCountVehicle(parking: ParkingEntranceExit) {
        parking.vehicle.validate(parkingRepository.getCountVehicleParking(parking.vehicle.javaClass.simpleName))
    }

    private suspend fun validateVehicleState(licensePlate: String): Long? {
        var id: Long? = null
        if (parkingRepository.getVehicleExistState(licensePlate)?.equals(OUT_STATE) == true) {
            id = parkingRepository.outVehicle(licensePlate)?.toLong()
        }

        return id

    }


    companion object {
        const val CALCULATE_ERROR = "Error en el cálculo"
        const val OUT_STATE = 1
    }

}