package com.vald3nir.toolkit.designsystem.theme.brands

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.vald3nir.toolkit.designsystem.theme.providers.BackgroundTheme

object BlueThemeBrand : BaseThemeBrand() {

    override val lightColorScheme: ColorScheme = lightColorScheme(
        primary = Color(0xFF3880FF),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFD8E2FF),
        onPrimaryContainer = Color(0xFF001D36),
        secondary = Color(0xFF46474A),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFFE2E2E6),
        onSecondaryContainer = Color(0xFF1A1C1E),
        tertiary = Color(0xFF5260FF),
        onTertiary = Color.White,
        tertiaryContainer = Color(0xFFDDE1FF),
        onTertiaryContainer = Color(0xFF001453),
        error = Color(0xFFBA1A1A),
        onError = Color.White,
        errorContainer = Color(0xFFFFDAD6),
        onErrorContainer = Color(0xFF410002),
        background = Color.White,
        onBackground = Color(0xFF191C1E),
        surface = Color.White,
        onSurface = Color(0xFF191C1E),
        surfaceVariant = Color(0xFFE1E2EC),
        onSurfaceVariant = Color(0xFF44474E),
        inverseSurface = Color(0xFF191C1E),
        inverseOnSurface = Color(0xFFF1F0F4),
        outline = Color(0xFF74777F),
    )

    override val darkColorScheme: ColorScheme = darkColorScheme(
        primary = Color(0xFFADC9FF),
        onPrimary = Color(0xFF003258),
        primaryContainer = Color(0xFF00497D),
        onPrimaryContainer = Color(0xFFD8E2FF),
        secondary = Color(0xFFC6C6C9),
        onSecondary = Color(0xFF2F3033),
        secondaryContainer = Color(0xFF46474A),
        onSecondaryContainer = Color(0xFFE2E2E6),
        tertiary = Color(0xFFBCC2FF),
        onTertiary = Color(0xFF232C9E),
        tertiaryContainer = Color(0xFF3B44B6),
        onTertiaryContainer = Color(0xFFDDE1FF),
        error = Color(0xFFFFB4AB),
        onError = Color(0xFF690005),
        errorContainer = Color(0xFF93000A),
        onErrorContainer = Color(0xFFFFDAD6),
        background = Color(0xFF121212),
        onBackground = Color(0xFFE2E2E6),
        surface = Color(0xFF1E1E1E),
        onSurface = Color(0xFFE2E2E6),
        surfaceVariant = Color(0xFF44474E),
        onSurfaceVariant = Color(0xFFC4C6D0),
        inverseSurface = Color(0xFFE2E2E6),
        inverseOnSurface = Color(0xFF191C1E),
        outline = Color(0xFF8E9199),
    )

    override val lightBackgroundTheme = BackgroundTheme(
        color = Color(0xFFF4F5F8)
    )

    override val darkBackgroundTheme = BackgroundTheme(
        color = Color.Black
    )
}