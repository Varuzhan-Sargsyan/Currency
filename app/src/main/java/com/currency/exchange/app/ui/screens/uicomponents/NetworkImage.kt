package com.currency.exchange.app.ui.screens.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter

@Composable
fun NetworkImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale,
    placeholder: Painter? = null,
    error: Painter? = null,
    colorFilter: ColorFilter? = null
) {
    Image(
        painter = rememberAsyncImagePainter(
            model = imageUrl,
            placeholder = placeholder,
            error = error
        ),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        colorFilter = colorFilter
    )
}
