package com.ceiba.domain.aggregate

import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.valueobject.Time

class ParkingValidateEnter(var vehicle: Vehicle, val time: Time) {

    fun validateEnterLicensePlate(): Boolean {
        if (vehicle.licensePlate.startsWith(LICENSE_PLATE_RESTRICTED)) {
            if (this.time.day.equals(SUNDAY_RESTRICTED) || this.time.day.equals(MONDAY_RESTRICTED)) {
                return true
            } else {
                throw ParkingException(MESSAGE_RESTRICTED)
            }
        }

        return false
    }

    companion object {
        const val LICENSE_PLATE_RESTRICTED = "A"
        const val SUNDAY_RESTRICTED = "domingo"
        const val MONDAY_RESTRICTED = "lunes"
        const val MESSAGE_RESTRICTED = "Este vehículo no está autorizado a ingresar"
    }

}