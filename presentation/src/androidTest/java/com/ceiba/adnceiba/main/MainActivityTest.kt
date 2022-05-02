package com.ceiba.adnceiba.main

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ceiba.adnceiba.MainActivity
import com.ceiba.adnceiba.main.pageobject.MainPageObject
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule @JvmField
    public val activityRule = ActivityScenarioRule(
        MainActivity::class.java
    )

    @Test
    fun clickEnterVehicleButton_openEnterUI() {
        MainPageObject.goToEnterVehicle()
    }

    @Test
    fun clickWithDrawVehicleButton_openWithDrawUI() {
        MainPageObject.goToWithDrawVehicle()

    }
}