package com.vald3nir.toolkit.core.theme.domain

import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum
import com.vald3nir.toolkit.designsystem.theme.domain.UIThemeConfigEnum

data class AppThemeDTO(
    val themeBrand: ThemeBrandEnum? = null,
    val themeConfigEnum: UIThemeConfigEnum? = null,
    val useDynamicColor: Boolean = false,
)