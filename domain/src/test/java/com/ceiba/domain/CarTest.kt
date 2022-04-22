package com.ceiba.domain

import com.ceiba.domain.core.CarBuilder
import com.ceiba.domain.core.TimeBuilder
import com.ceiba.domain.entity.Car
import org.junit.Assert
import org.junit.Test

class CarTest {

    @Test
    fun parking_calculateTotalValueParkingCar_isCorrect() {
        //Arrange
        val vehicleBuilder = CarBuilder.aCar()
            .build()

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-16 17:00:00"
        val day = "martes"
        val timeBuilder = TimeBuilder.aTime()
            .withStartDateTime(startDateTime)
            .withEndDateTime(endDateTime)
            .withDay(day)
            .build()

        //Act
        vehicleBuilder.calculateTotalForVehicle(timeBuilder)

        //Assert
        Assert.assertEquals(24000, vehicleBuilder.totalValueParking)
    }

    @Test
    fun parking_calculateTotalValueParkingCar_isCorrectHours() {
        //Arrange
        val vehicleBuilder = CarBuilder.aCar()
            .build()

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-16 15:00:00"
        val day = "martes"
        val timeBuilder = TimeBuilder.aTime()
            .withStartDateTime(startDateTime)
            .withEndDateTime(endDateTime)
            .withDay(day)
            .build()

        //Act
        vehicleBuilder.calculateTotalForVehicle(timeBuilder)

        //Assert
        Assert.assertEquals(23000, vehicleBuilder.totalValueParking)
    }

    @Test
    fun parking_calculateTotalValueParkingCar_isFailure() {
        //Arrange
        val vehicleBuilder = CarBuilder.aCar()
            .build()

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-16 15:00:00"
        val day = "martes"
        val timeBuilder = TimeBuilder.aTime()
            .withStartDateTime(startDateTime)
            .withEndDateTime(endDateTime)
            .withDay(day)
            .build()

        //Act
        vehicleBuilder.calculateTotalForVehicle(timeBuilder)

        //Assert
        Assert.assertNotEquals(24000, vehicleBuilder.totalValueParking)
    }
}