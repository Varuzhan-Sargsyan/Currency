package com.currency.exchange.datamodule.domain.interfaces

import com.currency.exchange.datamodule.domain.model.ApplicationSettings
import com.currency.exchange.datamodule.domain.model.Currency
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface ISettingsRepository {
    fun applicationSettings(applicationSettings: ApplicationSettings?)
    fun applicationSettings(): ApplicationSettings?
    fun appSettingsFlow(): Flow<ApplicationSettings?>

    fun sellCurrencyFlow(scope: CoroutineScope) : Flow<Currency?>
    fun buyCurrencyFlow(scope: CoroutineScope) : Flow<Currency?>

    fun sellCurrency(currency: Currency?)
    fun buyCurrency(currency: Currency?)

    fun sellCurrencyScreen()
    fun buyCurrencyScreen()
}