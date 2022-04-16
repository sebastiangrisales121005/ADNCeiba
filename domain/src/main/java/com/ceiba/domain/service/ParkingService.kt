package com.ceiba.domain.service

import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.repository.ParkingRepository
import javax.inject.Inject

class ParkingService @Inject constructor(parkingRepository: ParkingRepository) {
    private var parkingRepository: ParkingRepository? = null

    init {
        this.parkingRepository = parkingRepository
    }

    fun enterVehicle(parking: Parking) {
        parkingRepository?.enterVehicle(parking)
    }
}