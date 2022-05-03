package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.ParkingEntranceExit

interface VehicleEnterRepository {
    suspend fun enterVehicle(parkingEntranceExit: ParkingEntranceExit): Int
}