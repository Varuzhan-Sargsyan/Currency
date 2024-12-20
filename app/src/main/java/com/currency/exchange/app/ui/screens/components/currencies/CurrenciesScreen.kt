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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.currency.exchange.app.ui.extensions.OnClick
import com.currency.exchange.app.ui.screens.components.HorizontalSeparator
import com.currency.exchange.app.ui.screens.components.NetworkImage
import com.currency.exchange.app.ui.screens.components.SwipeToRefresh
import com.currency.exchange.app.ui.theme.CurrencyAppTheme
import com.currency.exchange.app.ui.theme.Dimensions.bigIconSize
import com.currency.exchange.app.ui.theme.Dimensions.minimalPadding
import com.currency.exchange.app.ui.theme.Dimensions.noPadding
import com.currency.exchange.app.ui.theme.Dimensions.paddingMedium
import com.currency.exchange.app.ui.theme.Dimensions.paddingNormal
import com.currency.exchange.app.ui.theme.Dimensions.paddingSmall
import com.currency.exchange.app.ui.theme.Typography
import com.currency.exchange.app.ui.theme.currencyCodeStyle
import com.currency.exchange.app.ui.theme.currencyNameStyle
import com.currency.exchange.app.ui.utils.emptyCurrency
import com.currency.exchange.app.ui.utils.iconModifier
import com.currency.exchange.datamodule.domain.model.Currency
import com.currency.exchange.datamodule.domain.model.Currency.Companion.testCurrencies
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@Composable
fun CurrenciesScreen(viewModel: CurrenciesViewModel = hiltViewModel()) {

    val coroutineScope = rememberCoroutineScope()

    BackHandler(enabled = true) {
        viewModel.navigateBack()
    }

    val exceptions = viewModel.flowExceptions().collectAsState(null)
    val currencies: State<List<Currency>> = viewModel.subscribeToCurrencies().collectAsState(emptyList())
    var isRefreshing by remember { mutableStateOf(false) }

    val refresh: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            viewModel.reload()
        }
    }

    if (isRefreshing) {
        LaunchedEffect(key1 = "coroutineScope") {
            coroutineScope.launch {
                delay(1000)
                isRefreshing = currencies.value.isEmpty() || exceptions.value != null
            }
        }
    }

    SwipeToRefresh(
        modifier = Modifier.fillMaxSize(),
        isRefreshing = isRefreshing,
        onRefresh = refresh
    ) {
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
    }
}

@Composable
fun CurrencyItem(
    currency: Currency,
    onCurrency: OnClick
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCurrency() }
            .padding(paddingMedium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NetworkImage(
            modifier = Modifier
                .iconModifier(iconSize = bigIconSize, iconPadding = noPadding).clip(CircleShape),
            contentScale = ContentScale.Crop,
            imageUrl = currency.imageFlagUrl(),
            contentDescription = "Arrow down button"
        )
        Spacer(modifier = Modifier.size(paddingNormal))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = currency.code,
                style = Typography.currencyCodeStyle()
            )
            Spacer(modifier = Modifier.size(paddingSmall))
            Text(
                text = currency.name,
                style = Typography.currencyNameStyle()
            )
        }
        Icon(
            modifier = Modifier.iconModifier(),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun CurrencyItemPreview() {
    val list = testCurrencies + emptyCurrency()
    CurrencyAppTheme(0) {
        LazyColumn (modifier = Modifier.fillMaxWidth().padding(4.dp)) {
            itemsIndexed(list) { index, currency ->
                CurrencyItem(currency = currency) {}
            }
        }
    }
}