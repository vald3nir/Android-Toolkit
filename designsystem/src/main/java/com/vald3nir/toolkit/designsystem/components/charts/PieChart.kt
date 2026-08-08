package com.vald3nir.toolkit.designsystem.components.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ToolkitPieChart(modifier: Modifier = Modifier, label: String = "", data: List<ItemChartDTO>, colors: List<Color>, labelsColor: Int = android.graphics.Color.BLACK) {
    if (data.isEmpty() || colors.isEmpty()) return

    val totalSum = data.sum()
    var startAngle = -90f
    Canvas(modifier = modifier.size(300.dp)) {
        val radius = size.minDimension / 2
        val labelRadius = radius * 0.6f

        data.forEachIndexed { index, item ->
            val fraction = if (totalSum == 0f) 0f else (item.value / totalSum)
            val sweepAngle = fraction * 360f
            val percentage = fraction * 100
            val angleInDegrees = startAngle + sweepAngle / 2
            val angleInRadians = Math.toRadians(angleInDegrees.toDouble())
            val x = center.x + labelRadius * cos(angleInRadians).toFloat()
            val y = center.y + labelRadius * sin(angleInRadians).toFloat()

            drawArc(
                color = colors[index % colors.size], startAngle = startAngle, sweepAngle = sweepAngle, useCenter = true
            )

            drawIntoCanvas { canvas ->
                val paint = android.graphics.Paint().apply {
                    color = labelsColor
                    textSize = 36f
                    textAlign = android.graphics.Paint.Align.CENTER
                    isAntiAlias = true
                }
                canvas.nativeCanvas.drawText(item.label, x, y, paint)
                canvas.nativeCanvas.drawText("${percentage.toInt()}%", x + 10, y - 30, paint)
            }
            startAngle += sweepAngle
        }

        drawIntoCanvas { canvas ->
            val paint = android.graphics.Paint().apply {
                color = labelsColor
                textSize = 48f
                textAlign = android.graphics.Paint.Align.CENTER
                isAntiAlias = true
            }
            canvas.nativeCanvas.drawText(label, center.x, center.y, paint)
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(300.dp)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(ToolkitSpacingMd),
            contentAlignment = Alignment.Center
        ) {
            ToolkitPieChart(
                label = "Consumo",
                data = listOf(
                    ItemChartDTO(40f, "A"),
                    ItemChartDTO(30f, "B"),
                    ItemChartDTO(30f, "C")
                ),
                colors = listOf(Color(0xFF4A90E2), Color(0xFF50E3C2), Color(0xFFF5A623))
            )
        }
    }
}