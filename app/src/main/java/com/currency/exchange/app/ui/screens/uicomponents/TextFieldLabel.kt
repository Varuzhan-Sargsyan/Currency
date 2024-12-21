package com.currency.exchange.app.ui.screens.uicomponents

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.currency.exchange.app.ui.theme.Dimensions.textFieldLabel

@Composable
fun TextFieldLabel(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Start,
    size: TextUnit = textFieldLabel,
    lineHeight: TextUnit = TextUnit.Unspecified,
    bold: Boolean = false,
    italic: Boolean = false,
) =
    Text(
        text = text,
        color = color,
        modifier = Modifier
            .then(modifier),
        maxLines = 1,
        textAlign = textAlign,
        overflow = TextOverflow.Ellipsis,
        fontSize = size,
        lineHeight = lineHeight,
        fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
        fontStyle = if (italic) FontStyle.Italic else FontStyle.Normal
    )