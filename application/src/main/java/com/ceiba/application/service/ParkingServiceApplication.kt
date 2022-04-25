package com.ceiba.application.service

import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.service.ParkingValidateEnterService
import javax.inject.Inject

class ParkingServiceApplication @Inject constructor() {

    @Inject
    lateinit var parkingService: ParkingValidateEnterService

    suspend fun enterVehicle(parking: ParkingEntranceExit): Long? {
        return parkingService.enterVehicle(parking)
    }

    suspend fun deleteVehicle(parking: ParkingEntranceExit): Int? {
        return parkingService.deleteVehicle(parking)
    }

    suspend fun calculateAmountParking(licensePlate: String, endTime: String): ParkingEntranceExit? {
        return parkingService.calculateAmountParking(licensePlate, endTime)
    }
}