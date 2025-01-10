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
            else
                currencies.map { currencyDTO ->
                    Currency(currencyDTO, countries.firstOrNull { it.currencies.keys.contains(currencyDTO.code) })
                }
        }
}

