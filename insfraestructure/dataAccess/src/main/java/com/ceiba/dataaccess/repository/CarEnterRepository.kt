package com.ceiba.dataaccess.repository

import com.ceiba.dataaccess.anticorruption.ParkingTranslator
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.entity.Car
import com.ceiba.domain.repository.VehicleEnterRepository
import javax.inject.Inject

class CarEnterRepository @Inject constructor(): ParkingRepositoryRoom(), VehicleEnterRepository {

    override suspend fun enterVehicle(parkingEntranceExit: ParkingEntranceExit): Long? {
        if (parkingEntranceExit.vehicle is Car) {
            val motorcycle = parkingEntranceExit.vehicle as Car

            var id: Long? = null
            val vehicleExist = parkingDbRoomImpl.validateVehicleExist(motorcycle.licensePlate)
            if (vehicleExist.isEmpty()) {
                val parkingEntity = ParkingTranslator.fromDomainToEntity(parkingEntranceExit)
                parkingEntity.cylinderCapacity = 0

                id = executeInsertVehicle(parkingEntity)
            }

            return id
        }

        return null
    }


}