package com.ceiba.domain.repository

import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.entity.Vehicle

interface MotorcycleEnterRepository {
    fun enterCylinderCapacityMotorCycle(cylinderCapacity: Int)
}