package com.ceiba.dataaccess.repository

import android.content.Context
import com.ceiba.dataaccess.anticorruption.ParkingTranslator
import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.repository.ParkingRepository
import com.ceiba.domain.valueobject.Time
import java.text.SimpleDateFormat
import javax.inject.Inject

class ParkingRepositoryRoom @Inject constructor(context: Context): ParkingRepository {

    override fun enterVehicle(parking: Parking) {
        val parkingDto = ParkingTranslator.fromDomainToDto(parking)
    }

    override fun calculateTimeParking(time: Time): Time {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val startDate = simpleDateFormat.parse(time.startDateTime!!)
        val endDate = simpleDateFormat.parse(time.endDateTime!!)

        val timeLong = endDate!!.time - startDate!!.time

        time.numberDays = (timeLong/(24 * 60 * 60 * 1000)).toInt()
        time.numberHours = ((timeLong/(60 * 60 * 1000) - time.numberDays!! * 24)).toInt()

        return time
    }
}