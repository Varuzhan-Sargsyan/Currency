package com.currency.exchange.datamodule.data.interfaces

import com.currency.exchange.datamodule.data.model.response.Response

interface IRemoteDataRepository {
    suspend fun downloadCurrencyInformation() : Response
    suspend fun downloadRate(code: String, date: String) : Response
    suspend fun downloadRates(date: String) : List<Response>
    suspend fun downloadCountryInformation() : Response
}