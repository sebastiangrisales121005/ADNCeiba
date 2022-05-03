package com.ceiba.dataaccess.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ceiba.dataaccess.dto.ParkingEntity

@Database(
    entities = [ParkingEntity::class],
    version = 2
)
abstract class ParkingDbRoomImpl: RoomDatabase() {
    abstract fun parkingDao(): ParkingServiceRoom
}