package com.vald3nir.toolkit.designsystem.components.selectors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitSelectorText(
    data: List<String>,
    current: String,
    onChangeText: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentIndex = remember(data, current) { data.indexOf(current) }
    val hasPrevious = currentIndex > 0
    val hasNext = currentIndex in 0 until data.lastIndex

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(ToolkitSpacingSm),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ToolkitIcon(
            imageVector = ToolkitIconCatalog.ChevronLeft,
            onClick = {
                if (hasPrevious) {
                    onChangeText(data[currentIndex - 1])
                }
            }
        )
        ToolkitText(
            text = current.ifEmpty { data.firstOrNull().orEmpty() },
            style = ToolkitTextStyle.TitleMedium
        )
        ToolkitIcon(
            imageVector = ToolkitIconCatalog.ChevronRight,
            onClick = {
                if (hasNext) {
                    onChangeText(data[currentIndex + 1])
                }
            }
        )
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(300.dp, 100.dp)) {
        ToolkitSelectorText(
            data = listOf("A", "B", "C"),
            current = "B",
            onChangeText = { }
        )
    }
}