package com.currency.exchange.app.ui.screens.components.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import com.currency.exchange.app.ui.theme.currencyCodeStyle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.currency.exchange.app.R
import com.currency.exchange.app.ui.extensions.OnFloat
import com.currency.exchange.app.ui.screens.components.EditText
import com.currency.exchange.app.ui.screens.components.GroupView
import com.currency.exchange.app.ui.screens.components.VerticalSeparator
import com.currency.exchange.app.ui.theme.CurrencyAppTheme
import com.currency.exchange.app.ui.theme.Dimensions.paddingBig
import com.currency.exchange.app.ui.theme.Dimensions.paddingMedium
import com.currency.exchange.app.ui.theme.Dimensions.paddingNormal
import com.currency.exchange.app.ui.theme.Dimensions.paddingTiny
import com.currency.exchange.app.ui.theme.Elevations.defaultElevation
import com.currency.exchange.app.ui.theme.Typography
import com.currency.exchange.datamodule.domain.model.Currency

@Composable
private fun emptyCurrency() =
    Currency(stringResource(R.string.empty_currency_code), stringResource(R.string.empty_currency_name))

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = hiltViewModel()) {
    val sellCurrency = viewModel.flowSellCurrency().collectAsState(null)
    val buyCurrency = viewModel.flowBuyCurrency().collectAsState(null)

    val sellSum = viewModel.sellSumFlow().collectAsState(0f)
    val buySum = viewModel.buySumFlow().collectAsState(0f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingNormal),
    ) {
        CurrencyView(
            title = stringResource(R.string.title_you_pay),
            currency = sellCurrency.value ?: emptyCurrency(),
            sum = sellSum.value,
            onSum = viewModel::setSellSum,
            onCurrency = viewModel::selectSellCurrency
        )
        Spacer(modifier = Modifier.size(paddingBig))
        CurrencyView(
            title = stringResource(R.string.title_you_receive),
            currency = buyCurrency.value ?: emptyCurrency(),
            sum = buySum.value,
            onSum = viewModel::setBuySum,
            onCurrency = viewModel::selectBuyCurrency
        )
    }
}

@Composable
fun CurrencyView(
    title: String,
    currency: Currency,
    sum: Float,
    onSum: OnFloat,
    onCurrency: () -> Unit,
) {
    GroupView(
        title = title,
        hasBorder = false,
        elevation = defaultElevation(),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            EditText(
                text = sum.toString(),
                onValueChange = { value ->
                    onSum(value.toFloatOrNull() ?: 0f)
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(paddingMedium),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                textStyle = Typography.currencyCodeStyle()
            )

            if (sum != 0f)
                IconButton(onClick = { onSum(0f) }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear button"
                    )
                }

            VerticalSeparator()

            Row(
                modifier = Modifier
                    .clickable { onCurrency() }
                    .padding(horizontal = paddingBig)
                    .fillMaxHeight()
                    .width(80.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Arrow down button"
                )
                Spacer(modifier = Modifier.size(paddingTiny))
                Text(
                    modifier = Modifier
                        .padding(paddingNormal),
                    text = currency.code,
                    style = Typography.currencyCodeStyle()
                )
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Arrow down button"
                )
            }
        }
    }
}

@Preview
@Composable
fun CurrencyViewPreview() {
    CurrencyAppTheme(0) {
        Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
            CurrencyView(
                title = stringResource(R.string.title_you_pay),
                currency = Currency("USD", "US Dollar"),
                sum = 10.01f,
                onSum = {},
                onCurrency = {}
            )
            Spacer(modifier = Modifier.size(paddingBig))
            CurrencyView(
                title = stringResource(R.string.title_you_receive),
                currency = Currency("AMD", "US Dollar"),
                sum = 1000.0001f,
                onSum = {},
                onCurrency = {}
            )
            Spacer(modifier = Modifier.size(paddingBig))
            CurrencyView(
                title = stringResource(R.string.title_you_receive),
                currency = emptyCurrency(),
                sum = 10f,
                onSum = {},
                onCurrency = {}
            )
        }
    }
}