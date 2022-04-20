package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.Parking

interface ParkingRepository {
    fun enterVehicle(parking: Parking): Long
}