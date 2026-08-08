package com.vald3nir.toolkit.designsystem.components.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.selectors.ToolkitRadioButtonGroup
import com.vald3nir.toolkit.designsystem.components.selectors.ToolkitRadioButtonGroupType
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitSelectDialog(
    title: String,
    selectedValue: String,
    items: List<String>,
    onConfirm: (String) -> Unit = {},
    onCancel: () -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = onCancel,
        title = { ToolkitText(text = title, style = ToolkitTextStyle.TitleSmall) },
        text = {
            Box(contentAlignment = Alignment.Center) {
                ToolkitRadioButtonGroup(
                    selectedValue = selectedValue,
                    onItemSelected = onConfirm,
                    items = items,
                    viewType = ToolkitRadioButtonGroupType.GRID,
                )
            }
        },
        dismissButton = { },
        confirmButton = { },
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        ToolkitSelectDialog(
            title = "Selecione um nome",
            selectedValue = "João da Silva",
            items = listOf("João da Silva", "Maria Souza", "Carlos Oliveira"),
            onConfirm = {},
            onCancel = {}
        )
    }
}