package com.vald3nir.toolkit.designsystem.components.containers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme
import com.vald3nir.toolkit.designsystem.theme.providers.GradientColors
import com.vald3nir.toolkit.designsystem.theme.providers.LocalBackgroundTheme
import com.vald3nir.toolkit.designsystem.theme.providers.LocalGradientColors
import kotlin.math.tan

@Composable
fun ToolkitBackground(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val backgroundTheme = LocalBackgroundTheme.current
    Surface(
        color = backgroundTheme.color.orTransparent(),
        tonalElevation = backgroundTheme.tonalElevation.orZero(),
        modifier = modifier.fillMaxSize(),
    ) {
        CompositionLocalProvider(LocalAbsoluteTonalElevation provides 0.dp) {
            content()
        }
    }
}

@Composable
fun ToolkitGradientBackground(
    modifier: Modifier = Modifier,
    gradientColors: GradientColors = LocalGradientColors.current,
    content: @Composable () -> Unit,
) {
    val topColor = gradientColors.top.orTransparent()
    val bottomColor = gradientColors.bottom.orTransparent()
    Surface(
        color = gradientColors.container.orTransparent(),
        modifier = modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    val offset = size.height * tan(Math.toRadians(11.06).toFloat())
                    val start = Offset(size.width / 2 + offset / 2, 0f)
                    val end = Offset(size.width / 2 - offset / 2, size.height)
                    val topGradient = Brush.linearGradient(
                        0f to topColor,
                        0.724f to Color.Transparent,
                        start = start,
                        end = end,
                    )
                    val bottomGradient = Brush.linearGradient(
                        0.2552f to Color.Transparent,
                        1f to bottomColor,
                        start = start,
                        end = end,
                    )

                    onDrawBehind {
                        drawRect(topGradient)
                        drawRect(bottomGradient)
                    }
                },
        ) {
            content()
        }
    }
}

@ThemePreviews
@Composable
private fun BackgroundPreview() {
    ToolkitTheme(disableDynamicTheming = true) {
        ToolkitBackground(Modifier.size(100.dp)) {}
    }
}

@ThemePreviews
@Composable
private fun GradientBackgroundPreview() {
    ToolkitTheme(disableDynamicTheming = true) {
        ToolkitGradientBackground(Modifier.size(100.dp)) {}
    }
}

private fun Color.orTransparent() = if (this == Color.Unspecified) Color.Transparent else this

private fun Dp.orZero() = if (this == Dp.Unspecified) 0.dp else this