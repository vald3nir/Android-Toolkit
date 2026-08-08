package com.vald3nir.toolkit.designsystem.components.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitLineChart(
    modifier: Modifier = Modifier,
    title: String,
    data: List<ItemChartDTO>,
    upperLimit: Float? = null,
    lowerLimit: Float? = null,
    lineColor: Color = Color(0xFF4A90E2), // Azul padrão
    upperLimitColor: Color = Color(0xFFFF0000),// Vermelho
    lowerLimitColor: Color = Color(0xFF00AA00) // Verde
) {
    if (data.isEmpty()) return

    val scrollState = rememberScrollState()
    val textPaint = remember {
        android.graphics.Paint().apply {
            color = android.graphics.Color.GRAY
            textSize = 30f
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        ToolkitText(
            text = title,
            style = ToolkitTextStyle.TitleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Container de Scroll
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
        ) {
            val chartDynamicWidth = (data.size * 80).dp.coerceAtLeast(400.dp)

            Canvas(
                modifier = Modifier
                    .width(chartDynamicWidth)
                    .height(300.dp)
                    .padding(16.dp)
            ) {
                val width = size.width
                val height = size.height
                val paddingLeft = 80f // Espaço maior para os valores do eixo Y não sumirem no scroll
                val paddingBottom = 60f
                val paddingRight = 100f
                val chartWidth = width - (paddingLeft + paddingRight)
                val chartHeight = height - (paddingBottom + 40f)

                val maxValue = data.maxOf { it.value }.coerceAtLeast(1.0f)

                drawLine(
                    color = Color.LightGray,
                    start = Offset(paddingLeft, height - paddingBottom),
                    end = Offset(width - paddingRight, height - paddingBottom),
                    strokeWidth = 2f
                )

                val ySteps = 5
                for (i in 0..ySteps) {
                    val fraction = i.toFloat() / ySteps
                    val yValue = maxValue * fraction
                    val yPos = (height - paddingBottom) - (fraction * chartHeight)
                    textPaint.textAlign = android.graphics.Paint.Align.RIGHT

                    drawContext.canvas.nativeCanvas.drawText(
                        formatChartValue(yValue),
                        paddingLeft - 20f,
                        yPos + 10f,
                        textPaint
                    )
                }

                val spaceBetweenPoints = chartWidth / (data.size - 1).coerceAtLeast(1)

                val points = data.mapIndexed { index, item ->
                    val x = paddingLeft + (index * spaceBetweenPoints)
                    val normalizedPower = (item.value / maxValue).coerceIn(0f, 1f)
                    val y = (height - paddingBottom) - (normalizedPower * chartHeight)
                    textPaint.textAlign = android.graphics.Paint.Align.CENTER

                    drawContext.canvas.nativeCanvas.drawText(
                        item.label,
                        x,
                        height - 10f,
                        textPaint
                    )

                    Pair(Offset(x, y), item.value)
                }

                for (i in 0 until points.size - 1) {
                    val pointColor = getPointColor(
                        value = points[i].second,
                        upperLimit = upperLimit,
                        lowerLimit = lowerLimit,
                        lineColor = lineColor,
                        upperLimitColor = upperLimitColor,
                        lowerLimitColor = lowerLimitColor
                    )
                    drawLine(
                        color = pointColor,
                        start = points[i].first,
                        end = points[i + 1].first,
                        strokeWidth = 4f
                    )
                }

                points.forEach { (point, value) ->
                    val pointColor = getPointColor(
                        value = value,
                        upperLimit = upperLimit,
                        lowerLimit = lowerLimit,
                        lineColor = lineColor,
                        upperLimitColor = upperLimitColor,
                        lowerLimitColor = lowerLimitColor
                    )
                    drawCircle(color = pointColor, radius = 6f, center = point)
                }
            }
        }
    }
}

private fun getPointColor(value: Float, upperLimit: Float?, lowerLimit: Float?, lineColor: Color, upperLimitColor: Color, lowerLimitColor: Color) = when {
    upperLimit != null && value > upperLimit -> upperLimitColor
    lowerLimit != null && value < lowerLimit -> lowerLimitColor
    else -> lineColor
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 300.dp)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(ToolkitSpacingMd),
            contentAlignment = Alignment.Center
        ) {
            ToolkitLineChart(
                title = "Titulo",
                data = listOf(
                    ItemChartDTO(700f, "Jan"),
                    ItemChartDTO(450f, "Fev"),
                    ItemChartDTO(300f, "Mar"),
                    ItemChartDTO(400f, "Abr"),
                    ItemChartDTO(200f, "Mai"),
                    ItemChartDTO(600f, "Jun"),
                    ItemChartDTO(700f, "Jul"),
                    ItemChartDTO(800f, "Ago"),
                ),
                upperLimit = 500f,
                lowerLimit = 250f
            )
        }
    }
}