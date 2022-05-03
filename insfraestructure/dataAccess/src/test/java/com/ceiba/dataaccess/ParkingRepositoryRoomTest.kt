package com.ceiba.dataaccess

import com.ceiba.dataaccess.anticorruption.ParkingTranslator
import com.ceiba.dataaccess.repository.MotorcycleEnterRepository
import com.ceiba.dataaccess.repository.ParkingRepositoryRoom
import com.ceiba.dataaccess.repository.ParkingRepositoryRoom.Companion.MESSAGE_GET_VEHICLE
import com.ceiba.dataaccess.repository.ParkingServiceRoom
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.valueobject.Time
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ParkingRepositoryRoomTest {
    @Mock
    lateinit var parkingServiceRoom: ParkingServiceRoom

    @Test
    fun vehicle_enterRoom_isFailure(): Unit = runBlocking {
        //Arrange
        val licensePlate = "ABC000"
        val cylinderCapacity = 150
        val vehicle = Motorcycle(licensePlate, cylinderCapacity)

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "martes"
        val time = Time(startDateTime, endDateTime, day)

        val parking = ParkingEntranceExit(vehicle, time)


        val listParking = listOf(ParkingTranslator.fromDomainToEntity(parking))

        val vehicleRepository = MotorcycleEnterRepository()

        val parkingRepositoryRoom = ParkingRepositoryRoom()
        parkingRepositoryRoom.parkingDbRoomImpl = parkingServiceRoom

        `when`(parkingRepositoryRoom.parkingDbRoomImpl.validateVehicleExist(vehicle.licensePlate)).thenReturn(listParking)

        val expectedMessage = MESSAGE_GET_VEHICLE

        //Act
        try {
            parkingRepositoryRoom.enterVehicle(parking, vehicleRepository)
        } catch (e: ParkingException) {
            //Assert
            Assert.assertEquals(expectedMessage, e.message)
        }
    }

}