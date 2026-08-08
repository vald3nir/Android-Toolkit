package com.vald3nir.toolkit.designsystem.components.selectors

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

enum class ToolkitRadioButtonGroupType { LIST, GRID }

@Composable
fun ToolkitRadioButtonGroup(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectedValue: String? = null,
    viewType: ToolkitRadioButtonGroupType,
    columnsSize: Int = 2, // for grid
    onItemSelected: (String) -> Unit = {},
) {
    var selectedItem by remember { mutableStateOf(selectedValue) }
    val itemContent: @Composable (String) -> Unit = { item ->
        ToolkitRadioButton(
            label = item,
            checked = selectedItem == item,
            onClick = {
                selectedItem = item
                onItemSelected(item)
            }
        )
    }
    when (viewType) {
        ToolkitRadioButtonGroupType.LIST -> {
            LazyColumn(modifier = modifier.selectableGroup()) {
                items.forEach { item ->
                    item { itemContent(item) }
                }
            }
        }

        ToolkitRadioButtonGroupType.GRID -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(columnsSize),
                modifier = modifier.selectableGroup()
            ) {
                items.forEach { item ->
                    item { itemContent(item) }
                }
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 200.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            ToolkitRadioButtonGroup(
                items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5"),
                viewType = ToolkitRadioButtonGroupType.GRID,
            )
        }
    }
}