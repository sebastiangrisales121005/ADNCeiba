package com.ceiba.domain.entity

import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.valueobject.Time

abstract class Vehicle(val licensePlate: String, val vehicleType: String, val cylinderCapacity: Int) {
    var totalValueParking: Int = 0

    init {
        validateData()
    }

    fun validate(amountVehicles: Int) {
        validateAmountVehicle(amountVehicles)

    }

    fun calculateTotalValueParking(time: Time, priceDay: Int, priceHour: Int): Int {
        time.calculateTimeParking()
        var totalForDays = 0
        time.numberDays.let {
            totalForDays = it * priceDay
        }

        var totalForHours = 0
        time.numberHours?.let {
            totalForHours = it * priceHour
        }

        return totalForDays + totalForHours
    }

    private fun validateData() {
        if (licensePlate.isNullOrEmpty() || vehicleType.isNullOrEmpty()||
            cylinderCapacity == 0) {
            throw ParkingException(MESSAGE_EMPTY)
        }
    }

    abstract fun validateAmountVehicle(amountVehicles: Int)

    abstract fun calculateTotalForVehicle(time: Time)

    companion object {
        const val MESSAGE_EMPTY = "Los campos no pueden estar vacíos"
    }


}