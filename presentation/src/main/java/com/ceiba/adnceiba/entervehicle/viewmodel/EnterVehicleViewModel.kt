package com.ceiba.adnceiba.entervehicle.viewmodel


import androidx.lifecycle.ViewModel
import com.ceiba.application.service.ParkingServiceApplication
import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.valueobject.Payment
import com.ceiba.domain.valueobject.Time

class EnterVehicleViewModel: ViewModel() {
    lateinit var parkingServiceApplication: ParkingServiceApplication

    fun insertVehicle() {
        val vehicle = Vehicle("ABC000", "CARRO", 0)
        val time = Time("2022-04-14", "2022-04-15", "Martes")
        val parking = Parking(vehicle, time, Payment())
        parkingServiceApplication.enterVehicle(parking)
    }



}