package com.ceiba.adnceiba

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ceiba.adnceiba.entervehicle.view.EnterVehicleActivity
import com.ceiba.adnceiba.withdrawvehicle.view.WithDrawVehicleActivity
import com.ceiba.domain.aggregate.ParkingValidateEnter
import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.valueobject.Time
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val vehicle = Vehicle("ABC000", "CARRO", 0)
        val time = Time("2022-04-14", "2022-04-15", "Martes")
        val parking = ParkingValidateEnter(vehicle, time)
        //parkingServiceApplication.enterVehicle(parking)

        findViewById<Button>(R.id.button_enter_vehicle).setOnClickListener {
            startActivity(Intent(this, EnterVehicleActivity::class.java))
        }

        findViewById<Button>(R.id.button_withdraw_vehicle).setOnClickListener {
            startActivity(Intent(this, WithDrawVehicleActivity::class.java))
        }
    }
}