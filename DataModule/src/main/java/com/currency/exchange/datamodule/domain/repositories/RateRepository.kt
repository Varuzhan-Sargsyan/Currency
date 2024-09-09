package com.currency.exchange.datamodule.domain.repositories

import com.currency.exchange.datamodule.data.datasource.AppDatabase
import com.currency.exchange.datamodule.data.model.responses.Response
import com.currency.exchange.datamodule.domain.api.CurrencyApi
import com.currency.exchange.datamodule.usecase.IRateRepository

class RateRepository(
    private val currencyApi: CurrencyApi,
    private val appDatabase: AppDatabase
) : IRateRepository {
    override suspend fun downloadRate(baseFrom: String, baseTo: String) =
        try {
            val result = currencyApi.downloadRate(baseFrom, baseTo)
            when(result.isSuccessful) {
                true -> {
                    val rate = result.body()!!
                    appDatabase.daoRate.insert(rate)

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

    override suspend fun subscribeToRate(baseFrom: String, baseTo: String) =
        appDatabase.daoRate.subscribe(baseFrom, baseTo)
}