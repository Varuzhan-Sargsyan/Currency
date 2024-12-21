package com.currency.exchange.app.ui.screens.settings

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.currency.exchange.app.R
import com.currency.exchange.app.ui.screens.uicomponents.GroupView
import com.currency.exchange.app.ui.screens.uicomponents.ThemeFieldView
import com.currency.exchange.app.ui.screens.uicomponents.topbar.SettingsBar
import com.currency.exchange.app.ui.theme.CurrencyAppTheme
import com.currency.exchange.app.ui.theme.Dimensions.paddingNormal
import com.currency.exchange.datamodule.domain.model.ApplicationSettings

@SuppressLint("SuspiciousIndentation")
@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {

    BackHandler(enabled = true) {
        viewModel.navigateBack()
    }

    val applicationSettingsFlow = viewModel.applicationSettingsFlow.collectAsState(
        ApplicationSettings.default()
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingNormal)
    ) {
        item {
            GroupView(
                title = stringResource(id = R.string.settings_configuration),
            ) {
                ThemeFieldView(
                    theme = applicationSettingsFlow.value!!.theme,
                    onSelect = { theme ->
                        viewModel.theme(theme)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun SettingsPreview() {
    CurrencyAppTheme {
        Column {
            SettingsBar {  }
            SettingsScreen()
        }
    }
}