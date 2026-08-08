package com.vald3nir.toolkit.designsystem.components.selectors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitSwitch(startEnable: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Switch(checked = startEnable, onCheckedChange = onCheckedChange)
}

@Composable
fun ToolkitSwitchLabeled(
    modifier: Modifier = Modifier,
    labelStart: String,
    labelEnd: String,
    startEnable: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ToolkitText(
            modifier = Modifier.padding(horizontal = ToolkitSpacingMd),
            text = labelStart,
            style = ToolkitTextStyle.BodyMedium
        )
        ToolkitSwitch(startEnable = startEnable, onCheckedChange = onCheckedChange)
        ToolkitText(
            modifier = Modifier.padding(horizontal = ToolkitSpacingMd),
            text = labelEnd,
            style = ToolkitTextStyle.BodyMedium
        )
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(200.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ToolkitSwitch(startEnable = true, onCheckedChange = {})
                ToolkitSwitch(startEnable = false, onCheckedChange = {})
                ToolkitSwitchLabeled(labelStart = "Sim", labelEnd = "Não", startEnable = true, onCheckedChange = {})
            }
        }
    }
}