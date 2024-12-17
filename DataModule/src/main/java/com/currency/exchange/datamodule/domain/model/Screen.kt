package com.currency.exchange.datamodule.domain.model

sealed class Screen(
    val route: String,
    val title: String,
) {
    object Dashboard : Screen("dashboard", "Dashboard")
    object Currencies : Screen("currencies", "Currencies")

    fun backRoute() =
        when (this) {
            is Dashboard -> null
            is Currencies -> Dashboard
        }
}

fun String.routeToScreen() =
    when (this) {
        Screen.Dashboard.route -> Screen.Dashboard
        Screen.Currencies.route -> Screen.Currencies
        else -> null
    }