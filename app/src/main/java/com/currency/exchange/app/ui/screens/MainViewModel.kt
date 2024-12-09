package com.currency.exchange.app.ui.screens

import androidx.lifecycle.ViewModel
import com.currency.exchange.app.ui.screens.navigation.Screen
import com.currency.exchange.datamodule.domain.interfaces.ICurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyRepository: ICurrencyRepository
) : ViewModel() {
    private val _screen: MutableStateFlow<Screen> = MutableStateFlow(
        Screen.Dashboard
    )
    val screen = _screen as StateFlow<Screen>

    fun moveTo(screen: Screen) {
        _screen.tryEmit(screen)
    }
}