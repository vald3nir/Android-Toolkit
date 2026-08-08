package com.vald3nir.toolkit.designsystem.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXs
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceWidth
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitFloatingButton(
    imageVector: ImageVector = ToolkitIconCatalog.Add,
    onClick: () -> Unit = {}
) {
    FloatingActionButton(
        modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
        onClick = onClick,
    ) {
        ToolkitIcon(
            imageVector = imageVector,
            onClick = onClick,
        )
    }
}

@Composable
fun ToolkitDoubleFloatingButton(
    mainIcon: ImageVector = ToolkitIconCatalog.Add,
    secondaryIcon: ImageVector = ToolkitIconCatalog.Edit,
    onMainClick: () -> Unit = {},
    onSecondaryClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
        verticalArrangement = Arrangement.spacedBy(ToolkitSpacingXs),
        horizontalAlignment = Alignment.End
    ) {
        FloatingActionButton(onClick = onMainClick) {
            ToolkitIcon(imageVector = mainIcon)
        }
        SmallFloatingActionButton(onClick = onSecondaryClick) {
            ToolkitIcon(imageVector = secondaryIcon)
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(300.dp)) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ToolkitFloatingButton(
                imageVector = ToolkitIconCatalog.Add,
                onClick = {}
            )
            ToolkitSpaceWidth()
            ToolkitDoubleFloatingButton(
                mainIcon = ToolkitIconCatalog.Add,
                secondaryIcon = ToolkitIconCatalog.QrCodeScanner,
                onMainClick = {},
                onSecondaryClick = {}
            )
        }
    }
}
