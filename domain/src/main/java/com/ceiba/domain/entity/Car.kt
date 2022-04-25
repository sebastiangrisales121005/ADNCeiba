package com.ceiba.domain.entity

import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.valueobject.Time

class Car(licensePlate: String): Vehicle(licensePlate)  {
    override var totalValueParking: Int = 0

    override fun validateAmountVehicle(amountVehicles: Int) {
        if (amountVehicles >= CAR_LIMIT_PARKING) {
            throw ParkingException(MESSAGE_RESTRICTED)
        }
    }

    override fun calculateTotalForVehicle(time: Time): Int {
        totalValueParking = calculateTotalValueParking(time, PRICE_DAY_CAR, PRICE_HOUR_CAR)

        return totalValueParking
    }

    companion object {
        const val PRICE_HOUR_CAR = 1000
        const val PRICE_DAY_CAR = 8000
        const val MESSAGE_RESTRICTED = "El parqueadero no permite más carros"
        const val CAR_LIMIT_PARKING = 20
    }

}