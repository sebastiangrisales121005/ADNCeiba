package com.ceiba.application.anticorruption

import com.ceiba.application.dto.ParkingApplicationDto
import com.ceiba.domain.aggregate.ParkingEntranceExit

class ParkingApplicationTranslator {
    companion object {
        fun fromDomainToApplication(parkingEntranceExit: ParkingEntranceExit): ParkingApplicationDto = ParkingApplicationDto().apply {
            totalValueParking = parkingEntranceExit.vehicle.calculateTotalForVehicle(parkingEntranceExit).toString()
            numberDays = parkingEntranceExit.time.numberDays.toString()
            numberHours = parkingEntranceExit.time.numberHours.toString()
        }
    }
}