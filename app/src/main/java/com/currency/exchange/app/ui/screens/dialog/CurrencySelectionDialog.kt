package com.currency.exchange.app.ui.screens.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.currency.exchange.app.ui.extensions.OnClick
import com.currency.exchange.app.ui.extensions.OnCurrency
import com.currency.exchange.app.ui.screens.components.GroupView
import com.currency.exchange.app.ui.screens.components.currency.CurrencyList
import com.currency.exchange.datamodule.data.datasource.Simulator.currencies
import com.currency.exchange.datamodule.data.model.entities.Currency

@Composable
fun CurrencySelectionDialog(
    currency: Currency,
    currencies: List<Currency>,
    onValue: OnCurrency,
    onClose: OnClick,
) {
    Dialog(onDismissRequest = onClose) {
        GroupView {
            CurrencyList(
                currency = currency,
                currencies = currencies,
                onCurrency = onValue
            )
        }
    }
}

@Preview
@Composable
fun CurrencySelectionDialogPreview() {
    val currencies = currencies()
    CurrencySelectionDialog(
        currency = currencies[0],
        currencies = currencies,
        onValue = {},
        onClose = {}
    )
}