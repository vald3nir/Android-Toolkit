package com.vald3nir.toolkit.designsystem.components.selectors

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitTab(
    modifier: Modifier = Modifier,
    selected: Boolean,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    text: String,
    onClick: () -> Unit
) {
    Tab(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        text = {
            val textStyle = ToolkitTextStyle.LabelLarge
            ProvideTextStyle(textStyle.textStyle.copy(textAlign = TextAlign.Center)) {
                Box(modifier = Modifier.padding(top = 7.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        icon?.let {
                            ToolkitIcon(imageVector = it, modifier = Modifier)
                            Spacer(modifier = Modifier.width(ToolkitSpacingSm))
                        }
                        ToolkitText(text = text, style = textStyle)
                    }
                }
            }
        }
    )
}

@Composable
fun ToolkitTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: @Composable () -> Unit,
) {
    SecondaryTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onSurface,
        tabs = tabs,
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 300.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ToolkitTabRow(selectedTabIndex = 0) {
                    listOf("Topics", "People").forEachIndexed { index, title ->
                        ToolkitTab(
                            selected = index == 0,
                            onClick = { },
                            text = title,
                        )
                    }
                }
                ToolkitSpaceHeight()
                ToolkitTabRow(selectedTabIndex = 0) {
                    listOf(
                        "Topics" to ToolkitIconCatalog.Downloading,
                        "People" to ToolkitIconCatalog.AccountCircle
                    ).forEachIndexed { index, item ->
                        ToolkitTab(
                            selected = index == 0,
                            onClick = { },
                            text = item.first,
                            icon = item.second,
                        )
                    }
                }
            }
        }
    }
}