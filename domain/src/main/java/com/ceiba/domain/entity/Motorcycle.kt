package com.ceiba.domain.entity

import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.valueobject.Time

class Motorcycle(licensePlate: String, vehicleType: String, cylinderCapacity: Int) : Vehicle(licensePlate, vehicleType, cylinderCapacity) {


    override fun validateAmountVehicle(amountVehicles: Int) {
        if (amountVehicles >= 10) {
            throw ParkingException(MESSAGE_RESTRICTED)
        }
    }

    override fun calculateTotalForVehicle(time: Time) {
        totalValueParking = calculateTotalValueParking(time, PRICE_DAY_MOTORCYCLE, PRICE_HOUR_MOTORCYCLE)

        if (cylinderCapacity > 500) {
            totalValueParking += 2000
        }
    }

    companion object {
        const val PRICE_HOUR_MOTORCYCLE = 500
        const val PRICE_DAY_MOTORCYCLE = 4000
        const val MESSAGE_RESTRICTED = "El parqueadero no permite más motos"
    }


}