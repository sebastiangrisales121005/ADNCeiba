package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.ParkingValidateEnter

interface ParkingRepository {
    suspend fun enterVehicle(parking: ParkingValidateEnter): Long?

    suspend fun deleteVehicle(parking: ParkingValidateEnter): Int?

    suspend fun calculateAmountParking(parking: ParkingValidateEnter): ParkingValidateEnter?

    suspend fun validateAmountMotorcycle(): Int

    suspend fun getCountCarParking(): Int
}