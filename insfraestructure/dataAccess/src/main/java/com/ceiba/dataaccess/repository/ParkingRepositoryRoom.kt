package com.ceiba.dataaccess.repository

import com.ceiba.application.service.factory.VehicleFactory
import com.ceiba.dataaccess.anticorruption.ParkingTranslator
import com.ceiba.dataaccess.dto.ParkingEntity
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.repository.ParkingEntranceExitRepository
import com.ceiba.domain.valueobject.Time
import javax.inject.Inject

class ParkingRepositoryRoom @Inject constructor(): ParkingEntranceExitRepository {
    
    @Inject
    lateinit var parkingDbRoomImpl: ParkingServiceRoom

    override suspend fun enterVehicle(parking: ParkingEntranceExit): Long? {
        var id: Long? = null
        val vehicleExist = parkingDbRoomImpl.validateVehicleExist(parking.vehicle.licensePlate)
        if (vehicleExist.isEmpty()) {
            val parkingEntity = ParkingTranslator.fromDomainToEntity(parking)
            enterCylinderCapacity(parking, parkingEntity)

            id = executeInsertVehicle(parkingEntity)
        }
        else if (vehicleExist[0].stateVehicle?.equals(1) == true) {
            id = parkingDbRoomImpl.outVehicle(0, parking.vehicle.licensePlate)?.toLong()

        }

        return id
    }

    override suspend fun outVehicle(licensePlate: String): Int? {
        parkingDbRoomImpl.outVehicle(1, licensePlate)
        return parkingDbRoomImpl
            .validateVehicleExist(licensePlate)[0].stateVehicle

    }

    override suspend fun calculateAmountParking(licensePlate: String, endTime: String): ParkingEntranceExit? =
        getVehiclesParkingDb(licensePlate, endTime)



    override suspend fun getCountVehicleParking(vehicleType: String): Int = getCountVehicle(vehicleType)

    private suspend fun getVehiclesParkingDb(licensePlate: String, endTime: String): ParkingEntranceExit? {
        updateWithDrawVehicle(licensePlate, endTime)
        val parkingDB = parkingDbRoomImpl.validateVehicleExist(licensePlate)[0]

        val vehicle = parkingDB.vehicleType?.let { vehicleType ->
            parkingDB.cylinderCapacity?.let { cylinder ->
                VehicleFactory.build(
                    parkingDB.licensePlate, vehicleType,
                    cylinder
                )
            }
        }

        return vehicle?.let {
            ParkingEntranceExit(
                it,
                Time(parkingDB.startDateTime, parkingDB.endDateTime, parkingDB.day)
            )
        }

    }

    private suspend fun updateWithDrawVehicle(licensePlate: String, endTime: String) {
        parkingDbRoomImpl.update(licensePlate, endTime)
    }

    private suspend fun getCountVehicle(vehicleType: String): Int = parkingDbRoomImpl.getCountVehicle(vehicleType)

    private suspend fun executeInsertVehicle(parkingEntity: ParkingEntity): Long = parkingDbRoomImpl.insertVehicle(parkingEntity)


    private fun enterCylinderCapacity(parking: ParkingEntranceExit, parkingEntity: ParkingEntity) {
        if (parking.vehicle is Motorcycle) {
            val motorcycle = parking.vehicle as Motorcycle
            parkingEntity.cylinderCapacity = motorcycle.cylinderCapacity
        }
    }

    companion object {
        const val DB_NAME = "PARKING"
    }
}