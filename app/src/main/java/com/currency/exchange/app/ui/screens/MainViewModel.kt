package com.currency.exchange.app.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currency.exchange.app.ui.extensions.runInThread
import com.currency.exchange.datamodule.data.model.entities.Currency
import com.currency.exchange.datamodule.data.model.entities.Rate
import com.currency.exchange.datamodule.usecase.ICurrencyRepository
import com.currency.exchange.datamodule.usecase.IRateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyRepository: ICurrencyRepository,
    private val rateRepository: IRateRepository
) : ViewModel() {

    val stateFirstCurrency = MutableStateFlow(Currency.firstDefaultCurrency())
    val stateSecondCurrency = MutableStateFlow(Currency.secondDefaultCurrency())

    val stateFirstValue = MutableStateFlow(1.0)
    val stateSecondValue = MutableStateFlow(1.0)

    private val rate = MutableStateFlow(Rate.default())

    var currencies: StateFlow<List<Currency>> = callbackFlow {
        runInThread {
            currencyRepository.subscribeToCurrencies().collectLatest { currencies ->
                currencies.find { stateFirstCurrency.value.name == it.name }?.let { stateFirstCurrency.value = it }
                currencies.find { stateSecondCurrency.value.name == it.name }?.let { stateSecondCurrency.value = it }

                send(currencies)
            }
        }
        awaitClose()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList() // Provide an initial value for the StateFlow
    )

    init {
        runInThread {
            currencyRepository.downloadCurrencies()
        }

        runInThread {
            merge(stateFirstValue, stateSecondValue).collect {
                subscribeToRate()
            }
        }
    }

    private var jobFirstCurrency = null as Job?
    private fun subscribeToRate() {
        jobFirstCurrency?.cancel()
        jobFirstCurrency = runInThread {
            val base1 = stateFirstCurrency.value.baseCode
            val base2 = stateSecondCurrency.value.baseCode
            rateRepository.subscribeToRate(base1, base2).collectLatest { rate ->
                this@MainViewModel.rate.value = rate ?: Rate.default()
            }
        }
    }
}