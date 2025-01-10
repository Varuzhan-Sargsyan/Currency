package com.currency.exchange.app.ui.screens.currencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currency.exchange.app.ui.extensions.runInThread
import com.currency.exchange.datamodule.data.interfaces.ICacheDataRepository
import com.currency.exchange.datamodule.data.repositories.isBuyCurrencyScreen
import com.currency.exchange.datamodule.data.repositories.isSellCurrencyScreen
import com.currency.exchange.datamodule.data.repositories.navigateBack
import com.currency.exchange.datamodule.domain.interfaces.ICurrencyRepository
import com.currency.exchange.datamodule.domain.interfaces.ISettingsRepository
import com.currency.exchange.datamodule.domain.model.Currency
import com.currency.exchange.datamodule.domain.repositories.ReloadDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val currencyRepository: ICurrencyRepository,
    private val sharedDataRepository: ICacheDataRepository,
    private val reloadDataUseCase: ReloadDataUseCase,
    private val settingsRepository: ISettingsRepository
) : ViewModel() {

    private var jobCurrencies = null as Job?
    private var jobReload = null as Job?

    private val stateRefreshing = MutableStateFlow(false)
    val isRefreshing = stateRefreshing.asStateFlow()

    fun subscribeToCurrencies() = callbackFlow {
        jobCurrencies?.cancel()
        jobCurrencies = runInThread {
            currencyRepository.currenciesFlow().collect {
                send(it)
            }
        }
        awaitClose()
    }.shareIn<List<Currency>>(viewModelScope, SharingStarted.Eagerly, 1)
        .buffer(Channel.CONFLATED).cancellable()

    private val errorFlow = MutableStateFlow(null as Exception?)
    fun flowExceptions() = errorFlow.asStateFlow()

    fun swipeToRefresh() {
        stateRefreshing.value = true

        jobReload?.cancel()
        jobReload = runInThread {
            reloadDataUseCase
                .invoke()
                .catch { }
                .collect {
                    stateRefreshing.value = it.isLoading()
                    it.message()?.let { errorFlow.value = Exception(it) }
                }
        }
    }

    fun select(currency: Currency) {
        when {
            sharedDataRepository.isSellCurrencyScreen() -> settingsRepository.sellCurrency(currency)
            sharedDataRepository.isBuyCurrencyScreen() -> settingsRepository.buyCurrency(currency)
        }
        navigateBack()
    }

    fun navigateBack() {
        sharedDataRepository.navigateBack()
    }

    override fun onCleared() {
        super.onCleared()
        jobCurrencies?.cancel()
    }
}