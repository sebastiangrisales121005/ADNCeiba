package com.ceiba.adnceiba.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ceiba.adnceiba.MainActivity
import com.ceiba.adnceiba.R
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
        onView(withId(R.id.spinner_vehicle_type_enter)).check(matches(isDisplayed()))
    }

    @Test
    fun clickWithDrawVehicleButton_openWithDrawUI() {
        MainPageObject.goToWithDrawVehicle()
        onView(withId(R.id.button_delete_vehicle)).check(matches(isDisplayed()))

    }
}