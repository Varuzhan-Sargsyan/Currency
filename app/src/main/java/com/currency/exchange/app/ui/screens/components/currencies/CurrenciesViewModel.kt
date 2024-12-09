package com.currency.exchange.app.ui.screens.components.currencies

import androidx.lifecycle.ViewModel
import com.currency.exchange.datamodule.domain.interfaces.ICurrencyRepository
import com.currency.exchange.datamodule.domain.model.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val currencyRepository: ICurrencyRepository
) : ViewModel() {

    fun flowCurrencies(): Flow<List<Currency>> = channelFlow {
        currencyRepository.currenciesFlow(true).collect {
            send(it)
        }
    }

}