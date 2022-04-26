package com.ceiba.dataaccess.repository

import android.content.Context
import androidx.room.Room
import com.ceiba.application.service.factory.VehicleFactory
import com.ceiba.dataaccess.anticorruption.ParkingTranslator
import com.ceiba.dataaccess.dto.ParkingEntity
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.repository.ParkingEntranceExitRepository
import com.ceiba.domain.valueobject.Time
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ParkingRepositoryRoom @Inject constructor(@ApplicationContext context: Context): ParkingEntranceExitRepository {

    private val parkingDbRoomImpl: ParkingDbRoomImpl = Room.databaseBuilder(context, ParkingDbRoomImpl::class.java, DB_NAME).build()

    override suspend fun enterVehicle(parking: ParkingEntranceExit): Long? {
        var id: Long? = null
        val vehicleExist = parkingDbRoomImpl.parkingDao().validateVehicleExist(parking.vehicle.licensePlate)
        if (vehicleExist.isEmpty()) {
            val parkingEntity = ParkingTranslator.fromDomainToEntity(parking)
            enterCylinderCapacity(parking, parkingEntity)

            id = executeInsertVehicle(parkingEntity)
        }
        else if (vehicleExist[0].stateVehicle?.equals(1) == true) {
            id = parkingDbRoomImpl.parkingDao().outVehicle(0, parking.vehicle.licensePlate)?.toLong()

        }

        return id
    }

    override suspend fun outVehicle(parking: ParkingEntranceExit): Int? {
        parkingDbRoomImpl.parkingDao().outVehicle(1, parking.vehicle.licensePlate)
        return parkingDbRoomImpl.parkingDao()
            .validateVehicleExist(parking.vehicle.licensePlate)[0].stateVehicle

    }

    override suspend fun calculateAmountParking(licensePlate: String, endTime: String): ParkingEntranceExit? {
        return getVehiclesParkingDb(licensePlate, endTime)

    }

    override suspend fun getCountMotorcycleParking(): Int {
        return getCountMotorCycle()
    }

    override suspend fun getCountCarParking(): Int {
        return getCountCar()
    }

    private suspend fun getVehiclesParkingDb(licensePlate: String, endTime: String): ParkingEntranceExit? {
        updateWithDrawVehicle(licensePlate, endTime)
        val parkingDB = parkingDbRoomImpl.parkingDao().validateVehicleExist(licensePlate)[0]

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
        parkingDbRoomImpl.parkingDao().update(licensePlate, endTime)
    }


    private suspend fun getCountCar(): Int {
        return parkingDbRoomImpl.parkingDao().getCountCar()
    }

    private suspend fun getCountMotorCycle(): Int {
        return parkingDbRoomImpl.parkingDao().getCountMotorCycle()
    }

    private suspend fun executeInsertVehicle(parkingEntity: ParkingEntity): Long {
        return parkingDbRoomImpl.parkingDao().insertVehicle(parkingEntity)

    }

    private fun enterCylinderCapacity(parking: ParkingEntranceExit, parkingEntity: ParkingEntity) {
        if (parking.vehicle.javaClass == Motorcycle::class.java) {
            val motorcycle = parking.vehicle as Motorcycle
            parkingEntity.cylinderCapacity = motorcycle.cylinderCapacity
        } else {
            parkingEntity.cylinderCapacity = 0
        }
    }

    companion object {
        const val DB_NAME = "PARKING"
    }
}