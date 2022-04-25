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
        val typeVehicle = "Moto"
        val cylinderCapacity = 150
        val vehicle = Motorcycle(licensePlate, typeVehicle, cylinderCapacity)

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "martes"
        val time = Time(startDateTime, endDateTime, day)

        val parkingDto = ParkingTranslator.fromDomainToEntity(ParkingEntranceExit(vehicle, time))

        //Act
        val id = parkingServiceRoom.insertVehicle(parkingDto)

        //Assert
        Assert.assertNotNull(id)
    }

    @Test
    fun vehicle_enter_exist() = runBlocking {
        //Arrange
        val licensePlate = "ABC000"
        val typeVehicle = "Moto"
        val cylinderCapacity = 150
        val vehicle = Motorcycle(licensePlate, typeVehicle, cylinderCapacity)

        val startDateTime = "2022-04-14 08:00:00"
        val endDateTime = "2022-04-14 10:00:00"
        val day = "martes"
        val time = Time(startDateTime, endDateTime, day)

        val parkingDto = ParkingTranslator.fromDomainToEntity(ParkingEntranceExit(vehicle, time))

        //Act
        val vehicles = parkingServiceRoom.validateVehicleExist(parkingDto.licensePlate).toString()

        //Assert
        Assert.assertEquals("[]", vehicles)

    }
}