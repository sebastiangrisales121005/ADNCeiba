package com.ceiba.domain

import com.ceiba.domain.core.VehicleBuilder
import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.exception.ParkingException
import org.junit.Assert
import org.junit.Test

class VehicleTest {

    @Test
    fun vehicle_validateType_isCorrect() {
        //Arrange
        val licensePlate = "ABC000"
        val typeVehicle = "Moto"
        val cylinderCapacity = 150

        val vehicleBuilder = VehicleBuilder.aVehicle()
            .withLicensePlate(licensePlate)
            .withVehicleType(typeVehicle)
            .withCylinderCapacity(cylinderCapacity)
            .build()

        //Act
        val result = vehicleBuilder.validateVehicleType()

        //Assert
        Assert.assertTrue(result)
    }

    @Test
    fun vehicle_validateAmount_exception() {
        //Arrange
        val licensePlate = "ABC000"
        val typeVehicle = "Moto"
        val cylinderCapacity = 150

        val vehicleBuilder = VehicleBuilder.aVehicle()
            .withLicensePlate(licensePlate)
            .withVehicleType(typeVehicle)
            .withCylinderCapacity(cylinderCapacity)
            .build()

        val amountCar = 21
        val amountMotorCycle = 11

        //Act
        try {
            vehicleBuilder.validateAmountVehicle(amountCar, amountMotorCycle)

            Assert.fail()

        } catch (e: ParkingException) {
            //Assert
            Assert.assertNotNull(e.message)
        }
    }
}