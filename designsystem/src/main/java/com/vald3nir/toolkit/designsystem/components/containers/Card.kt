package com.vald3nir.toolkit.designsystem.components.containers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitCard(modifier: Modifier = Modifier, content: @Composable () -> Unit, onClick: () -> Unit = {}) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(ToolkitSpacingMd),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier
    ) {
        content()
    }
}

@Composable
fun ToolkitCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Card(
        shape = RoundedCornerShape(ToolkitSpacingMd),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier
    ) {
        content()
    }
}

@ThemePreviews
@Composable
private fun CardPreview() {
    ToolkitPreviewContainer(modifier = Modifier.size(300.dp)) {
        Column(modifier = Modifier.padding(ToolkitSpacingMd)) {
            ToolkitCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                content = {
                    Column(modifier = Modifier.padding(ToolkitSpacingMd)) {
                        ToolkitText(text = "HeadlineLarge", style = ToolkitTextStyle.HeadlineLarge)
                        ToolkitText(text = "HeadlineMedium", style = ToolkitTextStyle.HeadlineMedium)
                        ToolkitText(text = "HeadlineSmall", style = ToolkitTextStyle.HeadlineSmall)
                    }
                },
            )
        }
    }
}