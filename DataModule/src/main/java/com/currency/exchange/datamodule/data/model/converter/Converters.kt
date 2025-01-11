package com.currency.exchange.datamodule.data.model.converter

import androidx.room.TypeConverter
import com.currency.exchange.datamodule.data.model.entities.CountryCurrencyDTO
import com.currency.exchange.datamodule.data.model.entities.FlagsDTO
import com.currency.exchange.datamodule.data.model.entities.NameDTO
import com.currency.exchange.datamodule.data.model.entities.NativeNameDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    // Rate map Converters
    @TypeConverter
    fun fromRateMap(rates: Map<String, Float>) : String {
        return gson.toJson(rates)
    }

    @TypeConverter
    fun toRateMap(nameJson: String): Map<String, Float> {
        val type = object : TypeToken<Map<String, Float>>() {}.type
        return gson.fromJson(nameJson, type)
    }
    // NameDTO Converters
    @TypeConverter
    fun fromNameDTO(name: NameDTO): String {
        return gson.toJson(name)
    }

    @TypeConverter
    fun toNameDTO(nameJson: String): NameDTO {
        return gson.fromJson(nameJson, NameDTO::class.java)
    }

    // FlagsDTO Converters
    @TypeConverter
    fun fromFlagsDTO(flags: FlagsDTO): String {
        return gson.toJson(flags)
    }

    @TypeConverter
    fun toFlagsDTO(flagsJson: String): FlagsDTO {
        return gson.fromJson(flagsJson, FlagsDTO::class.java)
    }

    // CountryCurrencyDTO Map Converters
    @TypeConverter
    fun fromCurrencyMap(currencies: Map<String, CountryCurrencyDTO>): String {
        return gson.toJson(currencies)
    }

    @TypeConverter
    fun toCurrencyMap(currenciesJson: String): Map<String, CountryCurrencyDTO> {
        val type = object : TypeToken<Map<String, CountryCurrencyDTO>>() {}.type
        return gson.fromJson(currenciesJson, type)
    }

    // NativeNameDTO Map Converters
    @TypeConverter
    fun fromNativeNameMap(nativeName: Map<String, NativeNameDTO>?): String {
        return gson.toJson(nativeName)
    }

    @TypeConverter
    fun toNativeNameMap(nativeNameJson: String): Map<String, NativeNameDTO>? {
        val type = object : TypeToken<Map<String, NativeNameDTO>>() {}.type
        return gson.fromJson(nativeNameJson, type)
    }
}