package com.ceiba.domain.entity

class Vehicle(licensePlate: String, vehicleType: String?, cylinderCapacity: Int) {
    var licensePlate: String? = null
    var vehicleType: String? = null
    var cylinderCapacity: Int? = null

    init {
        this.licensePlate = licensePlate
        this.vehicleType = vehicleType
        if (validateVehicleType()) {
            this.cylinderCapacity = cylinderCapacity
        } else {
            this.cylinderCapacity = 0
        }
    }

    fun validateVehicleType(): Boolean {
        if (this.vehicleType.equals("Moto")) {
            return true
        }
        return false
    }


}