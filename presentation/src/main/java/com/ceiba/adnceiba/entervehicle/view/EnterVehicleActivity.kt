package com.ceiba.adnceiba.entervehicle.view

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ceiba.adnceiba.R
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class EnterVehicleActivity : AppCompatActivity() {
    private val mCalendar = Calendar.getInstance()

    private val mPickerDate = DatePickerDialog.OnDateSetListener { _, year, monthYear, dayMonth ->
        mCalendar.set(Calendar.YEAR, year)
        mCalendar.set(Calendar.MONTH, monthYear)
        mCalendar.set(Calendar.DAY_OF_MONTH, dayMonth)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_vehicle)

        findViewById<TextInputEditText>(R.id.input_date_enter_vehicle).setOnClickListener {
            DatePickerDialog(this, mPickerDate, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
}