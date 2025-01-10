package com.currency.exchange.app.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currency.exchange.datamodule.domain.model.Screen
import com.currency.exchange.datamodule.data.interfaces.ICacheDataRepository
import com.currency.exchange.datamodule.data.repositories.navigateTo
import com.currency.exchange.datamodule.domain.interfaces.ISettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val sharedDataRepository: ICacheDataRepository,
    private val settingsRepository: ISettingsRepository,
) : ViewModel() {

    private val sellSum = MutableStateFlow(0f)
    private val buySum = MutableStateFlow(0f)

    fun flowSellCurrency() = settingsRepository.sellCurrencyFlow(viewModelScope)
    fun flowBuyCurrency() = settingsRepository.buyCurrencyFlow(viewModelScope)

    fun selectSellCurrency() {
        settingsRepository.sellCurrencyScreen()
        sharedDataRepository.navigateTo(Screen.Currencies)
    }

    fun selectBuyCurrency() {
        settingsRepository.buyCurrencyScreen()
        sharedDataRepository.navigateTo(Screen.Currencies)
    }

    fun sellSumFlow() = sellSum as StateFlow<Float>
    fun buySumFlow() = buySum as StateFlow<Float>

    fun setSellSum(value: Float) = sellSum.tryEmit(value)
    fun setBuySum(value: Float) = buySum.tryEmit(value)

}