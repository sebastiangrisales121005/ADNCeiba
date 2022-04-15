package com.ceiba.application.service

import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.service.ParkingService

class ParkingServiceApplication {
    var parkingService: ParkingService? = null

    fun enterVehicle(parking: Parking) {
        parkingService?.enterVehicle(parking)
    }
}