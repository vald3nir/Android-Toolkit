package com.vald3nir.toolkit.designsystem.components.inputs

import android.icu.text.NumberFormat
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
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
import java.util.Locale

@Composable
fun ToolkitInputMonetary(
    inputValue: Double = 0.0,
    errorValue: String? = null,
    placeholder: String = "",
    label: String = "",
    iconTint: Color = MaterialTheme.colorScheme.onSurface,
    leftIcon: ImageVector? = null,
    onClickLeftIcon: () -> Unit = {},
    onValueChange: (Double) -> Unit = {},
) {
    val formatter = remember {
        NumberFormat.getCurrencyInstance(Locale("pt", "BR")).apply {
            minimumFractionDigits = 2
            maximumFractionDigits = 2
        }
    }
    var text by remember(inputValue) {
        mutableStateOf(formatter.format(inputValue))
    }
    ToolkitInputText(
        inputValue = text,
        label = label,
        placeholder = placeholder,
        errorValue = errorValue,
        iconTint = iconTint,
        singleLine = true,
        leftIcon = leftIcon,
        onClickLeftIcon = onClickLeftIcon,
        keyboardType = KeyboardType.Number,
        onValueChange = { newValue ->
            val digitsOnly = newValue.filter { it.isDigit() }
            val value = if (digitsOnly.isEmpty()) 0.0 else digitsOnly.toDouble() / 100
            text = formatter.format(value)
            onValueChange(value)
        },
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(300.dp, 200.dp)) {
        var value by remember { mutableDoubleStateOf(100.0) }
        ToolkitInputMonetary(
            inputValue = value,
            label = "Valor",
            placeholder = "Ex: 100,00",
            leftIcon = ToolkitIconCatalog.AttachMoney,
            onValueChange = { value = it }
        )
    }
}