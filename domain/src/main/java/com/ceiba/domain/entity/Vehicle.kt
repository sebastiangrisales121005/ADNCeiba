package com.ceiba.domain.entity

import com.ceiba.domain.valueobject.Time

abstract class Vehicle(val licensePlate: String, val vehicleType: String) {
    var totalValueParking: Int = 0
    var cylinderCapacityVehicle: Int = 0

    fun validate(amountVehicles: Int) {
        validateAmountVehicle(amountVehicles)

    }

    fun calculateTotalValueParking(time: Time, priceDay: Int, priceHour: Int): Int {
        time.calculateTimeParking()
        var totalForDays = 0
        time.numberDays?.let {
            totalForDays = it * priceDay
        }

        var totalForHours = 0
        time.numberHours?.let {
            totalForHours = it * priceHour
        }

        return totalForDays + totalForHours
    }

    abstract fun validateAmountVehicle(amountVehicles: Int)

    abstract fun calculateTotalForVehicle(time: Time)


}