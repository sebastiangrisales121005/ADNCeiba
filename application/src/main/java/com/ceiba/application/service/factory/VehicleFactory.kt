package com.ceiba.application.service.factory

import com.ceiba.domain.entity.Car
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.entity.Vehicle

class VehicleFactory {
    companion object {
        private const val MOTORCYCLE = "Moto"
        private const val CAR = "Carro"

        fun build(licensePlate: String, vehicleType: String?, cylinderCapacity: Int): Vehicle? {
            var vehicle: Vehicle? = null
            if (vehicleType == MOTORCYCLE) {
                vehicle = Motorcycle(licensePlate, cylinderCapacity)
            } else if (vehicleType == CAR) {
                vehicle = Car(licensePlate)
            }

            return vehicle
        }
    }
}