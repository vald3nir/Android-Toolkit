package com.vald3nir.toolkit.designsystem.components.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXs
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitCard
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitFieldCard(modifier: Modifier = Modifier, label: String, value: String, onEdit: (() -> Unit)?, imageVector: ImageVector = ToolkitIconCatalog.Edit) {
    ToolkitCard(
        modifier = modifier,
        onClick = onEdit ?: {},
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(ToolkitSpacingMd),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    ToolkitText(
                        text = label,
                        style = ToolkitTextStyle.TitleMedium,
                    )
                    ToolkitSpaceHeight(ToolkitSpacingXs)
                    ToolkitText(
                        text = value.ifBlank { "-" },
                        style = ToolkitTextStyle.BodyMedium,
                    )
                }
                if (onEdit != null) {
                    IconButton(onClick = onEdit) {
                        ToolkitIcon(imageVector = imageVector)
                    }
                }
            }
        }
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = ToolkitSpacingMd),
            verticalArrangement = Arrangement.spacedBy(ToolkitSpacingMd),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ToolkitFieldCard(
                label = "Nome",
                value = "Valdenir Severino",
                onEdit = { }
            )
            ToolkitFieldCard(
                label = "email",
                value = "vald3nir@gmail.com",
                onEdit = null
            )
            ToolkitFieldCard(
                label = "Aniversário",
                value = "15/08/1991",
                onEdit = { }
            )
            ToolkitFieldCard(
                label = "Gênero",
                value = "Masculino",
                onEdit = { }
            )
        }
    }
}
