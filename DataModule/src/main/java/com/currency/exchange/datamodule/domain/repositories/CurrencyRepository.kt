package com.currency.exchange.datamodule.domain.repositories

import com.currency.exchange.datamodule.data.interfaces.IDataRepository
import com.currency.exchange.datamodule.domain.model.Currency
import com.currency.exchange.datamodule.domain.interfaces.ICurrencyRepository
import kotlinx.coroutines.flow.Flow

class CurrencyRepository(private val dataRepository: IDataRepository) : ICurrencyRepository {
    override suspend fun currenciesFlow() : Flow<List<Currency>> {
        
    }
}