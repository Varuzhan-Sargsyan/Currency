package com.currency.exchange.app.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currency.exchange.app.ui.extensions.runInThread
import com.currency.exchange.datamodule.data.model.entities.Currency
import com.currency.exchange.datamodule.usecase.ICurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyRepository: ICurrency
) : ViewModel() {

    val stateFirstCurrency = MutableStateFlow(Currency.firstDefaultCurrency())
    val stateSecondCurrency = MutableStateFlow(Currency.secondDefaultCurrency())

    val stateFirstValue = MutableStateFlow(0.0)
    val stateSecondValue = MutableStateFlow(0.0)

    var currencies: StateFlow<List<Currency>> = callbackFlow {
        runInThread {
            currencyRepository.subscribeToCurrencies().collectLatest {
                send(it)
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
            currencyRepository
        }
    }

}