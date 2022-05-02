package com.ceiba.dataaccess.repository

import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.repository.VehicleEnterRepository
import javax.inject.Inject

class MotorcycleEnterRepository @Inject constructor(): VehicleEnterRepository {

    override suspend fun enterVehicle(parkingEntranceExit: ParkingEntranceExit): Int? {
        if (parkingEntranceExit.vehicle is Motorcycle) {
            val motorcycle = parkingEntranceExit.vehicle as Motorcycle
            return motorcycle.cylinderCapacity

        }

        return null
    }

}