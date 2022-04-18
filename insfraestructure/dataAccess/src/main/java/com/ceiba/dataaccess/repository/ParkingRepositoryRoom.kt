package com.ceiba.dataaccess.repository

import android.content.Context
import com.ceiba.dataaccess.anticorruption.ParkingTranslator
import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.repository.ParkingRepository
import javax.inject.Inject

class ParkingRepositoryRoom @Inject constructor(context: Context): ParkingRepository {

    override fun enterVehicle(parking: Parking) {
        val parkingDto = ParkingTranslator.fromDomainToDto(parking)
    }
}