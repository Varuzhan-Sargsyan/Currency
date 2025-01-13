package com.currency.exchange.datamodule.domain.repositories

import com.currency.exchange.datamodule.data.interfaces.ILocalDataRepository
import com.currency.exchange.datamodule.domain.interfaces.IRateFlow
import com.currency.exchange.datamodule.domain.model.Currency
import com.currency.exchange.datamodule.domain.model.Rate
import com.currency.exchange.datamodule.utils.toCurrencyDateString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import java.util.Date

class IRateFlowUseCase(
    private val localDataRepository: ILocalDataRepository,
) : IRateFlow {
    override fun invoke(
        sellCurrency: Currency,
        buyCurrency: Currency,
        date: Date
    ) : Flow<Rate?> = channelFlow {
        localDataRepository.rateDTO(sellCurrency.code, date.toCurrencyDateString()).collectLatest {
            if (it == null)
                send(null as Rate?)
            else
                send(Rate(sellCurrency, buyCurrency, it.rates[buyCurrency.code] ?: 0f, it.amount))
        }
    }
}