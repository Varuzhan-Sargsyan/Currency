package com.currency.exchange.app.ui.screens.uicomponents

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun SpacerH(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun SpacerV(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun Spacer(width: Dp, height: Dp) {
    Spacer(modifier = Modifier.height(height).width(width))
}

@Composable
fun Spacer(size: Dp) {
    Spacer(modifier = Modifier.size(size))
}