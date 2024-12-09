package com.currency.exchange.app.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.currency.exchange.app.ui.screens.components.currencies.CurrenciesScreen
import com.currency.exchange.app.ui.screens.components.dashboard.DashboardScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Dashboard.route) {
        composable(Screen.Dashboard.route) {
            DashboardScreen()
        }
        composable(Screen.Currencies.route) {
            CurrenciesScreen()
        }
    }
}
