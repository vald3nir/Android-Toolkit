package com.vald3nir.toolkit.designsystem.components.icons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String? = null,
    tint: Color? = null,
    onClick: (() -> Unit)? = null,
) {
    val iconTint = tint ?: LocalContentColor.current
    if (onClick != null) {
        IconButton(onClick = onClick, modifier = modifier) {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                tint = iconTint
            )
        }
    } else {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = modifier,
            tint = iconTint
        )
    }
}

@Composable
fun ImageVector.BuildIconButton(
    modifier: Modifier = Modifier,
    tint: Color = Color.LightGray,
    contentDescription: String? = null,
    onClick: () -> Unit,
) = ToolkitIcon(
    modifier = modifier,
    imageVector = this,
    contentDescription = contentDescription,
    tint = tint,
    onClick = onClick
)

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(120.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(ToolkitSpacingMd)
        ) {
            ToolkitIcon(
                modifier = Modifier,
                imageVector = ToolkitIconCatalog.Favorite,
                contentDescription = "Favorite"
            )
            ToolkitIcon(
                modifier = Modifier,
                imageVector = ToolkitIconCatalog.Settings,
                contentDescription = "Settings",
                onClick = {}
            )
        }
    }
}