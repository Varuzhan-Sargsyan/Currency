package com.currency.exchange.app.ui.screens.components.currency

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.currency.exchange.app.ui.extensions.OnCurrency
import com.currency.exchange.datamodule.data.database.Simulator.currencies
import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO

@Composable
fun CurrencyList(
    currencyDTO: CurrencyDTO,
    currencies: List<CurrencyDTO>,
    onCurrency: OnCurrency
) {
    LazyColumn(

    ) {
        items(currencies) { item ->
            CurrencyListItem(
                currencyDTO = item,
                isSelected = currencyDTO == item,
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
        currencyDTO = currencies[0],
        currencies = currencies,
        onCurrency = {}
    )
}
