package com.currency.exchange.datamodule.data.repositories

import android.util.Log
import com.currency.exchange.datamodule.data.api.CountryApi
import com.currency.exchange.datamodule.data.api.CurrencyApi
import com.currency.exchange.datamodule.data.database.AppDatabase
import com.currency.exchange.datamodule.data.interfaces.IDataRepository
import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO
import com.currency.exchange.datamodule.data.model.entities.CountryDTO
import com.currency.exchange.datamodule.data.model.entities.RateDTO
import com.currency.exchange.datamodule.data.model.entities.toCurrencyDTOList
import com.currency.exchange.datamodule.data.model.response.Response
import com.currency.exchange.datamodule.utils.Const
import com.currency.exchange.datamodule.utils.toCurrencyDateString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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

    companion object {
        private const val TAG = "DataRepository"
    }

    private val currencyExceptionState = MutableStateFlow<Exception?>(null)
    private val dashboardExceptionState = MutableStateFlow<Exception?>(null)
    private val currenciesLocalInfo = mutableListOf<CountryDTO>()

    override suspend fun downloadCurrencyInformation() =
        try {
            val response = currencyApi.downloadCurrencies()
            if (response.isSuccessful) {
                val currencies = response.body()?.toCurrencyDTOList() ?: emptyList<CurrencyDTO>()
                if (currencies.isNotEmpty()) {
                    downloadRates(Const.now().toCurrencyDateString())
                }
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

    override suspend fun downloadRate(code: String, date: String) =
        try {
            val response = currencyApi.downloadRate(code = code, date = date )
            if (response.isSuccessful) {
                response.body()?.let { rateDTO ->
                    rateDTO.date = date
                    Log.d(TAG, "Downloaded code=$code, date=$date, data = $rateDTO")
                    saveRate(rateDTO)
                    Response.Success(rateDTO)
                } ?: Response.Error("Unknown error")
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

    override suspend fun downloadRates(date: String) {
        try {
            appDatabase.daoCurrency.currencies().map { currency ->
                coroutineScope.launch(Dispatchers.IO) {
                    downloadRate(code = currency.code, date = date)
                }
            }
        } catch (exception: Exception) {
            currencyExceptionState.value = exception
        }
    }

    private fun saveRate(rateDTO: RateDTO) {
        try {
            appDatabase.daoRate.insert(rateDTO)
        } catch (exception: Exception) {
            Response.Error(exception.message ?: "Unknown error")
        }
    }

    override suspend fun currencyExceptionsFlow(): Flow<Exception?> = currencyExceptionState.asStateFlow()
    override suspend fun dashboardExceptionsFlow(): Flow<Exception?> = dashboardExceptionState.asStateFlow()

    override fun flags(): List<CountryDTO> = currenciesLocalInfo
    override fun currencyLocalInfo(code: String) : CountryDTO? = null//flags().firstOrNull { it.code == code }
}