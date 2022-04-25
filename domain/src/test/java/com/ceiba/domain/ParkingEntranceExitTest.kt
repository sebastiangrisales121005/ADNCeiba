package com.ceiba.domain

import com.ceiba.domain.core.MotorcycleBuilder
import com.ceiba.domain.core.ParkingBuilder
import com.ceiba.domain.core.TimeBuilder
import com.ceiba.domain.exception.ParkingException
import org.junit.Assert
import org.junit.Test

class ParkingEntranceExitTest {

    @Test
    fun parking_validateEnterLicensePlate_isCorrect() {
        //Arrange
        val vehicleBuilder = MotorcycleBuilder.aMotorcycle()
            .build()

        val endDateTime = "2022-04-14 10:00:00"
        val day = "domingo"
        val timeBuilder = TimeBuilder.aTime()
            .withEndDateTime(endDateTime)
            .withDay(day)
            .build()

        val parking = ParkingBuilder.aParking()
            .withVehicle(vehicleBuilder)
            .withTime(timeBuilder)
            .build()

        //Act
        val result = parking.validateEnterLicensePlate()

        //Arrange
        Assert.assertTrue(result)
    }

    @Test
    fun parking_validateEnterLicensePlate_isFailureLicensePlate() {
        //Arrange
        val licensePlate = "EBC000"
        val vehicleBuilder = MotorcycleBuilder.aMotorcycle()
            .withLicensePlate(licensePlate)
            .build()

        val endDateTime = "2022-04-14 10:00:00"
        val day = "domingo"
        val timeBuilder = TimeBuilder.aTime()
            .withEndDateTime(endDateTime)
            .withDay(day)
            .build()

        val parking = ParkingBuilder.aParking()
            .withVehicle(vehicleBuilder)
            .withTime(timeBuilder)
            .build()

        //Act
        val result = parking.validateEnterLicensePlate()

        //Arrange
        Assert.assertFalse(result)
    }

    @Test
    fun parking_validateEnterLicensePlateWithoutDay_exception() {
        //Arrange
        val vehicleBuilder = MotorcycleBuilder.aMotorcycle()
            .build()

        val endDateTime = "2022-04-14 10:00:00"
        val timeBuilder = TimeBuilder.aTime()
            .withEndDateTime(endDateTime)
            .build()


        val parking = ParkingBuilder.aParking()
            .withVehicle(vehicleBuilder)
            .withTime(timeBuilder)
            .build()

        val expectedMessage = "Este vehículo no está autorizado a ingresar"

        //Act
        try {
            parking.validateEnterLicensePlate()

            Assert.fail()

        } catch (e: ParkingException) {
            //Assert
            Assert.assertEquals(expectedMessage, e.message)
        }

    }

}