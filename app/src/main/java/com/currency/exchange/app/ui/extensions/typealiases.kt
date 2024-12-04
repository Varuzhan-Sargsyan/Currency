package com.currency.exchange.app.ui.extensions

import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO

typealias OnClick = () -> Unit
typealias OnItem<T> = (T) -> Unit
typealias OnString = OnItem<String>
typealias OnCurrency = OnItem<CurrencyDTO>
