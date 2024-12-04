package com.currency.exchange.datamodule.data.database

import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO

object Simulator {
    fun currencies() = listOf(
        CurrencyDTO("AUD", "Australian Dollar"),
        CurrencyDTO("BGN", "Bulgarian Lev"),
        CurrencyDTO("BRL", "Brazilian Real"),
        CurrencyDTO("CAD", "Canadian Dollar"),
        CurrencyDTO("CHF", "Swiss Franc"),
        CurrencyDTO("CNY", "Chinese Renminbi Yuan"),
        CurrencyDTO("CZK", "Czech Koruna"),
        CurrencyDTO("DKK", "Danish Krone"),
        CurrencyDTO("EUR", "Euro"),
        CurrencyDTO("GBP", "British Pound"),
        CurrencyDTO("HKD", "Hong Kong Dollar"),
        CurrencyDTO("HUF", "Hungarian Forint"),
        CurrencyDTO("IDR", "Indonesian Rupiah"),
        CurrencyDTO("ILS", "Israeli New Sheqel"),
        CurrencyDTO("INR", "Indian Rupee"),
        CurrencyDTO("ISK", "Icelandic Króna"),
        CurrencyDTO("JPY", "Japanese Yen"),
        CurrencyDTO("KRW", "South Korean Won"),
        CurrencyDTO("MXN", "Mexican Peso"),
        CurrencyDTO("MYR", "Malaysian Ringgit"),
        CurrencyDTO("NOK", "Norwegian Krone"),
        CurrencyDTO("NZD", "New Zealand Dollar"),
        CurrencyDTO("PHP", "Philippine Peso"),
        CurrencyDTO("PLN", "Polish Złoty"),
        CurrencyDTO("RON", "Romanian Leu"),
        CurrencyDTO("SEK", "Swedish Krona"),
        CurrencyDTO("SGD", "Singapore Dollar"),
        CurrencyDTO("THB", "Thai Baht"),
        CurrencyDTO("TRY", "Turkish Lira"),
        CurrencyDTO("USD", "United States Dollar"),
        CurrencyDTO("ZAR", "South African Rand")
    )
}