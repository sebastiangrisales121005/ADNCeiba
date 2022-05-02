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
                .perform(ViewActions.click())
        }

        fun goToWithDrawVehicle() {

            Espresso.onView(ViewMatchers.withId(R.id.button_withdraw_vehicle))
                .perform(ViewActions.click())
        }
    }
}