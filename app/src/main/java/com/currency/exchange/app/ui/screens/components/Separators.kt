package com.currency.exchange.app.ui.screens.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalDivider(
    thickness: Dp = 1.dp,
    padding: Dp = 0.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.outlineVariant
) {
    HorizontalDivider(
        color = backgroundColor,
        modifier = Modifier.padding(horizontal = padding),
        thickness = thickness
    )
}

@Composable
fun VerticalSeparator() {
    VerticalDivider(
        modifier = Modifier.fillMaxHeight(),
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.outlineVariant
    )
}

@Composable
fun HorizontalSeparator(
) {
    HorizontalDivider(
        thickness = 1.dp,
        backgroundColor = MaterialTheme.colorScheme.outlineVariant
    )
}
