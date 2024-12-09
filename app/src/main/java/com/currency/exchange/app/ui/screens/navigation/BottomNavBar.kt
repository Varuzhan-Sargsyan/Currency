package com.currency.exchange.app.ui.screens.navigation

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BoxScope.BottomNavBar(currentRoute: String, onItemSelected: (Screen) -> Unit) {
    val items = listOf(Screen.Dashboard, Screen.Currencies)
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                icon = getIcon(screen),
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = { onItemSelected(screen) }
            )
        }
    }
}

fun getIcon(screen: Screen): @Composable () -> Unit = {
    when (screen) {
        Screen.Dashboard -> Icon(Icons.Default.Home, contentDescription = screen.title)
        Screen.Currencies -> Icon(Icons.Default.Settings, contentDescription = screen.title)
    }
}
