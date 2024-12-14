package com.currency.exchange.datamodule.domain.repositories

import com.currency.exchange.datamodule.data.interfaces.IDataRepository
import com.currency.exchange.datamodule.data.interfaces.ISharedDataRepository
import com.currency.exchange.datamodule.domain.extensions.buyCurrency
import com.currency.exchange.datamodule.domain.extensions.buyCurrencyFlow
import com.currency.exchange.datamodule.domain.extensions.sellCurrency
import com.currency.exchange.datamodule.domain.extensions.sellCurrencyFlow
import com.currency.exchange.datamodule.domain.interfaces.ICurrencyRepository
import com.currency.exchange.datamodule.domain.model.Currency
import com.currency.exchange.datamodule.domain.model.toCurrency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CurrencyRepository(
    private val dataRepository: IDataRepository,
    private val sharedDataRepository: ISharedDataRepository
) : ICurrencyRepository {

    override suspend fun reload() {
        dataRepository.downloadCurrencies()
    }

    override suspend fun currenciesFlow(reload: Boolean) =
        dataRepository.currenciesFlow(reload).map { it.map { currencyDTO -> currencyDTO.toCurrency() } }

    override suspend fun exceptionsFlow(): Flow<Exception?> =
        dataRepository.currencyExceptionsFlow()

    override fun sellCurrencyFlow(): Flow<Currency?> =
        sharedDataRepository.sellCurrencyFlow()

    override fun buyCurrencyFlow(): Flow<Currency?> =
        sharedDataRepository.buyCurrencyFlow()

    override fun sellCurrency(currency: Currency?) =
        sharedDataRepository.sellCurrency(currency)

    override fun buyCurrency(currency: Currency?) =
        sharedDataRepository.buyCurrency(currency)

}

