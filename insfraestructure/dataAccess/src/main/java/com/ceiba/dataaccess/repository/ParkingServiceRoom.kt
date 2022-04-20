package com.ceiba.dataaccess.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ceiba.dataaccess.dto.ParkingDto

@Dao
interface ParkingServiceRoom {
    @Insert
    suspend fun insertVehicle(parkingDto: ParkingDto): Long

    @Query("SELECT * FROM ParkingDto WHERE licensePlate = :licensePlate" )
    suspend fun validateVehicleExist(licensePlate: String): List<ParkingDto>

    @Query("SELECT COUNT(licensePlate) FROM ParkingDto WHERE vehicleType = 'Moto'")
    suspend fun getCountMotorCycle(): Int

    @Query("SELECT COUNT(licensePlate) FROM ParkingDto WHERE vehicleType = 'Carro'")
    suspend fun getCountCar(): Int
}