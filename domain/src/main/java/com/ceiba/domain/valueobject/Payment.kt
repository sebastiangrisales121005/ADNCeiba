package com.ceiba.domain.valueobject

class Payment {
    var totalValue: Int? = null

    companion object {
        const val PRICE_HOUR_CAR = 1000
        const val PRICE_HOUR_MOTORCYCLE = 500
        const val PRICE_DAY_CAR = 8000
        const val PRICE_DAY_MOTORCYCLE = 8000
    }
}