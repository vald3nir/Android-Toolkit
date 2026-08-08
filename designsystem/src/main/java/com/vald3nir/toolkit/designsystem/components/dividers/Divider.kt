package com.vald3nir.toolkit.designsystem.components.dividers

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ToolkitDivider(modifier: Modifier = Modifier, color: Color = Color.LightGray) {
    HorizontalDivider(modifier = modifier, thickness = 0.5.dp, color = color)
}