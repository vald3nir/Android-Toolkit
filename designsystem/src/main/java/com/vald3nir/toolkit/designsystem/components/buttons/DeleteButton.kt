package com.vald3nir.toolkit.designsystem.components.buttons

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXs
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitDeleteButton(
    confirmState: Boolean = false,
    onClickRemove: () -> Unit = {},
    onClickCancel: () -> Unit = {},
    onClickShowConfirm: () -> Unit = {},
) {
    AnimatedContent(
        targetState = confirmState,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
    ) { confirming ->
        if (confirming) {
            Row(horizontalArrangement = Arrangement.spacedBy(ToolkitSpacingXs)) {
                ToolkitIcon(
                    modifier = Modifier.background(
                        MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.2f),
                        CircleShape,
                    ), imageVector = ToolkitIconCatalog.Check, onClick = onClickRemove
                )
                ToolkitIcon(
                    modifier = Modifier, imageVector = ToolkitIconCatalog.Close, onClick = onClickCancel
                )
            }
        } else {
            ToolkitIcon(
                modifier = Modifier, imageVector = ToolkitIconCatalog.Delete, onClick = onClickShowConfirm
            )
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(100.dp)) {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ToolkitDeleteButton(
                confirmState = false,
                onClickRemove = { },
                onClickCancel = { },
                onClickShowConfirm = { },
            )
            ToolkitDeleteButton(
                confirmState = true,
                onClickRemove = { },
                onClickCancel = { },
                onClickShowConfirm = { },
            )
        }
    }
}
