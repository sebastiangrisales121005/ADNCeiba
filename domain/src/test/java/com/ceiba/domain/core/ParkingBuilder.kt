package com.ceiba.domain.core

import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.valueobject.Time

class ParkingBuilder {
    var vehicle: Vehicle? = null
    var time: Time? = null

    init {
        vehicle = null
        time = null
    }

    fun withVehicle(vehicle: Vehicle): ParkingBuilder {
        this.vehicle = vehicle
        return this
    }

    fun withTime(time: Time): ParkingBuilder {
        this.time = time
        return this
    }

    fun build(): ParkingEntranceExit {
        return ParkingEntranceExit(vehicle!!, time!!)
    }

    companion object {
        fun aParking(): ParkingBuilder {
            return ParkingBuilder()
        }
    }
}