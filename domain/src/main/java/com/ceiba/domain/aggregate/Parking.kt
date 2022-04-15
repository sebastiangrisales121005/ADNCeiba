package com.ceiba.domain.aggregate

import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.valueobject.Payment
import com.ceiba.domain.valueobject.Time

class Parking {
    var numberVehicles: Int? = null
    var vehicle: Vehicle? = null
    var time: Time? = null
    var payment: Payment? = null

}