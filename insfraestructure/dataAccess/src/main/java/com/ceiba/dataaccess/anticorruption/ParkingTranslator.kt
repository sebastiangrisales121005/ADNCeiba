package com.ceiba.dataaccess.anticorruption

import com.ceiba.dataaccess.dto.ParkingEntity
import com.ceiba.domain.aggregate.ParkingValidateEnter

class ParkingTranslator {
    companion object {
        fun fromDomainToEntity(parking: ParkingValidateEnter): ParkingEntity = ParkingEntity().apply {

            licensePlate = parking.vehicle.licensePlate
            vehicleType = parking.vehicle.javaClass.simpleName

            startDateTime = parking.time.startDateTime
            endDateTime = parking.time.endDateTime
            day = parking.time.day

        }
    }
}