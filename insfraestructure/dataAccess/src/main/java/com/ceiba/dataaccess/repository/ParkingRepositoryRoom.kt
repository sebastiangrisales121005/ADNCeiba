package com.ceiba.dataaccess.repository

import android.content.Context
import com.ceiba.dataaccess.anticorruption.ParkingTranslator
import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.repository.ParkingRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ParkingRepositoryRoom @Inject constructor(@ApplicationContext context: Context): ParkingRepository {
    private val context: Context = context

    override fun enterVehicle(parking: Parking) {
        val parkingDto = ParkingTranslator.fromDomainToDto(parking)
    }
}