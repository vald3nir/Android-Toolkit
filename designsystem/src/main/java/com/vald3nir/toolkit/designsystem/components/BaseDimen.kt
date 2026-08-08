package com.vald3nir.toolkit.designsystem.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val ToolkitSpacingXs = 4.dp
val ToolkitSpacingSm = 8.dp
val ToolkitSpacingMd = 16.dp
val ToolkitSpacingLg = 24.dp
val ToolkitSpacingXl = 32.dp
val ToolkitSpacingXxl = 48.dp

@Composable
fun ToolkitSpaceHeight(height: Dp = ToolkitSpacingMd) = Spacer(modifier = Modifier.height(height))

@Composable
fun ToolkitSpaceWidth(width: Dp = ToolkitSpacingMd) = Spacer(modifier = Modifier.width(width))
