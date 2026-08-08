package com.vald3nir.toolkit.designsystem.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

private const val DISABLED_ICON_BUTTON_CONTAINER_ALPHA = 0.12f

@Composable
fun ToolkitToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    checkedIcon: ImageVector,
    uncheckedIcon: ImageVector,
) {
    val disabledContainerColor = if (checked) {
        MaterialTheme.colorScheme.onBackground.copy(alpha = DISABLED_ICON_BUTTON_CONTAINER_ALPHA)
    } else {
        Color.Transparent
    }
    val icon = if (checked) {
        checkedIcon
    } else {
        uncheckedIcon
    }
    FilledIconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        colors = IconButtonDefaults.iconToggleButtonColors(
            checkedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            checkedContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = disabledContainerColor,
        ),
    ) {
        ToolkitIcon(imageVector = icon)
    }
}

@Composable
fun ToolkitMarkToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    ToolkitToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        checkedIcon = ToolkitIconCatalog.Bookmark,
        uncheckedIcon = ToolkitIconCatalog.BookmarkBorder
    )

}


@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(100.dp, 300.dp)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ToolkitToggleButton(
                checkedIcon = ToolkitIconCatalog.Wifi,
                uncheckedIcon = ToolkitIconCatalog.WifiOff,
                checked = true,
                onCheckedChange = { },
            )
            ToolkitToggleButton(
                checkedIcon = ToolkitIconCatalog.Wifi,
                uncheckedIcon = ToolkitIconCatalog.WifiOff,
                checked = false,
                onCheckedChange = { },
            )

            ToolkitMarkToggleButton(
                checked = true,
                onCheckedChange = { },
            )
            ToolkitMarkToggleButton(
                checked = false,
                onCheckedChange = { },
            )
        }
    }
}