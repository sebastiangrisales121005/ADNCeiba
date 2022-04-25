package com.ceiba.adnceiba.withdrawvehicle.viewmodel

import android.text.InputFilter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ceiba.application.service.ParkingServiceApplication
import com.ceiba.domain.aggregate.ParkingValidateEnter
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.valueobject.Time
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WithDrawViewModel: ViewModel() {

    lateinit var parkingServiceApplication: ParkingServiceApplication

    val showCalculateParkingLiveData = MutableLiveData<ParkingValidateEnter>()

    val deleteVehicleLiveData = MutableLiveData<Int>()

    val validateEnterEmojiLiveData = MutableLiveData<InputFilter>()

    val showMessageLiveData = MutableLiveData<String>()

    fun calculateAmount(licensePlate: String, endTime: String) {
        try {
            val time = Time(null, endTime, null)

            CoroutineScope(Dispatchers.Main).launch {
                val parkingUpdate =  time.endDateTime?.let { endTime ->
                    getCalculateAmount(licensePlate, endTime)
                }
                showCalculateParkingLiveData.value = parkingUpdate

                deleteVehicleLiveData.value = parkingUpdate?.let { deleteVehicle(it) }
            }
        } catch (e: ParkingException) {
            showMessageLiveData.value = e.message
        }

    }

    private suspend fun getCalculateAmount(licensePlate: String, endTime: String): ParkingValidateEnter? {
        return withContext(Dispatchers.IO){
            parkingServiceApplication.calculateAmountParking(licensePlate, endTime)
        }
    }

    private suspend fun deleteVehicle(parkingValidateEnter: ParkingValidateEnter): Int? {
        return withContext(Dispatchers.IO) {
            parkingServiceApplication.deleteVehicle(parkingValidateEnter)
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