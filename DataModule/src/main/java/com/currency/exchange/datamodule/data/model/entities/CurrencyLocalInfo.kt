package com.currency.exchange.datamodule.data.model.entities

data class CurrencyLocalInfo (
    val code: String,
    val name: String,
    val country: String,
    val countryCode: String,
    val flag: String // Base64 string for the flag image
)