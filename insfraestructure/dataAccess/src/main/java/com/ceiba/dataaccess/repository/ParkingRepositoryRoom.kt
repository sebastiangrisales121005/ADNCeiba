package com.ceiba.dataaccess.repository

import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.repository.ParkingRepository
import javax.inject.Inject

class ParkingRepositoryRoom @Inject constructor(): ParkingRepository {
    override fun enterVehicle(parking: Parking) {

    }
}