package com.currency.exchange.app.ui.screens.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.currency.exchange.app.R
import com.currency.exchange.app.ui.utils.iconModifier
import com.currency.exchange.datamodule.domain.model.Screen

@Composable
fun BottomNavBar(currentRoute: String, onItemSelected: (Screen) -> Unit) {
    val items = listOf(Screen.Dashboard, Screen.Currencies)
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
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
        Screen.Dashboard -> Icon(modifier = Modifier.iconModifier(), imageVector = Icons.Default.Home, contentDescription = screen.title)
        Screen.Currencies -> Icon(modifier = Modifier.iconModifier(), painter = painterResource(R.drawable.ic_currencies), contentDescription = screen.title)
        Screen.Settings -> Icon(modifier = Modifier.iconModifier(), imageVector = Icons.Default.Settings, contentDescription = screen.title)
    }
}
