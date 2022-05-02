package com.ceiba.adnceiba.withdrawvehicle.view

import androidx.test.ext.junit.rules.ActivityScenarioRule
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