package com.currency.exchange.app.ui.screens

import androidx.lifecycle.ViewModel
import com.currency.exchange.app.ui.extensions.runInThread
import com.currency.exchange.datamodule.data.model.entities.Currency
import com.currency.exchange.datamodule.usecase.ICurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyRepository: ICurrency
) : ViewModel() {

    val stateFirstCurrency = MutableStateFlow(Currency.firstDefaultCurrency())
    val stateSecondCurrency = MutableStateFlow(Currency.secondDefaultCurrency())

    val stateFirstValue = MutableStateFlow(0.0)
    val stateSecondValue = MutableStateFlow(0.0)

    init {

        runInThread {
            currencyRepository.download(stateFirstCurrency.value.baseCode)
            currencyRepository.download(stateSecondCurrency.value.baseCode)
        }

        subscribeFirstCurrency()
        subscribeSecondCurrency()
    }

    private var jobFirstCurrency = null as Job?
    private fun subscribeFirstCurrency(currency: Currency? = null) {
        jobFirstCurrency?.cancel()
        jobFirstCurrency = runInThread {
            currencyRepository.subscribeToCurrency(currency?.baseCode ?: "USD").collect { currency ->
                stateFirstCurrency.tryEmit(currency ?: Currency.firstDefaultCurrency())
            }
        }
    }

    fun selectFirstCurrency(currency: Currency) {
        stateFirstCurrency.tryEmit(currency)
        subscribeFirstCurrency(currency)
    }

    private var jobSecondCurrency = null as Job?
    private fun subscribeSecondCurrency(currency: Currency? = null) {
        jobSecondCurrency?.cancel()
        jobSecondCurrency = runInThread {
            currencyRepository.subscribeToCurrency(currency?.baseCode ?: "USD").collect { currency ->
                stateSecondCurrency.tryEmit(currency ?: Currency.secondDefaultCurrency())
            }
        }
    }

    fun selectSecondCurrency(currency: Currency) {
        stateSecondCurrency.tryEmit(currency)
        subscribeSecondCurrency(currency)
    }
}