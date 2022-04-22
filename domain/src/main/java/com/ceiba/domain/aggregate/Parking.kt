package com.ceiba.domain.aggregate

import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.valueobject.Time

class Parking(var vehicle: Vehicle, val time: Time) {
    var totalValueParking: Int? = null

    fun validateEnterLicensePlate(): Boolean {
        if (vehicle.licensePlate.startsWith("A")) {
            if (this.time.day.equals("domingo") || this.time.day.equals("lunes")) {
                return true
            } else {
                throw ParkingException("Este vehículo no está autorizado a ingresar")
            }
        }

        return false
    }

    fun calculateTotalValueParking() {
        val totalForDays: Int
        val totalForHours: Int
        time.calculateTimeParking()

        if (!vehicle.validateVehicleType()) {
            totalForDays = this.time.numberDays!! * PRICE_DAY_CAR
            totalForHours = this.time.numberHours!! * PRICE_HOUR_CAR

            totalValueParking = totalForDays + totalForHours
        } 


    }

    companion object {
        const val PRICE_HOUR_CAR = 1000
        const val PRICE_HOUR_MOTORCYCLE = 500
        const val PRICE_DAY_CAR = 8000
        const val PRICE_DAY_MOTORCYCLE = 4000
    }

}