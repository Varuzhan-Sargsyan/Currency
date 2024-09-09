package com.currency.exchange.app.ui.screens.components.currency

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.currency.exchange.app.ui.extensions.OnCurrency
import com.currency.exchange.datamodule.data.datasource.Simulator.currencies
import com.currency.exchange.datamodule.data.model.entities.Currency

@Composable
fun CurrencyList(
    currency: Currency,
    currencies: List<Currency>,
    onCurrency: OnCurrency
) {
    LazyColumn(

    ) {
        items(currencies) { item ->
            CurrencyListItem(
                currency = item,
                isSelected = currency == item,
                onClick = { onCurrency(item) }
            )
        }
    }
}

@Preview
@Composable
fun CurrencyListPreview() {
    val currencies = currencies()
    CurrencyList(
        currency = currencies[0],
        currencies = currencies,
        onCurrency = {}
    )
}
