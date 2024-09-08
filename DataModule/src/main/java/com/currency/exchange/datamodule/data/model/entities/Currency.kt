package com.currency.exchange.datamodule.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class Currency (
    @PrimaryKey(autoGenerate = false)
    var baseCode: String = "",
    var name: String = "",
) {
    companion object {
        fun firstDefaultCurrency() = Currency(baseCode = "USD", name = "United States Dollar")
        fun secondDefaultCurrency() = Currency(baseCode = "GBP", name = "British Pound")
    }
}