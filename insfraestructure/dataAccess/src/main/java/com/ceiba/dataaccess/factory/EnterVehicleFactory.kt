package com.ceiba.dataaccess.factory


import com.ceiba.application.service.factory.VehicleFactory.MOTORCYCLE_EN
import com.ceiba.application.service.factory.VehicleFactory.MOTORCYCLE_ES
import com.ceiba.dataaccess.repository.CarEnterRepository
import com.ceiba.dataaccess.repository.MotorcycleEnterRepository
import com.ceiba.domain.repository.VehicleEnterRepository

object EnterVehicleFactory {

    fun build(vehicleType : String?) : VehicleEnterRepository {
        var vehicle : VehicleEnterRepository = CarEnterRepository()
        if (vehicleType == MOTORCYCLE_ES || vehicleType == MOTORCYCLE_EN) {
            vehicle = MotorcycleEnterRepository()
        }
        return vehicle
    }

}