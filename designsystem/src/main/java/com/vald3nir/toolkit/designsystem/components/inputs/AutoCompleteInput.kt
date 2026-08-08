package com.vald3nir.toolkit.designsystem.components.inputs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.icons.BuildIconButton
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitAutoCompleteInput(
    suggestionList: List<String> = emptyList(),
    inputValue: String = "",
    placeholder: String = "",
    label: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    iconTint: Color = MaterialTheme.colorScheme.onSurface,
    leftIcon: ImageVector? = null,
    onClickLeftIcon: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var expanded by remember { mutableStateOf(false) }
    val trailingIcon = if (inputValue.isNotEmpty()) ToolkitIconCatalog.Close else ToolkitIconCatalog.Edit

    val filteredSuggestions by remember(inputValue, suggestionList) {
        derivedStateOf {
            if (inputValue.isBlank()) {
                emptyList()
            } else {
                suggestionList.filter { it.contains(inputValue, ignoreCase = true) }.take(5)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(ToolkitSpacingMd)
    ) {
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                expanded = true
                onValueChange(it)
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label) },
            placeholder = { Text(placeholder) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    expanded = false
                    keyboardController?.hide()
                }
            ),
            leadingIcon = leftIcon?.let { icon ->
                {
                    icon.BuildIconButton(
                        onClick = onClickLeftIcon,
                        tint = iconTint
                    )
                }
            },
            trailingIcon = { trailingIcon.BuildIconButton(tint = iconTint, onClick = { if (inputValue.isNotEmpty()) onValueChange("") }) }
        )

        AnimatedVisibility(visible = expanded && filteredSuggestions.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 200.dp) // Sets a max height to avoid the "Vertically scrollable component was measured with an infinity maximum height constraints" crash
                    .padding(top = ToolkitSpacingSm),
                verticalArrangement = Arrangement.spacedBy(ToolkitSpacingSm)
            ) {
                items(items = filteredSuggestions) { suggestion ->
                    ToolkitText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onValueChange(suggestion)
                                expanded = false
                                keyboardController?.hide()
                            }
                            .padding(vertical = ToolkitSpacingSm),
                        text = suggestion,
                        style = ToolkitTextStyle.LabelMedium
                    )
                }
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(300.dp, 200.dp)) {
        var value by remember { mutableStateOf("Ba") }
        ToolkitAutoCompleteInput(
            label = "Fruta",
            placeholder = "Digite o nome da fruta",
            inputValue = value,
            suggestionList = listOf("Banana", "Batata", "Maçã", "Morango", "Abacaxi"),
            onValueChange = { value = it }
        )
    }
}