package com.ceiba.domain.entity

import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.exception.ParkingException

abstract class Vehicle(val licensePlate: String) {
    var totalValueParking: Int = 0

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

    protected abstract fun assignCalculateValueParking(parkingEntranceExit: ParkingEntranceExit): Int

    fun calculateTotalForVehicle(parkingEntranceExit: ParkingEntranceExit){
        totalValueParking = assignCalculateValueParking(parkingEntranceExit)
    }

    companion object {
        const val MESSAGE_EMPTY = "Los campos no pueden estar vacíos"
    }


}