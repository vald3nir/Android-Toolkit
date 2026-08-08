package com.vald3nir.toolkit.core.theme.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vald3nir.toolkit.core.baseclasses.MainUiState
import com.vald3nir.toolkit.core.theme.domain.AppThemeDTO
import com.vald3nir.toolkit.core.theme.repository.ThemaRepository
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum
import com.vald3nir.toolkit.designsystem.theme.domain.UIThemeConfigEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class SelectThemeViewModel @Inject constructor(private val themaRepository: ThemaRepository) : ViewModel() {

    val uiState: StateFlow<MainUiState> = themaRepository.appThemaFlow.map {
        MainUiState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )

    val selectThemeUiState: StateFlow<SelectThemeUiState> = themaRepository.appThemaFlow
        .map { userData ->
            SelectThemeUiState.Success(
                settings = AppThemeDTO(
                    themeBrand = userData.themeBrand,
                    useDynamicColor = userData.useDynamicColor,
                    themeConfigEnum = userData.themeConfigEnum,
                ),
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5.seconds.inWholeMilliseconds),
            initialValue = SelectThemeUiState.Loading,
        )

    fun updateThemeBrand(themeBrand: ThemeBrandEnum?) {
        viewModelScope.launch {
            themaRepository.setThemeBrand(themeBrand)
        }
    }

    fun updateDarkThemeConfig(uIThemeConfigEnum: UIThemeConfigEnum?) {
        viewModelScope.launch {
            themaRepository.setDarkThemeConfig(uIThemeConfigEnum)
        }
    }

    fun updateDynamicColorPreference(useDynamicColor: Boolean) {
        viewModelScope.launch {
            themaRepository.setDynamicColorPreference(useDynamicColor)
        }
    }
}