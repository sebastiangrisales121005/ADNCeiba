package com.ceiba.domain

import com.ceiba.domain.core.MotorcycleBuilder
import com.ceiba.domain.core.ParkingBuilder
import com.ceiba.domain.core.TimeBuilder
import com.ceiba.domain.core.VehicleBuilder
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.exception.ParkingException
import org.junit.Assert
import org.junit.Test

class MotorcycleTest {

    @Test
    fun vehicle_validateAmount_exception() {
        //Arrange
        val vehicleBuilder = MotorcycleBuilder.aMotorcycle()
            .build()

        val amountMotorCycle = 11

        //Act
        try {
            vehicleBuilder.validateAmountVehicle(amountMotorCycle)

            Assert.fail()

        } catch (e: ParkingException) {
            //Assert
            Assert.assertNotNull(e.message)
        }
    }

    @Test
    fun parking_calculateTotalValueParkingMotorCycle_isCorrect() {
        //Arrange
        val licensePlate = "ABC000"
        val typeVehicle = "Moto"
        val cylinderCapacity = 150
        val vehicleBuilder = MotorcycleBuilder.aMotorcycle()
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
        vehicleBuilder.calculateTotalValueParking(timeBuilder, Motorcycle.PRICE_DAY_MOTORCYCLE, Motorcycle.PRICE_HOUR_MOTORCYCLE)

        //Assert
        Assert.assertEquals(12000, vehicleBuilder.totalValueParking)
    }

    @Test
    fun parking_calculateTotalValueParkingMotorCycle_isCorrectHours() {
        //Arrange
        val vehicleBuilder = MotorcycleBuilder.aMotorcycle()
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
        vehicleBuilder.calculateTotalValueParking(timeBuilder, Motorcycle.PRICE_DAY_MOTORCYCLE, Motorcycle.PRICE_HOUR_MOTORCYCLE)

        //Assert
        Assert.assertEquals(11500, vehicleBuilder.totalValueParking)
    }

    @Test
    fun parking_calculateTotalValueParkingMotorCycle_isFailure() {
        //Arrange
        val vehicleBuilder = MotorcycleBuilder.aMotorcycle()
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
        vehicleBuilder.calculateTotalValueParking(timeBuilder, Motorcycle.PRICE_DAY_MOTORCYCLE, Motorcycle.PRICE_HOUR_MOTORCYCLE)

        //Assert
        Assert.assertNotEquals(12000, vehicleBuilder.totalValueParking)
    }
}