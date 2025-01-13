package com.currency.exchange.app.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currency.exchange.datamodule.domain.model.Screen
import com.currency.exchange.datamodule.data.interfaces.ICacheDataRepository
import com.currency.exchange.datamodule.data.repositories.navigateTo
import com.currency.exchange.datamodule.domain.interfaces.ISettingsRepository
import com.currency.exchange.datamodule.domain.interfaces.IRateFlow
import com.currency.exchange.datamodule.domain.model.Currency
import com.currency.exchange.datamodule.domain.model.Rate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val sharedDataRepository: ICacheDataRepository,
    private val settingsRepository: ISettingsRepository,
    private val rateFlow: IRateFlow
) : ViewModel() {

    private val sellCurrency: Flow<Currency?> = settingsRepository.sellCurrencyFlow(viewModelScope)
    private val buyCurrency: Flow<Currency?> = settingsRepository.buyCurrencyFlow(viewModelScope)

    private val sellSum = MutableStateFlow(1.0f)
    private val buySum = combine(
        sellCurrency,
        buyCurrency,
        sellSum.asStateFlow(),
        flowRate()
    ) { sellCurrency: Currency?, buyCurrency: Currency?, sellSum: Float, rate: Rate? ->
        if (sellCurrency == null || buyCurrency == null || rate == null) {
            1.0f // Default value if any input is null
        } else {
            rate.rate * sellSum // Computed value
        }
    }

//    fun flowRate() : Flow<Rate?> =
//        sellCurrency.combine(buyCurrency) { sellCurrency: Currency?, buyCurrency: Currency? ->
//            if (sellCurrency == null || buyCurrency == null)
//                null
//            else
//                rateFlow.invoke(sellCurrency, buyCurrency)
//        }
//    fun flowRate(): Flow<Rate?> = flow<Rate?> {
//        sellCurrency.combine<Currency?, Currency?, Rate?>(buyCurrency) { sellCurrency: Currency?, buyCurrency: Currency? ->
//            if (sellCurrency == null || buyCurrency == null) {
//                emit(null as Rate?) // Emit null if inputs are invalid
//            } else {
//                rateFlow.invoke(sellCurrency, buyCurrency).collectLatest {
//                    emit(it)
//                }
//            }
//        }.flatMapLatest { emit(it) }
//    }
    @OptIn(ExperimentalCoroutinesApi::class)
    fun flowRate(): Flow<Rate?> =
        sellCurrency.combine(buyCurrency) { sellCurrency: Currency?, buyCurrency: Currency? ->
            if (sellCurrency == null || buyCurrency == null) {
                null // Return null if inputs are invalid
            } else {
                rateFlow.invoke(sellCurrency, buyCurrency) // Return the result of `rateFlow`
            }
        }.flatMapLatest { rateFlowResult ->
            rateFlowResult ?: flowOf(null) // If null, emit null as a fallback
        }

    fun flowSellCurrency() = sellCurrency
    fun flowBuyCurrency() = buyCurrency

    fun selectSellCurrency() {
        settingsRepository.sellCurrencyScreen()
        sharedDataRepository.navigateTo(Screen.Currencies)
    }

    fun selectBuyCurrency() {
        settingsRepository.buyCurrencyScreen()
        sharedDataRepository.navigateTo(Screen.Currencies)
    }

    fun sellSumFlow() = sellSum as StateFlow<Float>
    fun buySumFlow() = buySum

    fun setSellSum(value: Float) = sellSum.tryEmit(value)
//    fun setBuySum(value: Float) = buySum.tryEmit(value)

}