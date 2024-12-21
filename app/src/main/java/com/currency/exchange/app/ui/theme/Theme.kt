package com.currency.exchange.app.ui.theme

import android.app.Activity
import android.os.Build
import android.view.WindowInsetsController
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.currency.exchange.app.ui.extensions.toColor
import com.currency.exchange.datamodule.domain.model.Theme

private fun darkColorSchemeW(primary: Color = PrimaryDark) = darkColorScheme(
    primary = primary,
    secondary = SecondaryDark,
    tertiary = TertiaryDark,
    background = BackgroundDark,
    error = ErrorDark,
    surfaceBright = SurfaceBrightDark,
    surface = SurfaceDark,
    outlineVariant = OutlineVariantDark
)

private fun lightColorSchemeW(primary: Color = PrimaryLight) = lightColorScheme(
    primary = primary,
    secondary = SecondaryLight,
    tertiary = TertiaryLight,
    background = BackgroundLight,
    error = ErrorLight,
    surfaceBright = SurfaceBrightLight,
    surface = SurfaceLight,
    outlineVariant = OutlineVariantLight
)

@Composable
fun CurrencyAppTheme(
    themeInt: Theme = Theme.light(),
    colorCustom: Int? = null,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    Theme.theme = themeInt
    val darkTheme = Theme.isDarkTheme() || Theme.isSystemTheme() && isSystemInDarkTheme()
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> colorCustom?.let { darkColorSchemeW(it.toColor()) } ?: darkColorSchemeW()
        else -> colorCustom?.let { lightColorSchemeW(it.toColor()) } ?: lightColorSchemeW()
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Set status bar color with proper handling for API levels
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                // For API 30 and above
                val insetsController = window.insetsController
//                window.statusBarColor = colorScheme.primary.toArgb()
                insetsController?.setSystemBarsAppearance(
                    if (darkTheme) 0 else WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            } else {
                // For API below 30
                @Suppress("DEPRECATION")
                window.statusBarColor = colorScheme.primary.toArgb()
                @Suppress("DEPRECATION")
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

//@Composable
//fun MaterialTheme.backgroundColor() =
//    if (Theme.isDarkTheme(theme = theme)) SurfaceDark else SurfaceLight

@Composable
fun MaterialTheme.groupViewBackgroundColor() =
    if (Theme.isDarkTheme() || Theme.isSystemTheme() && isSystemInDarkTheme()) GroupViewBackgroundDark else GroupViewBackgroundLight

@Composable
fun MaterialTheme.groupViewTextColor() =
    if (!Theme.isDarkTheme() || Theme.isSystemTheme() && isSystemInDarkTheme()) GroupViewBackgroundDark else GroupViewBackgroundLight

//@Composable
//fun MaterialTheme.groupViewTextInverseColor() =
//    if (Theme.isDarkTheme(theme = theme)) GroupViewBackgroundDark else GroupViewBackgroundLight

@Composable
fun MaterialTheme.iconDefaultColor() =
    if (Theme.isDarkTheme() || Theme.isSystemTheme() && isSystemInDarkTheme()) IconColorDark else IconColorLight

//@Composable
//fun MaterialTheme.iconActionColor(enabled: Boolean) : Color =
//    if (enabled)
//        colorScheme.primary
//    else
//        colorScheme.outlineVariant

@Composable
fun MaterialTheme.borderDefaultColor() =
    if (!Theme.isDarkTheme() || Theme.isSystemTheme() && isSystemInDarkTheme()) IconColorDark else IconColorLight

//fun MaterialTheme.statusBarColor(status: Boolean) =
//    if (status) StatusBarActive else StatusBarInactive
//
//fun MaterialTheme.statusBarTextColor() = Color.White
//
//@Composable
//fun MaterialTheme.weightTextColor(isFinal: Boolean) =
//    if (isFinal) colorScheme.primary else WeightTextColor
//
//@Composable
//fun MaterialTheme.activityItemColor(): Color {
//    return groupViewBackgroundColor()
//}
//
//@Composable
//fun MaterialTheme.activityTextColor(): Color {
//    return colorScheme.secondary
//}
//
//@Composable
//fun MaterialTheme.linkColor(): Color {
//    return colorScheme.secondary
//}
//
//@Composable
//fun MaterialTheme.buttonColor(): Color {
//    return colorScheme.secondary
//}
//
//@Composable
//fun MaterialTheme.bluetoothColor(): Color {
//    return BluetoothColor
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialTheme.appBarColorSchema() = TopAppBarDefaults.topAppBarColors(
    containerColor = colorScheme.surface
)


