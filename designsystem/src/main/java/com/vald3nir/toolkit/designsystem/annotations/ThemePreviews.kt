package com.vald3nir.toolkit.designsystem.annotations

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme", showSystemUi = false)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme", showSystemUi = false)
annotation class ThemePreviews

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme", showSystemUi = false)
annotation class LightThemePreviews

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme", showSystemUi = false)
annotation class DarkThemePreviews
