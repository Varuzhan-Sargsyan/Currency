package com.currency.exchange.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.currency.exchange.app.ui.theme.Dimensions.noSize
import com.currency.exchange.app.ui.theme.Dimensions.paddingNormal
import com.currency.exchange.app.ui.theme.Dimensions.paddingSmall
import com.currency.exchange.app.ui.theme.Dimensions.paddingTiny

object Shapes {
    val listDefaultShapes = RoundedCornerShape(paddingNormal)
    val numberPadButtonShape = RoundedCornerShape(10.dp)
}

object Elevations {
    @Composable fun defaultElevation() = CardDefaults.cardElevation(defaultElevation = paddingSmall)
    @Composable fun noElevation() = CardDefaults.cardElevation(
        defaultElevation = noSize,
        pressedElevation = noSize,
        focusedElevation = noSize,
        hoveredElevation = noSize,
        draggedElevation = noSize,
        disabledElevation = noSize,
    )
    @Composable fun tinyElevation() = CardDefaults.cardElevation(defaultElevation = paddingTiny)
}
