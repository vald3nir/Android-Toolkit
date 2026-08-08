package com.vald3nir.toolkit.designsystem.extensions

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitGradientBackground
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme

@Composable
fun ToolkitPreviewContainer(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    ToolkitTheme(disableDynamicTheming = true) {
        ToolkitGradientBackground(modifier = modifier) {
            Column { content() }
        }
    }
}