package com.ceiba.application.service

import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.service.ParkingService
import javax.inject.Inject

class ParkingServiceApplication @Inject constructor() {

    @Inject
    lateinit var parkingService: ParkingService

    suspend fun enterVehicle(parking: Parking): Long? {
        return parkingService.enterVehicle(parking)
    }
}