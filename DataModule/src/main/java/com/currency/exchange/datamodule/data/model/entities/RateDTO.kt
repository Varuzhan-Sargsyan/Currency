package com.currency.exchange.datamodule.data.model.entities

import androidx.room.Entity

@Entity(tableName = "rates_dto", primaryKeys = ["currencyFrom", "currencyTo"])
data class RateDTO(
    var currencyFrom: String = "",
    var currencyTo: String = "",
    var date: String,
    var rate: Double = 1.0,
)
