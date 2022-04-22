package com.ceiba.application.service

import com.ceiba.domain.aggregate.ParkingValidateEnter
import com.ceiba.domain.service.ParkingService
import javax.inject.Inject

class ParkingServiceApplication @Inject constructor() {

    @Inject
    lateinit var parkingService: ParkingService

    suspend fun enterVehicle(parking: ParkingValidateEnter): Long? {
        return parkingService.enterVehicle(parking)
    }

    suspend fun deleteVehicle(parking: ParkingValidateEnter): Int? {
        return parkingService.deleteVehicle(parking)
    }

    suspend fun calculateAmountParking(parking: ParkingValidateEnter): ParkingValidateEnter? {
        return parkingService.calculateAmountParking(parking)
    }
}