package com.vald3nir.toolkit.core.theme.presentation

import com.vald3nir.toolkit.core.theme.domain.AppThemeDTO

sealed interface SelectThemeUiState {
    data object Loading : SelectThemeUiState
    data class Success(val settings: AppThemeDTO) : SelectThemeUiState
}