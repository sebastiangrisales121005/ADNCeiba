package com.ceiba.domain.entity

import com.ceiba.domain.aggregate.ParkingValidateEnter
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

    private fun validateData() {
        if (licensePlate.isNullOrEmpty()){
            throw ParkingException(MESSAGE_EMPTY)
        }
    }

    abstract fun validateAmountVehicle(amountVehicles: Int)

    abstract fun calculateTotalForVehicle(parkingValidateEnter: ParkingValidateEnter): Int

    companion object {
        const val MESSAGE_EMPTY = "Los campos no pueden estar vacíos"
    }


}