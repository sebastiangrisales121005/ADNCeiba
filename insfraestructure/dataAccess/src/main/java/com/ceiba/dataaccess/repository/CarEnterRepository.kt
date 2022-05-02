package com.ceiba.dataaccess.repository

import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.entity.Car
import com.ceiba.domain.repository.VehicleEnterRepository

class CarEnterRepository: VehicleEnterRepository {

    override suspend fun enterVehicle(parkingEntranceExit: ParkingEntranceExit): Int? {
        if (parkingEntranceExit.vehicle is Car) {
            return EMPTY_CYLINDER_CAPACITY
        }

        return null
    }

    companion object {
        const val EMPTY_CYLINDER_CAPACITY = 0
    }


}