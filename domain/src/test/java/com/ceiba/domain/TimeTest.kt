package com.ceiba.domain

import com.ceiba.domain.valueobject.Time
import org.junit.Assert
import org.junit.Test

class TimeTest {

    @Test
    fun time_calculateDaysFromHours_isCorrect() {
        //Arrange
        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "martes"
        val time = Time(startDateTime, endDateTime, day)
        time.numberHours = 9

        //Act
        time.calculateDaysFromHours()

        //Assert
        Assert.assertEquals(1, time.numberDays)
    }

    @Test
    fun time_calculateTimeParkingDays_isCorrect() {
        //Arrange
        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-15 10:00:00"
        val day = "martes"
        val time = Time(startDateTime, endDateTime, day)

        //Act
        time.calculateTimeParking()

        //Assert
        Assert.assertEquals(1, time.numberDays)
    }

    @Test
    fun time_calculateTimeParkingDays_isFailure() {
        //Arrange
        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-15 10:00:00"
        val day = "martes"
        val time = Time(startDateTime, endDateTime, day)

        //Act
        time.calculateTimeParking()

        //Assert
        Assert.assertNotEquals(2, time.numberDays)
    }
}