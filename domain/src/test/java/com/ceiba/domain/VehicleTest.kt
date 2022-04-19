package com.ceiba.domain

import com.ceiba.domain.entity.Vehicle
import org.junit.Assert
import org.junit.Test

class VehicleTest {

    @Test
    fun vehicle_validateType_isCorrect() {
        //Arrange
        val licensePlate = "ABC000"
        val typeVehicle = "Moto"
        val cylinderCapacity = 150

        val vehicle = Vehicle(licensePlate, typeVehicle, cylinderCapacity)

        //Act
        val result = vehicle.validateVehicleType()

        //Assert
        Assert.assertTrue(result)
    }
}