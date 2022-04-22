package com.ceiba.domain.entity

import com.ceiba.domain.valueobject.Time

class Car(licensePlate: String, vehicleType: String): Vehicle(licensePlate, vehicleType)  {
    override fun validateAmountVehicle(amountVehicles: Int) {
        TODO("Not yet implemented")
    }

    override fun calculateTotalForVehicle(time: Time): Int {
        return calculateTotalForVehicle(time)
    }

}