package com.ceiba.adnceiba.entervehicle.view.pageobject

import android.widget.DatePicker
import android.widget.TimePicker
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers
import com.ceiba.adnceiba.R

class EnterVehiclePageObject {

    companion object {
        fun sendFormEnterVehicleToParking(licensePlate: String, cylinderCapacity: String) {
            Espresso.onView(ViewMatchers.withId(R.id.input_license_plate_enter_vehicle))
                .perform(ViewActions.typeText(licensePlate))
            Espresso.onView(ViewMatchers.withId(R.id.input_date_enter_vehicle))
                .perform(ViewActions.click())

            Espresso.onView(ViewMatchers.isAssignableFrom(DatePicker::class.java)).perform(
                PickerActions.setDate(
                    2022,
                    3, 14
                )
            )
            Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())

            Espresso.onView(ViewMatchers.isAssignableFrom(TimePicker::class.java)).perform(
                PickerActions.setTime(7, 0)
            )
            Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())

            Espresso.onView(ViewMatchers.withId(R.id.input_cylinder_capacity_enter_vehicle))
                .perform(ViewActions.clearText())

            Espresso.onView(ViewMatchers.withId(R.id.input_cylinder_capacity_enter_vehicle))
                .perform(ViewActions.typeText(cylinderCapacity))

            Espresso.onView(ViewMatchers.withId(R.id.input_cylinder_capacity_enter_vehicle))
                .perform(ViewActions.closeSoftKeyboard())

            Espresso.onView(ViewMatchers.withId(R.id.button_save_vehicle))
                .perform(ViewActions.click())
        }

    }
}