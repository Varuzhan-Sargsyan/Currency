package com.currency.exchange.datamodule.domain.model

import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO

data class Currency(
    val code: String,
    val name: String,
)

fun CurrencyDTO.toCurrency() = Currency(code, name)