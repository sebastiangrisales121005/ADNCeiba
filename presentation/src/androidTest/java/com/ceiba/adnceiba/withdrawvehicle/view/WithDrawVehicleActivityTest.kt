package com.ceiba.adnceiba.withdrawvehicle.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
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
        
    }
}