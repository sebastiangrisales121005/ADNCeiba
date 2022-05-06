package com.ceiba.adnceiba.withdrawvehicle.viewmodel

import android.text.InputFilter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ceiba.adnceiba.utils.DisableCharacters
import com.ceiba.application.dto.ParkingApplicationDto
import com.ceiba.application.service.ParkingEntranceExitServiceApplication
import com.ceiba.domain.exception.ParkingException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WithDrawViewModel @Inject constructor(val parkingEntranceExitServiceApplication: ParkingEntranceExitServiceApplication): ViewModel() {

    val showCalculateParkingLiveData = MutableLiveData<ParkingApplicationDto>()

    val outVehicleLiveData = MutableLiveData<Int>()

    val validateEnterEmojiLiveData = MutableLiveData<InputFilter>()

    val showMessageLiveData = MutableLiveData<String>()

    fun calculateAmount(licensePlate: String, endTime: String) {

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val parkingUpdate = getCalculateAmount(licensePlate, endTime)

                    showCalculateParkingLiveData.value = parkingUpdate

                    val stateVehicle = outVehicle(licensePlate)
                    if (stateVehicle == 1) {
                        outVehicleLiveData.value = stateVehicle
                    }

                } catch (e: ParkingException) {
                    showMessageLiveData.value = e.message
                }
            }

    }

    private suspend fun getCalculateAmount(licensePlate: String, endTime: String): ParkingApplicationDto {
        return withContext(Dispatchers.IO){
            parkingEntranceExitServiceApplication.calculateAmountParking(licensePlate, endTime)
        }
    }

    private suspend fun outVehicle(licensePlate: String): Int? {
        return withContext(Dispatchers.IO) {
            parkingEntranceExitServiceApplication.outVehicle(licensePlate)
        }
    }

    fun disableEmoji() {
        validateEnterEmojiLiveData.value = DisableCharacters.disableEmoji()
    }


}