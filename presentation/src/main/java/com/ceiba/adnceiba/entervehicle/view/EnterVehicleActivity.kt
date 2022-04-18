package com.ceiba.adnceiba.entervehicle.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ceiba.adnceiba.R
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class EnterVehicleActivity : AppCompatActivity() {
    private val mCalendar = Calendar.getInstance()

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
        setContentView(R.layout.activity_enter_vehicle)

        findViewById<TextInputEditText>(R.id.input_date_enter_vehicle).setOnClickListener {
            DatePickerDialog(this, mPickerDate, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)).show()
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
}