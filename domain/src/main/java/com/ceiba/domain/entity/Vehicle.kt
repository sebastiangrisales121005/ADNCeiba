package com.ceiba.domain.entity

class Vehicle(licensePlate: String, vehicleType: String?, cylinderCapacity: Int) {
    var licensePlate: String? = null
    var vehicleType: String? = null
    var cylinderCapacity: Int? = null

    init {
        this.licensePlate = licensePlate
        this.vehicleType = vehicleType
        if (this.vehicleType.equals("MOTO")) {
            this.cylinderCapacity = cylinderCapacity
        } else {
            //Lanzar mensaje o excepción que indique que al ser un carro no es necesario obtener el cilindraje
        }
    }


}