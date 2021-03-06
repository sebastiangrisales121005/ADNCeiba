package com.ceiba.domain

import com.ceiba.domain.core.CarBuilder
import com.ceiba.domain.core.ParkingBuilder
import com.ceiba.domain.core.TimeBuilder
import com.ceiba.domain.entity.Car.Companion.MESSAGE_RESTRICTED
import com.ceiba.domain.entity.Vehicle.Companion.MESSAGE_EMPTY
import com.ceiba.domain.entity.Vehicle.Companion.MESSAGE_LENGTH
import com.ceiba.domain.exception.ParkingException
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

    @Test
    fun parking_validateDataEmpty_isCorrect() {
        //Arrange
        val vehicleBuilder = CarBuilder.aCar()
            .build()

        //Act
        val result = vehicleBuilder.validateDataEmpty()

        //Assert
        Assert.assertNotEquals(ParkingException(MESSAGE_EMPTY), result)
    }

    @Test
    fun parking_validateDataEmpty_isFailure() {
        //Arrange
        val vehicleBuilder = CarBuilder.aCar()
            .withLicensePlate("")

        val expectedMessage = MESSAGE_EMPTY

        try {
            //Act
            vehicleBuilder.build()
        } catch (e: ParkingException) {
            //Assert
            Assert.assertEquals(expectedMessage, e.message)
        }
    }

    @Test
    fun parking_validateDataLength_isCorrect() {
        //Arrange
        val vehicleBuilder = CarBuilder.aCar()
            .build()

        //Act
        val result = vehicleBuilder.validateLength()

        //Assert
        Assert.assertNotEquals(ParkingException(MESSAGE_LENGTH), result)
    }

    @Test
    fun parking_validateDataLength_isFailure() {
        //Arrange
        val licensePlate = "ABC0000"
        val vehicleBuilder = CarBuilder.aCar()
            .withLicensePlate(licensePlate)

        val expectedMessage = MESSAGE_LENGTH

        try {
            //Act
            vehicleBuilder.build()
        } catch (e: ParkingException) {
            //Assert
            Assert.assertEquals(expectedMessage, e.message)
        }

    }

    @Test
    fun parking_validateAmountVehicle_isCorrect() {
        //Arrange
        val vehicleBuilder = CarBuilder.aCar()
            .build()

        val amount = 11

        //Act
        val result = vehicleBuilder.validateAmountVehicle(amount)

        //Assert
        Assert.assertNotEquals(ParkingException(MESSAGE_RESTRICTED), result)
    }

    @Test
    fun parking_validateAmountVehicle_notException() {
        //Arrange
        val vehicleBuilder = CarBuilder.aCar()

        val expectedMessage = MESSAGE_RESTRICTED

        try {
            //Act
            vehicleBuilder.build()
        } catch (e: ParkingException) {
            //Assert
            Assert.assertNotEquals(expectedMessage, e.message)
        }

    }

    @Test
    fun parking_validateAmountVehicle_exception() {
        //Arrange
        val vehicleBuilder = CarBuilder.aCar()
            .build()

        val expectedMessage = MESSAGE_RESTRICTED
        val amount = 21

        try {
            //Act
            vehicleBuilder.validateAmountVehicle(amount)
        } catch (e: ParkingException) {
            //Assert
            Assert.assertEquals(expectedMessage, e.message)
        }


    }

}