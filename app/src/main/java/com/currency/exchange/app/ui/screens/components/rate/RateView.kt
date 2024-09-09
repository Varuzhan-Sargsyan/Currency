package com.currency.exchange.app.ui.screens.components.rate

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.currency.exchange.app.ui.extensions.OnClick
import com.currency.exchange.app.ui.theme.Paddings.normal
import com.currency.exchange.app.ui.theme.Sizes.currencyRateViewHeight
import java.util.Locale

@Composable
fun RateView(
    modifier: Modifier = Modifier,
    value: Double,
    onClick: OnClick
) {
    Row (
        modifier = Modifier
            .height(currencyRateViewHeight)
            .clickable { onClick() }
            .then(modifier),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(normal))
        Text(text = String.format(locale = Locale.getDefault(), format = "%.4f", value))
        Spacer(modifier = Modifier.size(normal))
    }
}

@Preview(showBackground = true)
@Composable
fun NameViewPreview() {
    Column {
        RateView(
            value = 0.0,
            onClick = {}
        )
        RateView(
            value = 555.000555,
            onClick = {}
        )
    }
}