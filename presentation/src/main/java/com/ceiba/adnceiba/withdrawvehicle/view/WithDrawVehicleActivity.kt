package com.ceiba.adnceiba.withdrawvehicle.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import com.ceiba.adnceiba.R
import com.ceiba.adnceiba.entervehicle.view.EnterVehicleActivity
import com.ceiba.adnceiba.withdrawvehicle.viewmodel.WithDrawViewModel
import com.ceiba.application.service.ParkingServiceApplication
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class WithDrawVehicleActivity : AppCompatActivity() {

    @Inject
    lateinit var parkingServiceApplication: ParkingServiceApplication

    private val mCalendar = Calendar.getInstance()

    private val vehicles = arrayOf(EnterVehicleActivity.CAR, EnterVehicleActivity.MOTORCYCLE)

    private var viewModel: WithDrawViewModel? = null

    private val mPickerDate = DatePickerDialog.OnDateSetListener { _, year, monthYear, dayMonth ->
        mCalendar.set(Calendar.YEAR, year)
        mCalendar.set(Calendar.MONTH, monthYear)
        mCalendar.set(Calendar.DAY_OF_MONTH, dayMonth)

        displayTimeDialog()
    }

    private val mPickerTime = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
        mCalendar.set(Calendar.HOUR_OF_DAY, hour)
        mCalendar.set(Calendar.MINUTE, minute)
        mCalendar.set(Calendar.SECOND, 0)
        mCalendar.set(Calendar.MILLISECOND, 0)

        Log.e("DATETIME", getDateTimeText())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_draw_vehicle)

        initializeWidgets()
    }

    private fun initializeWidgets() {
        findViewById<TextInputEditText>(R.id.input_date_withdraw_vehicle).setOnClickListener {
            DatePickerDialog(this, mPickerDate, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val adapterSpinnerVehicle = ArrayAdapter(
            this, android.R.layout.simple_spinner_item,
            vehicles
        )

        val spinner = findViewById<Spinner>(R.id.spinner_vehicle_type_withdraw)
        spinner.adapter = adapterSpinnerVehicle

        viewModel = ViewModelProvider(this)[WithDrawViewModel::class.java]
        viewModel?.parkingServiceApplication = parkingServiceApplication

        getVehicleSelected(spinner)
    }

    private fun displayTimeDialog() {
        val mTimePicker = TimePickerDialog(this, mPickerTime, mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE),
            false)
        mTimePicker.show()
    }

    fun getDateTimeText(): String {
        val myFormat = "yyyy-MM-dd HH:mm:ss"
        val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)

        return sdf.format(mCalendar.time.time)
    }



    fun getVehicleSelected(spinner: Spinner) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.e("VEHICULO", vehicles[position])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
}