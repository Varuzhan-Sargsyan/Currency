package com.currency.exchange.app.ui.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.currency.exchange.app.ui.extensions.OnClick
import com.currency.exchange.app.ui.screens.components.currency.BaseCodeView
import com.currency.exchange.app.ui.screens.components.rate.RateView
import com.currency.exchange.app.ui.theme.Sizes.verticalDividerHeight
import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO

@Composable
fun CurrencyRateView(
    currencyDTO: CurrencyDTO,
    sum: Double,
    onCurrency: OnClick,
    onValue: OnClick
) {
    GroupView {
        Row(
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            RateView(
                modifier = Modifier.weight(1f),
                value = sum,
                onClick = onValue
            )
            VerticalDivider(modifier = Modifier.height(verticalDividerHeight))
            BaseCodeView(
                currency = currencyDTO.code,
                onClick = onCurrency
            )
        }
    }
}

@Preview
@Composable
fun BaseCodeExchangeViewPreview() {
    CurrencyRateView(
        currencyDTO = CurrencyDTO(code = "USD"),
        sum = 2002.02,
        onCurrency = {},
        onValue = {}
    )
}