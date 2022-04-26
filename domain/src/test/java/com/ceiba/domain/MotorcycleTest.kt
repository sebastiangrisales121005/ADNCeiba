package com.ceiba.domain

import com.ceiba.domain.core.MotorcycleBuilder
import com.ceiba.domain.core.ParkingBuilder
import com.ceiba.domain.core.TimeBuilder
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
        val vehicleBuilder = MotorcycleBuilder.aMotorcycle()
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
        Assert.assertEquals(12000, result)
    }

    @Test
    fun parking_calculateTotalValueParkingMotorCycle_isCorrectHours() {
        //Arrange
        val vehicleBuilder = MotorcycleBuilder.aMotorcycle()
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
        Assert.assertEquals(11500, result)
    }

    @Test
    fun parking_calculateTotalValueParkingMotorCycle_isFailure() {
        //Arrange
        val vehicleBuilder = MotorcycleBuilder.aMotorcycle()
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
        Assert.assertNotEquals(12000, result)
    }
}