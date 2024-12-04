package com.currency.exchange.datamodule.data.repositories

import com.currency.exchange.datamodule.data.api.Api
import com.currency.exchange.datamodule.data.database.AppDatabase
import com.currency.exchange.datamodule.data.interfaces.IDataRepository
import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO

class DataRepository(
    private val appDatabase: AppDatabase,
    private val api: Api
) : IDataRepository {

    override suspend fun downloadCurrencies() {
        val response = api.downloadCurrencies()
        if (response.isSuccessful) {
            response.body()?.toList()?.let { currencies ->
                appDatabase.daoCurrency.insert(currencies)
            }
        }
    }

    override suspend fun downloadRates(currencyDTO: CurrencyDTO) {
//        val response = api.downloadCurrencyRates(currencyDTO.code)
//        if (response.isSuccessful) {
//            response.body()?.toRates()?.let { rate ->
//                appDatabase.daoRate.insert(rate)
//            }
//        }
    }

}