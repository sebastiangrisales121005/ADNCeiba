package com.ceiba.dataaccess.repository

import androidx.room.*
import com.ceiba.dataaccess.dto.ParkingEntity

@Dao
interface ParkingServiceRoom {
    @Insert
    suspend fun insertVehicle(parkingEntity: ParkingEntity): Long

    @Query("SELECT * FROM ParkingEntity WHERE licensePlate = :licensePlate")
    suspend fun validateVehicleExist(licensePlate: String): List<ParkingEntity>

    @Query("SELECT COUNT(licensePlate) FROM ParkingEntity WHERE vehicleType = :vehicleType")
    suspend fun getCountVehicle(vehicleType: String): Int

    @Query("UPDATE ParkingEntity SET endDateTime = :endTime WHERE licensePlate = :licensePlate")
    suspend fun update(licensePlate: String, endTime: String?)

    @Query("UPDATE ParkingEntity SET stateVehicle = :state WHERE licensePlate = :licensePlate")
    suspend fun outVehicle(state: Int, licensePlate: String): Int?
}