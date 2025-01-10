package com.currency.exchange.datamodule.data.repositories

import com.currency.exchange.datamodule.data.interfaces.ICacheDataRepository
import com.currency.exchange.datamodule.domain.model.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Suppress("UNCHECKED_CAST")
class CacheDataRepository : ICacheDataRepository {
    private val sharedDataFlowMap = mutableMapOf<String, MutableStateFlow<Any?>>()

    override fun <T> update(key: String, value: T?) {
        if (!sharedDataFlowMap.containsKey(key))
            sharedDataFlowMap[key] = MutableStateFlow(value)
        else
            sharedDataFlowMap[key]!!.tryEmit(value)
    }

    override fun <T> flow(key: String, defaultValue: T?) : Flow<T?> {
        if (!sharedDataFlowMap.containsKey(key))
            sharedDataFlowMap[key] = MutableStateFlow(defaultValue)
        return sharedDataFlowMap[key] as StateFlow<T?>
    }

    override fun <T> value(key: String) =
        sharedDataFlowMap[key]?.value as T?

}

private const val SELL_CURRENCY_KEY = "sellCurrency"
private const val BUY_CURRENCY_KEY = "buyCurrency"
private const val SCREEN_KEY = "screen"
private const val CURRENCY_SCREEN_KEY = "currencyScreen"

fun ICacheDataRepository.screenFlow() =
    this.flow(SCREEN_KEY, Screen.Dashboard) as StateFlow<Screen?>

fun ICacheDataRepository.navigateTo(screen: Screen) =
    this.update(SCREEN_KEY, screen)

fun ICacheDataRepository.navigateBack() =
    this.update(SCREEN_KEY, this.value<Screen>(SCREEN_KEY)?.backRoute())

private fun ICacheDataRepository.currencyScreen() =
    this.value<String>(CURRENCY_SCREEN_KEY)

private fun ICacheDataRepository.currencyScreen(screen: String) =
    this.update(CURRENCY_SCREEN_KEY, screen)

fun ICacheDataRepository.isSellCurrencyScreen() =
    this.currencyScreen() == SELL_CURRENCY_KEY

fun ICacheDataRepository.isBuyCurrencyScreen() =
    this.currencyScreen() == BUY_CURRENCY_KEY

fun ICacheDataRepository.sellCurrencyScreen() =
    this.currencyScreen(SELL_CURRENCY_KEY)

fun ICacheDataRepository.buyCurrencyScreen() =
    this.currencyScreen(BUY_CURRENCY_KEY)

