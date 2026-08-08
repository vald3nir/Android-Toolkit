package com.vald3nir.toolkit.designsystem.components.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitBaseButton
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitInputTextDialog(
    title: String,
    label: String,
    value: String = "",
        btnConfirmLabel: String,
    btnCancelLabel: String,
    onConfirm: (String) -> Unit = {},
    onCancel: () -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    var inputValue by remember { mutableStateOf(value) }
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { ToolkitText(text = title, style = ToolkitTextStyle.TitleSmall) },
        text = {
            OutlinedTextField(
                value = inputValue,
                onValueChange = { inputValue = it },
                label = { ToolkitText(text = label, style = ToolkitTextStyle.LabelMedium) }
            )
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                ToolkitText(text = btnCancelLabel, style = ToolkitTextStyle.LabelMedium)
            }
        },
        confirmButton = {
            ToolkitBaseButton(onClick = { onConfirm(inputValue) }, text = btnConfirmLabel)
        },
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        ToolkitInputTextDialog(
            title = "Editar nome",
            label = "Nome completo",
            value = "João da Silva",
            btnConfirmLabel = "Salvar",
            btnCancelLabel = "Cancelar",
        )
    }
}