package com.ceiba.adnceiba.entervehicle.view

import android.widget.DatePicker
import android.widget.TimePicker
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.PickerActions.setDate
import androidx.test.espresso.contrib.PickerActions.setTime
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.ceiba.adnceiba.R
import org.junit.Rule
import org.junit.Test

class EnterVehicleActivityTest {
    @Rule @JvmField
    public val activityRule = ActivityScenarioRule(EnterVehicleActivity::class.java)

    private val licensePlate = "ABC000"
    private val cylinderCapacity = "650"

    @Test
    fun clickEnterVehicleButton_isCorrect() {
        onView(withId(R.id.input_license_plate_enter_vehicle)).perform(typeText(licensePlate))
        onView(withId(R.id.input_date_enter_vehicle)).perform(click())

        onView(isAssignableFrom(DatePicker::class.java)).perform(setDate(2022,
        3, 14))
        onView(withId(android.R.id.button1)).perform(click())

        onView(isAssignableFrom(TimePicker::class.java)).perform(
            setTime(7, 0))
        onView(withId(android.R.id.button1)).perform(click())

        onView(withId(R.id.input_cylinder_capacity_enter_vehicle)).perform(clearText())

        onView(withId(R.id.input_cylinder_capacity_enter_vehicle)).perform(typeText(cylinderCapacity))

        onView(withId(R.id.input_cylinder_capacity_enter_vehicle)).perform(closeSoftKeyboard());

        onView(withId(R.id.button_save_vehicle)).perform(click())

    }
}