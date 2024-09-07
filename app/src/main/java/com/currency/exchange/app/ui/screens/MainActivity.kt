package com.currency.exchange.app.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.currency.exchange.app.ui.screens.components.CurrencyView
import com.currency.exchange.app.ui.screens.dialog.NumberInputDialog
import com.currency.exchange.app.ui.theme.CurrencyAppTheme
import com.currency.exchange.app.ui.theme.Paddings.normal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyAppTheme(0) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    var selectFirstCurrency by remember { mutableStateOf(false) }
                    var selectSecondCurrency by remember { mutableStateOf(false) }

                    val stateFirstCurrency = viewModel.stateFirstCurrency.collectAsState()
                    val stateSecondCurrency = viewModel.stateSecondCurrency.collectAsState()

                    val stateFirstValue = viewModel.stateFirstValue.collectAsState()
                    val stateSecondValue = viewModel.stateSecondValue.collectAsState()

                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Column(
                            modifier = Modifier.padding(normal)
                        ) {
                            CurrencyView(
                                currency = stateFirstCurrency.value,
                                sum = stateFirstValue.value.toString(),
                                onCurrency = {  },
                                onValue = { selectFirstCurrency = true }
                            )
                            Spacer(modifier = Modifier.padding(normal))
                            CurrencyView(
                                currency = stateSecondCurrency.value,
                                sum = stateSecondValue.value.toString(),
                                onCurrency = {},
                                onValue = {}
                            )
                        }
                    }

                    if (selectFirstCurrency)
                        NumberInputDialog(
                            value = stateFirstValue.value.toString(),
                            onValue = { viewModel.stateFirstValue.value = it.toDouble() },
                            onClose = { selectFirstCurrency = false }
                        )

                    if (selectSecondCurrency)
                        NumberInputDialog(
                            value = stateSecondValue.value.toString(),
                            onValue = { viewModel.stateSecondValue.value = it.toDouble() },
                            onClose = { selectSecondCurrency = false }
                        )
                }
            }
        }
    }
}