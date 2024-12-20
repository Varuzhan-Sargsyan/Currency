package com.currency.exchange.datamodule.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries_dto")
data class CountryDTO (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: NameDTO,
    val flags: FlagsDTO,
    val currencies: Map<String, CountryCurrencyDTO>
)