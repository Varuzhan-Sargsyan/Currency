package com.currency.exchange.datamodule.domain.repositories

import com.currency.exchange.datamodule.data.interfaces.IDataRepository
import com.currency.exchange.datamodule.data.interfaces.ILocalRepository
import com.currency.exchange.datamodule.data.interfaces.ISharedDataRepository
import com.currency.exchange.datamodule.data.interfaces.buyCurrency
import com.currency.exchange.datamodule.data.interfaces.sellCurrency
import com.currency.exchange.datamodule.data.interfaces.subscribeToBuyCurrency
import com.currency.exchange.datamodule.data.interfaces.subscribeToSellCurrency
import com.currency.exchange.datamodule.data.model.entities.CountryDTO
import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO
import com.currency.exchange.datamodule.data.repositories.buyCurrencyScreen
import com.currency.exchange.datamodule.data.repositories.sellCurrencyScreen
import com.currency.exchange.datamodule.domain.interfaces.ICurrencyRepository
import com.currency.exchange.datamodule.domain.model.Currency
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.util.Date

class CurrencyRepository(
    private val dataRepository: IDataRepository,
    private val sharedDataRepository: ISharedDataRepository,
    private val localRepository: ILocalRepository
) : ICurrencyRepository {
    override suspend fun getLastReloadDate(): Date {
        TODO("Not yet implemented")
    }

    override suspend fun reload() {
        dataRepository.downloadCurrencyInformation()
        dataRepository.downloadCountryInformation()
    }

    override suspend fun currenciesFlow(reload: Boolean) =
        combine<List<CurrencyDTO>, List<CountryDTO>, List<Currency>> (
            dataRepository.currenciesDTOFlow(reload),
            dataRepository.countriesDTOFlow(reload)
        ) { currencies, countries ->
            if (currencies.isEmpty() || countries.isEmpty())
                emptyList<Currency>()
            else
                currencies.map { currencyDTO -> Currency(currencyDTO, countries.firstOrNull { it.currencies.keys.contains(currencyDTO.code) }) }
        }

    override suspend fun exceptionsFlow(): Flow<Exception?> =
        dataRepository.currencyExceptionsFlow()

    override fun sellCurrencyFlow(scope: CoroutineScope) : Flow<Currency?> =
        localRepository.subscribeToSellCurrency(scope)

    override fun buyCurrencyFlow(scope: CoroutineScope): Flow<Currency?> =
        localRepository.subscribeToBuyCurrency(scope)

    override fun sellCurrency(currency: Currency?) =
        localRepository.sellCurrency(currency)

    override fun buyCurrency(currency: Currency?) =
        localRepository.buyCurrency(currency)

    override fun sellCurrencyScreen() =
        sharedDataRepository.sellCurrencyScreen()

    override fun buyCurrencyScreen() =
        sharedDataRepository.buyCurrencyScreen()

}

