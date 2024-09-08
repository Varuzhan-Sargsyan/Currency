package com.currency.exchange.datamodule.data.datasource

import com.currency.exchange.datamodule.data.model.entities.Currency

object Simulator {
    fun currencies() = listOf(
        Currency("AUD", "Australian Dollar"),
        Currency("BGN", "Bulgarian Lev"),
        Currency("BRL", "Brazilian Real"),
        Currency("CAD", "Canadian Dollar"),
        Currency("CHF", "Swiss Franc"),
        Currency("CNY", "Chinese Renminbi Yuan"),
        Currency("CZK", "Czech Koruna"),
        Currency("DKK", "Danish Krone"),
        Currency("EUR", "Euro"),
        Currency("GBP", "British Pound"),
        Currency("HKD", "Hong Kong Dollar"),
        Currency("HUF", "Hungarian Forint"),
        Currency("IDR", "Indonesian Rupiah"),
        Currency("ILS", "Israeli New Sheqel"),
        Currency("INR", "Indian Rupee"),
        Currency("ISK", "Icelandic Króna"),
        Currency("JPY", "Japanese Yen"),
        Currency("KRW", "South Korean Won"),
        Currency("MXN", "Mexican Peso"),
        Currency("MYR", "Malaysian Ringgit"),
        Currency("NOK", "Norwegian Krone"),
        Currency("NZD", "New Zealand Dollar"),
        Currency("PHP", "Philippine Peso"),
        Currency("PLN", "Polish Złoty"),
        Currency("RON", "Romanian Leu"),
        Currency("SEK", "Swedish Krona"),
        Currency("SGD", "Singapore Dollar"),
        Currency("THB", "Thai Baht"),
        Currency("TRY", "Turkish Lira"),
        Currency("USD", "United States Dollar"),
        Currency("ZAR", "South African Rand")
    )
}