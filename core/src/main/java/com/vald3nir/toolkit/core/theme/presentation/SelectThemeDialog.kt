package com.vald3nir.toolkit.core.theme.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.toolkit.core.R
import com.vald3nir.toolkit.core.theme.domain.AppThemeDTO
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceWidth
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingLg
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitTextButton
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitLoadingWheel
import com.vald3nir.toolkit.designsystem.components.selectors.ToolkitRadioButtonGroup
import com.vald3nir.toolkit.designsystem.components.selectors.ToolkitRadioButtonGroupType
import com.vald3nir.toolkit.designsystem.components.selectors.ToolkitSwitch
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme
import com.vald3nir.toolkit.designsystem.theme.domain.ThemeBrandEnum
import com.vald3nir.toolkit.designsystem.theme.domain.UIThemeConfigEnum
import com.vald3nir.toolkit.designsystem.theme.supportsDynamicTheming

@Composable
fun SelectThemeDialog(
    onDismiss: () -> Unit,
    viewModel: SelectThemeViewModel = hiltViewModel(),
) {
    val settingsUiState by viewModel.selectThemeUiState.collectAsStateWithLifecycle()
    SelectThemeDialog(
        onDismiss = onDismiss,
        selectThemeUiState = settingsUiState,
        onChangeThemeBrand = viewModel::updateThemeBrand,
        onChangeDynamicColorPreference = viewModel::updateDynamicColorPreference,
        onChangeDarkThemeConfig = viewModel::updateDarkThemeConfig,
    )
}


@Composable
private fun SelectThemeDialog(
    selectThemeUiState: SelectThemeUiState,
    supportDynamicColor: Boolean = supportsDynamicTheming(),
    onDismiss: () -> Unit = {},
    onChangeThemeBrand: (themeBrand: ThemeBrandEnum?) -> Unit = {},
    onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit = {},
    onChangeDarkThemeConfig: (uIThemeConfigEnum: UIThemeConfigEnum?) -> Unit = {},
) {
    AlertDialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier.widthIn(max = LocalConfiguration.current.screenWidthDp.dp - 80.dp),
        onDismissRequest = { onDismiss() },
        title = {
            ToolkitText(text = stringResource(R.string.select_theme_title), style = ToolkitTextStyle.TitleLarge)
        },
        text = {
            HorizontalDivider()
            Column {
                when (selectThemeUiState) {
                    SelectThemeUiState.Loading -> {
                        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                            ToolkitSpaceHeight()
                            ToolkitLoadingWheel()
                        }
                    }
                    is SelectThemeUiState.Success -> {
                        SettingsPanel(
                            settings = selectThemeUiState.settings,
                            supportDynamicColor = supportDynamicColor,
                            onChangeThemeBrand = onChangeThemeBrand,
                            onChangeDynamicColorPreference = onChangeDynamicColorPreference,
                            onChangeDarkThemeConfig = onChangeDarkThemeConfig,
                        )
                    }
                }
            }
        },
        confirmButton = {
            ToolkitTextButton(
                onClick = onDismiss,
                modifier = Modifier.padding(horizontal = ToolkitSpacingSm),
                label = stringResource(R.string.select_theme_btn_ok)
            )
        },
    )
}

@Composable
private fun ColumnScope.SettingsPanel(
    settings: AppThemeDTO,
    supportDynamicColor: Boolean,
    onChangeThemeBrand: (themeBrand: ThemeBrandEnum?) -> Unit,
    onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit,
    onChangeDarkThemeConfig: (uIThemeConfigEnum: UIThemeConfigEnum?) -> Unit,
) {
    AnimatedVisibility(visible = !settings.useDynamicColor) {
        Column {
            ToolkitSpaceHeight()
            ToolkitText(text = stringResource(R.string.select_theme_brands), style = ToolkitTextStyle.TitleMedium)
            ToolkitRadioButtonGroup(
                items = ThemeBrandEnum.entries.map { it.key },
                viewType = ToolkitRadioButtonGroupType.GRID,
                selectedValue = settings.themeBrand?.key,
                onItemSelected = {
                    onChangeThemeBrand(ThemeBrandEnum.fromKey(it))
                }
            )
        }
    }
    AnimatedVisibility(visible = supportDynamicColor) {
        Column {
            ToolkitSpaceHeight()
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                ToolkitText(text = stringResource(R.string.select_theme_use_dynamic_color), style = ToolkitTextStyle.TitleMedium)
                ToolkitSpaceWidth(ToolkitSpacingLg)
                ToolkitSwitch(
                    startEnable = settings.useDynamicColor,
                    onCheckedChange = { onChangeDynamicColorPreference(it) }
                )
            }
        }
    }
    ToolkitSpaceHeight()
    ToolkitText(text = stringResource(R.string.select_theme_preferences), style = ToolkitTextStyle.TitleMedium)
    ToolkitRadioButtonGroup(
        items = UIThemeConfigEnum.entries.map { it.key },
        viewType = ToolkitRadioButtonGroupType.GRID,
        selectedValue = settings.themeConfigEnum?.key,
        onItemSelected = {
            onChangeDarkThemeConfig(UIThemeConfigEnum.fromKey(it))
        }
    )
}

@Preview
@Composable
private fun PreviewDialog() {
    ToolkitTheme {
        SelectThemeDialog(
            selectThemeUiState = SelectThemeUiState.Success(
                AppThemeDTO(
                    themeBrand = ThemeBrandEnum.RED,
                    themeConfigEnum = UIThemeConfigEnum.FOLLOW_SYSTEM,
                    useDynamicColor = false,
                ),
            ),
        )
    }
}

@Preview
@Composable
private fun PreviewDialogDynamicColor() {
    ToolkitTheme {
        SelectThemeDialog(
            selectThemeUiState = SelectThemeUiState.Success(
                AppThemeDTO(
                    themeBrand = ThemeBrandEnum.BLUE,
                    themeConfigEnum = UIThemeConfigEnum.DARK,
                    useDynamicColor = true,
                ),
            ),
        )
    }
}

@Preview
@Composable
private fun PreviewDialogLoading() {
    ToolkitTheme {
        SelectThemeDialog(
            selectThemeUiState = SelectThemeUiState.Loading,
        )
    }
}