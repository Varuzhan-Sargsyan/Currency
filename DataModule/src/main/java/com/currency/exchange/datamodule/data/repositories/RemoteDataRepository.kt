package com.currency.exchange.datamodule.data.repositories

import com.currency.exchange.datamodule.data.api.CountryApi
import com.currency.exchange.datamodule.data.api.CurrencyApi
import com.currency.exchange.datamodule.data.interfaces.ILocalDataRepository
import com.currency.exchange.datamodule.data.interfaces.IRemoteDataRepository
import com.currency.exchange.datamodule.data.model.entities.CountryDTO
import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO
import com.currency.exchange.datamodule.data.model.entities.toCurrencyDTOList
import com.currency.exchange.datamodule.data.model.response.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

class RemoteDataRepository(
    private val localDataRepository: ILocalDataRepository,
    private val currencyApi: CurrencyApi,
    private val countryApi: CountryApi,
    private val coroutineScope: CoroutineScope
) : IRemoteDataRepository {

    override suspend fun downloadRates(date: String) : List<Response> =
        try {
            val allAsyncRates = localDataRepository.currenciesDTO().map { currency ->
                coroutineScope.async (Dispatchers.IO) {
                    downloadRate(code = currency.code, date = date)
                }
            }
            allAsyncRates.awaitAll()
        } catch (exception: Exception) {
            mutableListOf<Response>() + Response.Error(exception.message ?: "Unable download rates!")
        }

    override suspend fun downloadRate(code: String, date: String) : Response =
        try {
            val response = currencyApi.downloadRate(code = code, date = date )
            if (response.isSuccessful) {
                response.body()?.let { rateDTO ->
                    rateDTO.date = date
                    Response.Success(rateDTO)
                } ?: Response.Error("Unknown error")
            } else
                Response.Error(response.message())
        } catch (exception: Exception) {
            Response.Error(exception.message ?: "Unable download rate $code!")
        }

    override suspend fun downloadCurrencyInformation() =
        try {
            val response = currencyApi.downloadCurrencies()
            if (response.isSuccessful)
                Response.Success(response.body()?.toCurrencyDTOList() ?: emptyList<CurrencyDTO>())
            else
                Response.Error(response.message())
        } catch (exception: Exception) {
            Response.Error(exception.message ?: "Unknown error")
        }

    override suspend fun downloadCountryInformation() =
        try {
            val response = countryApi.downloadCountries()
            if (response.isSuccessful) {
                val countries: List<CountryDTO> = response.body() ?: emptyList<CountryDTO>()
                Response.Success(countries)
            } else
                Response.Error(response.message())
        } catch (exception: Exception) {
            Response.Error(exception.message ?: "Unknown error")
        }
}