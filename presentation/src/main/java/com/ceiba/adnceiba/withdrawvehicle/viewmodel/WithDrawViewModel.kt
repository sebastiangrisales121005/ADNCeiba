package com.ceiba.adnceiba.withdrawvehicle.viewmodel

import androidx.lifecycle.ViewModel
import com.ceiba.application.service.ParkingServiceApplication
import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.valueobject.Time
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WithDrawViewModel: ViewModel() {

    lateinit var parkingServiceApplication: ParkingServiceApplication

    fun calculateAmount(parking: Parking) {
        CoroutineScope(Dispatchers.Main).launch {
            parkingServiceApplication.calculateAmountParking(parking)
        }
    }
}