package com.ceiba.domain.entity

class Vehicle(licensePlate: String, vehicleType: String?, cylinderCapacity: Int) {
    var licensePlate: String? = null
    var vehicleType: String? = null
    var cylinderCapacity: Int? = null

    init {
        this.licensePlate = licensePlate
        this.vehicleType = vehicleType
        if (validateVehicleType(this.vehicleType)) {
            this.cylinderCapacity = cylinderCapacity
        } else {
            this.cylinderCapacity = 0
        }
    }

    fun validateVehicleType(vehicleType: String?): Boolean {
        if (vehicleType.equals("MOTO")) {
            return true
        }
        return false
    }


}