package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.Parking

interface ParkingRepository {
    suspend fun enterVehicle(parking: Parking): Long?

    suspend fun deleteVehicle(parking: Parking): Int?

    suspend fun calculateAmountParking(parking: Parking): Parking?
}