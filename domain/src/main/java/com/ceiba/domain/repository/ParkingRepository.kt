package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.valueobject.Time

interface ParkingRepository {
    fun enterVehicle(parking: Parking)

    fun calculateTimeParking(time: Time): Time
}