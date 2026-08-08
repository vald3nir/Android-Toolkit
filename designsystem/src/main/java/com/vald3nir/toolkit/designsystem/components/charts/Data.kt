package com.vald3nir.toolkit.designsystem.components.charts

import androidx.compose.ui.graphics.Color
import java.util.Locale

fun formatChartValue(value: Float): String {
    return when {
        value >= 1_000_000f -> String.format(Locale.getDefault(), "%.0fM", value / 1_000_000f)
        value >= 1_000f -> String.format(Locale.getDefault(), "%.0fK", value / 1_000f)
        else -> String.format(Locale.getDefault(), "%.0f", value)
    }
}

data class ProgressChartColorThresholdDTO(
    val upToPercentage: Int,
    val color: Color
)

data class ItemChartDTO(val value: Float, val label: String)

fun List<ItemChartDTO>.sum(): Float = sumOf { it.value.toDouble() }.toFloat()