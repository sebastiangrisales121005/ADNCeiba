package com.ceiba.adnceiba.entervehicle.viewmodel


//import androidx.hilt.Assisted
//import androidx.hilt.lifecycle.ViewModelInject
//import androidx.lifecycle.SavedStateHandle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ceiba.application.service.ParkingServiceApplication
import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.valueobject.Time

class EnterVehicleViewModel /*@ViewModelInject constructor(parkingServiceApplication: ParkingServiceApplication,
                                                         @Assisted private val savedStateHandle: SavedStateHandle)*/ : ViewModel() {
    lateinit var parkingServiceApplication: ParkingServiceApplication

    val enterVehicleLiveData = MutableLiveData<Long>()
    val showMessageLiveData = MutableLiveData<String>()

    fun insertVehicle(vehicle: Vehicle, time: Time){
        val parking = Parking(vehicle, time)
        try {
            enterVehicleLiveData.value = parkingServiceApplication.enterVehicle(parking)
        } catch (e: ParkingException) {
            showMessageLiveData.value = e.message
        }

    }



}