package com.currency.exchange.datamodule.domain.repositories

import com.currency.exchange.datamodule.data.interfaces.ILocalDataRepository
import com.currency.exchange.datamodule.data.model.entities.CountryDTO
import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO
import com.currency.exchange.datamodule.domain.interfaces.ICurrencyRepository
import com.currency.exchange.datamodule.domain.model.Currency
import kotlinx.coroutines.flow.combine

class CurrencyRepository(
    private val localDataRepository: ILocalDataRepository,
) : ICurrencyRepository {
    override suspend fun currenciesFlow() =
        combine<List<CurrencyDTO>, List<CountryDTO>, List<Currency>> (
            localDataRepository.currenciesDTOFlow(),
            localDataRepository.countriesDTOFlow()
        ) { currencies, countries ->
            if (currencies.isEmpty() || countries.isEmpty())
                emptyList<Currency>()
            else {
//                val acceptableCurrencyOfCountries = countries.map { it.currencyName }
//                val filteredCountries = countries
//                    .filter { country ->
//
//                    }
//                val filteredCurrencies = currencies
//                    .filter { currencyDTO ->
//                        currencyDTO.code in localDataRepository.acceptableCountries.values
//                    }
                val internationalCurrencies = internationalCurrencies()
                val internationalCurrencyCodes = internationalCurrencies.map { it.code }
                val currencyCodes = currencies.map { it.code }
                val filteredCountries = countries
                    .filter {
                        it.currencies.keys.any { code -> code in currencyCodes && code !in internationalCurrencyCodes }
                    }.associateBy { it.currencyName }
                currencies.map { currencyDTO ->
                    Currency(
                        currencyDTO,
                        filteredCountries[currencyDTO.code]
                    )
                }// + internationalCurrencies
            }
        }

    private fun internationalCurrencies() : List<Currency> =
        listOf(
            Currency.defaultCurrency()
        )

}

