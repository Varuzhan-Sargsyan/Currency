package com.currency.exchange.datamodule.data.model.entities

import androidx.room.Entity
import androidx.room.Ignore
import com.google.gson.annotations.SerializedName

@Entity(tableName = "countries_dto", primaryKeys = ["countryCode"])
data class CountryDTO (
    @SerializedName("cca2")
    val countryCode: String,
    val name: NameDTO,
    val flags: FlagsDTO,
    val currencies: Map<String, CountryCurrencyDTO>,
) {
    @Ignore
    val currencyName: String? = currencies.keys.firstOrNull()
}