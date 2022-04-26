package com.ceiba.adnceiba.entervehicle.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ceiba.adnceiba.R
import com.ceiba.adnceiba.databinding.ActivityEnterVehicleBinding
import com.ceiba.adnceiba.entervehicle.viewmodel.EnterVehicleViewModel
import com.ceiba.application.service.ParkingEntranceExitServiceApplication
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class EnterVehicleActivity : AppCompatActivity() {

    @Inject
    lateinit var parkingEntranceExitServiceApplication: ParkingEntranceExitServiceApplication

    private val mCalendar = Calendar.getInstance()

    private val vehicles = arrayOf(CAR, MOTORCYCLE)

    private lateinit var selectedVehicle: String

    private var viewModel: EnterVehicleViewModel? = null

    private var mActivityEnterVehicleBinding: ActivityEnterVehicleBinding? = null

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

        mActivityEnterVehicleBinding?.inputDateEnterVehicle?.setText(getDateTimeText())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityEnterVehicleBinding = ActivityEnterVehicleBinding.inflate(layoutInflater)
        setContentView(mActivityEnterVehicleBinding?.root)

        initializeWidgets()
        observables()
    }

    private fun initializeWidgets() {
        mActivityEnterVehicleBinding?.inputDateEnterVehicle?.setOnClickListener {
            DatePickerDialog(this, mPickerDate, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        val adapterSpinnerVehicle = ArrayAdapter(
            this, android.R.layout.simple_spinner_item,
            vehicles
        )

        mActivityEnterVehicleBinding?.spinnerVehicleTypeEnter?.adapter = adapterSpinnerVehicle


        viewModel = ViewModelProvider(this)[EnterVehicleViewModel::class.java]
        viewModel?.parkingEntranceExitServiceApplication = parkingEntranceExitServiceApplication

        viewModel?.disableEmoji()

        getVehicleSelected(mActivityEnterVehicleBinding?.spinnerVehicleTypeEnter)
        mActivityEnterVehicleBinding?.buttonSaveVehicle?.setOnClickListener {
            val valueLicensePlate = mActivityEnterVehicleBinding?.inputLicensePlateEnterVehicle?.text.toString()
            val valueCylinderCapacity = mActivityEnterVehicleBinding?.inputCylinderCapacityEnterVehicle?.text.toString()

            viewModel?.insertVehicle(
                valueLicensePlate, selectedVehicle, valueCylinderCapacity.toInt(),
                getDateTimeText(), getDayOfWeek()
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

        viewModel?.validateEnterEmojiLiveData?.observe(this) {
            mActivityEnterVehicleBinding?.inputLicensePlateEnterVehicle?.filters = arrayOf(it)
            mActivityEnterVehicleBinding?.inputCylinderCapacityEnterVehicle?.filters = arrayOf(it)
        }
    }

    private fun displayTimeDialog() {
        val mTimePicker = TimePickerDialog(this, mPickerTime, mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE),
            false)
        mTimePicker.show()
    }

    private fun getDateTimeText(): String {
        val myFormat = "yyyy-MM-dd HH:mm:ss"
        val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)

        return sdf.format(mCalendar.time.time)
    }

    @SuppressLint("SimpleDateFormat")
    fun getDayOfWeek(): String {
        val simpleDateFormat = SimpleDateFormat("EEEE")
        val date = Date(mCalendar.time.time)

        return simpleDateFormat.format(date)

    }

    private fun getVehicleSelected(spinner: Spinner?) {
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedVehicle = vehicles[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val CAR = "Carro"
        const val MOTORCYCLE = "Moto"
    }
}