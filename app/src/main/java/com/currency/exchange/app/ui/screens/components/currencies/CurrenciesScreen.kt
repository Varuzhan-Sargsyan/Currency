package com.currency.exchange.app.ui.screens.components.currencies

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.currency.exchange.app.ui.extensions.OnClick
import com.currency.exchange.app.ui.screens.components.HorizontalSeparator
import com.currency.exchange.app.ui.theme.Dimensions.minimalPadding
import com.currency.exchange.app.ui.theme.Dimensions.paddingNormal
import com.currency.exchange.app.ui.theme.Typography
import com.currency.exchange.app.ui.theme.currencyCodeStyle
import com.currency.exchange.app.ui.theme.currencyNameStyle
import com.currency.exchange.app.ui.utils.iconModifier
import com.currency.exchange.datamodule.domain.model.Currency

@SuppressLint("SuspiciousIndentation")
@Composable
fun CurrenciesScreen(viewModel: CurrenciesViewModel = hiltViewModel()) {

    BackHandler(enabled = true) {
        viewModel.navigateBack()
    }

    val exceptions = viewModel.flowExceptions().collectAsState(null)
    val currencies: State<List<Currency>> = viewModel.subscribeToCurrencies().collectAsState(emptyList())

//    SwipeToRefresh(
//        modifier = Modifier.fillMaxSize(),
//        onRefresh = { viewModel.reload() }
//    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            exceptions.value?.let {
                Text(
                    modifier = Modifier.padding(paddingNormal),
                    text = it.message ?: "Unknown error"
                )
                Spacer(modifier = Modifier.size(paddingNormal))
            }
            val stateLazyList = rememberLazyListState()
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(minimalPadding),
                state = stateLazyList
            ) {
                items(currencies.value) { currency ->
                    CurrencyItem(currency = currency) { viewModel.select(currency) }
                    HorizontalSeparator()
                }
            }
        }
//    }
}

@Composable
fun CurrencyItem(
    currency: Currency,
    onCurrency: OnClick
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(minimalPadding)
            .clickable { onCurrency() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(paddingNormal)
                .weight(0.2f),
            text = currency.code,
            style = Typography.currencyCodeStyle()
        )
        Text(
            modifier = Modifier.padding(paddingNormal),
            text = currency.name,
            style = Typography.currencyNameStyle()
        )
        Icon(
            modifier = Modifier.iconModifier(),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

