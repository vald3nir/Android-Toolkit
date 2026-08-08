package com.vald3nir.toolkit.designsystem.components.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = NavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = NavigationDefaults.navigationContentColor(),
            selectedTextColor = NavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = NavigationDefaults.navigationContentColor(),
            indicatorColor = NavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

@Composable
fun ToolkitNavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    NavigationRail(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = NavigationDefaults.navigationContentColor(),
        header = header,
        content = content,
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    val items = listOf("Home", "Perfil", "Configurações")
    val icons = listOf(
        ToolkitIconCatalog.Home,
        ToolkitIconCatalog.AccountCircle,
        ToolkitIconCatalog.Settings,
    )
    val selectedIcons = listOf(
        ToolkitIconCatalog.Home,
        ToolkitIconCatalog.AccountCircle,
        ToolkitIconCatalog.Settings,
    )
    ToolkitPreviewContainer(modifier = Modifier.size(100.dp, 200.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            ToolkitNavigationRail {
                items.forEachIndexed { index, item ->
                    val icon = icons.getOrElse(index) { icons.lastOrNull() ?: return@forEachIndexed }
                    val selectedIcon = selectedIcons.getOrElse(index) { icon }
                    ToolkitNavigationRailItem(
                        icon = {
                            Icon(
                                imageVector = icon,
                                contentDescription = item,
                            )
                        },
                        selectedIcon = {
                            Icon(
                                imageVector = selectedIcon,
                                contentDescription = item,
                            )
                        },
                        label = { Text(item) },
                        selected = index == 0,
                        onClick = { },
                    )
                }
            }
        }
    }
}