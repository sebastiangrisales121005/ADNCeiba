package com.ceiba.domain.entity

import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.valueobject.Time

abstract class Vehicle(val licensePlate: String, val vehicleType: String) {

    fun validate(amountVehicles: Int) {
        validateAmountVehicle(amountVehicles)

    }

    /*fun validateVehicleType(): Boolean {
        if (this.vehicleType.equals("Moto")) {
            return true
        }
        return false
    }*/

    //abstract fun validateVehicleType(): Boolean

    abstract fun validateAmountVehicle(amountVehicles: Int)

    abstract fun calculateTotalValueParking(time: Time): Int

    /*fun validateAmountVehicle(amountCar: Int, amountMotorCycle: Int) {
        if (amountCar >= 20) {
            throw ParkingException("El parqueadero no permite más carros")
        }
        if (amountMotorCycle >= 10) {
            throw ParkingException("El parqueadero no permite más motos")
        }
    }*/


}