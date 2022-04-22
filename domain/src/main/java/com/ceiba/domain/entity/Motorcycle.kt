package com.ceiba.domain.entity

import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.valueobject.Time

class Motorcycle(licensePlate: String, vehicleType: String, val cylinderCapacity: Int) : Vehicle(licensePlate, vehicleType) {

    /*override fun validateVehicleType(): Boolean {
        return vehicleType.equals("Moto")

    }*/

    override fun validateAmountVehicle(amountVehicles: Int) {
        if (amountVehicles >= 10) {
            throw ParkingException("El parqueadero no permite más motos")
        }
    }

    override fun calculateTotalForVehicle(time: Time): Int {
        var totalValueParking = calculateTotalValueParking(time)

        if (cylinderCapacity > 500) {
            totalValueParking += 2000
        }

        return totalValueParking
    }


}