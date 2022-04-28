package com.ceiba.domain.core

import com.ceiba.domain.valueobject.Time

class TimeBuilder {
    var startDateTime: String? = null
    var endDateTime: String? = null
    var day: String? = null

    init {
        startDateTime = "2022-04-14 08:00:00"
        endDateTime = "2022-04-16 17:00:00"
        day = "martes"
    }

    fun withStartDateTime(startDateTime: String): TimeBuilder {
        this.startDateTime = startDateTime
        return this
    }

    fun withEndDateTime(endDateTime: String): TimeBuilder {
        this.endDateTime = endDateTime
        return this
    }

    fun withDay(day: String): TimeBuilder {
        this.day = day
        return this
    }

    fun build(): Time {
        return Time(startDateTime, endDateTime, day)
    }

    companion object {
        fun aTime(): TimeBuilder {
            return TimeBuilder()
        }
    }
}