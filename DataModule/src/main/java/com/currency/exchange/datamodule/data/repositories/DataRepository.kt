package com.currency.exchange.datamodule.data.repositories

import com.currency.exchange.datamodule.data.api.Api
import com.currency.exchange.datamodule.data.database.AppDatabase
import com.currency.exchange.datamodule.data.interfaces.IDataRepository
import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO
import com.currency.exchange.datamodule.data.model.entities.toCurrencyDTOList
import com.currency.exchange.datamodule.data.model.response.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.channelFlow

class DataRepository(
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
        }

    private suspend fun saveCurrencies(currencies: List<CurrencyDTO>) {
        try {
            appDatabase.daoCurrency.insert(currencies)
        } catch (exception: Exception) {
            Response.Error(exception.message ?: "Unknown error")
        }
    }

    override suspend fun currenciesFlow(reload: Boolean) : Flow<List<CurrencyDTO>> = channelFlow {
        if (reload) {
            coroutineScope.async {
                val response = downloadCurrencies()
                if (response.isError())
                    currencyExceptionState.value = Exception(response.toString())
                else
                    currencyExceptionState.value = null
            }.await()
        }
        appDatabase.daoCurrency.currenciesFlow().collect {
            send(it)
        }
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