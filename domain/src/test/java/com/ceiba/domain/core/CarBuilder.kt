package com.ceiba.domain.core

import com.ceiba.domain.entity.Car

class CarBuilder {

    var licensePlate: String
    var vehicleType: String

    init {
        this.licensePlate = "ABC000"
        this.vehicleType = "Moto"
    }

    fun withLicensePlate(licensePlate: String): CarBuilder {
        this.licensePlate = licensePlate
        return this
    }

    fun withVehicleType(vehicleType: String): CarBuilder {
        this.vehicleType = vehicleType
        return this
    }

    fun build(): Car {
        return Car(licensePlate, vehicleType)
    }

    companion object {
        fun aCar(): CarBuilder {
            return CarBuilder()
        }
    }

}