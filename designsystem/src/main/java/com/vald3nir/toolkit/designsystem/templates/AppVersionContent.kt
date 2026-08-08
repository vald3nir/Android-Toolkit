package com.vald3nir.toolkit.designsystem.templates

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.R
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXs
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitAppVersionContent(
    versionName: String? = null,
    versionCode: String? = null
) {
    val versionLabel = "${versionName.orEmpty()} (Build ${versionCode.orEmpty()})"
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = ToolkitSpacingMd),
        horizontalAlignment = CenterHorizontally
    ) {
        HorizontalDivider()
        ToolkitSpaceHeight()
        ToolkitText(text = stringResource(R.string.app_version), style = ToolkitTextStyle.BodySmall)
        ToolkitSpaceHeight(ToolkitSpacingXs)
        ToolkitText(text = versionLabel, style = ToolkitTextStyle.LabelSmall)
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 150.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            ToolkitAppVersionContent(
                versionName = "2026.4.0-dev",
                versionCode = "12",
            )
        }
    }
}