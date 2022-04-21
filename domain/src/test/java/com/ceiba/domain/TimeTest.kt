package com.ceiba.domain

import com.ceiba.domain.core.TimeBuilder
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

        val timeBuilder = TimeBuilder()
            .withStartDateTime(startDateTime)
            .withEndDateTime(endDateTime)
            .withDay(day)
            .build()

        timeBuilder.numberHours = 9

        //Act
        timeBuilder.calculateDaysFromHours()

        //Assert
        Assert.assertEquals(1, timeBuilder.numberDays)
    }

    @Test
    fun time_calculateTimeParkingDays_isCorrect() {
        //Arrange
        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-15 10:00:00"
        val day = "martes"
        val timeBuilder = TimeBuilder()
            .withStartDateTime(startDateTime)
            .withEndDateTime(endDateTime)
            .withDay(day)
            .build()

        //Act
        timeBuilder.calculateTimeParking()

        //Assert
        Assert.assertEquals(1, timeBuilder.numberDays)
    }

    @Test
    fun time_calculateTimeParkingDays_isFailure() {
        //Arrange
        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-15 10:00:00"
        val day = "martes"
        val timeBuilder = TimeBuilder()
            .withStartDateTime(startDateTime)
            .withEndDateTime(endDateTime)
            .withDay(day)
            .build()

        //Act
        timeBuilder.calculateTimeParking()

        //Assert
        Assert.assertNotEquals(2, timeBuilder.numberDays)
    }

    @Test
    fun time_calculateTimeParkingHours_isCorrect() {
        //Arrange
        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-15 10:00:00"
        val day = "martes"
        val timeBuilder = TimeBuilder()
            .withStartDateTime(startDateTime)
            .withEndDateTime(endDateTime)
            .withDay(day)
            .build()

        //Act
        timeBuilder.calculateTimeParking()

        //Assert
        Assert.assertEquals(2, timeBuilder.numberHours)
    }

    @Test
    fun time_calculateTimeParkingHours_isFailure() {
        //Arrange
        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-15 11:00:00"
        val day = "martes"
        val timeBuilder = TimeBuilder()
            .withStartDateTime(startDateTime)
            .withEndDateTime(endDateTime)
            .withDay(day)
            .build()

        //Act
        timeBuilder.calculateTimeParking()

        //Assert
        Assert.assertNotEquals(2, timeBuilder.numberHours)
    }
}