package com.ceiba.adnceiba.entervehicle.viewmodel


import android.text.InputFilter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ceiba.application.service.ParkingEntranceExitServiceApplication
import com.ceiba.application.service.factory.VehicleFactory
import com.ceiba.dataaccess.factory.EnterVehicleFactory
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.repository.VehicleEnterRepository
import com.ceiba.domain.valueobject.Time
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EnterVehicleViewModel: ViewModel() {
    lateinit var parkingEntranceExitServiceApplication: ParkingEntranceExitServiceApplication

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
        val emojiFilter = InputFilter { source, start, end, _, _, _ ->
            for (index in start until end) {

                when (Character.getType(source[index])) {
                    '*'.code,
                    Character.OTHER_SYMBOL.toInt(),
                    Character.SURROGATE.toInt() -> {
                        return@InputFilter ""
                    }
                    Character.LOWERCASE_LETTER.toInt() -> {
                        val index2 = index + 1
                        if (index2 < end && Character.getType(source[index + 1]) == Character.NON_SPACING_MARK.toInt())
                            return@InputFilter ""
                    }
                    Character.DECIMAL_DIGIT_NUMBER.toInt() -> {
                        val index2 = index + 1
                        val index3 = index + 2
                        if (index2 < end && index3 < end &&
                            Character.getType(source[index2]) == Character.NON_SPACING_MARK.toInt() &&
                            Character.getType(source[index3]) == Character.ENCLOSING_MARK.toInt()
                        )
                            return@InputFilter ""
                    }
                    Character.OTHER_PUNCTUATION.toInt() -> {
                        val index2 = index + 1

                        if (index2 < end && Character.getType(source[index2]) == Character.NON_SPACING_MARK.toInt()) {
                            return@InputFilter ""
                        }
                    }
                    Character.MATH_SYMBOL.toInt() -> {
                        val index2 = index + 1
                        if (index2 < end && Character.getType(source[index2]) == Character.NON_SPACING_MARK.toInt())
                            return@InputFilter ""
                    }
                }
            }
            return@InputFilter null
        }

        validateEnterEmojiLiveData.value = emojiFilter
    }



}