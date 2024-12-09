package com.currency.exchange.app.ui.screens.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.currency.exchange.app.ui.extensions.OnClick
import com.currency.exchange.app.ui.extensions.OnCurrency
import com.currency.exchange.app.ui.screens.components.GroupView
import com.currency.exchange.datamodule.data.database.Simulator.currencies
import com.currency.exchange.datamodule.data.model.entities.CurrencyDTO

@Composable
fun CurrencySelectionDialog(
    currencyDTO: CurrencyDTO,
    currencies: List<CurrencyDTO>,
    onValue: OnCurrency,
    onClose: OnClick,
) {
    Dialog(onDismissRequest = onClose) {
        GroupView {
//            CurrencyList(
//                currencyDTO = currencyDTO,
//                currencies = currencies,
//                onCurrency = onValue
//            )
        }
    }
}

@Preview
@Composable
fun CurrencySelectionDialogPreview() {
    val currencies = currencies()
    CurrencySelectionDialog(
        currencyDTO = currencies[0],
        currencies = currencies,
        onValue = {},
        onClose = {}
    )
}