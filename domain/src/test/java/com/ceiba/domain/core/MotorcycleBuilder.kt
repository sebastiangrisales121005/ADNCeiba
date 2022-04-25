package com.ceiba.domain.core

import com.ceiba.domain.entity.Motorcycle

class MotorcycleBuilder {
    var licensePlate: String
    var cylinderCapacity: Int

    init {
        this.licensePlate = "ABC000"
        this.cylinderCapacity = 150
    }

    fun withLicensePlate(licensePlate: String): MotorcycleBuilder {
        this.licensePlate = licensePlate
        return this
    }

    fun withCylinderCapacity(cylinderCapacity: Int): MotorcycleBuilder {
        this.cylinderCapacity = cylinderCapacity
        return this
    }

    fun build(): Motorcycle {
        return Motorcycle(licensePlate, cylinderCapacity)
    }

    companion object {
        fun aMotorcycle(): MotorcycleBuilder {
            return MotorcycleBuilder()
        }
    }
}