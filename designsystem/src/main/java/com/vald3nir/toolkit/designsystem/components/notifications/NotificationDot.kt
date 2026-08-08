package com.vald3nir.toolkit.designsystem.components.notifications

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.notificationDot(radius: Dp = 5.dp, color: Color? = null, spacing: Dp = 6.dp) = this
    .padding(spacing)
    .composed {
        val targetColor = color ?: MaterialTheme.colorScheme.tertiary
        val radiusPx = with(androidx.compose.ui.platform.LocalDensity.current) { radius.toPx() }
        drawWithContent {
            drawContent()
            val centerX = size.width - (radiusPx * .2f)
            val centerY = radiusPx * .2f
            drawCircle(
                color = targetColor, radius = radiusPx, center = Offset(centerX, centerY)
            )
        }
    }

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(200.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Topic",
                modifier = Modifier.notificationDot(color = Color.Red)
            )
        }
    }
}