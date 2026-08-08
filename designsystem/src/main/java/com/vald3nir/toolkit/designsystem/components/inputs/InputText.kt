package com.vald3nir.toolkit.designsystem.components.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.icons.BuildIconButton
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitInputText(
    inputValue: String = "",
    errorValue: String? = null,
    placeholder: String = "",
    label: String = "",
    singleLine: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    iconTint: Color = MaterialTheme.colorScheme.onSurface,
    leftIcon: ImageVector? = null,
    onClickLeftIcon: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val trailingIcon = if (inputValue.isNotEmpty()) ToolkitIconCatalog.Close else ToolkitIconCatalog.Edit
    OutlinedTextField(
        value = inputValue,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(ToolkitSpacingMd),
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType, imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        leadingIcon = leftIcon?.let { icon ->
            { icon.BuildIconButton(onClick = onClickLeftIcon, tint = iconTint) }
        },
        trailingIcon = { trailingIcon.BuildIconButton(tint = iconTint, onClick = { if (inputValue.isNotEmpty()) onValueChange("") }) },
        supportingText = {
            errorValue?.let {
                ToolkitText(
                    text = it,
                    style = ToolkitTextStyle.LabelLarge,
                    textColor = Color.Red
                )
            }
        }
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(300.dp, 200.dp)) {
        var value by remember { mutableStateOf("Texto pré-preenchido") }
        ToolkitInputText(
            leftIcon = ToolkitIconCatalog.Person,
            inputValue = value,
            label = "Exemplo de label",
            placeholder = "Digite seu nome aqui",
            errorValue = "Mensagem de erro",
            onValueChange = { value = it }
        )
    }
}