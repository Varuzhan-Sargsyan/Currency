package com.currency.exchange.datamodule.data.repositories

import android.content.Context
import com.currency.exchange.datamodule.R
import com.currency.exchange.datamodule.data.api.Api
import com.currency.exchange.datamodule.data.database.AppDatabase
import com.currency.exchange.datamodule.data.interfaces.IDataRepository
import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO
import com.currency.exchange.datamodule.data.model.entities.CurrencyLocalInfo
import com.currency.exchange.datamodule.data.model.entities.toCurrencyDTOList
import com.currency.exchange.datamodule.data.model.response.Response
import com.currency.exchange.datamodule.data.utils.JsonHelper
import com.currency.exchange.datamodule.domain.model.Screen
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DataRepository(
    private val context: Context,
    private val appDatabase: AppDatabase,
    private val api: Api,
    private val coroutineScope: CoroutineScope
) : IDataRepository {

    private val currencyExceptionState = MutableStateFlow<Exception?>(null)
    private val dashboardExceptionState = MutableStateFlow<Exception?>(null)

    override suspend fun downloadCurrencies() =
        try {
            val response = api.downloadCurrencies()
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

    override suspend fun currencyFlags() : Response {
        // Read the JSON file from res/raw
        val inputStream = context.resources.openRawResource(R.raw.currencies_with_flags)
        val json = inputStream.bufferedReader().use { it.readText() }

        // Parse JSON to a list of CurrencyInfo objects
        val currencies = JsonHelper.fromJsonList(json, CurrencyLocalInfo::class.java)
        return Response.Success(currencies)
    }

    private suspend fun saveCurrencies(currencies: List<CurrencyDTO>) {
        try {
            appDatabase.daoCurrency.insert(currencies)
        } catch (exception: Exception) {
            Response.Error(exception.message ?: "Unknown error")
        }
    }

    override suspend fun currenciesFlow(reload: Boolean) : Flow<List<CurrencyDTO>> {// = channelFlow {
        if (reload) {
            coroutineScope.async {
                downloadCurrencies()
            }.await()
        }
        return appDatabase.daoCurrency.currenciesFlow()
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


}