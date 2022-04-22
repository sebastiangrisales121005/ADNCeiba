package com.ceiba.adnceiba.withdrawvehicle.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.ceiba.adnceiba.R
import com.ceiba.adnceiba.withdrawvehicle.viewmodel.WithDrawViewModel
import com.ceiba.application.service.ParkingServiceApplication
import com.ceiba.domain.aggregate.ParkingValidateEnter
import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.valueobject.Time
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

    private var viewModel: WithDrawViewModel? = null

    private var inputLicensePlate: TextInputEditText? = null

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

        findViewById<TextInputEditText>(R.id.input_date_withdraw_vehicle).setText(getDateTimeText())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_draw_vehicle)

        initializeWidgets()
        observables()
    }

    private fun initializeWidgets() {
        findViewById<TextInputEditText>(R.id.input_date_withdraw_vehicle).setOnClickListener {
            DatePickerDialog(this, mPickerDate, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        inputLicensePlate = findViewById(R.id.input_license_plate_withdraw_vehicle)

        viewModel = ViewModelProvider(this)[WithDrawViewModel::class.java]
        viewModel?.parkingServiceApplication = parkingServiceApplication
        viewModel?.disableEmoji()

        findViewById<Button>(R.id.button_delete_vehicle).setOnClickListener {
            val valueLicensePlate = inputLicensePlate?.text.toString()

            viewModel?.calculateAmount(valueLicensePlate,
                Time(null, getDateTimeText(), null))
        }
    }

    private fun observables() {
        viewModel?.showCalculateParkingLiveData?.observe(this) {
            findViewById<ConstraintLayout>(R.id.container_payment_vehicle).visibility = View.VISIBLE

            findViewById<TextView>(R.id.count_day_vehicle).text = it.time.numberDays.toString()
            findViewById<TextView>(R.id.count_hour_vehicle).text = it.time.numberHours.toString()
            findViewById<TextView>(R.id.payment_vehicle).text = it.vehicle.totalValueParking.toString()
        }

        viewModel?.deleteVehicleLiveData?.observe(this) {
            it?.let {
                showToast(getString(R.string.retiro_correcto))
            } ?: kotlin.run {
                showToast(getString(R.string.error_retiro_vehiculo))
            }
        }

        viewModel?.validateEnterEmojiLiveData?.observe(this) {
            inputLicensePlate?.filters = arrayOf(it)
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

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}