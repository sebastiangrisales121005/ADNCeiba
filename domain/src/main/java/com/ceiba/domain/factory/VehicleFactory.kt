package com.ceiba.domain.factory

import com.ceiba.domain.entity.Car
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.entity.Vehicle

class VehicleFactory {
    companion object {
        fun build(licensePlate: String, vehicleType: String, cylinderCapacity: Int): Vehicle? {
            var vehicle: Vehicle? = null
            if (vehicleType == "Moto") {
                vehicle = Motorcycle(licensePlate, vehicleType, cylinderCapacity)
            } else if (vehicleType == "Carro") {
                vehicle = Car(licensePlate, vehicleType)
            }

            return vehicle
        }
    }
}