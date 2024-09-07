package com.currency.exchange.app.ui.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.currency.exchange.app.ui.extensions.OnClick
import com.currency.exchange.datamodule.data.model.entities.Currency

@Composable
fun CurrencyView(
    currency: Currency,
    sum: String,
    onCurrency: OnClick,
    onValue: OnClick
) {
    GroupView {
        Row(
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            CurrencyValueView(
                modifier = Modifier.weight(1f),
                text = sum,
                onClick = onValue
            )
            VerticalDivider(modifier = Modifier.height(45.dp))
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
    CurrencyView(
        currency = Currency(baseCode = "USD"),
        sum = "2002.02",
        onCurrency = {},
        onValue = {}
    )
}