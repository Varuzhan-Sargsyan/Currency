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
import com.currency.exchange.app.ui.screens.navigation.AppNavHost
import com.currency.exchange.app.ui.screens.navigation.BottomNavBar
import com.currency.exchange.app.ui.screens.navigation.Screen
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()
                    val navBackStackEntry = navController.currentBackStackEntryAsState().value
                    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Dashboard.route

                    LaunchedEffect(key1 = viewModel.screen) {
                        viewModel.screen.collectLatest { screen ->
                            if (navController.currentBackStackEntry?.destination?.route != screen.route) {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    }

                    Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                        AppNavHost(navController = navController)

                        BottomNavBar(currentRoute = currentRoute) { route ->
                            viewModel.moveTo(route)
                        }
                    }
                }
            }
        }
    }
}