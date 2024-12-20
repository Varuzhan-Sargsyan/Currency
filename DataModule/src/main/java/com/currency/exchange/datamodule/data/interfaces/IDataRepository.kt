package com.currency.exchange.datamodule.data.interfaces

import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO
import com.currency.exchange.datamodule.data.model.entities.CountryDTO
import com.currency.exchange.datamodule.data.model.response.Response
import kotlinx.coroutines.flow.Flow

interface IDataRepository {
    suspend fun downloadCurrencyInformation() : Response
    suspend fun currenciesDTOFlow(reload: Boolean) : Flow<List<CurrencyDTO>>

    suspend fun downloadCountryInformation() : Response
    suspend fun countriesDTOFlow(reload: Boolean) : Flow<List<CountryDTO>>

    suspend fun downloadRates(currencyDTO: CurrencyDTO)
    suspend fun currencyExceptionsFlow() : Flow<Exception?>
    suspend fun dashboardExceptionsFlow() : Flow<Exception?>

    fun flags() : List<CountryDTO>
    fun currencyLocalInfo(code: String) : CountryDTO?
}