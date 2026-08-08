package com.vald3nir.toolkit.designsystem.components.charts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

// ─── Colors ───────────────────────────────────────────────────────────────────
val ProgressChartColorDanger = Color(0xFFE53935)
val ProgressChartColorWaring = Color(0xFFFFD600)
val ProgressChartColorNormal = Color(0xFF43A047)
private val ProgressChartCardColor = Color(0xFFEEEEEE)

@Composable
fun ToolkitProgressChart(
    title: String,
    currentValue: Int,
    minValue: Int = 0,
    maxValue: Int = 100,
    unit: String = "",
    thresholds: List<ProgressChartColorThresholdDTO> = listOf(
        ProgressChartColorThresholdDTO(25, ProgressChartColorNormal),
        ProgressChartColorThresholdDTO(50, ProgressChartColorWaring),
        ProgressChartColorThresholdDTO(100, ProgressChartColorDanger)
    ),
    defaultColor: Color = ProgressChartColorNormal,
    onClick: () -> Unit = {}
) {
    val range = maxValue - minValue
    val fraction = if (range > 0) ((currentValue - minValue).toFloat() / range).coerceIn(0f, 1f) else 0f
    val percentage = (fraction * 100).toInt()

    // Busca a primeira cor cujo limite é maior ou igual à porcentagem atual
    val statusColor = thresholds
        .sortedBy { it.upToPercentage } // Garante a ordem crescente para a lógica funcionar
        .firstOrNull { percentage <= it.upToPercentage }?.color ?: defaultColor

    Column(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .clip(RoundedCornerShape(ToolkitSpacingMd))
            .background(statusColor.copy(alpha = 0.3f))
            .border(2.dp, statusColor.copy(alpha = 0.3f), RoundedCornerShape(ToolkitSpacingMd))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ToolkitText(
            modifier = Modifier.padding(bottom = ToolkitSpacingMd),
            text = title,
            style = ToolkitTextStyle.TitleMedium
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(ToolkitSpacingSm))
                .background(ProgressChartCardColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction)
                    .clip(RoundedCornerShape(ToolkitSpacingSm))
                    .background(statusColor)
            )
            ToolkitText(
                modifier = Modifier.align(Alignment.Center),
                textColor = Color.Black,
                text = "${currentValue}${unit}",
                style = ToolkitTextStyle.BodyLarge
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = ToolkitSpacingSm),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ToolkitText(text = "Min: $minValue", style = ToolkitTextStyle.LabelSmall)
            ToolkitText(text = "Max: $maxValue", style = ToolkitTextStyle.LabelSmall)
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(300.dp, 150.dp)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(ToolkitSpacingMd),
            contentAlignment = Alignment.Center
        ) {
            ToolkitProgressChart(
                title = "Medidor Nivel Caixa D'água",
                unit = "%",
                currentValue = 81,
                thresholds = listOf(
                    ProgressChartColorThresholdDTO(100, ProgressChartColorDanger),
                    ProgressChartColorThresholdDTO(80, ProgressChartColorWaring),
                    ProgressChartColorThresholdDTO(60, ProgressChartColorNormal),
                    ProgressChartColorThresholdDTO(39, ProgressChartColorWaring),
                    ProgressChartColorThresholdDTO(20, ProgressChartColorDanger),
                ),
            )
        }
    }
}