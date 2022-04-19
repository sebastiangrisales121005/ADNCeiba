package com.ceiba.adnceiba.entervehicle.viewmodel


//import androidx.hilt.Assisted
//import androidx.hilt.lifecycle.ViewModelInject
//import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ceiba.application.service.ParkingServiceApplication
import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.valueobject.Time

class EnterVehicleViewModel /*@ViewModelInject constructor(parkingServiceApplication: ParkingServiceApplication,
                                                         @Assisted private val savedStateHandle: SavedStateHandle)*/ : ViewModel() {
    lateinit var parkingServiceApplication: ParkingServiceApplication

    fun insertVehicle() {
        val vehicle = Vehicle("ABC000", "CARRO", 0)
        val time = Time("2022-04-14", "2022-04-15", "Martes")
        val parking = Parking(vehicle, time)
        parkingServiceApplication.enterVehicle(parking)
    }



}