package com.vald3nir.toolkit.designsystem.theme.domain

data class ThemeSettingsDTO(
    val themaBrandEnum: ThemeBrandEnum = ThemeBrandEnum.BLUE,
    val darkTheme: Boolean = true,
    val disableDynamicTheming: Boolean = true,
)