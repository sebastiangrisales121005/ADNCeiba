package com.ceiba.domain

import com.ceiba.domain.core.MotorcycleBuilder
import com.ceiba.domain.core.ParkingBuilder
import com.ceiba.domain.core.TimeBuilder
import com.ceiba.domain.core.VehicleBuilder
import com.ceiba.domain.exception.ParkingException
import org.junit.Assert
import org.junit.Test

class ParkingValidateEnterTest {

    @Test
    fun parking_validateEnterLicensePlate_isCorrect() {
        //Arrange
        val vehicleBuilder = MotorcycleBuilder.aMotorcycle()
            .build()

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "domingo"
        val timeBuilder = TimeBuilder.aTime()
            .withStartDateTime(startDateTime)
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

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "domingo"
        val timeBuilder = TimeBuilder.aTime()
            .withStartDateTime(startDateTime)
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

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "martes"
        val timeBuilder = TimeBuilder.aTime()
            .withStartDateTime(startDateTime)
            .withEndDateTime(endDateTime)
            .withDay(day)
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

    @Test
    fun parking_calculateTotalValueParkingCar_isCorrect() {
        //Arrange
        val licensePlate = "ABC000"
        val typeVehicle = "Carro"
        val cylinderCapacity = 150
        val vehicleBuilder = VehicleBuilder.aVehicle()
            .withLicensePlate(licensePlate)
            .withVehicleType(typeVehicle)
            .withCylinderCapacity(cylinderCapacity)
            .build()

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-16 17:00:00"
        val day = "martes"
        val timeBuilder = TimeBuilder.aTime()
            .withStartDateTime(startDateTime)
            .withEndDateTime(endDateTime)
            .withDay(day)
            .build()

        val parking = ParkingBuilder.aParking()
            .withVehicle(vehicleBuilder)
            .withTime(timeBuilder)
            .build()

        //Act
        parking.calculateTotalValueParking()

        //Assert
        Assert.assertEquals(24000, parking.totalValueParking)
    }

    @Test
    fun parking_calculateTotalValueParkingCar_isCorrectHours() {
        //Arrange
        val licensePlate = "ABC000"
        val typeVehicle = "Carro"
        val cylinderCapacity = 150
        val vehicleBuilder = VehicleBuilder.aVehicle()
            .withLicensePlate(licensePlate)
            .withVehicleType(typeVehicle)
            .withCylinderCapacity(cylinderCapacity)
            .build()

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-16 15:00:00"
        val day = "martes"
        val timeBuilder = TimeBuilder.aTime()
            .withStartDateTime(startDateTime)
            .withEndDateTime(endDateTime)
            .withDay(day)
            .build()

        val parking = ParkingBuilder.aParking()
            .withVehicle(vehicleBuilder)
            .withTime(timeBuilder)
            .build()

        //Act
        parking.calculateTotalValueParking()

        //Assert
        Assert.assertEquals(23000, parking.totalValueParking)
    }

    @Test
    fun parking_calculateTotalValueParkingCar_isFailure() {
        //Arrange
        val licensePlate = "ABC000"
        val typeVehicle = "Carro"
        val cylinderCapacity = 150
        val vehicleBuilder = VehicleBuilder.aVehicle()
            .withLicensePlate(licensePlate)
            .withVehicleType(typeVehicle)
            .withCylinderCapacity(cylinderCapacity)
            .build()

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-16 15:00:00"
        val day = "martes"
        val timeBuilder = TimeBuilder.aTime()
            .withStartDateTime(startDateTime)
            .withEndDateTime(endDateTime)
            .withDay(day)
            .build()

        val parking = ParkingBuilder.aParking()
            .withVehicle(vehicleBuilder)
            .withTime(timeBuilder)
            .build()

        //Act
        parking.calculateTotalValueParking()

        //Assert
        Assert.assertNotEquals(24000, parking.totalValueParking)
    }

}