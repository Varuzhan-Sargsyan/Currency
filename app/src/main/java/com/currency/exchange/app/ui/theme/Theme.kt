package com.currency.exchange.app.ui.theme

import android.app.Activity
import android.os.Build
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
import androidx.compose.ui.res.stringResource
import androidx.core.view.WindowCompat
import com.currency.exchange.app.R
import com.currency.exchange.app.ui.extensions.toColor

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

var theme: Int? = null

@Composable
fun CurrencyAppTheme(
    themeInt: Int?,
    colorCustom: Int? = null,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    theme = themeInt
    val darkTheme = Theme.isDarkTheme(theme = theme)
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
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun MaterialTheme.backgroundColor() =
    if (Theme.isDarkTheme(theme = theme)) SurfaceDark else SurfaceLight

@Composable
fun MaterialTheme.groupViewBackgroundColor() =
    if (Theme.isDarkTheme(theme = theme)) GroupViewBackgroundDark else GroupViewBackgroundLight

@Composable
fun MaterialTheme.sessionBackground(selected: Boolean) =
    if (selected) iconDefaultColor() else groupViewBackgroundColor()

@Composable
fun MaterialTheme.groupViewTextColor() =
    if (!Theme.isDarkTheme(theme = theme)) GroupViewBackgroundDark else GroupViewBackgroundLight

@Composable
fun MaterialTheme.groupViewTextInverseColor() =
    if (Theme.isDarkTheme(theme = theme)) GroupViewBackgroundDark else GroupViewBackgroundLight

@Composable
fun MaterialTheme.iconDefaultColor() =
    if (Theme.isDarkTheme(theme = theme)) IconColorDark else IconColorLight

@Composable
fun MaterialTheme.iconActionColor(enabled: Boolean) : Color =
    if (enabled)
        colorScheme.primary
    else
        colorScheme.outlineVariant

@Composable
fun MaterialTheme.borderDefaultColor() =
    if (!Theme.isDarkTheme(theme = theme)) IconColorDark else IconColorLight

fun MaterialTheme.statusBarColor(status: Boolean) =
    if (status) StatusBarActive else StatusBarInactive

fun MaterialTheme.statusBarTextColor() = Color.White

@Composable
fun MaterialTheme.weightTextColor(isFinal: Boolean) =
    if (isFinal) colorScheme.primary else WeightTextColor

@Composable
fun MaterialTheme.activityItemColor(): Color {
    return groupViewBackgroundColor()
}

@Composable
fun MaterialTheme.activityTextColor(): Color {
    return colorScheme.secondary
}

@Composable
fun MaterialTheme.linkColor(): Color {
    return colorScheme.secondary
}

@Composable
fun MaterialTheme.buttonColor(): Color {
    return colorScheme.secondary
}

@Composable
fun MaterialTheme.bluetoothColor(): Color {
    return BluetoothColor
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialTheme.appBarColorSchema() = TopAppBarDefaults.topAppBarColors(
    containerColor = MaterialTheme.colorScheme.surface
)


object Theme {
    const val LIGHT = 0
    const val DARK = 1
    const val SYSTEM = 2
//    const val CUSTOM = 3

    @Composable
    fun isDarkTheme(theme: Int?) = when(theme) {
        LIGHT -> false
        DARK -> true
        else -> isSystemInDarkTheme()
    }

//    @Composable
//    fun themeName(value: Int) = when (value) {
//        LIGHT -> stringResource(R.string.theme_light)
//        DARK -> stringResource(R.string.theme_dark)
//        else -> stringResource(R.string.theme_system)
//    }
//
//    @Composable
//    fun themeValue(name: String) = when (name) {
//        stringResource(R.string.theme_light) -> LIGHT
//        stringResource(R.string.theme_dark) -> DARK
//        else -> SYSTEM
//    }

    @Composable
    fun themeMapNameValue() = mapOf(
        stringResource(R.string.theme_light) to LIGHT,
        stringResource(R.string.theme_dark) to DARK,
        stringResource(R.string.theme_system) to SYSTEM,
//        stringResource(R.string.theme_custom) to CUSTOM
    )

    @Composable
    fun themeMapValueName() = mapOf(
        LIGHT to stringResource(R.string.theme_light),
        DARK to stringResource(R.string.theme_dark),
        SYSTEM to stringResource(R.string.theme_system),
//        CUSTOM to stringResource(R.string.theme_custom)
    )

//    @Composable
//    fun themeNames() = listOf(
//        stringResource(R.string.theme_light),
//        stringResource(R.string.theme_dark),
//        stringResource(R.string.theme_system)
//    )
//
//    @Composable
//    fun themeValues() = listOf(LIGHT, DARK, SYSTEM)

}
