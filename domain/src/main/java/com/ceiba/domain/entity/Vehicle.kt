package com.ceiba.domain.entity

import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.valueobject.Time

abstract class Vehicle(val licensePlate: String) {
    abstract var totalValueParking: Int

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
        if (licensePlate.isNullOrEmpty()){
            throw ParkingException(MESSAGE_EMPTY)
        }
    }

    abstract fun validateAmountVehicle(amountVehicles: Int)

    abstract fun calculateTotalForVehicle(time: Time): Int

    companion object {
        const val MESSAGE_EMPTY = "Los campos no pueden estar vacíos"
    }


}