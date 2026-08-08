package com.vald3nir.toolkit.designsystem.components.chips

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitInputChip(
    imageVector: ImageVector? = null,
    label: String,
    selected: Boolean = true,
    onDismiss: () -> Unit,
) {
    var isVisible by remember(selected) { mutableStateOf(selected) }
    if (!isVisible) return

    InputChip(
        onClick = {
            isVisible = false
            onDismiss()
        },
        label = { Text(label) },
        selected = isVisible,
        avatar = imageVector?.let {
            {
                ToolkitIcon(
                    imageVector = it,
                    modifier = Modifier.size(InputChipDefaults.AvatarSize),
                )
            }
        },
        trailingIcon = {
            ToolkitIcon(
                imageVector = ToolkitIconCatalog.Close,
                modifier = Modifier.size(InputChipDefaults.AvatarSize),
            )
        },
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(160.dp, 80.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            ToolkitInputChip(
                imageVector = ToolkitIconCatalog.Bookmark,
                label = "Input chip",
                selected = true,
                onDismiss = {},
            )
        }
    }
}
