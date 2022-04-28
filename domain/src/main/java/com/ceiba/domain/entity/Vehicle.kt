package com.ceiba.domain.entity

import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.exception.ParkingException

abstract class Vehicle(val licensePlate: String) {
    private var totalValueParking: Int = 0

    init {
        validateDataEmpty()
        validateLength()
    }

    fun validate(amountVehicles: Int) {
        validateAmountVehicle(amountVehicles)

    }

    private fun validateDataEmpty() {
        if (licensePlate.isEmpty()){
            throw ParkingException(MESSAGE_EMPTY)
        }
    }

    private fun validateLength() {
        if (licensePlate.length > 6) {
            throw ParkingException(MESSAGE_LENGTH)
        }
    }

    abstract fun validateAmountVehicle(amountVehicles: Int)

    protected abstract fun assignCalculateValueParking(parkingEntranceExit: ParkingEntranceExit): Int

    fun calculateTotalForVehicle(parkingEntranceExit: ParkingEntranceExit): Int{
        totalValueParking = assignCalculateValueParking(parkingEntranceExit)

        return totalValueParking
    }

    companion object {
        const val MESSAGE_EMPTY = "Los campos no pueden estar vacíos"
        const val MESSAGE_LENGTH = "La placa no debe ser mayor de 6 caracteres"
    }


}