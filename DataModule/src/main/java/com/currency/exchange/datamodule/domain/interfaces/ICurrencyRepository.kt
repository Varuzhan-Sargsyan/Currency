package com.currency.exchange.datamodule.domain.interfaces

import com.currency.exchange.datamodule.domain.model.Currency
import kotlinx.coroutines.flow.Flow

interface ICurrencyRepository {
    suspend fun currenciesFlow() : Flow<List<Currency>>
}