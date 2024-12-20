package com.currency.exchange.datamodule.domain.interfaces

import com.currency.exchange.datamodule.domain.model.Currency
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface ICurrencyRepository {
    suspend fun reload()
    suspend fun currenciesFlow(reload: Boolean) : Flow<List<Currency>>

    suspend fun exceptionsFlow() : Flow<Exception?>

    fun sellCurrencyFlow(scope: CoroutineScope) : Flow<Currency?>
    fun buyCurrencyFlow(scope: CoroutineScope) : Flow<Currency?>

    fun sellCurrency(currency: Currency?)
    fun buyCurrency(currency: Currency?)

    fun sellCurrencyScreen()
    fun buyCurrencyScreen()
}