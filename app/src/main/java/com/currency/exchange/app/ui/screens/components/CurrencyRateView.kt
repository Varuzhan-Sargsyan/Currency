package com.currency.exchange.app.ui.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.currency.exchange.app.ui.extensions.OnClick
import com.currency.exchange.app.ui.screens.components.currency.BaseCodeView
import com.currency.exchange.app.ui.screens.components.rate.RateView
import com.currency.exchange.app.ui.theme.Sizes.verticalDividerHeight
import com.currency.exchange.datamodule.data.model.entities.Currency

@Composable
fun CurrencyRateView(
    currency: Currency,
    sum: String,
    onCurrency: OnClick,
    onValue: OnClick
) {
    GroupView {
        Row(
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            RateView(
                modifier = Modifier.weight(1f),
                text = sum,
                onClick = onValue
            )
            VerticalDivider(modifier = Modifier.height(verticalDividerHeight))
            BaseCodeView(
                currency = currency.baseCode,
                onClick = onCurrency
            )
        }
    }
}

@Preview
@Composable
fun BaseCodeExchangeViewPreview() {
    CurrencyRateView(
        currency = Currency(baseCode = "USD"),
        sum = "2002.02",
        onCurrency = {},
        onValue = {}
    )
}