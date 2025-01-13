package com.currency.exchange.datamodule.data.api

import com.currency.exchange.datamodule.data.model.entities.CountryDTO
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {
    @GET("v3.1/all?fields=name,flags,currencies,cca2")
    suspend fun downloadCountries() : Response<List<CountryDTO>>
}