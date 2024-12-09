package com.currency.exchange.datamodule.data.repositories

import com.currency.exchange.datamodule.data.api.Api
import com.currency.exchange.datamodule.data.database.AppDatabase
import com.currency.exchange.datamodule.data.interfaces.IDataRepository
import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO
import com.currency.exchange.datamodule.data.model.response.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

class DataRepository(
    private val appDatabase: AppDatabase,
    private val api: Api,
    private val coroutineScope: CoroutineScope
) : IDataRepository {

    override suspend fun downloadCurrencies() =
        try {
            val response = api.downloadCurrencies()
            if (response.isSuccessful)
                Response.Success(response.body() ?: emptyList<CurrencyDTO>())
            else
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

    override fun currenciesFlow(reload: Boolean) : Flow<List<CurrencyDTO>> = channelFlow {
        coroutineScope.launch {
            if (reload) {
                val response = coroutineScope.async {
                    downloadCurrencies()
                }

                val result = response.await()
                if (result is Response.Success) {
                    saveCurrencies(result.data as List<CurrencyDTO>)
                }
            }
            appDatabase.daoCurrency.currenciesFlow().collect {
                send(it)
            }
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

}