package com.ceiba.adnceiba.withdrawvehicle.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ceiba.adnceiba.R
import com.ceiba.adnceiba.databinding.ActivityWithDrawVehicleBinding
import com.ceiba.adnceiba.withdrawvehicle.viewmodel.WithDrawViewModel
import com.ceiba.application.service.ParkingEntranceExitServiceApplication
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class WithDrawVehicleActivity : AppCompatActivity() {

    @Inject
    lateinit var parkingEntranceExitServiceApplication: ParkingEntranceExitServiceApplication

    private val mCalendar = Calendar.getInstance()

    private var viewModel: WithDrawViewModel? = null

    private var mActivityWithDrawVehicleBinding: ActivityWithDrawVehicleBinding? = null

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

        mActivityWithDrawVehicleBinding?.inputDateWithdrawVehicle?.setText(getDateTimeText())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityWithDrawVehicleBinding = ActivityWithDrawVehicleBinding.inflate(layoutInflater)
        setContentView(mActivityWithDrawVehicleBinding?.root)

        initializeWidgets()
        observables()
    }

    private fun initializeWidgets() {
        mActivityWithDrawVehicleBinding?.inputDateWithdrawVehicle?.setOnClickListener {
            DatePickerDialog(this, mPickerDate, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        viewModel = ViewModelProvider(this)[WithDrawViewModel::class.java]
        viewModel?.parkingEntranceExitServiceApplication = parkingEntranceExitServiceApplication

        mActivityWithDrawVehicleBinding?.buttonDeleteVehicle?.setOnClickListener {
            val valueLicensePlate = mActivityWithDrawVehicleBinding?.inputLicensePlateWithdrawVehicle?.text.toString()

            viewModel?.calculateAmount(valueLicensePlate,
                getDateTimeText())
        }
    }

    private fun observables() {
        viewModel?.showCalculateParkingLiveData?.observe(this) {
            mActivityWithDrawVehicleBinding?.containerPaymentVehicle?.visibility = View.VISIBLE

            mActivityWithDrawVehicleBinding?.countDayVehicle?.text = it.numberDays
            mActivityWithDrawVehicleBinding?.countHourVehicle?.text = it.numberHours
            mActivityWithDrawVehicleBinding?.paymentVehicle?.text = it.totalValueParking
        }

        viewModel?.outVehicleLiveData?.observe(this) {
            it?.let {
                showToast(getString(R.string.retiro_correcto))
            } ?: kotlin.run {
                showToast(getString(R.string.error_retiro_vehiculo))
            }
        }

        viewModel?.validateEnterEmojiLiveData?.observe(this) {
            mActivityWithDrawVehicleBinding?.inputLicensePlateWithdrawVehicle?.filters = arrayOf(it)
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

    private fun getDateTimeText(): String {
        val myFormat = "yyyy-MM-dd HH:mm:ss"
        val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)

        return sdf.format(mCalendar.time.time)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}