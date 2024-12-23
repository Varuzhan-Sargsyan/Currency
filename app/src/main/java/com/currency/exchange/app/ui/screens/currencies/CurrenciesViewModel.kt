package com.currency.exchange.app.ui.screens.currencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currency.exchange.app.ui.extensions.runInThread
import com.currency.exchange.datamodule.data.interfaces.ISharedDataRepository
import com.currency.exchange.datamodule.data.repositories.isBuyCurrencyScreen
import com.currency.exchange.datamodule.data.repositories.isSellCurrencyScreen
import com.currency.exchange.datamodule.data.repositories.navigateBack
import com.currency.exchange.datamodule.domain.interfaces.ICurrencyRepository
import com.currency.exchange.datamodule.domain.model.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val currencyRepository: ICurrencyRepository,
    private val sharedDataRepository: ISharedDataRepository
) : ViewModel() {

    private var jobCurrencies = null as Job?

    fun subscribeToCurrencies() = callbackFlow {
        jobCurrencies?.cancel()
        jobCurrencies = runInThread {
            currencyRepository.currenciesFlow(false).collect {
                send(it)
            }
        }
        awaitClose()
    }.shareIn<List<Currency>>(viewModelScope, SharingStarted.Eagerly, 1)
        .buffer(Channel.CONFLATED).cancellable()

    fun flowExceptions(): Flow<Exception?> = channelFlow {
        currencyRepository.exceptionsFlow().collect {
            send(it)
        }
    }

    fun reload() {
        runInThread { currencyRepository.reload() }
    }

    fun select(currency: Currency) {
        when {
            sharedDataRepository.isSellCurrencyScreen() -> currencyRepository.sellCurrency(currency)
            sharedDataRepository.isBuyCurrencyScreen() -> currencyRepository.buyCurrency(currency)
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