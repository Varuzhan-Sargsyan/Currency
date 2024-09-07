package com.currency.exchange.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.currency.exchange.app.ui.theme.Paddings.small
import com.currency.exchange.app.ui.theme.Paddings.tiny

object Shapes {
    val listDefaultShapes = RoundedCornerShape(Paddings.normal)
    val numberPadButtonShape = RoundedCornerShape(10.dp)
}

object Elevations {
    @Composable fun defaultElevation() = CardDefaults.cardElevation(defaultElevation = small)
    @Composable fun noElevation() = CardDefaults.cardElevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        focusedElevation = 0.dp,
        hoveredElevation = 0.dp,
        draggedElevation = 0.dp,
        disabledElevation = 0.dp,
    )
    @Composable fun tinyElevation() = CardDefaults.cardElevation(defaultElevation = tiny)
}

object Paddings {
//    val empty = 0.dp
    val tiny = 2.dp
    val small = 4.dp
    val medium = 8.dp
    val normal = 12.dp
    val big = 16.dp
    val biggest = 24.dp
    val huge = 32.dp
}

object Texts {
    val textFieldLabel = 15.sp
    val numberPadTextSize = 32.sp
}

object Sizes {
    val groupTitle = 14.sp
    val numberPadButtonSize = 60.dp
}