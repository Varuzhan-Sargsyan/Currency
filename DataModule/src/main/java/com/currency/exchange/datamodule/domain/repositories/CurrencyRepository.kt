package com.currency.exchange.datamodule.domain.repositories

import com.currency.exchange.datamodule.data.interfaces.IDataRepository
import com.currency.exchange.datamodule.domain.interfaces.ICurrencyRepository
import com.currency.exchange.datamodule.domain.model.toCurrency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CurrencyRepository(private val dataRepository: IDataRepository) : ICurrencyRepository {
    override suspend fun currenciesFlow(reload: Boolean) =
        dataRepository.currenciesFlow(reload).map { it.map { currencyDTO -> currencyDTO.toCurrency() } }

    override suspend fun exceptionsFlow(): Flow<Exception?> =
        dataRepository.currencyExceptionsFlow()
}