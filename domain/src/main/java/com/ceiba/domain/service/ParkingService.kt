package com.ceiba.domain.service

import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.repository.ParkingRepository

class ParkingService(parkingRepository: ParkingRepository) {
    private var parkingRepository: ParkingRepository? = null

    init {
        this.parkingRepository = parkingRepository
    }

    fun enterVehicle(parking: Parking) {
        parkingRepository?.enterVehicle(parking)
    }
}