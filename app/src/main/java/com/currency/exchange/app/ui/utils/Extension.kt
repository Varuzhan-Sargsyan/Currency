package com.currency.exchange.app.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.currency.exchange.app.R
import com.currency.exchange.datamodule.domain.model.Theme

@Composable
fun String.toTheme() : Theme = when(this) {
    stringResource(R.string.theme_light) -> Theme.light()
    stringResource(R.string.theme_dark) -> Theme.dark()
    stringResource(R.string.theme_system) -> Theme.system()
    else -> throw IllegalArgumentException("Invalid theme value: $this")
}

@Composable
fun Theme.toStringValue() : String = when(this()) {
    Theme.DARK -> stringResource(R.string.theme_dark)
    Theme.SYSTEM -> stringResource(R.string.theme_system)
    else -> stringResource(R.string.theme_light)
}

@Composable
fun List<Theme>.allNames() = map { it.toStringValue() }

@Composable
fun List<Theme>.byName(name: String) = find { it.toStringValue() == name } ?: Theme.system()