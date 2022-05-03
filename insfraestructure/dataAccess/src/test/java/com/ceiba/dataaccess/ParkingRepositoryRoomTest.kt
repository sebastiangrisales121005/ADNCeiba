package com.ceiba.dataaccess

import com.ceiba.dataaccess.repository.MotorcycleEnterRepository
import com.ceiba.dataaccess.repository.ParkingRepositoryRoom
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.valueobject.Time
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ParkingRepositoryRoomTest {
    @Mock
    lateinit var parkingRepositoryRoom: ParkingRepositoryRoom

    @Test
    fun vehicle_enterRoom_isFailure() = runBlockingTest {
        //Arrange
        val licensePlate = "ABC000"
        val cylinderCapacity = 150
        val vehicle = Motorcycle(licensePlate, cylinderCapacity)

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "martes"
        val time = Time(startDateTime, endDateTime, day)

        val parking = ParkingEntranceExit(vehicle, time)

        val vehicleRepository = MotorcycleEnterRepository()

        //Act
        val result = parkingRepositoryRoom.enterVehicle(parking, vehicleRepository)

        //Assert
        Assert.assertNull(result)
    }

}