package com.ceiba.dataaccess.anticorruption

import com.ceiba.application.service.factory.VehicleFactory
import com.ceiba.dataaccess.dto.ParkingEntity
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.valueobject.Time

class ParkingTranslator {
    companion object {
        fun fromDomainToEntity(parking: ParkingEntranceExit): ParkingEntity =
            ParkingEntity().apply {

                licensePlate = parking.vehicle.licensePlate
                vehicleType = parking.vehicle.javaClass.simpleName

                startDateTime = parking.time.startDateTime
                endDateTime = parking.time.endDateTime
                day = parking.time.day

                stateVehicle = 0

            }

        fun fromEntityToDomain(parkingEntity: ParkingEntity): ParkingEntranceExit? {
            val vehicle = parkingEntity.cylinderCapacity?.let {
                VehicleFactory.build(parkingEntity.licensePlate,
                    parkingEntity.vehicleType, it
                )
            }

            val time = Time(parkingEntity.startDateTime, parkingEntity.endDateTime,
            parkingEntity.day)

            return vehicle?.let { ParkingEntranceExit(it, time) }

        }
    }
}