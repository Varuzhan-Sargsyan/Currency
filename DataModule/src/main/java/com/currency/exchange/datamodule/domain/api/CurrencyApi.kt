package com.currency.exchange.datamodule.domain.api

import com.currency.exchange.datamodule.data.model.responses.Currencies
import com.currency.exchange.datamodule.data.model.entities.Currency
import com.currency.exchange.datamodule.data.model.entities.Rate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
    @GET("currencies")
    suspend fun downloadCurrencies() : Response<Currencies>

    @GET("latest?from={from}&to={to}")
    suspend fun downloadRate(
        @Query("from") from: String,
        @Query("to") to: String
    ) : Response<Rate>
}