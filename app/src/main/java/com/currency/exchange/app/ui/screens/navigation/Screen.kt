package com.currency.exchange.app.ui.screens.navigation

sealed class Screen(val route: String, val title: String) {
    object Dashboard : Screen("dashboard", "Dashboard")
    object Currencies : Screen("currencies", "Currencies")
}
