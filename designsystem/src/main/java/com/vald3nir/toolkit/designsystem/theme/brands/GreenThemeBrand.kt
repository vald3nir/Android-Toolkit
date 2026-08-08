package com.vald3nir.toolkit.designsystem.theme.brands

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.vald3nir.toolkit.designsystem.theme.providers.BackgroundTheme

object GreenThemeBrand : BaseThemeBrand() {

    override val lightColorScheme: ColorScheme = lightColorScheme(
        primary = Color(0xFF006D32),
        onPrimary = Color.White,
        primaryContainer = Color(0xFF9EF6AF),
        onPrimaryContainer = Color(0xFF00210B),
        secondary = Color(0xFF384B3B),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFFD2E8D3),
        onSecondaryContainer = Color(0xFF0D1F12),
        tertiary = Color(0xFF006780),
        onTertiary = Color.White,
        tertiaryContainer = Color(0xFFB8EAFF),
        onTertiaryContainer = Color(0xFF001F28),
        error = Color(0xFFBA1A1A),
        onError = Color.White,
        errorContainer = Color(0xFFFFDAD6),
        onErrorContainer = Color(0xFF410002),
        background = Color(0xFFF7FBF6),
        onBackground = Color(0xFF0F1511),
        surface = Color(0xFFF7FBF6),
        onSurface = Color(0xFF0F1511),
        surfaceVariant = Color(0xFFDDE5DB),
        onSurfaceVariant = Color(0xFF414942),
        inverseSurface = Color(0xFF0F1511),
        inverseOnSurface = Color(0xFFF0F2EE),
        outline = Color(0xFF717971),
    )

    override val darkColorScheme: ColorScheme = darkColorScheme(
        primary = Color(0xFF82D995),
        onPrimary = Color(0xFF003919),
        primaryContainer = Color(0xFF005225),
        onPrimaryContainer = Color(0xFF9EF6AF),
        secondary = Color(0xFFB6CCB8),
        onSecondary = Color(0xFF223526),
        secondaryContainer = Color(0xFF384B3B),
        onSecondaryContainer = Color(0xFFD2E8D3),
        tertiary = Color(0xFF5DD5FC),
        onTertiary = Color(0xFF003544),
        tertiaryContainer = Color(0xFF004D61),
        onTertiaryContainer = Color(0xFFB8EAFF),
        error = Color(0xFFFFB4AB),
        onError = Color(0xFF690005),
        errorContainer = Color(0xFF93000A),
        onErrorContainer = Color(0xFFFFDAD6),
        background = Color(0xFF0F1511),
        onBackground = Color(0xFFE1E3DF),
        surface = Color(0xFF0F1511),
        onSurface = Color(0xFFE1E3DF),
        surfaceVariant = Color(0xFF414942),
        onSurfaceVariant = Color(0xFFC1C9BF),
        inverseSurface = Color(0xFFE1E3DF),
        inverseOnSurface = Color(0xFF0F1511),
        outline = Color(0xFF8B938A),
    )

    override val lightBackgroundTheme = BackgroundTheme(
        color = Color(0xFFF0F2EE)
    )

    override val darkBackgroundTheme = BackgroundTheme(
        color = Color(0xFF0A0F0D)
    )
}