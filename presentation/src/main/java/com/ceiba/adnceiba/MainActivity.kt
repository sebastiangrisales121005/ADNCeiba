package com.ceiba.adnceiba

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ceiba.adnceiba.entervehicle.view.EnterVehicleActivity
import com.ceiba.adnceiba.withdrawvehicle.view.WithDrawVehicleActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_enter_vehicle).setOnClickListener {
            startActivity(Intent(this, EnterVehicleActivity::class.java))
        }

        findViewById<Button>(R.id.button_withdraw_vehicle).setOnClickListener {
            startActivity(Intent(this, WithDrawVehicleActivity::class.java))
        }
    }
}