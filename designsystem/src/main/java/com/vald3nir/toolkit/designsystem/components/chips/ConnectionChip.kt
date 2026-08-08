package com.vald3nir.toolkit.designsystem.components.chips

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingLg
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXs
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitConnectionChip(isConnected: Boolean) {
    val pulseAlpha by rememberInfiniteTransition(label = "pulse").animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(800), RepeatMode.Reverse),
        label = "alpha",
    )
    val backgroundColor = if (isConnected) Color(0xFF1B3A2A) else Color(0xFF3A1B1B)
    val statusColor = if (isConnected) Color(0xFF4ADE80) else Color(0xFFF87171)
    val indicatorColor = if (isConnected) statusColor.copy(alpha = pulseAlpha) else statusColor
    val statusText = if (isConnected) "Online" else "Offline"

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(ToolkitSpacingXs),
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(ToolkitSpacingLg)
            )
            .padding(horizontal = ToolkitSpacingMd, vertical = ToolkitSpacingXs)
    ) {
        Box(
            modifier = Modifier
                .size(ToolkitSpacingSm)
                .background(
                    color = indicatorColor,
                    shape = CircleShape
                )
        )
        ToolkitText(
            text = statusText,
            textColor = statusColor,
            style = ToolkitTextStyle.BodyMedium
        )
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(160.dp, 120.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ToolkitConnectionChip(isConnected = true)
                ToolkitSpaceHeight()
                ToolkitConnectionChip(isConnected = false)
            }
        }
    }
}
