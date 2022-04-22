package com.ceiba.domain.service

import com.ceiba.domain.aggregate.ParkingValidateEnter
import com.ceiba.domain.repository.ParkingRepository
import javax.inject.Inject

class ParkingService @Inject constructor(parkingRepository: ParkingRepository) {
    private var parkingRepository: ParkingRepository = parkingRepository

    suspend fun enterVehicle(parking: ParkingValidateEnter): Long? {
        return parkingRepository.enterVehicle(parking)
    }

    suspend fun deleteVehicle(parking: ParkingValidateEnter): Int? {
        return parkingRepository.deleteVehicle(parking)
    }

    suspend fun calculateAmountParking(parking: ParkingValidateEnter): ParkingValidateEnter? {
        return parkingRepository.calculateAmountParking(parking)
    }
}