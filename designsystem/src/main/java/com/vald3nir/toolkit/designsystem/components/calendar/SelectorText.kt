package com.vald3nir.toolkit.designsystem.components.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
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
fun ToolkitSelectorText(data: List<String>, current: String, onChangeText: (String) -> Unit) {
    val lastIndex = data.indexOf(current) - 1
    val nextIndex = data.indexOf(current) + 1
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(ToolkitSpacingSm),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ToolkitIcon(
            imageVector = ToolkitIconCatalog.ChevronLeft,
            onClick = { onChangeText(data.getOrNull(lastIndex).orEmpty()) }
        )
        ToolkitText(text = current, style = ToolkitTextStyle.TitleMedium)
        ToolkitIcon(
            imageVector = ToolkitIconCatalog.ChevronRight,
            onClick = { onChangeText(data.getOrNull(nextIndex).orEmpty()) }
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