package com.currency.exchange.datamodule.domain.repositories

import com.currency.exchange.datamodule.data.datasource.AppDatabase
import com.currency.exchange.datamodule.data.model.responses.Response
import com.currency.exchange.datamodule.domain.api.CurrencyApi
import com.currency.exchange.datamodule.usecase.ICurrencyRepository

class CurrencyRepository(
    private val currencyApi: CurrencyApi,
    private val appDatabase: AppDatabase
) : ICurrencyRepository {
    override suspend fun downloadCurrencies() =
        try {
            val result = currencyApi.downloadCurrencies()
            when(result.isSuccessful) {
                true -> {
                    val currencies = result.body()!!
                    appDatabase.daoCurrency.insert(currencies.all)

                    Response("Success", 200)
                }
                false -> {
                    Response(result.message(), result.code())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Response(e.message, 500)
        }

    override suspend fun subscribeToCurrencies() =
        appDatabase.daoCurrency.currenciesFlow()
}