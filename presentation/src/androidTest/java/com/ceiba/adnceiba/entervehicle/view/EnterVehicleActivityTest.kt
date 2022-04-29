package com.ceiba.adnceiba.entervehicle.view

import androidx.test.ext.junit.rules.ActivityScenarioRule
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
        EnterVehiclePageObject.sendVehicleToParking(licensePlate, cylinderCapacity)
    }
}