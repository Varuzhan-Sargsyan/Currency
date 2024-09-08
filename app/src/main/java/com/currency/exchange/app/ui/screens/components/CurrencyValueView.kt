package com.currency.exchange.app.ui.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.currency.exchange.app.ui.extensions.OnClick
import com.currency.exchange.app.ui.theme.Paddings.normal

@Composable
fun CurrencyValueView(
    modifier: Modifier = Modifier,
    text: String,
    onClick: OnClick
) {
    Row (
        modifier = Modifier
            .height(50.dp)
            .clickable { onClick() }
            .then(modifier),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(normal))
        Text(text = text)
        Spacer(modifier = Modifier.size(normal))
    }
}

@Preview(showBackground = true)
@Composable
fun NameViewPreview() {
    Column {
        CurrencyValueView(
            text = "0.0",
            onClick = {}
        )
        CurrencyValueView(
            text = "555.000555",
            onClick = {}
        )
    }
}