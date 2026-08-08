package com.vald3nir.toolkit.designsystem.components.containers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitSurface(
    modifier: Modifier = Modifier,
    paddingValue: Dp = ToolkitSpacingMd,
    content: @Composable (PaddingValues) -> Unit,
) {
    val contentPadding = WindowInsets
        .systemBars
        .add(WindowInsets(left = paddingValue, top = paddingValue, right = paddingValue, bottom = paddingValue))
        .asPaddingValues()
    Surface(modifier = modifier) {
        content(contentPadding)
    }
}

@ThemePreviews
@Composable
private fun SurfacePreview() {
    ToolkitPreviewContainer(modifier = Modifier.size(220.dp, 120.dp)) {
        ToolkitSurface(modifier = Modifier.fillMaxSize()) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                ToolkitText(text = "Surface", style = ToolkitTextStyle.BodyMedium)
            }
        }
    }
}