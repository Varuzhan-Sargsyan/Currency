package com.currency.exchange.app.ui.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.currency.exchange.app.ui.theme.Dimensions

fun Modifier.iconModifier(iconSize: Dp = Dimensions.iconSize, iconPadding: Dp = Dimensions.iconPadding) =
    this.size(iconSize).padding(iconPadding)
