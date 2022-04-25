package com.ceiba.adnceiba.entervehicle.viewmodel


import android.text.InputFilter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ceiba.application.service.ParkingServiceApplication
import com.ceiba.domain.aggregate.ParkingEntranceExit
import com.ceiba.domain.exception.ParkingException
import com.ceiba.application.service.factory.VehicleFactory
import com.ceiba.domain.valueobject.Time
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EnterVehicleViewModel: ViewModel() {
    lateinit var parkingServiceApplication: ParkingServiceApplication

    val enterVehicleLiveData = MutableLiveData<Long>()
    val showMessageLiveData = MutableLiveData<String>()
    val validateEnterEmojiLiveData = MutableLiveData<InputFilter>()

    fun insertVehicle(licensePlate: String, selectedVehicle: String, cylinderCapacity: Int,
                    startTime: String, day: String){
        try {
            val vehicle = VehicleFactory.build(licensePlate, selectedVehicle, cylinderCapacity)
            val time = Time(startTime, null, day)

            val parking = vehicle?.let { ParkingEntranceExit(it, time) }

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    enterVehicleLiveData.value = parking?.let { getEnterVehicle(it) }
                } catch (e: ParkingException) {
                    showMessageLiveData.value = e.message
                }
            }
        } catch (e: ParkingException) {
            showMessageLiveData.value = e.message
        }


    }

    private suspend fun getEnterVehicle(parking: ParkingEntranceExit): Long? {
       return withContext(Dispatchers.IO) {
            parkingServiceApplication.enterVehicle(parking)
        }
    }

    fun disableEmoji() {
        val emojiFilter = InputFilter { source, start, end, dest, dstart, dend ->
            for (index in start until end) {
                val type = Character.getType(source[index])

                when (type) {
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