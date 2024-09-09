package com.currency.exchange.app.ui.screens.components.currency

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.currency.exchange.app.ui.extensions.OnClick
import com.currency.exchange.app.ui.theme.Paddings.normal
import com.currency.exchange.app.ui.theme.Texts
import com.currency.exchange.datamodule.data.model.entities.Currency

@Composable fun CurrencyListItem(
    currency: Currency,
    isSelected: Boolean,
    onClick: OnClick
) {
    Row(
        modifier = Modifier
            .padding(normal)
            .clickable { onClick() }
    ) {
        Text(
            text = currency.baseCode,
//            modifier = Modifier.widthIn(min = 60.dp),
            fontSize = Texts.textFieldLabel,
        )
        Spacer(modifier = Modifier.size(normal))
        Text(
            text = currency.name,
            modifier = Modifier.weight(1f),
            fontSize = Texts.textFieldLabel,
        )
        if (isSelected) {
            Spacer(modifier = Modifier.size(normal))
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                modifier = Modifier.width(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}