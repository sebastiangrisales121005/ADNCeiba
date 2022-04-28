package com.ceiba.application.service

import com.ceiba.application.anticorruption.ParkingApplicationTranslator
import com.ceiba.application.dto.ParkingApplicationDto
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.service.ParkingEntranceExitService
import javax.inject.Inject

class ParkingEntranceExitServiceApplication @Inject constructor() {

    @Inject
    lateinit var parkingService: ParkingEntranceExitService

    suspend fun enterVehicle(parking: ParkingEntranceExit): Long? = parkingService.enterVehicle(parking)


    suspend fun outVehicle(licensePlate: String): Int?  = parkingService.outVehicle(licensePlate)


    suspend fun calculateAmountParking(licensePlate: String, endTime: String): ParkingApplicationDto =
        ParkingApplicationTranslator.fromDomainToApplication(parkingService.calculateAmountParking(licensePlate, endTime))

}