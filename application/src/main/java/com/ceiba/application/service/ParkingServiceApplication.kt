package com.ceiba.application.service

import com.ceiba.domain.aggregate.ParkingValidateEnter
import com.ceiba.domain.service.ParkingValidateEnterService
import javax.inject.Inject

class ParkingServiceApplication @Inject constructor() {

    @Inject
    lateinit var parkingService: ParkingValidateEnterService

    suspend fun enterVehicle(parking: ParkingValidateEnter): Long? {
        return parkingService.enterVehicle(parking)
    }

    suspend fun deleteVehicle(parking: ParkingValidateEnter): Int? {
        return parkingService.deleteVehicle(parking)
    }

    suspend fun calculateAmountParking(licensePlate: String, endTime: String): ParkingValidateEnter? {
        return parkingService.calculateAmountParking(licensePlate, endTime)
    }
}