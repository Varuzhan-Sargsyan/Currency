package com.currency.exchange.datamodule.data.repositories

import android.util.Log
import com.currency.exchange.datamodule.data.api.CountryApi
import com.currency.exchange.datamodule.data.api.CurrencyApi
import com.currency.exchange.datamodule.data.database.AppDatabase
import com.currency.exchange.datamodule.data.interfaces.ILocalDataRepository
import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO
import com.currency.exchange.datamodule.data.model.entities.CountryDTO
import com.currency.exchange.datamodule.data.model.entities.RateDTO
import com.currency.exchange.datamodule.data.model.entities.toCurrencyDTOList
import com.currency.exchange.datamodule.data.model.response.Response
import com.currency.exchange.datamodule.utils.Const
import com.currency.exchange.datamodule.utils.toCurrencyDateString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocalDataRepository(
    private val appDatabase: AppDatabase,
//    private val currencyApi: CurrencyApi,
//    private val countryApi: CountryApi,
    private val coroutineScope: CoroutineScope
) : ILocalDataRepository {

    override suspend fun saveCurrencies(currencies: List<CurrencyDTO>) {
        try {
            appDatabase.daoCurrency.insert(currencies)
        } catch (exception: Exception) {
            Response.Error(exception.message ?: "Unknown error")
        }
    }

    override suspend fun saveCountries(countries: List<CountryDTO>) {
        try {
            appDatabase.daoCountry.insert(countries)
        } catch (exception: Exception) {
            Response.Error(exception.message ?: "Unknown error")
        }
    }

    override suspend fun currenciesDTOFlow() = appDatabase.daoCurrency.currenciesFlow()
    override suspend fun currenciesDTO() = appDatabase.daoCurrency.currencies()

    override suspend fun countriesDTOFlow() = appDatabase.daoCountry.countriesFlow()

    override suspend fun saveRate(rateDTO: RateDTO) {
        try {
            appDatabase.daoRate.insert(rateDTO)
        } catch (exception: Exception) {
            Response.Error(exception.message ?: "Unknown error")
        }
    }

}