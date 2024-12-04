package com.currency.exchange.app.ui.screens

import androidx.lifecycle.ViewModel
import com.currency.exchange.datamodule.data.interfaces.IDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
//    private val currencyRepository: ICurrencyRepository,
//    private val rateRepository: IRateRepository,
    private val dataRepository: IDataRepository
) : ViewModel() {

//    val stateFirstCurrency = MutableStateFlow(Currency.firstDefaultCurrency())
//    val stateSecondCurrency = MutableStateFlow(Currency.secondDefaultCurrency())
//
//    private val rate = MutableStateFlow(Rate.default())

//    var currencies: StateFlow<List<Currency>> = callbackFlow {
//        runInThread {
//            currencyRepository.subscribeToCurrencies().collectLatest { currencies ->
//                currencies.find { stateFirstCurrency.value.name == it.name }?.let { stateFirstCurrency.value = it }
//                currencies.find { stateSecondCurrency.value.name == it.name }?.let { stateSecondCurrency.value = it }
//
//                send(currencies)
//            }
//        }
//        awaitClose()
//    }.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.Eagerly,
//        initialValue = emptyList() // Provide an initial value for the StateFlow
//    )

//    init {
//        runInThread {
//            currencyRepository.downloadCurrencies()
//        }
//
//        runInThread {
//            combine(stateFirstCurrency, stateSecondCurrency) { first, second ->
//                Pair(first, second)
//            }.collect {
//                rateRepository.downloadRate(it.first.baseCode, it.second.baseCode)
//                subscribeToRate()
//            }
//        }
//
//        runInThread {
//            rate.collect {
//                secondValue = (firstValue * it.rate).roundToDecimalPlaces()
//            }
//        }
//    }

//    private var jobFirstCurrency = null as Job?
//    private fun subscribeToRate() {
//        jobFirstCurrency?.cancel()
//        jobFirstCurrency = runInThread {
//            val base1 = stateFirstCurrency.value.baseCode
//            val base2 = stateSecondCurrency.value.baseCode
//            rateRepository.subscribeToRate(base1, base2).collectLatest { rate ->
//                this@MainViewModel.rate.value = rate ?: Rate.default()
//            }
//        }
//    }



//    private var _firstValue = MutableStateFlow(1.0)
//    private var _secondValue = MutableStateFlow(1.0)
//
//    var firstValue: Double
//        get() = _firstValue.value
//        set(value) {
//            _firstValue.value = value.roundToDecimalPlaces()
//            _secondValue.value = (_firstValue.value * rate.value.rate).roundToDecimalPlaces()
//        }
//
//    var secondValue: Double
//        get() = _secondValue.value
//        set(value) {
//            _secondValue.value = value.roundToDecimalPlaces()
//            _firstValue.value = (_secondValue.value / rate.value.rate).roundToDecimalPlaces()
//        }
//
//    var stateFirstValue = _firstValue as StateFlow<Double>
//    val stateSecondValue = _secondValue as StateFlow<Double>
}