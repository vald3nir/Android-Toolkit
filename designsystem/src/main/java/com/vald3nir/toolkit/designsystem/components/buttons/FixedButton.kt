package com.vald3nir.toolkit.designsystem.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingLg
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitFixedButton(
    modifier: Modifier = Modifier,
    label: String,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    showLoading: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val buttonEnabled = enabled && !showLoading
    ToolkitBaseButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = ToolkitSpacingMd, vertical = ToolkitSpacingLg),
        onClick = onClick,
        enabled = buttonEnabled,
        contentPadding = if (leadingIcon != null && !showLoading) {
            ButtonDefaults.ButtonWithIconContentPadding
        } else {
            ButtonDefaults.ContentPadding
        },
        content = {
            if (showLoading) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                ToolkitButtonContent(
                    label = label,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon
                )
            }
        }
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(200.dp, 500.dp)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ToolkitFixedButton(label = "Continuar", showLoading = true)
            ToolkitFixedButton(label = "Continuar", showLoading = false)
            ToolkitFixedButton(label = "Continuar", leadingIcon = ToolkitIconCatalog.Check)
            ToolkitFixedButton(label = "Continuar", trailingIcon = ToolkitIconCatalog.Edit)
        }
    }
}