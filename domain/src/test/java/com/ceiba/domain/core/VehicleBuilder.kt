package com.ceiba.domain.core

import com.ceiba.domain.entity.Vehicle

class VehicleBuilder() {
    var licensePlate: String? = null
    var vehicleType: String? = null
    var cylinderCapacity: Int? = null

    init {
        this.licensePlate = ""
        this.vehicleType = ""
        this.cylinderCapacity = 0
    }

    fun withLicensePlate(licensePlate: String): VehicleBuilder {
        this.licensePlate = licensePlate
        return this
    }

    fun withVehicleType(vehicleType: String): VehicleBuilder {
        this.vehicleType = vehicleType
        return this
    }

    fun withCylinderCapacity(cylinderCapacity: Int): VehicleBuilder {
        this.cylinderCapacity = cylinderCapacity
        return this
    }

    fun build(): Vehicle {
        return Vehicle(licensePlate!!, vehicleType, cylinderCapacity!!)
    }

    companion object {
        fun aVehicle(): VehicleBuilder {
            return VehicleBuilder()
        }
    }
}