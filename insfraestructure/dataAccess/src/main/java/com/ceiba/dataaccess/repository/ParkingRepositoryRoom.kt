package com.ceiba.dataaccess.repository

import android.content.Context
import androidx.room.Room
import com.ceiba.dataaccess.anticorruption.ParkingTranslator
import com.ceiba.dataaccess.dto.ParkingDto
import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.repository.ParkingRepository
import com.ceiba.domain.valueobject.Time
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ParkingRepositoryRoom @Inject constructor(@ApplicationContext context: Context): ParkingRepository {
    private val context: Context = context

    private val parkingDbRoomImpl: ParkingDbRoomImpl = Room.databaseBuilder(this.context, ParkingDbRoomImpl::class.java, DB_NAME).build()

    override suspend fun enterVehicle(parking: Parking): Long? {
        val parkingDto = ParkingTranslator.fromDomainToDto(parking)
        var id: Long? = null

        //parking.vehicle?.validateAmountVehicle(getCountCar(), getCountMotorCycle())
        //val car: Vehicle = Car(parking.vehicle.licensePlate, parking.vehicle.vehicleType)
        parking.vehicle.validate(getCountMotorCycle())
        if (parkingDbRoomImpl.parkingDao().validateVehicleExist(parking.vehicle?.licensePlate!!).isEmpty()) {
            id = if (parking.validateEnterLicensePlate()) {
                executeInsertVehicle(parkingDto)
            } else {
                executeInsertVehicle(parkingDto)
            }
        }

        return id
    }

    override suspend fun deleteVehicle(parking: Parking): Int? {
        val parkingDto = ParkingTranslator.fromDomainToDto(parking)
        return withContext(Dispatchers.IO) {
            parkingDbRoomImpl.parkingDao().deleteVehicle(parkingDto)
        }
    }

    override suspend fun calculateAmountParking(parking: Parking): Parking {
        val parkingUpdate = Parking(Vehicle(getVehiclesParkingDb(parking).licensePlate, getVehiclesParkingDb(parking).vehicleType,
            getVehiclesParkingDb(parking).cylinderCapacity!!),
            Time(getVehiclesParkingDb(parking).startDateTime, getVehiclesParkingDb(parking).endDateTime, getVehiclesParkingDb(parking).day))

        parkingUpdate.calculateTotalValueParking()

        return parkingUpdate

    }

    private suspend fun getVehiclesParkingDb(parking: Parking): ParkingDto {
        updateWithDrawVehicle(parking)
        return withContext(Dispatchers.IO) {
            parkingDbRoomImpl.parkingDao().validateVehicleExist(parking.vehicle?.licensePlate!!)[0]
        }
    }

    private suspend fun updateWithDrawVehicle(parking: Parking) {
        CoroutineScope(Dispatchers.IO).launch {
            val parkingDto = ParkingTranslator.fromDomainToDto(parking)
            parkingDbRoomImpl.parkingDao().update(parkingDto.licensePlate, parkingDto.endDateTime)
        }
    }


    private suspend fun getCountCar(): Int {
        return withContext(Dispatchers.IO) {
            parkingDbRoomImpl.parkingDao().getCountCar()
        }
    }

    private suspend fun getCountMotorCycle(): Int {
        return withContext(Dispatchers.IO) {
            parkingDbRoomImpl.parkingDao().getCountMotorCycle()
        }
    }

    private suspend fun executeInsertVehicle(parkingDto: ParkingDto): Long {
        return withContext(Dispatchers.IO) {
            parkingDbRoomImpl.parkingDao().insertVehicle(parkingDto)
        }
    }

    companion object {
        const val DB_NAME = "PARKING"
    }
}