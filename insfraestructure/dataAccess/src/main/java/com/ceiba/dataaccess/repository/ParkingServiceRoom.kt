package com.ceiba.dataaccess.repository

import androidx.room.*
import com.ceiba.dataaccess.dto.ParkingEntity

@Dao
interface ParkingServiceRoom {
    @Insert
    suspend fun insertVehicle(parkingEntity: ParkingEntity): Long

    @Query("SELECT * FROM ParkingEntity WHERE licensePlate = :licensePlate")
    suspend fun validateVehicleExist(licensePlate: String): List<ParkingEntity>

    @Query("SELECT COUNT(licensePlate) FROM ParkingEntity WHERE vehicleType = 'Moto'")
    suspend fun getCountMotorCycle(): Int

    @Query("SELECT COUNT(licensePlate) FROM ParkingEntity WHERE vehicleType = 'Carro'")
    suspend fun getCountCar(): Int

    @Query("UPDATE ParkingEntity SET endDateTime = :endTime WHERE licensePlate = :licensePlate")
    suspend fun update(licensePlate: String, endTime: String?)

    @Delete
    suspend fun deleteVehicle(parkingEntity: ParkingEntity): Int?
}