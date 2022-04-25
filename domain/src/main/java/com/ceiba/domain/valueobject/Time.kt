package com.ceiba.domain.valueobject

import java.text.SimpleDateFormat
import java.util.*

class Time(val startDateTime: String?, val endDateTime: String?, val day: String?) {
    var numberDays: Int = 0
    var numberHours: Int? = null


    fun calculateDaysFromHours() {
        if (this.numberHours in MIN_RANGE_DAY..MAX_RANGE_DAY) {
            this.numberDays = this.numberDays.plus(1)
            this.numberHours = 0
        }
    }

    fun calculateTimeParking() {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        val startDate: Date? = startDateTime?.let { simpleDateFormat.parse(it) }
        val endDate: Date? = endDateTime?.let { simpleDateFormat.parse(it)}

        val timeLong = (endDate?.time ?: 0) - (startDate?.time ?: 0)
        val numberDaysOperation = timeLong/(HOURS_FOR_DAY * MINUTES_FOR_HOUR * SECONDS_FOR_MINUTE * 1000)
        val numberHoursOperation = timeLong/(MINUTES_FOR_HOUR * SECONDS_FOR_MINUTE * MILLISECONDS) - numberDaysOperation * HOURS_FOR_DAY

        this.numberDays = numberDaysOperation.toInt()
        this.numberHours = numberHoursOperation.toInt()
        calculateDaysFromHours()
    }


    companion object {
        const val HOURS_FOR_DAY = 24
        const val MINUTES_FOR_HOUR = 60
        const val SECONDS_FOR_MINUTE = 60
        const val MILLISECONDS = 1000
        const val MIN_RANGE_DAY = 9
        const val MAX_RANGE_DAY = 23
    }

}