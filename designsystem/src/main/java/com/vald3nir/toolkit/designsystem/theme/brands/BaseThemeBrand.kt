package com.vald3nir.toolkit.designsystem.theme.brands

import androidx.compose.material3.ColorScheme
import com.vald3nir.toolkit.designsystem.theme.providers.BackgroundTheme

abstract class BaseThemeBrand {

    abstract val lightColorScheme: ColorScheme
    abstract val darkColorScheme: ColorScheme
    abstract val lightBackgroundTheme: BackgroundTheme
    abstract val darkBackgroundTheme: BackgroundTheme

    fun getColorScheme(darkTheme: Boolean): ColorScheme = if (darkTheme) darkColorScheme else lightColorScheme

    fun getBackgroundTheme(darkTheme: Boolean): BackgroundTheme = if (darkTheme) darkBackgroundTheme else lightBackgroundTheme
}