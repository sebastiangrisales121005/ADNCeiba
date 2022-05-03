package com.ceiba.dataaccess

import com.ceiba.application.service.factory.VehicleFactory
import com.ceiba.dataaccess.anticorruption.ParkingTranslator
import com.ceiba.dataaccess.factory.EnterVehicleFactory
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.valueobject.Time
import org.junit.Assert
import org.junit.Test


class ParkingTranslatorTest {

    @Test
    fun parking_translator_isCorrect() {
        //Arrange
        val licensePlate = "ABC000"
        val cylinderCapacity = 150
        val vehicle = Motorcycle(licensePlate, cylinderCapacity)

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "martes"
        val time = Time(startDateTime, endDateTime, day)

        val parking = ParkingEntranceExit(vehicle, time)
        val parkingEntity = ParkingTranslator.fromDomainToEntity(parking)

        //Act
        val parkingDomain = ParkingTranslator.fromEntityToDomain(parkingEntity)

        //Assert
        Assert.assertNotNull(parkingDomain)
    }

    @Test
    fun parking_factory_isCorrect() {
        //Arrange
        val licensePlate = "ABC000"
        val cylinderCapacity = 150
        val vehicleType = "Moto"

        //Act
        val factory = VehicleFactory.build(licensePlate, vehicleType, cylinderCapacity)

        //Assert
        Assert.assertNotNull(factory)
    }

    @Test
    fun parking_enterVehicleFactory_isCorrect() {
        //Arrange
        val vehicleType = "Moto"

        //Act
        val factory = EnterVehicleFactory.build(vehicleType)

        //Assert
        Assert.assertNotNull(factory)
    }
}