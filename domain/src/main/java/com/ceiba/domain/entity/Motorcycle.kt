package com.ceiba.domain.entity

import com.ceiba.domain.exception.ParkingException

class Motorcycle(val licensePlate: String, val vehicleType: String, val cylinderCapacity: Int) : Vehicle(licensePlate, vehicleType) {

    /*override fun validateVehicleType(): Boolean {
        return vehicleType.equals("Moto")

    }*7

    override fun validateAmountVehicle(amountVehicles: Int) {
        if (amountVehicles >= 10) {
            throw ParkingException("El parqueadero no permite más motos")
        }
    }


}