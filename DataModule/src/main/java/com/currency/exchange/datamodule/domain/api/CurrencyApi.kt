package com.currency.exchange.datamodule.domain.api

import com.currency.exchange.datamodule.BuildConfig
import com.currency.exchange.datamodule.data.model.entities.Currency
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApi {
    @GET("v6/${BuildConfig.SECRET_KEY}/latest/{base_code}")
    suspend fun downloadCurrency(
        @Path("base_code") baseCode: String,
    ) : Response<Currency>
}