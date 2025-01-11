package com.currency.exchange.datamodule.domain.model

data class Rate(
    val sellCurrency: Currency,
    val buyCurrency: Currency?,
    val rate: Float,
    val amount: Float
)