package com.currency.exchange.datamodule.data.interfaces

import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO
import com.currency.exchange.datamodule.data.model.response.Response
import kotlinx.coroutines.flow.Flow

interface IDataRepository {
    suspend fun downloadCurrencies() : Response
    fun currenciesFlow(reload: Boolean) : Flow<List<CurrencyDTO>>
    suspend fun downloadRates(currencyDTO: CurrencyDTO)
}