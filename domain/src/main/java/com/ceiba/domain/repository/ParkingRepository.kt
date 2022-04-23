package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.ParkingValidateEnter

interface ParkingRepository {
    suspend fun enterVehicle(parking: ParkingValidateEnter): Long?

    suspend fun deleteVehicle(parking: ParkingValidateEnter): Int?

    suspend fun calculateAmountParking(licensePlate: String, endTime: String): ParkingValidateEnter?

    suspend fun getCountMotorcycleParking(): Int

    suspend fun getCountCarParking(): Int
}