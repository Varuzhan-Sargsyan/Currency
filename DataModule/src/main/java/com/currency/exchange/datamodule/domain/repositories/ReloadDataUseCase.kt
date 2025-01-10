package com.currency.exchange.datamodule.domain.repositories

import com.currency.exchange.datamodule.data.interfaces.ILocalDataRepository
import com.currency.exchange.datamodule.data.interfaces.IRemoteDataRepository
import com.currency.exchange.datamodule.data.model.entities.CountryDTO
import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO
import com.currency.exchange.datamodule.data.model.entities.RateDTO
import com.currency.exchange.datamodule.data.model.response.Response
import com.currency.exchange.datamodule.utils.Const
import com.currency.exchange.datamodule.utils.toCurrencyDateString
import kotlinx.coroutines.flow.flow
import java.util.Date

class ReloadDataUseCase(
    private val remoteDataRepository: IRemoteDataRepository,
    private val localDataRepository: ILocalDataRepository
) {
    fun invoke(date: Date = Const.now()) = flow<Response> {
        emit(Response.Loading)

        var error = null as String?
        val countryResponse = remoteDataRepository.downloadCountryInformation()
        localDataRepository.saveCountries(countryResponse.toListData<CountryDTO>())

        val currencyResponse = remoteDataRepository.downloadCurrencyInformation()
        localDataRepository.saveCurrencies(currencyResponse.toListData<CurrencyDTO>())

        val ratesResponse = remoteDataRepository.downloadRates(date.toCurrencyDateString())
        ratesResponse.forEach {
            if (it.isSuccess())
                it.toData<RateDTO>()?.let { rateDTO ->localDataRepository.saveRate(rateDTO) }
        }

        val responses = listOf<Response>() +
            countryResponse +
            currencyResponse +
            ratesResponse

        responses.forEach { it.message()?.let { error += it + "\n" } }

        if (error != null)
            emit(Response.Error(error))
        else
            emit(Response.Success(Unit))
    }
}
