package com.currency.exchange.datamodule.domain.interfaces

import com.currency.exchange.datamodule.domain.model.Currency
import com.currency.exchange.datamodule.domain.model.Rate
import com.currency.exchange.datamodule.utils.Const
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface IRateFlow {
    fun invoke(
        sellCurrency: Currency,
        buyCurrency: Currency,
        date: Date = Const.now()
    ) : Flow<Rate?>
}