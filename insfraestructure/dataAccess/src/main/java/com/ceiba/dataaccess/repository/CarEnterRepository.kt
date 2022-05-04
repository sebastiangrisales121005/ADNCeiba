package com.ceiba.dataaccess.repository

import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.entity.Car
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.repository.VehicleEnterRepository

class CarEnterRepository: VehicleEnterRepository {

    override suspend fun enterVehicle(parkingEntranceExit: ParkingEntranceExit): Int {
        if (parkingEntranceExit.vehicle is Car) {
            return EMPTY_CYLINDER_CAPACITY
        }

        throw ParkingException(CYLINDER_CAPACITY_ERROR)
    }

    companion object {
        const val EMPTY_CYLINDER_CAPACITY = 0
        const val CYLINDER_CAPACITY_ERROR = "Error en el cilindraje"
    }


}