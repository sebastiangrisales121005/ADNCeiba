package com.ceiba.application.service.factory

import com.ceiba.domain.entity.Car
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.entity.Vehicle

class VehicleFactory {
    companion object {
        private const val MOTORCYCLE_ES = "Moto"
        private const val MOTORCYCLE_EN = "Motorcycle"
        private const val CAR_ES = "Carro"
        private const val CAR_EN = "Car"

        fun build(licensePlate: String, vehicleType: String?, cylinderCapacity: Int): Vehicle? {
            var vehicle: Vehicle? = null
            if (vehicleType == MOTORCYCLE_ES || vehicleType == MOTORCYCLE_EN) {
                vehicle = Motorcycle(licensePlate, cylinderCapacity)
            } else if (vehicleType == CAR_ES || vehicleType == CAR_EN) {
                vehicle = Car(licensePlate)
            }

            return vehicle
        }
    }
}