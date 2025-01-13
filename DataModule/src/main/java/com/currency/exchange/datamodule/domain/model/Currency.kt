package com.currency.exchange.datamodule.domain.model

import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO
import com.currency.exchange.datamodule.data.model.entities.CountryDTO

data class Currency(
    val code: String,
    val name: String,
    val countryName: String,
    val countryCode: String,
    private val flag: String
) {
    constructor(currencyDTO: CurrencyDTO, countryDTOInfo: CountryDTO?) :
            this(
                code = currencyDTO.code,
                name = currencyDTO.name,
                countryName = countryDTOInfo?.name?.official ?: DEFAULT_COUNTRY_NAME,
                countryCode = countryDTOInfo?.currencies?.keys?.first() ?: DEFAULT_COUNTRY_CODE,
                flag = countryDTOInfo?.flags?.png ?: DEFAULT_COUNTRY_FLAG
            )

    fun imageFlagUrl() = flag

    companion object {
        const val DEFAULT_COUNTRY_FLAG  = "https://flagcdn.com/w640/eu.png"
        const val DEFAULT_COUNTRY_CODE  = "EU"
        const val DEFAULT_COUNTRY_NAME  = "European Union"
        const val DEFAULT_CURRENCY_CODE = "EUR"
        const val DEFAULT_CURRENCY_NAME = "EUR"

        val testCurrencies = listOf(
            Currency("USD", "US Dollar", "United States", "US", DEFAULT_COUNTRY_FLAG),
            Currency("EUR", "Euro", "EU", "European Union", DEFAULT_COUNTRY_FLAG),
            Currency("AMD", "Armenian Dram", "AM", "Republic of Armenia", DEFAULT_COUNTRY_FLAG),
        )

        fun defaultCurrency() =
            Currency(
                code = DEFAULT_CURRENCY_CODE,
                name = DEFAULT_CURRENCY_NAME,
                countryName = DEFAULT_COUNTRY_NAME,
                countryCode = DEFAULT_COUNTRY_CODE,
                flag = DEFAULT_COUNTRY_FLAG
            )

    }
}