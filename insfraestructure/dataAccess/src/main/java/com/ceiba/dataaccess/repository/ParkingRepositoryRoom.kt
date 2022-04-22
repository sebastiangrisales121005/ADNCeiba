package com.ceiba.dataaccess.repository

import android.content.Context
import androidx.room.Room
import com.ceiba.dataaccess.anticorruption.ParkingTranslator
import com.ceiba.dataaccess.dto.ParkingDto
import com.ceiba.domain.aggregate.ParkingValidateEnter
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

    override suspend fun enterVehicle(parking: ParkingValidateEnter): Long? {
        val parkingDto = ParkingTranslator.fromDomainToDto(parking)
        var id: Long? = null

        //parking.vehicle.validate(getCountMotorCycle())

        if (parkingDbRoomImpl.parkingDao().validateVehicleExist(parking.vehicle.licensePlate).isEmpty()) {
            executeInsertVehicle(parkingDto)
        }

        return id
    }

    override suspend fun deleteVehicle(parking: ParkingValidateEnter): Int? {
        val parkingDto = ParkingTranslator.fromDomainToDto(parking)
        return withContext(Dispatchers.IO) {
            parkingDbRoomImpl.parkingDao().deleteVehicle(parkingDto)
        }
    }

    override suspend fun calculateAmountParking(parking: ParkingValidateEnter): ParkingValidateEnter {
        parking.vehicle.calculateTotalForVehicle(parking.time)

        return parking

    }

    private suspend fun getVehiclesParkingDb(parking: ParkingValidateEnter): ParkingDto {
        updateWithDrawVehicle(parking)
        return withContext(Dispatchers.IO) {
            parkingDbRoomImpl.parkingDao().validateVehicleExist(parking.vehicle?.licensePlate!!)[0]
        }
    }

    private suspend fun updateWithDrawVehicle(parking: ParkingValidateEnter) {
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