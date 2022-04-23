package com.ceiba.domain.core

import com.ceiba.domain.entity.Car

class CarBuilder {

    var licensePlate: String
    var vehicleType: String
    var cylinderCapacity: Int

    init {
        this.licensePlate = "ABC000"
        this.vehicleType = "Carro"
        this.cylinderCapacity = 1200
    }

    fun withLicensePlate(licensePlate: String): CarBuilder {
        this.licensePlate = licensePlate
        return this
    }

    fun withVehicleType(vehicleType: String): CarBuilder {
        this.vehicleType = vehicleType
        return this
    }

    fun withCylinderCapacity(cylinderCapacity: Int): CarBuilder {
        this.cylinderCapacity = cylinderCapacity
        return this
    }

    fun build(): Car {
        return Car(licensePlate, vehicleType, cylinderCapacity)
    }

    companion object {
        fun aCar(): CarBuilder {
            return CarBuilder()
        }
    }

}