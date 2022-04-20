package com.ceiba.adnceiba.entervehicle.viewmodel


//import androidx.hilt.Assisted
//import androidx.hilt.lifecycle.ViewModelInject
//import androidx.lifecycle.SavedStateHandle
import android.util.Log
import androidx.lifecycle.ViewModel
import com.ceiba.application.service.ParkingServiceApplication
import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.valueobject.Time

class EnterVehicleViewModel /*@ViewModelInject constructor(parkingServiceApplication: ParkingServiceApplication,
                                                         @Assisted private val savedStateHandle: SavedStateHandle)*/ : ViewModel() {
    lateinit var parkingServiceApplication: ParkingServiceApplication

    fun insertVehicle(vehicle: Vehicle, time: Time) {
        val parking = Parking(vehicle, time)
        try {
            parkingServiceApplication.enterVehicle(parking)
        } catch (e: ParkingException) {
            Log.e("MENSAJE_ERROR", e.message!!)
        }

    }



}