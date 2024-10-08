package com.currency.exchange.app.ui.screens.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.currency.exchange.app.ui.extensions.OnClick
import com.currency.exchange.app.ui.extensions.OnString
import com.currency.exchange.app.ui.screens.components.GroupView
import com.currency.exchange.app.ui.screens.components.rate.CustomNumericPad

@Composable
fun NumberInputDialog(
    value: String?,
    onValue: OnString,
    onClose: OnClick,
) {
    if (value == null) return

    Dialog(onDismissRequest = onClose) {
        GroupView {
            CustomNumericPad(
                initialValue = value,
                onClose = onClose,
                onValue = onValue
            )
        }
    }
}

@Preview
@Composable
fun NumberInputDialogPreview() {
    NumberInputDialog(
        value = "This is a message",
        onValue = {},
        onClose = {}
    )
}