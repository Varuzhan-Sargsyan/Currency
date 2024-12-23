package com.currency.exchange.datamodule.data.api

import com.currency.exchange.datamodule.data.model.entities.RateDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
    @GET("currencies")
    suspend fun downloadCurrencies() : Response<Map<String, String>>

    @GET("latest?")
    suspend fun downloadRate(
        @Query("from") code: String,
        @Query("date") date: String
    ) : Response<RateDTO>
}