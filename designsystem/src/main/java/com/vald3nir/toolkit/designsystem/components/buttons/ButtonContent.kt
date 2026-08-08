package com.vald3nir.toolkit.designsystem.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

@Composable
fun ToolkitButtonContent(
    label: String,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (leadingIcon != null) {
            ToolkitIcon(imageVector = leadingIcon)
        }
        Box(
            modifier = Modifier
                .weight(1f, fill = false)
                .padding(start = leadingIcon.calculateSpace(), end = trailingIcon.calculateSpace())
        ) {
            ToolkitText(text = label, style = ToolkitTextStyle.LabelMedium)
        }
        if (trailingIcon != null) {
            ToolkitIcon(imageVector = trailingIcon)
        }
    }
}

private fun ImageVector?.calculateSpace() = if (this != null) ButtonDefaults.IconSpacing else 0.dp

@Composable
fun ToolkitButtonContent(
    text: String,
    leadingIcon: ImageVector? = null,
    leadingIconContentDescription: String? = null,
) {
    if (leadingIcon != null) {
        Box(Modifier.sizeIn(maxHeight = ButtonDefaults.IconSize)) {
            ToolkitIcon(imageVector = leadingIcon, contentDescription = leadingIconContentDescription)
        }
    }
    Box(Modifier.padding(start = if (leadingIcon != null) ButtonDefaults.IconSpacing else 0.dp)) {
        ToolkitText(text = text, style = ToolkitTextStyle.LabelMedium)
    }
}