package com.ceiba.adnceiba

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ceiba.adnceiba.databinding.ActivityMainBinding
import com.ceiba.adnceiba.entervehicle.view.EnterVehicleActivity
import com.ceiba.adnceiba.withdrawvehicle.view.WithDrawVehicleActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var mActivityMainBinding: ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mActivityMainBinding?.root)

        mActivityMainBinding?.buttonEnterVehicle?.setOnClickListener {
            startActivity(Intent(this, EnterVehicleActivity::class.java))
        }

        mActivityMainBinding?.buttonWithdrawVehicle?.setOnClickListener {
            startActivity(Intent(this, WithDrawVehicleActivity::class.java))
        }
    }
}