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
            val parkingEntity = ParkingTranslator.fromDomainToEntity(parkingEntranceExit)
            parkingEntity.cylinderCapacity = motorcycle.cylinderCapacity

            return enterVehicleParking(parkingEntity, motorcycle.licensePlate)

        }

        return null
    }

}