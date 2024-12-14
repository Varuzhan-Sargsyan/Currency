package com.currency.exchange.app.ui.screens.components.topbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.currency.exchange.app.R
import com.currency.exchange.app.ui.theme.CurrencyAppTheme
import com.currency.exchange.app.ui.theme.Dimensions.paddingNormal
import com.currency.exchange.app.ui.theme.Dimensions.paddingSmall
import com.currency.exchange.app.ui.theme.appBarColorSchema
import com.currency.exchange.app.ui.utils.iconModifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardBar() {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = MaterialTheme.appBarColorSchema(),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_currencies),
                    contentDescription = null,
                    modifier = Modifier.iconModifier()
                )
                Spacer(modifier = Modifier.width(paddingSmall))
                Text(
                    text = stringResource(R.string.title_dashboard),
                    modifier = Modifier
                        .weight(1f)
                        .padding(paddingNormal)
                )
                Spacer(modifier = Modifier.width(paddingSmall))
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun HomeAppBarPreview() {
    CurrencyAppTheme(0) {
        DashboardBar()
    }
}
