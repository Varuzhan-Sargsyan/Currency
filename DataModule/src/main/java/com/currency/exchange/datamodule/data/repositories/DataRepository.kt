package com.currency.exchange.datamodule.data.repositories

import com.currency.exchange.datamodule.data.api.CountryApi
import com.currency.exchange.datamodule.data.api.CurrencyApi
import com.currency.exchange.datamodule.data.database.AppDatabase
import com.currency.exchange.datamodule.data.interfaces.IDataRepository
import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO
import com.currency.exchange.datamodule.data.model.entities.CountryDTO
import com.currency.exchange.datamodule.data.model.entities.toCurrencyDTOList
import com.currency.exchange.datamodule.data.model.response.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DataRepository(
    private val appDatabase: AppDatabase,
    private val currencyApi: CurrencyApi,
    private val countryApi: CountryApi,
    private val coroutineScope: CoroutineScope
) : IDataRepository {

    private val currencyExceptionState = MutableStateFlow<Exception?>(null)
    private val dashboardExceptionState = MutableStateFlow<Exception?>(null)
    private val currenciesLocalInfo = mutableListOf<CountryDTO>()

    override suspend fun downloadCurrencyInformation() =
        try {
            val response = currencyApi.downloadCurrencies()
            if (response.isSuccessful) {
                val currencies = response.body()?.toCurrencyDTOList() ?: emptyList<CurrencyDTO>()
                saveCurrencies(currencies)
                Response.Success(currencies)
            } else
                Response.Error(response.message())
        } catch (exception: Exception) {
            Response.Error(exception.message ?: "Unknown error")
        }.apply {
            if (isError())
                currencyExceptionState.value = Exception(toString())
            else
                currencyExceptionState.value = null
        }

    override suspend fun downloadCountryInformation() =
        try {
            val response = countryApi.downloadCountries()
            if (response.isSuccessful) {
                val countries: List<CountryDTO> = response.body() ?: emptyList<CountryDTO>()
                saveCountries(countries)
                Response.Success(countries)
            } else
                Response.Error(response.message())
        } catch (exception: Exception) {
            Response.Error(exception.message ?: "Unknown error")
        }.apply {
            if (isError())
                currencyExceptionState.value = Exception(toString())
            else
                currencyExceptionState.value = null
        }

//    override suspend fun downloadCurrencyFlags() : Response {
//        // Read the JSON file from res/raw
//        val inputStream = context.resources.openRawResource(R.raw.currencies_with_flags)
//        val json = inputStream.bufferedReader().use { it.readText() }
//
//        // Parse JSON to a list of CurrencyInfo objects
//        val currencies = JsonHelper.fromJsonList(json, CountryDTO::class.java)
//        if (currenciesLocalInfo.isNotEmpty())
//            currenciesLocalInfo.addAll(currencies)
//        return Response.Success(currencies)
//    }

    private suspend fun saveCurrencies(currencies: List<CurrencyDTO>) {
        try {
            appDatabase.daoCurrency.insert(currencies)
        } catch (exception: Exception) {
            Response.Error(exception.message ?: "Unknown error")
        }
    }

    private suspend fun saveCountries(countries: List<CountryDTO>) {
        try {
            appDatabase.daoCountry.insert(countries)
        } catch (exception: Exception) {
            Response.Error(exception.message ?: "Unknown error")
        }
    }

    override suspend fun currenciesDTOFlow(reload: Boolean) : Flow<List<CurrencyDTO>> {
        if (reload) {
            coroutineScope.launch {
                downloadCurrencyInformation()
            }
        }
        return appDatabase.daoCurrency.currenciesFlow()
    }

    override suspend fun countriesDTOFlow(reload: Boolean) : Flow<List<CountryDTO>> {
        if (reload) {
            coroutineScope.launch {
                downloadCountryInformation()
            }
        }
        return appDatabase.daoCountry.countriesFlow()
    }

    override suspend fun downloadRates(currencyDTO: CurrencyDTO) {
//        val response = api.downloadCurrencyRates(currencyDTO.code)
//        if (response.isSuccessful) {
//            response.body()?.toRates()?.let { rate ->
//                appDatabase.daoRate.insert(rate)
//            }
//        }
    }

    override suspend fun currencyExceptionsFlow(): Flow<Exception?> = currencyExceptionState.asStateFlow()
    override suspend fun dashboardExceptionsFlow(): Flow<Exception?> = dashboardExceptionState.asStateFlow()

    override fun flags(): List<CountryDTO> = currenciesLocalInfo
    override fun currencyLocalInfo(code: String) : CountryDTO? = null//flags().firstOrNull { it.code == code }
}