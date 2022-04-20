package com.ceiba.dataaccess.repository

import androidx.room.Dao
import androidx.room.Insert
import com.ceiba.dataaccess.dto.ParkingDto

@Dao
interface ParkingServiceRoom {
    @Insert
    suspend fun insertVehicle(parkingDto: ParkingDto): Long
}