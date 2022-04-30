package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.ParkingEntranceExit

interface ParkingEntranceExitRepository {
    //suspend fun enterVehicle(parking: ParkingEntranceExit): Long?

    suspend fun outVehicle(licensePlate: String): Int?

    suspend fun calculateAmountParking(licensePlate: String, endTime: String): ParkingEntranceExit?

    suspend fun getCountVehicleParking(vehicleType: String): Int

    suspend fun getVehicleExistState(licensePlate: String): Int?
}