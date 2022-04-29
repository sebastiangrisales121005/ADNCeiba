package com.ceiba.adnceiba.main.pageobject

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.ceiba.adnceiba.R

class MainPageObject {
    companion object {
        fun goToEnterVehicle() {
            Espresso.onView(ViewMatchers.withId(R.id.button_enter_vehicle))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

            Espresso.onView(ViewMatchers.withId(R.id.button_enter_vehicle))
                .perform(ViewActions.click())

            Espresso.onView(ViewMatchers.withId(R.id.button_save_vehicle))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            Espresso.onView(ViewMatchers.withId(R.id.input_license_plate_enter_vehicle))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            Espresso.onView(ViewMatchers.withId(R.id.input_date_enter_vehicle))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            Espresso.onView(ViewMatchers.withId(R.id.input_cylinder_capacity_enter_vehicle))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        fun goToWithDrawVehicle() {
            Espresso.onView(ViewMatchers.withId(R.id.button_withdraw_vehicle))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

            Espresso.onView(ViewMatchers.withId(R.id.button_withdraw_vehicle))
                .perform(ViewActions.click())

            Espresso.onView(ViewMatchers.withId(R.id.input_date_withdraw_vehicle))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }
}