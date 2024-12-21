package com.currency.exchange.app.ui.screens.uicomponents.topbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import com.currency.exchange.app.ui.theme.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.currency.exchange.app.R
import com.currency.exchange.app.ui.extensions.OnClick
import com.currency.exchange.app.ui.theme.CurrencyAppTheme
import com.currency.exchange.app.ui.theme.Dimensions.paddingNormal
import com.currency.exchange.app.ui.theme.appBarColorSchema
import com.currency.exchange.app.ui.theme.titleStyle
import com.currency.exchange.app.ui.utils.clickableIconModifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardBar(
    onSettings: OnClick
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = MaterialTheme.appBarColorSchema(),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(paddingNormal))

                Text(
                    text = stringResource(R.string.title_dashboard),
                    style = Typography.titleStyle(),
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    modifier = Modifier.clickableIconModifier { onSettings() }
                )

                Spacer(modifier = Modifier.width(paddingNormal))
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun HomeAppBarPreview() {
    CurrencyAppTheme {
        DashboardBar {}
    }
}
