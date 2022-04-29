package com.ceiba.domain.entity

import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.repository.MotorcycleEnterRepository
import javax.inject.Inject

class Motorcycle @Inject constructor(licensePlate: String, val cylinderCapacity: Int) : Vehicle(licensePlate) {

    @Inject
    lateinit var motorcycleEnterRepository: MotorcycleEnterRepository

    override fun validateAmountVehicle(amountVehicles: Int) {
        if (amountVehicles >= MOTORCYCLE_LIMIT_PARKING) {
            throw ParkingException(MESSAGE_RESTRICTED)
        }
    }

    override fun assignCalculateValueParking(parkingEntranceExit: ParkingEntranceExit): Int {
        var totalValueParking = parkingEntranceExit.calculateTotalValueParking(PRICE_DAY_MOTORCYCLE, PRICE_HOUR_MOTORCYCLE)

        if (cylinderCapacity > CYLINDER_CAPACITY_LIMIT) {
            totalValueParking += SURPLUS_MOTORCYCLE
        }

        return totalValueParking
    }

    companion object {
        const val PRICE_HOUR_MOTORCYCLE = 500
        const val PRICE_DAY_MOTORCYCLE = 4000
        const val MESSAGE_RESTRICTED = "El parqueadero no permite más motos"
        const val CYLINDER_CAPACITY_LIMIT = 500
        const val SURPLUS_MOTORCYCLE = 2000
        const val MOTORCYCLE_LIMIT_PARKING = 10
    }


}