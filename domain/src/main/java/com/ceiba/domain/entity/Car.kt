package com.ceiba.domain.entity

import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.valueobject.Time

class Car(licensePlate: String, vehicleType: String): Vehicle(licensePlate, vehicleType)  {
    override fun validateAmountVehicle(amountVehicles: Int) {
        if (amountVehicles >= 20) {
            throw ParkingException("El parqueadero no permite más carros")
        }
    }

    override fun calculateTotalForVehicle(time: Time): Int {
        return calculateTotalValueParking(time, PRICE_DAY_CAR, PRICE_HOUR_CAR)
    }

    companion object {
        const val PRICE_HOUR_CAR = 1000
        const val PRICE_DAY_CAR = 8000
    }

}