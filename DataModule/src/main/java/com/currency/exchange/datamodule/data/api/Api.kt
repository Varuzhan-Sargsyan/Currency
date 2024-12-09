package com.currency.exchange.datamodule.data.api

import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("currencies")
    suspend fun downloadCurrencies() : Response<Map<String, String>>
}