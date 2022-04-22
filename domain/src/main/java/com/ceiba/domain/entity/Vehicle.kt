package com.ceiba.domain.entity

import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.valueobject.Time

abstract class Vehicle(val licensePlate: String, val vehicleType: String) {

    fun validate(amountVehicles: Int) {
        validateAmountVehicle(amountVehicles)

    }

    fun calculateTotalValueParking(time: Time): Int {
        time.calculateTimeParking()
        var totalForDays = 0
        time.numberDays?.let {
            totalForDays = it * Parking.PRICE_DAY_MOTORCYCLE
        }

        var totalForHours = 0
        time.numberHours?.let {
            totalForHours = it * Parking.PRICE_HOUR_MOTORCYCLE
        }

        return totalForDays + totalForHours
    }

    /*fun validateVehicleType(): Boolean {
        if (this.vehicleType.equals("Moto")) {
            return true
        }
        return false
    }*/

    //abstract fun validateVehicleType(): Boolean

    abstract fun validateAmountVehicle(amountVehicles: Int)

    abstract fun calculateTotalForVehicle(time: Time): Int

    /*fun validateAmountVehicle(amountCar: Int, amountMotorCycle: Int) {
        if (amountCar >= 20) {
            throw ParkingException("El parqueadero no permite más carros")
        }
        if (amountMotorCycle >= 10) {
            throw ParkingException("El parqueadero no permite más motos")
        }
    }*/


}