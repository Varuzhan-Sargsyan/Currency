package com.currency.exchange.datamodule.domain.interfaces

import com.currency.exchange.datamodule.domain.model.Currency
import kotlinx.coroutines.flow.Flow

interface ICurrencyRepository {
    suspend fun reload()
    suspend fun currenciesFlow(reload: Boolean) : Flow<List<Currency>>

    suspend fun exceptionsFlow() : Flow<Exception?>

    fun sellCurrencyFlow() : Flow<Currency?>
    fun buyCurrencyFlow() : Flow<Currency?>

    fun sellCurrency(currency: Currency?)
    fun buyCurrency(currency: Currency?)

}