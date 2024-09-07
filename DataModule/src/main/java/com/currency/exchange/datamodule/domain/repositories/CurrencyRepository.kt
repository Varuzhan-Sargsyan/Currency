package com.currency.exchange.datamodule.domain.repositories

import com.currency.exchange.datamodule.data.datasource.AppDatabase
import com.currency.exchange.datamodule.data.model.responses.Response
import com.currency.exchange.datamodule.domain.api.CurrencyApi
import com.currency.exchange.datamodule.usecase.ICurrency
import kotlinx.coroutines.flow.flow

class CurrencyRepository(
    private val currencyApi: CurrencyApi,
    private val appDatabase: AppDatabase
) : ICurrency {
    override suspend fun download(baseCode: String) =
        try {
            val result = currencyApi.downloadCurrency(baseCode)
            when(result.isSuccessful) {
                true -> {
                    val currency = result.body()!!
                    val idCurrency = appDatabase.daoCurrency.insert(currency)
                    currency.apply {
                        id = idCurrency
                        conversionRates.forEach {
                            it.currencyId = idCurrency
                            appDatabase.daoRate.insert(it)
                        }
                    }

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

    override suspend fun subscribeToCurrency(baseCode: String) =
        flow {
            appDatabase.daoCurrency.currencyFlowByBase(baseCode).collect { currency ->
                currency?.let {
                    it.conversionRates = appDatabase.daoRate.ratesByCurrencyId(it.id)
                }
                emit(currency)
            }
        }
}