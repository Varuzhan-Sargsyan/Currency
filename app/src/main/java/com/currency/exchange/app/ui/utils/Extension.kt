package com.currency.exchange.app.ui.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.currency.exchange.app.R
import com.currency.exchange.datamodule.domain.model.Theme

@Composable
fun Theme.toStringValue() : String = when(this()) {
    Theme.DARK -> stringResource(R.string.theme_dark)
    Theme.SYSTEM -> stringResource(R.string.theme_system)
    else -> stringResource(R.string.theme_light)
}

@Composable
fun List<Theme>.allNames() = map { it.toStringValue() }

@Composable
fun isDarkTheme() = Theme.isDarkTheme() || Theme.isSystemTheme() && isSystemInDarkTheme()
