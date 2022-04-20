package com.ceiba.adnceiba.entervehicle.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.ceiba.adnceiba.R
import com.ceiba.adnceiba.entervehicle.viewmodel.EnterVehicleViewModel
import com.ceiba.application.service.ParkingServiceApplication
import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.valueobject.Time
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class EnterVehicleActivity : AppCompatActivity() {

    @Inject
    lateinit var parkingServiceApplication: ParkingServiceApplication

    private val mCalendar = Calendar.getInstance()

    private val vehicles = arrayOf(CAR, MOTORCYCLE)

    private var selectedVehicle: String? = null

    private var viewModel: EnterVehicleViewModel? = null

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

        findViewById<TextInputEditText>(R.id.input_date_enter_vehicle).setText(getDateTimeText())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_vehicle)

        initializeWidgets()
        observables()
    }

    private fun initializeWidgets() {
        findViewById<TextInputEditText>(R.id.input_date_enter_vehicle).setOnClickListener {
            DatePickerDialog(this, mPickerDate, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val adapterSpinnerVehicle = ArrayAdapter(
            this, android.R.layout.simple_spinner_item,
            vehicles
        )

        val spinner = findViewById<Spinner>(R.id.spinner_vehicle_type_enter)
        spinner.adapter = adapterSpinnerVehicle

        viewModel = ViewModelProvider(this)[EnterVehicleViewModel::class.java]
        viewModel?.parkingServiceApplication = parkingServiceApplication

        getVehicleSelected(spinner)
        findViewById<Button>(R.id.button_save_vehicle).setOnClickListener {
            val valueLicensePlate = findViewById<TextInputEditText>(R.id.input_license_plate_enter_vehicle).text.toString()
            val valueCylinderCapacity = findViewById<TextInputEditText>(R.id.input_cylinder_capacity_enter_vehicle).text.toString()
            
            viewModel?.insertVehicle(
                Vehicle(valueLicensePlate, selectedVehicle, valueCylinderCapacity.toInt()),
                Time(getDateTimeText(), null, getDayOfWeek())
            )
        }
    }

    private fun observables() {
        viewModel?.enterVehicleLiveData?.observe(this) {
            it?.let {
                showToast(getString(R.string.vehiculo_ingresado))
                finish()
            } ?: kotlin.run {
                showToast(getString(R.string.error_ingreso_vehiculo))
            }
        }

        viewModel?.showMessageLiveData?.observe(this) {
            showToast(it)
        }
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

    fun getDayOfWeek(): String {
        val simpleDateFormat = SimpleDateFormat("EEEE")
        val date = Date(mCalendar.time.time)

        return simpleDateFormat.format(date)

    }

    fun getVehicleSelected(spinner: Spinner) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedVehicle = vehicles[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val CAR = "Carro"
        const val MOTORCYCLE = "Moto"
    }
}