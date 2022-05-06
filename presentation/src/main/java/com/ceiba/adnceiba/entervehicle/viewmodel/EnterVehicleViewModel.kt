package com.ceiba.adnceiba.entervehicle.viewmodel


import android.text.InputFilter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ceiba.adnceiba.utils.DisableCharacters
import com.ceiba.application.service.ParkingEntranceExitServiceApplication
import com.ceiba.application.service.factory.VehicleFactory
import com.ceiba.dataaccess.factory.EnterVehicleFactory
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.repository.VehicleEnterRepository
import com.ceiba.domain.valueobject.Time
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EnterVehicleViewModel @Inject constructor(val parkingEntranceExitServiceApplication: ParkingEntranceExitServiceApplication): ViewModel() {
    //lateinit var parkingEntranceExitServiceApplication: ParkingEntranceExitServiceApplication

    val enterVehicleLiveData = MutableLiveData<Long>()
    val showMessageLiveData = MutableLiveData<String>()
    val validateEnterEmojiLiveData = MutableLiveData<InputFilter>()

    fun insertVehicle(licensePlate: String, selectedVehicle: String, cylinderCapacity: Int,
                    startTime: String, day: String){
        try {
            val vehicle = VehicleFactory.build(licensePlate, selectedVehicle, cylinderCapacity)
            val time = Time(startTime, null, day)

            val parking = vehicle?.let { ParkingEntranceExit(it, time) }
            val vehicleEnterRepository = EnterVehicleFactory.build(selectedVehicle)

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    enterVehicleLiveData.value = parking?.let { getEnterVehicle(it, vehicleEnterRepository) }
                } catch (e: ParkingException) {
                    showMessageLiveData.value = e.message
                }
            }
        } catch (e: ParkingException) {
            showMessageLiveData.value = e.message
        }


    }

    private suspend fun getEnterVehicle(parking: ParkingEntranceExit, vehicleEnterRepository: VehicleEnterRepository): Long? {
       return withContext(Dispatchers.IO) {
            parkingEntranceExitServiceApplication.enterVehicle(parking, vehicleEnterRepository)
        }
    }

    fun disableEmoji() {
        validateEnterEmojiLiveData.value = DisableCharacters.disableEmoji()
    }



}