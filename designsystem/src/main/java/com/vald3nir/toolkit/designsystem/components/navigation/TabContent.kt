package com.vald3nir.toolkit.designsystem.components.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitTabContent(isVisible: Boolean, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier.graphicsLayer {
            alpha = if (isVisible) 1f else 0f
        }
    ) {
        content()
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 300.dp)) {
        ToolkitTabContent(isVisible = true) {
            ToolkitText(text = "Tab content", style = ToolkitTextStyle.TitleSmall)
        }
    }
}