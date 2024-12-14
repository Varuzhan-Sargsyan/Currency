package com.currency.exchange.app.ui.utils

import com.currency.exchange.app.ui.screens.navigation.Screen
import com.currency.exchange.datamodule.data.interfaces.ISharedDataRepository
import com.currency.exchange.datamodule.domain.extensions.BUY_CURRENCY_KEY
import com.currency.exchange.datamodule.domain.extensions.SELL_CURRENCY_KEY
import kotlinx.coroutines.flow.StateFlow


private const val SCREEN_KEY = "screen"
private const val CURRENCY_SCREEN_KEY = "currencyScreen"
fun ISharedDataRepository.screenFlow() =
    this.flow(SCREEN_KEY, Screen.Dashboard) as StateFlow<Screen?>

fun ISharedDataRepository.navigateTo(screen: Screen) =
    this.update(SCREEN_KEY, screen)

fun ISharedDataRepository.navigateBack() {
    val screen = this.value<Screen>(SCREEN_KEY)
    this.update(SCREEN_KEY, screen?.backRoute())
}

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

