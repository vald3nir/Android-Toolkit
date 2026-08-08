package com.vald3nir.toolkit.designsystem.components.inputs

import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitInputInteger(
    inputValue: Int?,
    errorValue: String? = null,
    placeholder: String = "",
    label: String = "",
    iconTint: Color = MaterialTheme.colorScheme.onSurface,
    leftIcon: ImageVector? = null,
    onClickLeftIcon: () -> Unit = {},
    onValueChange: (Int) -> Unit = {},
) {
    ToolkitInputText(
        inputValue = inputValue?.toString() ?: "",
        label = label,
        placeholder = placeholder,
        errorValue = errorValue,
        iconTint = iconTint,
        singleLine = true,
        leftIcon = leftIcon,
        onClickLeftIcon = onClickLeftIcon,
        keyboardType = KeyboardType.Number,
        onValueChange = {
            onValueChange(it.take(9).toIntOrNull() ?: 0)
        },
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(300.dp, 200.dp)) {
        var value by remember { mutableIntStateOf(100) }
        ToolkitInputInteger(
            inputValue = value,
            label = "Quantidade de itens",
            placeholder = "Ex: 1",
            leftIcon = ToolkitIconCatalog.Pin,
            onValueChange = { value = it }
        )
    }
}