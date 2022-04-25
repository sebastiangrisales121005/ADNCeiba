package com.ceiba.domain.core

import com.ceiba.domain.entity.Car

class CarBuilder {

    var licensePlate: String

    init {
        this.licensePlate = "ABC000"
    }

    fun withLicensePlate(licensePlate: String): CarBuilder {
        this.licensePlate = licensePlate
        return this
    }

    fun build(): Car {
        return Car(licensePlate)
    }

    companion object {
        fun aCar(): CarBuilder {
            return CarBuilder()
        }
    }

}