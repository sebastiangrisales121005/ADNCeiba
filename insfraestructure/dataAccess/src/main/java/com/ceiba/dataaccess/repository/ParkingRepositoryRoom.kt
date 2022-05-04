package com.ceiba.dataaccess.repository

import com.ceiba.dataaccess.anticorruption.ParkingTranslator
import com.ceiba.dataaccess.dto.ParkingEntity
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.repository.ParkingEntranceExitRepository
import com.ceiba.domain.repository.VehicleEnterRepository
import javax.inject.Inject

open class ParkingRepositoryRoom @Inject constructor(): ParkingEntranceExitRepository {
    
    @Inject
    lateinit var parkingDbRoomImpl: ParkingServiceRoom

    override suspend fun enterVehicle(parkingEntranceExit: ParkingEntranceExit, vehicleEnterRepository: VehicleEnterRepository): Long? {
        val parkingEntity = ParkingTranslator.fromDomainToEntity(parkingEntranceExit)
        parkingEntity.cylinderCapacity = vehicleEnterRepository.enterVehicle(parkingEntranceExit)

        return executeInsertVehicle(parkingEntity)

    }

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

        throw ParkingException(MESSAGE_GET_VEHICLE)
    }

    suspend fun updateEndTimeVehicleParkingDb(licensePlate: String, endTime: String): ParkingEntranceExit {
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