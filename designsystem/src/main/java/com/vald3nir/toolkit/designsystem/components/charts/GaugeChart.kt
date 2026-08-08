package com.vald3nir.toolkit.designsystem.components.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import kotlin.math.abs

@Composable
fun ToolkitGaugeChart(modifier: Modifier = Modifier, currentValue: Float, maxValue: Float, suffix: String = "") {
    val labelsColor: Int = android.graphics.Color.WHITE
    val chartMaxValue = maxOf(currentValue, maxValue)
    val diff = abs(chartMaxValue - currentValue)
    val data = listOf(
        ItemChartDTO(currentValue, ""),
        ItemChartDTO(diff, ""),
    )
    val colors = listOf(Color.Red, Color.LightGray)
    val label = "${currentValue.toInt()} $suffix".trim()

    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        val totalSum = data.sum()
        var startAngle = -90f
        Canvas(modifier = modifier.size(300.dp)) {
            data.forEachIndexed { index, item ->
                val sweepAngle = if (totalSum == 0f) 0f else (item.value / totalSum) * 360f
                drawArc(
                    color = colors[index], startAngle = startAngle, sweepAngle = sweepAngle, useCenter = true
                )
                startAngle += sweepAngle
            }
            drawIntoCanvas { canvas ->
                val paint = android.graphics.Paint().apply {
                    color = labelsColor
                    textSize = 52f
                    textAlign = android.graphics.Paint.Align.CENTER
                    isAntiAlias = true
                }
                canvas.nativeCanvas.drawText(label, center.x, center.y + 50, paint)
            }
        }
    }
}

@ThemePreviews
@Composable
private fun ToolkitSpeedometerPreview() {
    ToolkitPreviewContainer(modifier = Modifier.size(300.dp)) {
        ToolkitGaugeChart(currentValue = 60f, maxValue = 100f, suffix = "kWh")
    }
}