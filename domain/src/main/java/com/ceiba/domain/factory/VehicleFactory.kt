package com.ceiba.domain.factory

import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.entity.Vehicle

class VehicleFactory {
    companion object {
        fun build(licensePlate: String, vehicleType: String, cylinderCapacity: Int): Vehicle? {
            var vehicle: Vehicle? = null
            if (vehicleType == "Moto") {
                vehicle = Motorcycle(licensePlate, vehicleType, cylinderCapacity)
            }

            return vehicle
        }
    }
}