package com.currency.exchange.datamodule.data.datasource

import androidx.room.TypeConverter
import com.currency.exchange.datamodule.data.model.responses.Currencies
import com.currency.exchange.datamodule.data.model.entities.Rate
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CurrencyListConverter {
    @TypeConverter
    fun fromAgeTierList(value: Currencies): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toAgeTierList(value: String?): Currencies {
        val type = object : TypeToken<List<Rate>>() {}.type
        return Gson().fromJson(value, type)
    }
}