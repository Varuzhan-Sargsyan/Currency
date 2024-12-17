package com.currency.exchange.datamodule.data.repositories

import com.currency.exchange.datamodule.data.interfaces.ISharedDataRepository
import com.currency.exchange.datamodule.domain.model.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Suppress("UNCHECKED_CAST")
class SharedDataRepository : ISharedDataRepository {
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

fun ISharedDataRepository.screenFlow() =
    this.flow(SCREEN_KEY, Screen.Dashboard) as StateFlow<Screen?>

fun ISharedDataRepository.navigateTo(screen: Screen) =
    this.update(SCREEN_KEY, screen)

fun ISharedDataRepository.navigateBack() =
    this.update(SCREEN_KEY, this.value<Screen>(SCREEN_KEY)?.backRoute())

private fun ISharedDataRepository.currencyScreen() =
    this.value<String>(CURRENCY_SCREEN_KEY)

private fun ISharedDataRepository.currencyScreen(screen: String) =
    this.update(CURRENCY_SCREEN_KEY, screen)

fun ISharedDataRepository.isSellCurrencyScreen() =
    this.currencyScreen() == SELL_CURRENCY_KEY

fun ISharedDataRepository.isBuyCurrencyScreen() =
    this.currencyScreen() == BUY_CURRENCY_KEY

fun ISharedDataRepository.sellCurrencyScreen() =
    this.currencyScreen(SELL_CURRENCY_KEY)

fun ISharedDataRepository.buyCurrencyScreen() =
    this.currencyScreen(BUY_CURRENCY_KEY)

