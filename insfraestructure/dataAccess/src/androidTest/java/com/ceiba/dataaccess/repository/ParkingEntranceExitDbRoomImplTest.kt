package com.ceiba.dataaccess.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ceiba.dataaccess.anticorruption.ParkingTranslator
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.valueobject.Time
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ParkingEntranceExitDbRoomImplTest: TestCase() {
    private lateinit var parkingDbRoomImpl: ParkingDbRoomImpl
    private lateinit var parkingServiceRoom: ParkingServiceRoom

    @Before
    public override fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()

        parkingDbRoomImpl = Room.inMemoryDatabaseBuilder(context, ParkingDbRoomImpl::class.java).build()
        parkingServiceRoom = parkingDbRoomImpl.parkingDao()
    }

    @After
    fun closeDb() {
        parkingDbRoomImpl.close()
    }

    @Test
    fun vehicle_enter_isCorrect() = runBlocking {
        //Arrange
        val licensePlate = "ABC000"
        val cylinderCapacity = 150
        val vehicle = Motorcycle(licensePlate, cylinderCapacity)

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "martes"
        val time = Time(startDateTime, endDateTime, day)

        val parkingEntity = ParkingTranslator.fromDomainToEntity(ParkingEntranceExit(vehicle, time))

        //Act
        val id = parkingServiceRoom.insertVehicle(parkingEntity)

        //Assert
        Assert.assertNotNull(id)
    }

    @Test
    fun vehicle_enter_exist() = runBlocking {
        //Arrange
        val licensePlate = "ABC000"
        val cylinderCapacity = 150
        val vehicle = Motorcycle(licensePlate, cylinderCapacity)

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "martes"
        val time = Time(startDateTime, endDateTime, day)

        val parkingEntity = ParkingTranslator.fromDomainToEntity(ParkingEntranceExit(vehicle, time))

        //Act
        val vehicles = parkingServiceRoom.validateVehicleExist(parkingEntity.licensePlate).toString()

        //Assert
        Assert.assertEquals("[]", vehicles)

    }

    @Test
    fun vehicle_update_isCorrect() = runBlocking {
        //Arrange
        val licensePlate = "ABC000"
        val cylinderCapacity = 150
        val vehicle = Motorcycle(licensePlate, cylinderCapacity)

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "martes"
        val time = Time(startDateTime, endDateTime, day)

        val parkingEntity = ParkingTranslator.fromDomainToEntity(ParkingEntranceExit(vehicle, time))

        //Act
        parkingServiceRoom.insertVehicle(parkingEntity)
        val id = parkingServiceRoom.update(licensePlate, endDateTime)

        //Assert
        Assert.assertNotNull(id)
    }

    @Test
    fun vehicle_out_isCorrect() = runBlocking {
        //Arrange
        val licensePlate = "ABC000"
        val cylinderCapacity = 150
        val vehicle = Motorcycle(licensePlate, cylinderCapacity)

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "martes"
        val time = Time(startDateTime, endDateTime, day)

        val parkingEntity = ParkingTranslator.fromDomainToEntity(ParkingEntranceExit(vehicle, time))
        val state = 1

        //Act
        parkingServiceRoom.insertVehicle(parkingEntity)
        val id = parkingServiceRoom.outVehicle(state, licensePlate)

        //Assert
        Assert.assertNotNull(id)
    }

    @Test
    fun vehicle_count_isCorrect() = runBlocking {
        //Arrange
        val licensePlate = "ABC000"
        val cylinderCapacity = 150
        val vehicle = Motorcycle(licensePlate, cylinderCapacity)

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "martes"
        val time = Time(startDateTime, endDateTime, day)

        val parkingEntity = ParkingTranslator.fromDomainToEntity(ParkingEntranceExit(vehicle, time))
        val vehicleType = "Moto"

        //Act
        parkingServiceRoom.insertVehicle(parkingEntity)
        val id = parkingServiceRoom.getCountVehicle(vehicleType)

        //Assert
        Assert.assertNotNull(id)
    }
}