package com.currency.exchange.datamodule.domain.extensions

import com.currency.exchange.datamodule.data.interfaces.ISharedDataRepository
import com.currency.exchange.datamodule.domain.model.Currency

const val SELL_CURRENCY_KEY = "sellCurrency"
const val BUY_CURRENCY_KEY = "buyCurrency"

fun ISharedDataRepository.sellCurrencyFlow() =
    this.flow(SELL_CURRENCY_KEY, null as Currency?)

fun ISharedDataRepository.buyCurrencyFlow() =
    this.flow(BUY_CURRENCY_KEY, null as Currency?)

fun ISharedDataRepository.sellCurrency(currency: Currency?) =
    this.update(SELL_CURRENCY_KEY, currency)

fun ISharedDataRepository.buyCurrency(currency: Currency?) =
    this.update(BUY_CURRENCY_KEY, currency)