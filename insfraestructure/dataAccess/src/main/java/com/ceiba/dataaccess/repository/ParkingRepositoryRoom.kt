package com.ceiba.dataaccess.repository

import android.content.Context
import androidx.room.Room
import com.ceiba.dataaccess.anticorruption.ParkingTranslator
import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.repository.ParkingRepository
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

        if (parking.validateEnterLicensePlate()) {
            id = withContext(Dispatchers.IO) {
                parkingDbRoomImpl.parkingDao().insertVehicle(parkingDto)
            }
        }

        return id
    }

    companion object {
        const val DB_NAME = "PARKING"
    }
}