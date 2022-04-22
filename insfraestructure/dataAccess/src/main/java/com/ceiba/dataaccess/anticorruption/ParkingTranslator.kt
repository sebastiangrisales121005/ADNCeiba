package com.ceiba.dataaccess.anticorruption

import com.ceiba.dataaccess.dto.ParkingDto
import com.ceiba.domain.aggregate.ParkingValidateEnter

class ParkingTranslator {
    companion object {
        fun fromDomainToDto(parking: ParkingValidateEnter): ParkingDto {
            val parkingDto = ParkingDto()
            parkingDto.licensePlate = parking.vehicle!!.licensePlate!!
            parkingDto.vehicleType = parking.vehicle?.vehicleType
            parkingDto.cylinderCapacity = parking.vehicle?.cylinderCapacity

            parkingDto.startDateTime = parking.time?.startDateTime
            parkingDto.endDateTime = parking.time?.endDateTime
            parkingDto.day = parking.time?.day
            return parkingDto
        }
    }
}