package com.currency.exchange.datamodule.data.model.entities

import androidx.room.Entity

@Entity(tableName = "rates_dto", primaryKeys = ["base", "date"])
data class RateDTO(
    var amount: Float,
    val base: String,
    var date: String,
    val rates: Map<String, Float>,
)
