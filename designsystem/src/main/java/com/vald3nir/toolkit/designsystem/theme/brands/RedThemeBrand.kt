package com.vald3nir.toolkit.designsystem.theme.brands

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.vald3nir.toolkit.designsystem.theme.providers.BackgroundTheme

object RedThemeBrand : BaseThemeBrand() {

    override val lightColorScheme: ColorScheme = lightColorScheme(
        primary = Color(0xFFBA1A1A),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFFFDAD6),
        onPrimaryContainer = Color(0xFF410002),
        secondary = Color(0xFF5D3F41),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFFFFDADA),
        onSecondaryContainer = Color(0xFF2B1516),
        tertiary = Color(0xFF7D5260),
        onTertiary = Color.White,
        tertiaryContainer = Color(0xFFFFD8E4),
        onTertiaryContainer = Color(0xFF31101D),
        error = Color(0xFFBA1A1A),
        onError = Color.White,
        errorContainer = Color(0xFFFFDAD6),
        onErrorContainer = Color(0xFF410002),
        background = Color(0xFFFFFBFF),
        onBackground = Color(0xFF201A1A),
        surface = Color(0xFFFFFBFF),
        onSurface = Color(0xFF201A1A),
        surfaceVariant = Color(0xFFF5DDDB),
        onSurfaceVariant = Color(0xFF524343),
        inverseSurface = Color(0xFF201A1A),
        inverseOnSurface = Color(0xFFF5EEEE),
        outline = Color(0xFF857372),
    )

    override val darkColorScheme: ColorScheme = darkColorScheme(
        primary = Color(0xFFFFB4AB),
        onPrimary = Color(0xFF690005),
        primaryContainer = Color(0xFF93000A),
        onPrimaryContainer = Color(0xFFFFDAD6),
        secondary = Color(0xFFE7BDBE),
        onSecondary = Color(0xFF44292B),
        secondaryContainer = Color(0xFF5D3F41),
        onSecondaryContainer = Color(0xFFFFDADA),
        tertiary = Color(0xFFEFB8C8),
        onTertiary = Color(0xFF492532),
        tertiaryContainer = Color(0xFF633B48),
        onTertiaryContainer = Color(0xFFFFD8E4),
        error = Color(0xFFFFB4AB),
        onError = Color(0xFF690005),
        errorContainer = Color(0xFF93000A),
        onErrorContainer = Color(0xFFFFDAD6),
        background = Color(0xFF201A1A),
        onBackground = Color(0xFFECE0E0),
        surface = Color(0xFF201A1A),
        onSurface = Color(0xFFECE0E0),
        surfaceVariant = Color(0xFF524343),
        onSurfaceVariant = Color(0xFFD8C2C1),
        inverseSurface = Color(0xFFECE0E0),
        inverseOnSurface = Color(0xFF201A1A),
        outline = Color(0xFFA08C8B),
    )

    override val lightBackgroundTheme = BackgroundTheme(
        color = Color(0xFFF5EEEE)
    )

    override val darkBackgroundTheme = BackgroundTheme(
        color = Color(0xFF1A1111)
    )
}