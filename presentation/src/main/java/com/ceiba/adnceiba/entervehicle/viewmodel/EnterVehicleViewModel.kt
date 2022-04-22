package com.ceiba.adnceiba.entervehicle.viewmodel


//import androidx.hilt.Assisted
//import androidx.hilt.lifecycle.ViewModelInject
//import androidx.lifecycle.SavedStateHandle
import android.text.InputFilter
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ceiba.application.service.ParkingServiceApplication
import com.ceiba.domain.aggregate.Parking
import com.ceiba.domain.entity.Motorcycle
import com.ceiba.domain.entity.Vehicle
import com.ceiba.domain.exception.ParkingException
import com.ceiba.domain.factory.VehicleFactory
import com.ceiba.domain.valueobject.Time
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EnterVehicleViewModel /*@ViewModelInject constructor(parkingServiceApplication: ParkingServiceApplication,
                                                         @Assisted private val savedStateHandle: SavedStateHandle)*/ : ViewModel() {
    lateinit var parkingServiceApplication: ParkingServiceApplication

    val enterVehicleLiveData = MutableLiveData<Long>()
    val showMessageLiveData = MutableLiveData<String>()
    val validateEnterEmojiLiveData = MutableLiveData<InputFilter>()

    fun insertVehicle(licensePlate: String, selectedVehicle: String, cylinderCapacity: Int,
                    time: Time){
        val vehicle = VehicleFactory.build(licensePlate, selectedVehicle, cylinderCapacity)
        vehicle?.let {
            val parking = Parking(it, time)

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    enterVehicleLiveData.value =
                        parkingServiceApplication.enterVehicle(parking)
                } catch (e: ParkingException) {
                    showMessageLiveData.value = e.message
                }
            }
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