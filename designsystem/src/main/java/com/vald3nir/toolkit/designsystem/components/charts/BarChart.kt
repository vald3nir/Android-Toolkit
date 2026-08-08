package com.vald3nir.toolkit.designsystem.components.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

private val BarChartPalette = listOf(
    Color(0xFF4A90E2),
    Color(0xFF50E3C2),
    Color(0xFFF5A623),
    Color(0xFFD0021B),
    Color(0xFF9013FE),
    Color(0xFF7ED321)
)

@Composable
fun ToolkitBarChart(
    modifier: Modifier = Modifier,
    title: String,
    data: List<ItemChartDTO>,
    onClickBar: (index: Int) -> Unit = {},
) {
    if (data.isEmpty()) return

    val textMeasurer = rememberTextMeasurer()
    val scrollState = rememberScrollState()
    val labelsColor = MaterialTheme.colorScheme.onSurface

    val barWidthFixed = 60.dp
    val maxHeightChart = 350.dp
    val barSpacing = 15.dp
    val totalBarWidth = barWidthFixed + barSpacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = ToolkitSpacingMd)
    ) {
        ToolkitText(text = title, style = ToolkitTextStyle.TitleMedium)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
        ) {
            Canvas(
                modifier = Modifier
                    .height(maxHeightChart)
                    .size(width = totalBarWidth * data.size, height = maxHeightChart)
                    .pointerInput(data) {
                        detectTapGestures { offset ->
                            val index = (offset.x / totalBarWidth.toPx()).toInt()
                            if (index in data.indices) onClickBar(index)
                        }
                    }
            ) {
                val canvasHeight = size.height
                val bottomPadding = 80f
                val topPadding = 60f
                val chartHeight = canvasHeight - bottomPadding - topPadding
                val maxValue = data.maxOf { it.value }.coerceAtLeast(1.0f)

                data.forEachIndexed { index, item ->
                    val xStart = index * totalBarWidth.toPx() + (barSpacing.toPx() / 2)
                    val bWidth = barWidthFixed.toPx()

                    val normalizedHeight = (item.value / maxValue) * chartHeight
                    val topY = (canvasHeight - bottomPadding) - normalizedHeight
                    val baseY = canvasHeight - bottomPadding

                    val baseColor = BarChartPalette[index % BarChartPalette.size]

                    drawRect(
                        brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                            colors = listOf(baseColor, baseColor.copy(alpha = 0.3f)),
                            startY = topY,
                            endY = baseY
                        ),
                        topLeft = Offset(xStart, topY),
                        size = androidx.compose.ui.geometry.Size(bWidth, normalizedHeight)
                    )

                    val dateText = textMeasurer.measure(
                        text = item.label,
                        style = TextStyle(fontSize = 10.sp, color = labelsColor)
                    )
                    drawText(
                        textLayoutResult = dateText,
                        topLeft = Offset(
                            x = xStart + (bWidth / 2) - (dateText.size.width / 2),
                            y = baseY + 15f
                        )
                    )

                    val valueText = textMeasurer.measure(
                        text = formatChartValue(item.value),
                        style = TextStyle(fontSize = 11.sp, color = labelsColor, fontWeight = FontWeight.Bold)
                    )
                    drawText(
                        textLayoutResult = valueText,
                        topLeft = Offset(
                            x = xStart + (bWidth / 2) - (valueText.size.width / 2),
                            y = topY - 40f
                        )
                    )
                }

                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, canvasHeight - bottomPadding),
                    end = Offset(size.width, canvasHeight - bottomPadding),
                    strokeWidth = 2f
                )
            }
        }
    }
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
            ToolkitBarChart(
                title = "Titulo",
                data = listOf(
                    ItemChartDTO(750f, "Jan"),
                    ItemChartDTO(200f, "Fev"),
                    ItemChartDTO(300f, "Mar"),
                    ItemChartDTO(400f, "Abr"),
                    ItemChartDTO(500f, "Mai"),
                )
            )
        }
    }
}