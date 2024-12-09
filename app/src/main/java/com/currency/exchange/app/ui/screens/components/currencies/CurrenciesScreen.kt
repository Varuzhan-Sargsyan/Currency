package com.currency.exchange.app.ui.screens.components.currencies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.currency.exchange.app.ui.theme.Typography
import com.currency.exchange.app.ui.theme.currencyCodeStyle
import com.currency.exchange.datamodule.domain.model.Currency

@Composable
fun CurrenciesScreen(viewModel: CurrenciesViewModel = hiltViewModel()) {
    val currencies = viewModel.flowCurrencies().collectAsState(emptyList())

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(Color.Yellow)
    ) {
        items(currencies.value) { currency ->
            CurrencyItem(currency = currency)
        }
    }
}

@Composable
fun CurrencyItem(currency: Currency) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(0.4f),
            text = currency.code,
            style = Typography.currencyCodeStyle()
        )
        Spacer(modifier = Modifier.weight(0.1f))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = currency.code,
            style = Typography.currencyCodeStyle()
        )
    }
}