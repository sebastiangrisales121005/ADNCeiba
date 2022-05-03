package com.ceiba.domain

import com.ceiba.dataaccess.repository.MotorcycleEnterRepository
import com.ceiba.domain.core.MotorcycleBuilder
import com.ceiba.domain.core.ParkingBuilder
import com.ceiba.domain.core.TimeBuilder
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.repository.ParkingEntranceExitRepository
import com.ceiba.domain.service.ParkingEntranceExitService.Companion.CALCULATE_ERROR
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ParkingEntranceExitServiceTest {

    @Mock
    lateinit var parkingEntranceExitRepository: ParkingEntranceExitRepository

    @Test
    fun vehicle_enter_isFailure() = runBlocking  {
        //Arrange
        val vehicleBuilder = MotorcycleBuilder.aMotorcycle()
            .build()

        val endDateTime = "2022-04-14 10:00:00"
        val day = "domingo"
        val timeBuilder = TimeBuilder.aTime()
            .withEndDateTime(endDateTime)
            .withDay(day)
            .build()

        val parking = ParkingBuilder.aParking()
            .withVehicle(vehicleBuilder)
            .withTime(timeBuilder)
            .build()

        val vehicleRepository = MotorcycleEnterRepository()

        //Act
        val result = parkingEntranceExitRepository.enterVehicle(parking, vehicleRepository)

        //Assert
        Assert.assertNull(result)

    }

    @Test
    fun vehicle_calculateAmount_isFailure(): Unit = runBlocking  {
        //Arrange
        val vehicleBuilder = MotorcycleBuilder.aMotorcycle()
            .build()

        val endDateTime = "2022-04-14 10:00:00"
        val day = "domingo"
        val timeBuilder = TimeBuilder.aTime()
            .withEndDateTime(endDateTime)
            .withDay(day)
            .build()

        val expectedMessage = CALCULATE_ERROR

        //Act
        try {
            timeBuilder.endDateTime?.let {
                parkingEntranceExitRepository.calculateAmountParking(vehicleBuilder.licensePlate,
                    it
                )
            }

        } catch (e: ParkingException) {

            //Assert
            Assert.assertEquals(expectedMessage, e.message)
        }

    }

    @Test
    fun vehicle_out_isFailure() = runBlocking  {
        //Arrange
        val vehicleBuilder = MotorcycleBuilder.aMotorcycle()
            .build()

        //Act
        val result = parkingEntranceExitRepository.outVehicle(vehicleBuilder.licensePlate)

        //Assert
        Assert.assertNull(result)

    }

    @Test
    fun vehicle_validateState_isFailure() = runBlocking {
        //Arrange
        val vehicleBuilder = MotorcycleBuilder.aMotorcycle()
            .build()

        //Act
        val result = parkingEntranceExitRepository.getVehicleExistState(vehicleBuilder.licensePlate)

        //Assert
        Assert.assertNull(result)
    }
}