package com.ceiba.dataaccess.repository

import androidx.room.Dao
import com.ceiba.dataaccess.dto.ParkingDto

@Dao
interface ParkingServiceRoom {
    suspend fun insertVehicle(parkingDto: ParkingDto)
}