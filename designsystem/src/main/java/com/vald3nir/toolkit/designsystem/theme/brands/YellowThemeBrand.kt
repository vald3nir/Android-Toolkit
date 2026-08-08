package com.vald3nir.toolkit.designsystem.theme.brands

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.vald3nir.toolkit.designsystem.theme.providers.BackgroundTheme

object YellowThemeBrand : BaseThemeBrand() {

    override val lightColorScheme: ColorScheme = lightColorScheme(
        primary = Color(0xFF645E00),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFFFE247),
        onPrimaryContainer = Color(0xFF1E1C00),
        secondary = Color(0xFF625E42),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFFE9E2BE),
        onSecondaryContainer = Color(0xFF1E1B05),
        tertiary = Color(0xFF3F6662),
        onTertiary = Color.White,
        background = Color(0xFFFFFBFF),
        onBackground = Color(0xFF1D1B16),
        surface = Color(0xFFFFFBFF),
        onSurface = Color(0xFF1D1B16),
        surfaceVariant = Color(0xFFE7E2D1),
        onSurfaceVariant = Color(0xFF49473A),
        outline = Color(0xFF7A7768)
    )

    override val darkColorScheme: ColorScheme = darkColorScheme(
        primary = Color(0xFFFFE247),
        onPrimary = Color(0xFF333000),
        primaryContainer = Color(0xFF4B4600),
        onPrimaryContainer = Color(0xFFFFE247),
        secondary = Color(0xFFCDC6A3),
        onSecondary = Color(0xFF333017),
        secondaryContainer = Color(0xFF4A472C),
        onSecondaryContainer = Color(0xFFE9E2BE),
        tertiary = Color(0xFFA6CFCB),
        onTertiary = Color(0xFF0E3734),
        background = Color(0xFF141311),
        onBackground = Color(0xFFE6E2D9),
        surface = Color(0xFF141311),
        onSurface = Color(0xFFE6E2D9),
        surfaceVariant = Color(0xFF49473A),
        onSurfaceVariant = Color(0xFFCBC6B5),
        outline = Color(0xFF949181)
    )

    override val lightBackgroundTheme = BackgroundTheme(color = Color(0xFFF0F1F3))

    override val darkBackgroundTheme = BackgroundTheme(color = Color(0xFF0B0E11))
}