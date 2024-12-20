package com.currency.exchange.datamodule.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies_dto")
data class CurrencyDTO (
    @PrimaryKey(autoGenerate = false)
    var code: String = "",
    var name: String = "",
)

fun Map<String, String>.toCurrencyDTOList() = map { CurrencyDTO(it.key, it.value) }.toMutableList()