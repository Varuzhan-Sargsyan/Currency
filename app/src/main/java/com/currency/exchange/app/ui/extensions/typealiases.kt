package com.currency.exchange.app.ui.extensions

typealias OnClick = () -> Unit
typealias OnItem<T> = (T) -> Unit
typealias OnString = OnItem<String>
typealias OnFloat = OnItem<Float>
