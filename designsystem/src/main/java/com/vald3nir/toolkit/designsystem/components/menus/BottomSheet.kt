package com.vald3nir.toolkit.designsystem.components.menus

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalInspectionMode
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceWidth
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXxl
import com.vald3nir.toolkit.designsystem.components.dividers.ToolkitDivider
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.selectors.ToolkitSwitch
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

sealed class ToolkitItemBottomSheet {
    data class Switch(
        val enable: Boolean,
        val title: String,
        val onCheckedChange: (Boolean) -> Unit,
    ) : ToolkitItemBottomSheet()

    data class Default(
        val icon: ImageVector? = null,
        val title: String,
        val onClick: () -> Unit
    ) : ToolkitItemBottomSheet()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolkitBottomSheet(items: List<ToolkitItemBottomSheet>, onDismissRequest: () -> Unit) {
    if (LocalInspectionMode.current) {
        ToolkitBottomSheetContent(items = items, onDismissRequest = onDismissRequest)
        return
    }
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(onDismissRequest = onDismissRequest, sheetState = sheetState) {
        ToolkitBottomSheetContent(items = items, onDismissRequest = onDismissRequest)
    }
}

@Composable
private fun ToolkitBottomSheetContent(items: List<ToolkitItemBottomSheet>, onDismissRequest: () -> Unit) {
    LazyColumn(modifier = Modifier.padding(vertical = ToolkitSpacingMd)) {
        itemsIndexed(items) { index, item ->
            when (item) {
                is ToolkitItemBottomSheet.Default -> DefaultItemBottomSheet(item = item, onDismissRequest = onDismissRequest)
                is ToolkitItemBottomSheet.Switch -> SwitchItemBottomSheet(item = item, onDismissRequest = onDismissRequest)
            }
            if (index < items.lastIndex) {
                ToolkitDivider()
            }
        }
    }
    ToolkitSpaceHeight(ToolkitSpacingXxl)
}

@Composable
private fun DefaultItemBottomSheet(item: ToolkitItemBottomSheet.Default, onDismissRequest: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .height(ToolkitSpacingXxl)
            .fillMaxWidth()
            .clickable {
                item.onClick()
                onDismissRequest()
            }
            .padding(
                horizontal = ToolkitSpacingMd,
                vertical = ToolkitSpacingMd * 0.75f,
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item.icon?.let { icon ->
            ToolkitIcon(imageVector = icon, modifier = Modifier.padding(end = ToolkitSpacingMd * 0.5f))
        }
        ToolkitText(text = item.title, style = ToolkitTextStyle.TitleSmall)
    }
}

@Composable
private fun SwitchItemBottomSheet(item: ToolkitItemBottomSheet.Switch, onDismissRequest: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .height(ToolkitSpacingXxl)
            .fillMaxWidth()
            .clickable {
                onDismissRequest()
            }
            .padding(
                horizontal = ToolkitSpacingMd,
                vertical = ToolkitSpacingMd * 0.75f,
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ToolkitText(modifier = Modifier.weight(1f), text = item.title, style = ToolkitTextStyle.LabelMedium)
        ToolkitSpaceWidth()
        ToolkitSwitch(startEnable = item.enable, onCheckedChange = item.onCheckedChange)
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        ToolkitBottomSheet(
            items = listOf(
                ToolkitItemBottomSheet.Default(
                    icon = ToolkitIconCatalog.AccountCircle,
                    title = "Profile",
                    onClick = { }
                ),
                ToolkitItemBottomSheet.Switch(
                    title = "Switch 1",
                    enable = false,
                    onCheckedChange = { }
                ),
                ToolkitItemBottomSheet.Switch(
                    title = "Switch 2",
                    enable = true,
                    onCheckedChange = { }
                ),
                ToolkitItemBottomSheet.Default(
                    icon = ToolkitIconCatalog.Settings,
                    title = "Settings",
                    onClick = { }
                ),
                ToolkitItemBottomSheet.Default(
                    title = "Logout",
                    onClick = { }
                ),
            ), onDismissRequest = { }
        )
    }
}