package com.ceiba.adnceiba.entervehicle.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.ceiba.adnceiba.R
import com.ceiba.adnceiba.entervehicle.view.pageobject.EnterVehiclePageObject
import org.junit.Rule
import org.junit.Test

class EnterVehicleActivityTest {
    @Rule @JvmField
    public val activityRule = ActivityScenarioRule(EnterVehicleActivity::class.java)

    private val licensePlate = "ABC000"
    private val cylinderCapacity = "650"

    @Test
    fun clickEnterVehicleButton_isCorrect() {
        EnterVehiclePageObject.sendFormEnterVehicleToParking(licensePlate, cylinderCapacity)
        onView(ViewMatchers.withId(R.id.button_save_vehicle))
            .perform(ViewActions.click())
    }
}