package com.ceiba.domain.entity

import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.exception.ParkingException

abstract class Vehicle(val licensePlate: String) {
    abstract var totalValueParking: Int

    init {
        validateData()
    }

    fun validate(amountVehicles: Int) {
        validateAmountVehicle(amountVehicles)

    }

    private fun validateData() {
        if (licensePlate.isEmpty()){
            throw ParkingException(MESSAGE_EMPTY)
        }
    }

    abstract fun validateAmountVehicle(amountVehicles: Int)

    abstract fun calculateTotalForVehicle(parkingEntranceExit: ParkingEntranceExit): Int

    companion object {
        const val MESSAGE_EMPTY = "Los campos no pueden estar vacíos"
    }


}