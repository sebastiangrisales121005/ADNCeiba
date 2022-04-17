package com.ceiba.dataaccess.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ceiba.dataaccess.dto.ParkingDto

@Database(
    entities = [ParkingDto::class],
    version = 1
)
abstract class ParkingDbRoomImpl: RoomDatabase() {
    abstract fun parkingDao(): ParkingDto
}