package com.ceiba.domain.aggregate

import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.valueobject.Payment
import com.ceiba.domain.valueobject.Time

class Parking(vehicle: Vehicle, time: Time, payment: Payment) {
    var numberVehicles: Int? = null
    var vehicle: Vehicle? = null
    var time: Time? = null
    var payment: Payment? = null

    init {
        this.vehicle = vehicle
        this.time = time
        this.payment = payment
    }

}