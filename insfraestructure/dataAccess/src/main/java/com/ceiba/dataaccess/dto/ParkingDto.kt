package com.ceiba.dataaccess.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ParkingDto  {
    @PrimaryKey lateinit var licensePlate: String
    var vehicleType: String? = null
    var cylinderCapacity: Int? = null
    var startDateTime: String? = null
    var endDateTime: String? = null
    var day: String? = null
}