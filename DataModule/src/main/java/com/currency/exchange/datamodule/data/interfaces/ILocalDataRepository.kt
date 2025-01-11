package com.currency.exchange.datamodule.data.interfaces

import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO
import com.currency.exchange.datamodule.data.model.entities.CountryDTO
import com.currency.exchange.datamodule.data.model.entities.RateDTO
import com.currency.exchange.datamodule.data.model.response.Response
import kotlinx.coroutines.flow.Flow

interface ILocalDataRepository {
    suspend fun currenciesDTOFlow() : Flow<List<CurrencyDTO>>
    suspend fun currenciesDTO() : List<CurrencyDTO>

    suspend fun countriesDTOFlow() : Flow<List<CountryDTO>>

    suspend fun saveCurrencies(currencies: List<CurrencyDTO>)
    suspend fun saveCountries(countries: List<CountryDTO>)
    suspend fun saveRate(rateDTO: RateDTO)

    suspend fun rateDTO(code: String, date: String) : Flow<RateDTO?>

}