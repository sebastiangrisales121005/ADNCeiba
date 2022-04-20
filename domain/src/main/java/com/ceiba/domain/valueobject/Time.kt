package com.ceiba.domain.valueobject

import java.text.SimpleDateFormat

class Time(startDateTime: String, endDateTime: String?, day: String) {
    var startDateTime: String? = null
    var endDateTime: String? = null
    var day: String? = null
    var numberDays: Int? = 0
    var numberHours: Int? = null

    init {
        this.startDateTime = startDateTime
        this.endDateTime = endDateTime
        this.day = day
    }

    fun calculateDaysFromHours() {
        if (this.numberHours!! in 9..23) {
            this.numberDays = this.numberDays!! + 1
            this.numberHours = 0
        }
    }

    fun calculateTimeParking() {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val startDate = simpleDateFormat.parse(startDateTime!!)
        val endDate = simpleDateFormat.parse(endDateTime!!)

        val timeLong = endDate!!.time - startDate!!.time

        this.numberDays = (timeLong/(24 * 60 * 60 * 1000)).toInt()
        this.numberHours = ((timeLong/(60 * 60 * 1000) - this.numberDays!! * 24)).toInt()
        calculateDaysFromHours()
    }

}