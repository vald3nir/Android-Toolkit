package com.vald3nir.toolkit.designsystem.components.selectors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitTextButton
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitSelectButtonGroup(
    modifier: Modifier = Modifier,
    numColumns: Int = 2,
    items: List<String>,
    onItemSelected: (String) -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(numColumns),
        modifier = modifier.padding(horizontal = ToolkitSpacingMd),
        horizontalArrangement = Arrangement.spacedBy(ToolkitSpacingSm),
        verticalArrangement = Arrangement.spacedBy(ToolkitSpacingSm)
    ) {
        items.forEach { item ->
            item {
                ToolkitTextButton(
                    label = item,
                    trailingIcon = ToolkitIconCatalog.Add,
                    onClick = { onItemSelected(item) }
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 150.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            ToolkitSelectButtonGroup(
                numColumns = 3,
                items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"),
            )
        }
    }
}