package com.currency.exchange.datamodule.domain.repositories

import com.currency.exchange.datamodule.data.interfaces.ICacheDataRepository
import com.currency.exchange.datamodule.data.interfaces.ISharedPrefs
import com.currency.exchange.datamodule.data.interfaces.buyCurrency
import com.currency.exchange.datamodule.data.interfaces.flow
import com.currency.exchange.datamodule.data.interfaces.sellCurrency
import com.currency.exchange.datamodule.data.interfaces.subscribeToBuyCurrency
import com.currency.exchange.datamodule.data.interfaces.subscribeToSellCurrency
import com.currency.exchange.datamodule.data.repositories.buyCurrencyScreen
import com.currency.exchange.datamodule.data.repositories.sellCurrencyScreen
import com.currency.exchange.datamodule.domain.interfaces.ISettingsRepository
import com.currency.exchange.datamodule.domain.model.ApplicationSettings
import com.currency.exchange.datamodule.domain.model.Currency
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class SettingsRepository(
    private val sharedPrefs: ISharedPrefs,
    private val cacheDataRepository: ICacheDataRepository,
) : ISettingsRepository {
    companion object {
        private const val KEY_APPLICATION_SETTINGS = "application_settings"
    }

    init {
        if(!sharedPrefs.contains(KEY_APPLICATION_SETTINGS))
            applicationSettings(ApplicationSettings.default())
    }

    override fun applicationSettings(applicationSettings: ApplicationSettings?) =
        applicationSettings?.let {
            sharedPrefs.save(
                KEY_APPLICATION_SETTINGS,
                applicationSettings
            )
        } ?: sharedPrefs.remove(KEY_APPLICATION_SETTINGS)

    override fun applicationSettings() =
        sharedPrefs.load(KEY_APPLICATION_SETTINGS, ApplicationSettings::class.java)

    override fun appSettingsFlow() : Flow<ApplicationSettings?> =
        sharedPrefs.flow<ApplicationSettings>(KEY_APPLICATION_SETTINGS)

    override fun sellCurrencyFlow(scope: CoroutineScope) : Flow<Currency?> =
        sharedPrefs.subscribeToSellCurrency(scope)

    override fun buyCurrencyFlow(scope: CoroutineScope): Flow<Currency?> =
        sharedPrefs.subscribeToBuyCurrency(scope)

    override fun sellCurrency(currency: Currency?) =
        sharedPrefs.sellCurrency(currency)

    override fun buyCurrency(currency: Currency?) =
        sharedPrefs.buyCurrency(currency)

    override fun sellCurrencyScreen() =
        cacheDataRepository.sellCurrencyScreen()

    override fun buyCurrencyScreen() =
        cacheDataRepository.buyCurrencyScreen()
}