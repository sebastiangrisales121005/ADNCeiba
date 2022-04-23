package com.ceiba.domain.valueobject

import java.text.SimpleDateFormat

class Time(val startDateTime: String?, val endDateTime: String?, val day: String?) {
    var numberDays: Int? = 0
    var numberHours: Int? = null

    fun calculateDaysFromHours() {
        if (this.numberHours!! in 9..23) {
            this.numberDays = this.numberDays!! + 1
            this.numberHours = 0
        }
    }

    fun calculateTimeParking() {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val startDate = simpleDateFormat.parse(startDateTime)
        val endDate = simpleDateFormat.parse(endDateTime)

        val timeLong = endDate!!.time - startDate!!.time

        this.numberDays = (timeLong/(HOURS_FOR_DAY * MINUTES_FOR_HOUR * SECONDS_FOR_MINUTE * 1000)).toInt()
        this.numberHours = ((timeLong/(MINUTES_FOR_HOUR * SECONDS_FOR_MINUTE * MILLISECONDS) - this.numberDays!! * HOURS_FOR_DAY)).toInt()
        calculateDaysFromHours()
    }

    companion object {
        const val HOURS_FOR_DAY = 24
        const val MINUTES_FOR_HOUR = 60
        const val SECONDS_FOR_MINUTE = 60
        const val MILLISECONDS = 1000
    }

}