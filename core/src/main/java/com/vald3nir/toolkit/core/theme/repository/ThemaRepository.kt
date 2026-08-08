package com.vald3nir.toolkit.core.theme.repository

import com.vald3nir.toolkit.core.theme.domain.AppThemeDTO
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum
import com.vald3nir.toolkit.designsystem.theme.domain.UIThemeConfigEnum
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ThemaRepository {
    val appThemaFlow: Flow<AppThemeDTO>
    suspend fun setThemeBrand(themeBrand: ThemeBrandEnum?)
    suspend fun setDarkThemeConfig(themeConfigEnum: UIThemeConfigEnum?)
    suspend fun setDynamicColorPreference(useDynamicColor: Boolean)
}

internal class ThemaRepositoryImpl @Inject constructor(private val themaDataSource: ThemaDataSource) : ThemaRepository {

    override val appThemaFlow: Flow<AppThemeDTO> = themaDataSource.appThemaFlow

    override suspend fun setThemeBrand(themeBrand: ThemeBrandEnum?) {
        themeBrand?.let {
            themaDataSource.setThemeBrand(it)
        }
    }

    override suspend fun setDarkThemeConfig(themeConfigEnum: UIThemeConfigEnum?) {
        themeConfigEnum?.let {
            themaDataSource.setDarkThemeConfig(it)
        }
    }

    override suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        themaDataSource.setDynamicColorPreference(useDynamicColor)
    }
}