package com.currency.exchange.app.ui.extensions

import com.currency.exchange.datamodule.domain.model.Theme

typealias OnClick = () -> Unit
typealias OnItem<T> = (T) -> Unit
typealias OnString = OnItem<String>
typealias OnFloat = OnItem<Float>
typealias OnTheme = OnItem<Theme>

