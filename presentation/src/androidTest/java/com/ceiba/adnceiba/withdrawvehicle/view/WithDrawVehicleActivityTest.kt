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
import com.ceiba.adnceiba.withdrawvehicle.view.pageobject.WithDrawVehiclePageObject
import org.junit.Rule
import org.junit.Test

class WithDrawVehicleActivityTest {
    @Rule @JvmField
    public val activityRule = ActivityScenarioRule(WithDrawVehicleActivity::class.java)

    private val licensePlate = "ABC000"

    @Test
    fun clickWithDrawVehicle_isCorrect() {
        WithDrawVehiclePageObject.sendFormOutVehicle(licensePlate)

        onView(withId(R.id.container_payment_vehicle)).check(matches(isDisplayed()))
    }
}