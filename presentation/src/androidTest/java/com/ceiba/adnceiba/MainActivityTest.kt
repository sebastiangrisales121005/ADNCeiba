package com.ceiba.adnceiba

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
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
        onView(withId(R.id.button_enter_vehicle)).check(matches(isDisplayed()))

        onView(withId(R.id.button_enter_vehicle)).perform(click())

        onView(withId(R.id.button_save_vehicle)).check(matches(isDisplayed()))
        onView(withId(R.id.input_license_plate_enter_vehicle)).check(matches(isDisplayed()))
        onView(withId(R.id.input_date_enter_vehicle)).check(matches(isDisplayed()))
        onView(withId(R.id.input_cylinder_capacity_enter_vehicle)).check(matches(isDisplayed()))
        onView(withId(R.id.spinner_vehicle_type_enter)).check(matches(isDisplayed()))
    }
}