package com.vald3nir.toolkit.designsystem.templates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXl
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitBaseButton
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitEmptyStateScreen(
    modifier: Modifier = Modifier,
    title: String,
    btnText: String? = null,
    imageVector: ImageVector = ToolkitIconCatalog.Inbox,
    leadingIcon: ImageVector = ToolkitIconCatalog.Add,
    onClickBtn: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(ToolkitSpacingXl),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(ToolkitSpacingMd)
        ) {
            ToolkitIcon(imageVector = imageVector, modifier = Modifier.size(52.dp))
            ToolkitText(text = title, style = ToolkitTextStyle.TitleMedium)
            btnText?.let {
                ToolkitBaseButton(onClick = onClickBtn, text = it, leadingIcon = leadingIcon)
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 300.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            ToolkitEmptyStateScreen(
                title = "Sem itens cadastrados",
                btnText = "Adicionar",
            )
        }
    }
}