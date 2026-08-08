package com.vald3nir.toolkit.core.baseclasses

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.vald3nir.toolkit.core.theme.presentation.SelectThemeViewModel
import com.vald3nir.toolkit.core.utils.extensions.isSystemInDarkTheme
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeSettingsDTO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
abstract class BaseActivity : ComponentActivity() {

    private val viewModel: SelectThemeViewModel by viewModels()

    private val uiStateFlow: StateFlow<MainUiState>
        get() = viewModel.uiState

    protected var themeSettings by mutableStateOf(ThemeSettingsDTO())
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { uiStateFlow.value.shouldKeepSplashScreen() }

        // Update the uiState
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                combine(isSystemInDarkTheme(), uiStateFlow) { systemDark, uiState ->
                    ThemeSettingsDTO(
                        themaBrandEnum = uiState.themaEnum,
                        darkTheme = uiState.shouldUseDarkTheme(systemDark),
                        disableDynamicTheming = uiState.shouldDisableDynamicTheming,
                    )
                }.onEach { themeSettings = it }.map { it.darkTheme }.distinctUntilChanged().collect { darkTheme ->
                    enableEdgeToEdge(
                        statusBarStyle = SystemBarStyle.auto(
                            lightScrim = Color.TRANSPARENT,
                            darkScrim = Color.TRANSPARENT,
                        ) { darkTheme },
                        navigationBarStyle = SystemBarStyle.auto(
                            lightScrim = lightScrim,
                            darkScrim = darkScrim,
                        ) { darkTheme },
                    )
                }
            }
        }
    }
}

private val lightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
private val darkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)

