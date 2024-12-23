package com.currency.exchange.app.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currency.exchange.app.ui.extensions.runInThread
import com.currency.exchange.datamodule.domain.model.Screen
import com.currency.exchange.datamodule.data.interfaces.ISharedDataRepository
import com.currency.exchange.datamodule.data.repositories.navigateTo
import com.currency.exchange.datamodule.domain.interfaces.ICurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val currencyRepository: ICurrencyRepository,
    private val sharedDataRepository: ISharedDataRepository,
) : ViewModel() {

    init {
        runInThread {
            currencyRepository.reload()
        }
    }

    private val sellSum = MutableStateFlow(0f)
    private val buySum = MutableStateFlow(0f)

    fun flowSellCurrency() = currencyRepository.sellCurrencyFlow(viewModelScope)
    fun flowBuyCurrency() = currencyRepository.buyCurrencyFlow(viewModelScope)

    fun selectSellCurrency() {
        currencyRepository.sellCurrencyScreen()
        sharedDataRepository.navigateTo(Screen.Currencies)
    }

    fun selectBuyCurrency() {
        currencyRepository.buyCurrencyScreen()
        sharedDataRepository.navigateTo(Screen.Currencies)
    }

    fun sellSumFlow() = sellSum as StateFlow<Float>
    fun buySumFlow() = buySum as StateFlow<Float>

    fun setSellSum(value: Float) = sellSum.tryEmit(value)
    fun setBuySum(value: Float) = buySum.tryEmit(value)

}