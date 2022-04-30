package com.ceiba.dataaccess.repository

import com.ceiba.dataaccess.anticorruption.ParkingTranslator
import com.ceiba.dataaccess.dto.ParkingEntity
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.repository.ParkingEntranceExitRepository
import javax.inject.Inject

class ParkingRepositoryRoom @Inject constructor(): ParkingEntranceExitRepository {
    
    @Inject
    lateinit var parkingDbRoomImpl: ParkingServiceRoom


    /*override suspend fun enterVehicle(parking: ParkingEntranceExit): Long? {
        var id: Long? = null
        val vehicleExist = parkingDbRoomImpl.validateVehicleExist(parking.vehicle.licensePlate)
        if (vehicleExist.isEmpty()) {
            val parkingEntity = ParkingTranslator.fromDomainToEntity(parking)

            enterCylinderCapacity(parking, parkingEntity)
            id = executeInsertVehicle(parkingEntity)
        }

        return id
    }*/

    override suspend fun outVehicle(licensePlate: String): Int? {
        parkingDbRoomImpl.outVehicle(OUT_STATE, licensePlate)
        return parkingDbRoomImpl
            .validateVehicleExist(licensePlate)[0].stateVehicle

    }

    override suspend fun calculateAmountParking(licensePlate: String, endTime: String): ParkingEntranceExit =
        updateEndTimeVehicleParkingDb(licensePlate, endTime)



    override suspend fun getCountVehicleParking(vehicleType: String): Int = getCountVehicle(vehicleType)

    override suspend fun getVehicleExistState(licensePlate: String): Int? {
        if (parkingDbRoomImpl.validateVehicleExist(licensePlate).isNotEmpty()) {
            return parkingDbRoomImpl.validateVehicleExist(licensePlate)[0].stateVehicle
        }

        return null
    }

    private suspend fun updateEndTimeVehicleParkingDb(licensePlate: String, endTime: String): ParkingEntranceExit {
        var parking: ParkingEntranceExit? = null
        val vehicleExist = parkingDbRoomImpl.validateVehicleExist(licensePlate)
        if (vehicleExist.isNotEmpty()) {
            updateWithDrawVehicle(licensePlate, endTime)
            val parkingDB = parkingDbRoomImpl.validateVehicleExist(licensePlate)[0]

            parking = ParkingTranslator.fromEntityToDomain(parkingDB)
        }

        return parking?.let {return it}
            ?: kotlin.run { throw ParkingException(MESSAGE_GET_VEHICLE) }

    }

    private suspend fun updateWithDrawVehicle(licensePlate: String, endTime: String) {
        parkingDbRoomImpl.update(licensePlate, endTime)
    }

    private suspend fun getCountVehicle(vehicleType: String): Int = parkingDbRoomImpl.getCountVehicle(vehicleType)

    suspend fun executeInsertVehicle(parkingEntity: ParkingEntity): Long = parkingDbRoomImpl.insertVehicle(parkingEntity)



    companion object {
        const val DB_NAME = "PARKING"
        const val MESSAGE_GET_VEHICLE = "Error encontrando el vehículo solictado"
        const val OUT_STATE = 1
    }

}