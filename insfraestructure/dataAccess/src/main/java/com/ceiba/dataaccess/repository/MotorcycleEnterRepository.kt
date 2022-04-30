package com.ceiba.dataaccess.repository

import com.ceiba.dataaccess.anticorruption.ParkingTranslator
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.repository.VehicleEnterRepository
import javax.inject.Inject

class MotorcycleEnterRepository @Inject constructor(): ParkingRepositoryRoom(), VehicleEnterRepository {


    override suspend fun enterVehicle(parkingEntranceExit: ParkingEntranceExit): Long? {
        if (parkingEntranceExit.vehicle is Motorcycle) {
            val motorcycle = parkingEntranceExit.vehicle as Motorcycle

            var id: Long? = null
            val vehicleExist = parkingDbRoomImpl.validateVehicleExist(motorcycle.licensePlate)
            if (vehicleExist.isEmpty()) {
                val parkingEntity = ParkingTranslator.fromDomainToEntity(parkingEntranceExit)
                parkingEntity.cylinderCapacity = motorcycle.cylinderCapacity

                id = executeInsertVehicle(parkingEntity)
            }

            return id
        }

        return null
    }

}