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
import com.currency.exchange.app.ui.screens.components.CurrencyRateView
import com.currency.exchange.app.ui.screens.dialog.CurrencySelectionDialog
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

                    var editFirstRate by remember { mutableStateOf(false) }
                    var editSecondRate by remember { mutableStateOf(false) }

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
                            CurrencyRateView(
                                currency = stateFirstCurrency.value,
                                sum = stateFirstValue.value.toString(),
                                onCurrency = { selectFirstCurrency = true },
                                onValue = { editFirstRate = true }
                            )
                            Spacer(modifier = Modifier.padding(normal))
                            CurrencyRateView(
                                currency = stateSecondCurrency.value,
                                sum = stateSecondValue.value.toString(),
                                onCurrency = { selectSecondCurrency = true },
                                onValue = { editSecondRate = true }
                            )
                        }
                    }

                    if (editFirstRate)
                        NumberInputDialog(
                            value = stateFirstValue.value.toString(),
                            onValue = {
                                viewModel.stateFirstValue.value = it.toDouble()
                                editFirstRate = false
                            },
                            onClose = { editFirstRate = false }
                        )

                    if (editSecondRate)
                        NumberInputDialog(
                            value = stateSecondValue.value.toString(),
                            onValue = {
                                viewModel.stateSecondValue.value = it.toDouble()
                                editSecondRate = false
                            },
                            onClose = { editSecondRate = false }
                        )

                    if (selectFirstCurrency)
                        CurrencySelectionDialog(
                            currency = stateFirstCurrency.value,
                            currencies = viewModel.currencies.value,
                            onValue = {
                                viewModel.stateFirstCurrency.value = it
                                selectFirstCurrency = false
                            },
                            onClose = { selectFirstCurrency = false }
                        )

                    if (selectSecondCurrency)
                        CurrencySelectionDialog(
                            currency = stateSecondCurrency.value,
                            currencies = viewModel.currencies.value,
                            onValue = {
                                viewModel.stateSecondCurrency.value = it
                                selectSecondCurrency = false
                            },
                            onClose = { selectSecondCurrency = false }
                        )
                }
            }
        }
    }
}