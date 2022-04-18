package com.ceiba.adnceiba

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ceiba.adnceiba.entervehicle.view.EnterVehicleActivity
import com.ceiba.application.service.ParkingServiceApplication
import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.valueobject.Payment
import com.ceiba.domain.valueobject.Time
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var parkingServiceApplication: ParkingServiceApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val vehicle = Vehicle("ABC000", "CARRO", 0)
        val time = Time("2022-04-14", "2022-04-15", "Martes")
        val parking = Parking(vehicle, time, Payment())
        //parkingServiceApplication.enterVehicle(parking)

        findViewById<Button>(R.id.button_enter_vehicle).setOnClickListener {
            startActivity(Intent(this, EnterVehicleActivity::class.java))
        }
    }
}