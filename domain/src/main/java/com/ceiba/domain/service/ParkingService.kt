package com.ceiba.domain.service

import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.repository.ParkingRepository
import javax.inject.Inject

class ParkingService @Inject constructor(parkingRepository: ParkingRepository) {
    lateinit var parkingRepository: ParkingRepository

    init {
        this.parkingRepository = parkingRepository
    }

    fun enterVehicle(parking: Parking) {
        parkingRepository.enterVehicle(parking)
    }
}