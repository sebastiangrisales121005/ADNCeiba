package com.ceiba.domain.service

import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.repository.ParkingRepository
import com.ceiba.domain.valueobject.Time
import javax.inject.Inject

class ParkingService @Inject constructor(parkingRepository: ParkingRepository) {
    private var parkingRepository: ParkingRepository = parkingRepository

    fun enterVehicle(parking: Parking) {
        parkingRepository.enterVehicle(parking)
    }

    fun calculateTimeParking(time: Time): Time {
        return parkingRepository.calculateTimeParking(time)
    }
}