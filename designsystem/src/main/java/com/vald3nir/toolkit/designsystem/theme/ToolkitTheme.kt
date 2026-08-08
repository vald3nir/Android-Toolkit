package com.vald3nir.toolkit.designsystem.theme

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import com.vald3nir.toolkit.designsystem.theme.brands.BaseThemeBrand
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum
import com.vald3nir.toolkit.designsystem.theme.providers.GradientColors
import com.vald3nir.toolkit.designsystem.theme.providers.LocalBackgroundTheme
import com.vald3nir.toolkit.designsystem.theme.providers.LocalGradientColors
import com.vald3nir.toolkit.designsystem.theme.providers.LocalTintTheme
import com.vald3nir.toolkit.designsystem.theme.providers.TintTheme
import com.vald3nir.toolkit.designsystem.theme.text.ToolkitTypography

@Composable
/**
 * Applies the Toolkit theme with default blue brand.
 *
 * @param darkTheme whether to use dark theme
 * @param disableDynamicTheming whether to disable dynamic theming
 * @param content the composable content
 */
fun ToolkitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    disableDynamicTheming: Boolean = true,
    content: @Composable () -> Unit,
) {
    ToolkitTheme(
        darkTheme = darkTheme,
        themeBrandEnum = ThemeBrandEnum.BLUE,
        disableDynamicTheming = disableDynamicTheming,
        content = content,
    )
}

@Composable
/**
 * Applies the Toolkit theme with specified brand.
 *
 * @param darkTheme whether to use dark theme
 * @param themeBrandEnum the theme brand to use
 * @param disableDynamicTheming whether to disable dynamic theming
 * @param content the composable content
 */
fun ToolkitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    themeBrandEnum: ThemeBrandEnum,
    disableDynamicTheming: Boolean,
    content: @Composable () -> Unit,
) {
    val themeBrand = themeBrandEnum.toThemeBrand()
    val dynamicEnabled = !disableDynamicTheming && supportsDynamicTheming()
    val colorScheme = buildColorScheme(themeBrand, darkTheme, dynamicEnabled)
    val gradientColors = colorScheme.buildGradientColor()
    val backgroundTheme = themeBrand.getBackgroundTheme(darkTheme)
    val tintTheme = colorScheme.buildTintTheme(dynamicEnabled)

    // Composition locals
    CompositionLocalProvider(
        LocalGradientColors provides gradientColors,
        LocalBackgroundTheme provides backgroundTheme,
        LocalTintTheme provides tintTheme,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = ToolkitTypography,
            content = content,
        )
    }
}


@SuppressLint("VisibleForTests")
@Composable
private fun buildColorScheme(
    themeBrand: BaseThemeBrand,
    darkTheme: Boolean,
    dynamicEnabled: Boolean
): ColorScheme = if (dynamicEnabled) {
    LocalContext.current.loadDynamicColors(darkTheme)
} else {
    themeBrand.getColorScheme(darkTheme)
}

@RequiresApi(Build.VERSION_CODES.S)
private fun Context.loadDynamicColors(darkTheme: Boolean) = if (darkTheme) {
    dynamicDarkColorScheme(this)
} else {
    dynamicLightColorScheme(this)
}

private fun ColorScheme.buildGradientColor() = GradientColors(
    top = inverseOnSurface,
    bottom = primaryContainer,
    container = surface,
)

private fun ColorScheme.buildTintTheme(dynamicEnabled: Boolean) = if (dynamicEnabled) {
    TintTheme(primary)
} else {
    TintTheme()
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
/**
 * Checks if the device supports dynamic theming (Android S and above).
 *
 * @return true if supported, false otherwise
 */
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S