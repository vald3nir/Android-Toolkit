package com.vald3nir.toolkit.designsystem.components.chips

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitFilterChip(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    enabled: Boolean = true,
    label: String,
) {
    val colorScheme = MaterialTheme.colorScheme
    val disabledContentColor = colorScheme.onBackground.copy(
        alpha = ToolkitFilterChipDefaults.DISABLED_CHIP_CONTENT_ALPHA,
    )
    val disabledContainerColor = if (selected) {
        colorScheme.onBackground.copy(alpha = ToolkitFilterChipDefaults.DISABLED_CHIP_CONTAINER_ALPHA)
    } else {
        Color.Transparent
    }
    FilterChip(
        selected = selected,
        onClick = { onSelectedChange(!selected) },
        label = {
            ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                Text(label)
            }
        },
        modifier = modifier,
        enabled = enabled,
        leadingIcon = if (selected) {
            {
                ToolkitIcon(imageVector = ToolkitIconCatalog.Check)
            }
        } else {
            null
        },
        shape = CircleShape,
        border = FilterChipDefaults.filterChipBorder(
            enabled = enabled,
            selected = selected,
            borderColor = colorScheme.onBackground,
            selectedBorderColor = colorScheme.onBackground,
            disabledBorderColor = disabledContentColor,
            disabledSelectedBorderColor = disabledContentColor,
            selectedBorderWidth = ToolkitFilterChipDefaults.ChipBorderWidth,
        ),
        colors = FilterChipDefaults.filterChipColors(
            labelColor = colorScheme.onBackground,
            iconColor = colorScheme.onBackground,
            disabledContainerColor = disabledContainerColor,
            disabledLabelColor = disabledContentColor,
            disabledLeadingIconColor = disabledContentColor,
            selectedContainerColor = colorScheme.primaryContainer,
            selectedLabelColor = colorScheme.onBackground,
            selectedLeadingIconColor = colorScheme.onBackground,
        ),
    )
}

private object ToolkitFilterChipDefaults {
    const val DISABLED_CHIP_CONTAINER_ALPHA = 0.12f
    const val DISABLED_CHIP_CONTENT_ALPHA = 0.38f
    val ChipBorderWidth = 1.dp
}

@ThemePreviews
@Composable
private fun Preview() {
    var selected by remember { mutableStateOf(true) }
    ToolkitPreviewContainer(modifier = Modifier.size(140.dp, 80.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            ToolkitFilterChip(
                selected = selected,
                onSelectedChange = { selected = it },
                label = "Filter chip",
            )
        }
    }
}
