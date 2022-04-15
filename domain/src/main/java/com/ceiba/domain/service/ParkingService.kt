package com.ceiba.domain.service

import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.repository.ParkingRepository
import javax.inject.Inject

class ParkingService {
    private var parkingRepository: ParkingRepository? = null

    @Inject
    constructor(parkingRepository: ParkingRepository) {
        this.parkingRepository = parkingRepository
    }

    fun enterVehicle(parking: Parking) {
        parkingRepository?.enterVehicle(parking)
    }
}