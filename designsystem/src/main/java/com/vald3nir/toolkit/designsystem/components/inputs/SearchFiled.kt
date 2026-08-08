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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.icons.BuildIconButton
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitSearchFiled(
    searchQuery: String = "",
    placeholder: String = "Pesquisar",
    label: String = "",
    onValueChange: (String) -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val trailingIcon = if (searchQuery.isNotEmpty()) ToolkitIconCatalog.Close else ToolkitIconCatalog.Search
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(ToolkitSpacingMd),
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { keyboardController?.hide() }),
        trailingIcon = {
            trailingIcon.BuildIconButton(
                tint = MaterialTheme.colorScheme.onSurface,
                onClick = { if (searchQuery.isNotEmpty()) onValueChange("") }
            )
        }
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(300.dp, 200.dp)) {
        var query by remember { mutableStateOf("Banana") }
        ToolkitSearchFiled(
            label = "Fruta",
            placeholder = "Digite o nome da fruta",
            searchQuery = query,
            onValueChange = { query = it }
        )
    }
}