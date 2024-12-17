package com.currency.exchange.app.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.currency.exchange.app.ui.screens.components.topbar.CurrenciesBar
import com.currency.exchange.app.ui.screens.components.topbar.DashboardBar
import com.currency.exchange.app.ui.screens.navigation.AppNavHost
import com.currency.exchange.datamodule.domain.model.Screen
import com.currency.exchange.datamodule.domain.model.routeToScreen
import com.currency.exchange.app.ui.theme.CurrencyAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyAppTheme(0) {

                val navController = rememberNavController()
                LaunchedEffect(key1 = viewModel.screenFlow) {
                    viewModel.screenFlow.collectLatest { screen ->
                        screen ?: run {
                            finish()
                            return@collectLatest
                        }

                        if (navController.currentBackStackEntry?.destination?.route != screen.route) {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                }

                val navBackStackEntry = navController.currentBackStackEntryAsState().value
                val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Dashboard.route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        when (currentRoute.routeToScreen()) {
                            Screen.Dashboard -> DashboardBar()
                            Screen.Currencies -> CurrenciesBar { viewModel.navigateBack() }
                            else -> {}
                        }
                    }
//                    bottomBar = {
//                        BottomNavBar(currentRoute = currentRoute) { route ->
//                            viewModel.moveTo(route)
//                        }
//                    }
                ) { innerPadding ->
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)) {
                        AppNavHost(navController = navController)
                    }
                }
            }
        }
    }
}