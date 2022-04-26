package com.ceiba.application.service

import com.ceiba.application.anticorruption.ParkingApplicationTranslator
import com.ceiba.application.dto.ParkingApplicationDto
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.service.ParkingEntranceExitService
import javax.inject.Inject

class ParkingEntranceExitServiceApplication @Inject constructor() {

    @Inject
    lateinit var parkingService: ParkingEntranceExitService

    suspend fun enterVehicle(parking: ParkingEntranceExit): Long? {
        return parkingService.enterVehicle(parking)
    }

    suspend fun outVehicle(parking: ParkingEntranceExit): Int? {
        return parkingService.outVehicle(parking)
    }

    suspend fun calculateAmountParking(licensePlate: String, endTime: String): ParkingApplicationDto {
        return ParkingApplicationTranslator.fromDomainToApplication(parkingService.calculateAmountParking(licensePlate, endTime))

    }
}