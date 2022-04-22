package com.ceiba.domain

import com.ceiba.domain.core.MotorcycleBuilder
import com.ceiba.domain.core.VehicleBuilder
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
}