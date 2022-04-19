package com.ceiba.domain

import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.valueobject.Payment
import com.ceiba.domain.valueobject.Time
import org.junit.Assert
import org.junit.Test

class ParkingTest {

    @Test
    fun parking_validateEnterLicensePlate_isCorrect() {
        //Arrange
        val licensePlate = "ABC000"
        val typeVehicle = "Moto"
        val cylinderCapacity = 150
        val vehicle = Vehicle(licensePlate, typeVehicle, cylinderCapacity)

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "domingo"
        val time = Time(startDateTime, endDateTime, day)

        val payment = Payment()

        val parking = Parking(vehicle, time, payment)

        //Act
        val result = parking.validateEnterLicensePlate()

        //Arrange
        Assert.assertTrue(result)
    }

    @Test
    fun parking_validateEnterLicensePlate_isFailureLicensePlate() {
        //Arrange
        val licensePlate = "EBC000"
        val typeVehicle = "Moto"
        val cylinderCapacity = 150
        val vehicle = Vehicle(licensePlate, typeVehicle, cylinderCapacity)

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "domingo"
        val time = Time(startDateTime, endDateTime, day)

        val payment = Payment()

        val parking = Parking(vehicle, time, payment)

        //Act
        val result = parking.validateEnterLicensePlate()

        //Arrange
        Assert.assertFalse(result)
    }
}