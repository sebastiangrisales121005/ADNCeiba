package com.ceiba.domain

import com.ceiba.domain.core.CarBuilder
import com.ceiba.domain.core.ParkingBuilder
import com.ceiba.domain.core.TimeBuilder
import org.junit.Assert
import org.junit.Test

class CarTest {

    @Test
    fun parking_calculateTotalValueParkingCar_isCorrect() {
        //Arrange
        val vehicleBuilder = CarBuilder.aCar()
            .build()

        val timeBuilder = TimeBuilder.aTime()
            .build()

        val parkingBuilder = ParkingBuilder.aParking()
            .withTime(timeBuilder)
            .withVehicle(vehicleBuilder)
            .build()

        //Act
        val result = vehicleBuilder.calculateTotalForVehicle(parkingBuilder)

        //Assert
        Assert.assertEquals(24000, result)
    }

    @Test
    fun parking_calculateTotalValueParkingCar_isCorrectHours() {
        //Arrange
        val vehicleBuilder = CarBuilder.aCar()
            .build()

        val endDateTime = "2022-04-16 15:00:00"
        val timeBuilder = TimeBuilder.aTime()
            .withEndDateTime(endDateTime)
            .build()

        val parkingBuilder = ParkingBuilder.aParking()
            .withTime(timeBuilder)
            .withVehicle(vehicleBuilder)
            .build()

        //Act
        val result = vehicleBuilder.calculateTotalForVehicle(parkingBuilder)

        //Assert
        Assert.assertEquals(23000, result)
    }

    @Test
    fun parking_calculateTotalValueParkingCar_isFailure() {
        //Arrange
        val vehicleBuilder = CarBuilder.aCar()
            .build()

        val endDateTime = "2022-04-16 15:00:00"
        val timeBuilder = TimeBuilder.aTime()
            .withEndDateTime(endDateTime)
            .build()

        val parkingBuilder = ParkingBuilder.aParking()
            .withTime(timeBuilder)
            .withVehicle(vehicleBuilder)
            .build()

        //Act
        val result = vehicleBuilder.calculateTotalForVehicle(parkingBuilder)

        //Assert
        Assert.assertNotEquals(24000, result)
    }
}