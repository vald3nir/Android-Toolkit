package com.vald3nir.toolkit.designsystem.theme.brands

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.theme.providers.BackgroundTheme

object PurpleThemaBrand : BaseThemeBrand() {

    override val lightColorScheme: ColorScheme = lightColorScheme(
        primary = Color(0xFF8B418F),
        onPrimary = Color.White,
        primaryContainer = Color(0xFFFFD6FA),
        onPrimaryContainer = Color(0xFF36003C),
        secondary = Color(0xFFA23F16),
        onSecondary = Color.White,
        secondaryContainer = Color(0xFFFFDBCF),
        onSecondaryContainer = Color(0xFF380D00),
        tertiary = Color(0xFF006780),
        onTertiary = Color.White,
        tertiaryContainer = Color(0xFFB8EAFF),
        onTertiaryContainer = Color(0xFF001F28),
        error = Color(0xFFBA1A1A),
        onError = Color.White,
        errorContainer = Color(0xFFFFDAD6),
        onErrorContainer = Color(0xFF410002),
        background = Color(0xFFFCFCFC),
        onBackground = Color(0xFF201A1B),
        surface = Color(0xFFFCFCFC),
        onSurface = Color(0xFF201A1B),
        surfaceVariant = Color(0xFFEDDEE8),
        onSurfaceVariant = Color(0xFF4D444C),
        inverseSurface = Color(0xFF362F30),
        inverseOnSurface = Color(0xFFFAEEEF),
        outline = Color(0xFF7F747C),
    )

    override val darkColorScheme: ColorScheme = darkColorScheme(
        primary = Color(0xFFFFA9FE),
        onPrimary = Color(0xFF560A5D),
        primaryContainer = Color(0xFF702776),
        onPrimaryContainer = Color(0xFFFFD6FA),
        secondary = Color(0xFFFFB59B),
        onSecondary = Color(0xFF5B1A00),
        secondaryContainer = Color(0xFF812800),
        onSecondaryContainer = Color(0xFFFFDBCF),
        tertiary = Color(0xFF5DD5FC),
        onTertiary = Color(0xFF003544),
        tertiaryContainer = Color(0xFF004D61),
        onTertiaryContainer = Color(0xFFB8EAFF),
        error = Color(0xFFFFB4AB),
        onError = Color(0xFF690005),
        errorContainer = Color(0xFF93000A),
        onErrorContainer = Color(0xFFFFDAD6),
        background = Color(0xFF201A1B),
        onBackground = Color(0xFFECDFE0),
        surface = Color(0xFF201A1B),
        onSurface = Color(0xFFECDFE0),
        surfaceVariant = Color(0xFF4D444C),
        onSurfaceVariant = Color(0xFFD0C3CC),
        inverseSurface = Color(0xFFECDFE0),
        inverseOnSurface = Color(0xFF201A1B),
        outline = Color(0xFF998D96),
    )

    override val lightBackgroundTheme = BackgroundTheme(
        color = Color(0xFFFCFCFC),
        tonalElevation = 2.dp
    )

    override val darkBackgroundTheme = BackgroundTheme(
        color = Color(0xFFFCFCFC),
        tonalElevation = 2.dp
    )
}