package com.ceiba.dataaccess.repository

import com.ceiba.dataaccess.anticorruption.ParkingTranslator
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.entity.Car
import com.ceiba.domain.repository.VehicleEnterRepository
import javax.inject.Inject

class CarEnterRepository @Inject constructor(): ParkingRepositoryRoom(), VehicleEnterRepository {

    override suspend fun enterVehicle(parkingEntranceExit: ParkingEntranceExit): Long? {
        if (parkingEntranceExit.vehicle is Car) {
            val car = parkingEntranceExit.vehicle as Car
            val parkingEntity = ParkingTranslator.fromDomainToEntity(parkingEntranceExit)
            parkingEntity.cylinderCapacity = EMPTY_CYLINDER_CAPACITY

            return enterVehicleParking(parkingEntity, car.licensePlate)
        }

        return null
    }

    companion object {
        const val EMPTY_CYLINDER_CAPACITY = 0
    }


}