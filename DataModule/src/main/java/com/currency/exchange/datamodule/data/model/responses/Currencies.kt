package com.currency.exchange.datamodule.data.model.responses

import androidx.room.TypeConverters
import com.currency.exchange.datamodule.data.datasource.CurrencyListConverter
import com.currency.exchange.datamodule.data.model.entities.Currency

data class Currencies (
    @TypeConverters(CurrencyListConverter::class)
    val all: List<Currency> = emptyList()
)