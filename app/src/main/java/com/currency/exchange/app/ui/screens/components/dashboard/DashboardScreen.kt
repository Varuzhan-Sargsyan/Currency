package com.currency.exchange.app.ui.screens.components.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import com.currency.exchange.app.ui.theme.currencyCodeStyle
import com.currency.exchange.app.ui.theme.currencyNameStyle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.currency.exchange.app.ui.screens.components.HorizontalSeparator
import com.currency.exchange.app.ui.theme.Dimensions.paddingNormal
import com.currency.exchange.app.ui.theme.Typography

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = hiltViewModel()) {
    val sellCurrency = viewModel.flowSellCurrency().collectAsState(null)
    val buyCurrency = viewModel.flowBuyCurrency().collectAsState(null)

    Column(
        modifier = Modifier.fillMaxSize().padding(paddingNormal),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.selectSellCurrency() },
        ) {
            Text(
                modifier = Modifier
                    .padding(paddingNormal)
                    .weight(0.8f),
                text = sellCurrency.value?.name ?: "Select sell currency",
                style = Typography.currencyCodeStyle()
            )
            Text(
                modifier = Modifier.padding(paddingNormal),
                text = sellCurrency.value?.code ?: "",
                style = Typography.currencyNameStyle()
            )
        }
        HorizontalSeparator()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.selectBuyCurrency() },
        ) {
            Text(
                modifier = Modifier
                    .padding(paddingNormal)
                    .weight(0.8f),
                text = buyCurrency.value?.name ?: "Select buy currency",
                style = Typography.currencyCodeStyle()
            )
            Text(
                modifier = Modifier.padding(paddingNormal),
                text = buyCurrency.value?.code ?: "",
                style = Typography.currencyNameStyle()
            )
        }
    }
}