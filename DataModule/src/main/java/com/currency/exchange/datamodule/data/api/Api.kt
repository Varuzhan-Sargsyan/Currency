package com.currency.exchange.datamodule.data.api

import com.currency.exchange.datamodule.data.model.entities.CurrencyAndRates
import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("currencies")
    suspend fun downloadCurrencies() : Response<List<CurrencyDTO>>

//    @GET("latest?")
//    suspend fun downloadCurrencyRates(
//        @Query("from") from: String,
//    ) : Response<CurrencyAndRates>
}