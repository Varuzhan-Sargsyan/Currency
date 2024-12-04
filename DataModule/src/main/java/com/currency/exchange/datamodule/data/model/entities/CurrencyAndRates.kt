package com.currency.exchange.datamodule.data.model.entities

data class CurrencyAndRates(
    val amount: Double,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
) {
//    fun toRates() : List<RateDTO> {
//        return rates.map { RateDTO(currencyTo = base, baseSecond = it.key, rate = it.value, date = date) }
//    }
}
