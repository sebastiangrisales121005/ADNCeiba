package com.ceiba.domain.valueobject

class Time(startDateTime: String, endDateTime: String, day: String) {
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
        if (this.numberHours!! >= 9) {
            this.numberDays = this.numberDays!! + 1
        }
    }

}