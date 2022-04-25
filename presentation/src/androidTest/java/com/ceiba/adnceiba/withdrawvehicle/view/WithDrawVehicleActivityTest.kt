package com.ceiba.adnceiba.withdrawvehicle.view

import android.widget.DatePicker
import android.widget.TimePicker
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions.setDate
import androidx.test.espresso.contrib.PickerActions.setTime
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.ceiba.adnceiba.R
import org.junit.Rule
import org.junit.Test

class WithDrawVehicleActivityTest {
    @Rule @JvmField
    public val activityRule = ActivityScenarioRule(WithDrawVehicleActivity::class.java)

    private val licensePlate = "ABC000"

    @Test
    fun clickWithDrawVehicle_isCorrect() {
        onView(withId(R.id.input_license_plate_withdraw_vehicle))
            .perform(typeText(licensePlate))

        onView(withId(R.id.input_date_withdraw_vehicle))
            .perform(click())

        onView(isAssignableFrom(DatePicker::class.java)).perform(
            setDate(2022, 3, 14))
        onView(withId(android.R.id.button1)).perform(click())

        onView(isAssignableFrom(TimePicker::class.java)).perform(
            setTime(7, 0))
        onView(withId(android.R.id.button1)).perform(click())

        onView(withId(R.id.button_delete_vehicle)).perform(click())

        onView(withId(R.id.container_payment_vehicle)).check(matches(isDisplayed()))
    }
}