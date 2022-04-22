package com.ceiba.domain.aggregate

import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.valueobject.Time

class Parking(var vehicle: Vehicle, val time: Time) {
    var totalValueParking: Int? = null

    fun validateEnterLicensePlate(): Boolean {
        if (vehicle.licensePlate.startsWith("A")) {
            if (this.time.day.equals("domingo") || this.time.day.equals("lunes")) {
                return true
            } else {
                throw ParkingException("Este vehículo no está autorizado a ingresar")
            }
        }

        return false
    }

    companion object {

    }

}