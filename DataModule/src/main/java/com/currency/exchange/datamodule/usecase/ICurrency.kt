package com.currency.exchange.datamodule.usecase

import com.currency.exchange.datamodule.data.model.entities.Currency
import com.currency.exchange.datamodule.data.model.responses.Response
import kotlinx.coroutines.flow.Flow

interface ICurrency {
    suspend fun downloadCurrencies() : Response
    suspend fun subscribeToCurrencies() : Flow<List<Currency>>
}