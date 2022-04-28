package com.ceiba.dataaccess.repository

import android.content.Context
import androidx.room.Room

class RoomInstance {
    companion object {
        fun createDatabase(context: Context): ParkingDbRoomImpl =
            Room.databaseBuilder(context, ParkingDbRoomImpl::class.java, ParkingRepositoryRoom.DB_NAME).build()
    }
}