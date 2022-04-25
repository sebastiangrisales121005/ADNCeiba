package com.ceiba.dataaccess.anticorruption

import com.ceiba.dataaccess.dto.ParkingEntity
import com.ceiba.domain.aggregate.ParkingEntranceExit

class ParkingTranslator {
    companion object {
        fun fromDomainToEntity(parking: ParkingEntranceExit): ParkingEntity = ParkingEntity().apply {

            licensePlate = parking.vehicle.licensePlate
            vehicleType = parking.vehicle.javaClass.simpleName

            startDateTime = parking.time.startDateTime
            endDateTime = parking.time.endDateTime
            day = parking.time.day

        }
    }
}