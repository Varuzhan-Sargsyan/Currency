package com.currency.exchange.app.ui.extensions

import com.currency.exchange.datamodule.data.model.entities.Currency

typealias OnClick = () -> Unit
typealias OnItem<T> = (T) -> Unit
typealias OnString = OnItem<String>
typealias OnCurrency = OnItem<Currency>
