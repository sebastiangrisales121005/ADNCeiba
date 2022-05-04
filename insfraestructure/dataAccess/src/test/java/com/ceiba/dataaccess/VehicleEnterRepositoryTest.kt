package com.ceiba.dataaccess

import com.ceiba.dataaccess.repository.CarEnterRepository
import com.ceiba.dataaccess.repository.CarEnterRepository.Companion.CYLINDER_CAPACITY_ERROR
import com.ceiba.dataaccess.repository.MotorcycleEnterRepository
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.entity.Car
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.valueobject.Time
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class VehicleEnterRepositoryTest {

    @Test
    fun motorcycle_enterRepository_isCorrect() = runBlocking {
        //Arrange
        val licensePlate = "ABC000"
        val cylinderCapacity = 150
        val vehicle = Motorcycle(licensePlate, cylinderCapacity)

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "martes"
        val time = Time(startDateTime, endDateTime, day)

        val parking = ParkingEntranceExit(vehicle, time)

        val motorcycle = MotorcycleEnterRepository()

        //Act
        val result = motorcycle.enterVehicle(parking)

        //Assert
        Assert.assertNotNull(result)
    }

    @Test
    fun car_enterRepository_isCorrect() = runBlocking {
        //Arrange
        val licensePlate = "ABC000"
        val vehicle = Car(licensePlate)

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "martes"
        val time = Time(startDateTime, endDateTime, day)

        val parking = ParkingEntranceExit(vehicle, time)

        val car = CarEnterRepository()

        //Act
        val result = car.enterVehicle(parking)

        //Assert
        Assert.assertNotNull(result)
    }

    @Test
    fun car_enterRepository_isFailure(): Unit = runBlocking {
        //Arrange
        val licensePlate = "ABC000"
        val cylinderCapacity = 150
        val vehicle = Motorcycle(licensePlate, cylinderCapacity)

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "martes"
        val time = Time(startDateTime, endDateTime, day)

        val parking = ParkingEntranceExit(vehicle, time)

        val car = CarEnterRepository()

        val expectedMessage = CYLINDER_CAPACITY_ERROR

        try {
            //Act
            car.enterVehicle(parking)

        } catch (e: ParkingException) {
            //Assert
            Assert.assertEquals(expectedMessage, e.message)
        }
    }

    @Test
    fun motorcycle_enterRepository_isFailure(): Unit = runBlocking {
        //Arrange
        val licensePlate = "ABC000"
        val vehicle = Car(licensePlate)

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "martes"
        val time = Time(startDateTime, endDateTime, day)

        val parking = ParkingEntranceExit(vehicle, time)

        val motorcycle = MotorcycleEnterRepository()

        val expectedMessage = CYLINDER_CAPACITY_ERROR

        try {
            //Act
            motorcycle.enterVehicle(parking)

        } catch (e: ParkingException) {
            //Assert
            Assert.assertEquals(expectedMessage, e.message)
        }
    }
}