package com.ceiba.dataaccess.factory


import com.ceiba.application.service.factory.VehicleFactory.Companion.CAR_EN
import com.ceiba.application.service.factory.VehicleFactory.Companion.CAR_ES
import com.ceiba.application.service.factory.VehicleFactory.Companion.MOTORCYCLE_EN
import com.ceiba.application.service.factory.VehicleFactory.Companion.MOTORCYCLE_ES
import com.ceiba.dataaccess.repository.CarEnterRepository
import com.ceiba.dataaccess.repository.MotorcycleEnterRepository
import com.ceiba.domain.repository.VehicleEnterRepository

class EnterVehicleFactory {
    companion object {
        fun build(vehicleType: String?): VehicleEnterRepository {
            var vehicle: VehicleEnterRepository = CarEnterRepository()
            if (vehicleType == MOTORCYCLE_ES || vehicleType == MOTORCYCLE_EN) {
                vehicle = MotorcycleEnterRepository()
            } else if (vehicleType == CAR_ES || vehicleType == CAR_EN) {
                vehicle = CarEnterRepository()
            }

            return vehicle
        }
    }
}