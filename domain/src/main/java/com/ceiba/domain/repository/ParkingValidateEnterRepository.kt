package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.ParkingEntranceExit

interface ParkingValidateEnterRepository {
    suspend fun enterVehicle(parking: ParkingEntranceExit): Long?

    suspend fun deleteVehicle(parking: ParkingEntranceExit): Int?

    suspend fun calculateAmountParking(licensePlate: String, endTime: String): ParkingEntranceExit?

    suspend fun getCountMotorcycleParking(): Int

    suspend fun getCountCarParking(): Int
}