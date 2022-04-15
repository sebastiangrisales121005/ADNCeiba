package com.ceiba.domain.valueobject

class Time(startDateTime: String, endDateTime: String, day: String) {
    var startDateTime: String? = null
    var endDateTime: String? = null
    var day: String? = null
    var numberDays: Int? = null
    var numberHours: Int? = null

    init {
        this.startDateTime = startDateTime
        this.endDateTime = endDateTime
        this.day = day
    }

}