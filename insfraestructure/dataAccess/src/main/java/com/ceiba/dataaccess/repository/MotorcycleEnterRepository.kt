package com.ceiba.dataaccess.repository

import com.ceiba.dataaccess.repository.CarEnterRepository.Companion.CYLINDER_CAPACITY_ERROR
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.repository.VehicleEnterRepository

class MotorcycleEnterRepository: VehicleEnterRepository {

    override suspend fun enterVehicle(parkingEntranceExit: ParkingEntranceExit): Int {
        if (parkingEntranceExit.vehicle is Motorcycle) {
            val motorcycle = parkingEntranceExit.vehicle as Motorcycle
            return motorcycle.cylinderCapacity

        }

        throw ParkingException(CYLINDER_CAPACITY_ERROR)
    }

}