package com.currency.exchange.app.ui.screens.components.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DashboardViewModel : ViewModel() {
    private val _theme = MutableStateFlow("Light Theme")
    val theme: StateFlow<String> = _theme
}