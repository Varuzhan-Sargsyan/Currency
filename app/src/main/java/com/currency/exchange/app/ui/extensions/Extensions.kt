package com.currency.exchange.app.ui.extensions

import androidx.compose.ui.graphics.Color

fun Int.toColor() : Color = Color(this)

fun String.addLabel(label: String) =
    when (label) {
        "C" -> ""
        "." -> if (!contains(".")) this + label else this
        else -> {
            if (this.isEmpty() || this == "0" || this == "0." || this == "." || toDouble() == 0.0)
                label
            else
                this + label
        }
    }

fun Double.roundToDecimalPlaces(places: Int = 4) =
    (this * (10.0 * places)) / (10.0 * places)