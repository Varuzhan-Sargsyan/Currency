package com.currency.exchange.app.ui.screens.components.currency

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.currency.exchange.app.ui.extensions.OnClick
import com.currency.exchange.app.ui.theme.Paddings.medium
import com.currency.exchange.app.ui.theme.Paddings.small
import com.currency.exchange.app.ui.theme.Sizes.currencyRateViewHeight
import com.currency.exchange.app.ui.theme.Texts

@Composable
fun BaseCodeView(
    modifier: Modifier = Modifier,
    currency: String,
    onClick: OnClick
) {
    Row (
        modifier = Modifier
            .height(currencyRateViewHeight)
            .clickable { onClick() }
            .then(modifier),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(medium))
        Text(
            text = currency,
            fontSize = Texts.textFieldLabel,
        )
        Spacer(modifier = Modifier.size(small))
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            modifier = Modifier.size(24.dp),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun CurrencyViewPreview() {
    BaseCodeView(currency = "USD") {}
}