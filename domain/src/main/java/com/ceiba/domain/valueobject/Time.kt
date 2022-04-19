package com.ceiba.domain.valueobject

import java.text.SimpleDateFormat

class Time(startDateTime: String, endDateTime: String, day: String) {
    var startDateTime: String? = null
    var endDateTime: String? = null
    var day: String? = null
    var numberDays: Long? = 0
    var numberHours: Long? = null

    init {
        this.startDateTime = startDateTime
        this.endDateTime = endDateTime
        this.day = day
    }

    fun calculateDaysFromHours() {
        if (this.numberHours!! >= 9) {
            this.numberDays = this.numberDays!! + 1
        }
    }

    fun calculateTimeParking() {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val startDate = simpleDateFormat.parse(startDateTime!!)
        val endDate = simpleDateFormat.parse(endDateTime!!)

        val timeLong = endDate!!.time - startDate!!.time

        this.numberDays = timeLong/(24 * 60 * 60 * 1000)
        this.numberHours = (timeLong/(60 * 60 * 1000) - this.numberDays!! * 24)
    }

}