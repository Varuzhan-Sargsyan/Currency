package com.currency.exchange.datamodule.data.model.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "currency")
data class Currency (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @SerializedName("time_last_update_unix")
    var lastUpdateUnix: Long = 0L,
    @SerializedName("time_last_update_utc")
    var lastUpdateUtc: String = "",
    @SerializedName("time_next_update_unix")
    var nextUpdateUnix: Long = 0L,
    @SerializedName("time_next_update_utc")
    var nextUpdateUtc: String = "",
    @SerializedName("base_code")
    var baseCode: String = "",

    @Ignore
    @SerializedName("conversion_rates")
    var conversionRates: List<Rate> = listOf()
) {
    companion object {
        fun firstDefaultCurrency() = Currency(baseCode = "USD")
        fun secondDefaultCurrency() = Currency(baseCode = "GBP")
    }
}