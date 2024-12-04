package com.currency.exchange.datamodule.data.interfaces

import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO

interface IDataRepository {
    suspend fun downloadCurrencies()
    suspend fun downloadRates(currencyDTO: CurrencyDTO)
}