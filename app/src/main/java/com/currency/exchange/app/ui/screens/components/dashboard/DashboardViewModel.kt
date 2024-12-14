package com.currency.exchange.app.ui.screens.components.dashboard

import androidx.lifecycle.ViewModel
import com.currency.exchange.app.ui.screens.navigation.Screen
import com.currency.exchange.app.ui.utils.buyCurrencyScreen
import com.currency.exchange.app.ui.utils.navigateTo
import com.currency.exchange.app.ui.utils.sellCurrencyScreen
import com.currency.exchange.datamodule.data.interfaces.ISharedDataRepository
import com.currency.exchange.datamodule.domain.interfaces.ICurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val currencyRepository: ICurrencyRepository,
    private val sharedDataRepository: ISharedDataRepository
) : ViewModel() {

    fun flowSellCurrency() = currencyRepository.sellCurrencyFlow()
    fun flowBuyCurrency() = currencyRepository.buyCurrencyFlow()

    fun selectSellCurrency() {
        sharedDataRepository.sellCurrencyScreen()
        sharedDataRepository.navigateTo(Screen.Currencies)
    }

    fun selectBuyCurrency() {
        sharedDataRepository.buyCurrencyScreen()
        sharedDataRepository.navigateTo(Screen.Currencies)
    }
}