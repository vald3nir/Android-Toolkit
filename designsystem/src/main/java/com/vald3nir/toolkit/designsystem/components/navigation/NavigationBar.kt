package com.vald3nir.toolkit.designsystem.components.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitNavigationBar(
    modifier: Modifier = Modifier,
    labels: List<String>,
    icons: List<ImageVector>,
    selectedIcons: List<ImageVector>? = null,
    selectedTab: Int = 0,
    onClick: (index: Int) -> Unit = {}
) {
    val safeSelectedIcons = selectedIcons ?: icons
    NavigationBar(
        modifier = modifier,
        contentColor = NavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp
    ) {
        labels.forEachIndexed { index, label ->
            val icon = icons.getOrElse(index) { icons.lastOrNull() ?: return@forEachIndexed }
            val selectedIcon = safeSelectedIcons.getOrElse(index) { icon }
            ToolkitNavigationBarItem(
                icon = {
                    ToolkitIcon(
                        imageVector = icon,
                        contentDescription = label,
                    )
                },
                selectedIcon = {
                    ToolkitIcon(
                        imageVector = selectedIcon,
                        contentDescription = label,
                    )
                },
                label = { Text(label) },
                selected = index == selectedTab,
                onClick = { onClick(index) },
            )
        }
    }
}

@Composable
private fun RowScope.ToolkitNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = NavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = NavigationDefaults.navigationContentColor(),
            selectedTextColor = NavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = NavigationDefaults.navigationContentColor(),
            indicatorColor = NavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    val labels = listOf("Home", "Perfil", "Configurações")
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
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 100.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            ToolkitNavigationBar(
                labels = labels,
                icons = icons,
                selectedIcons = selectedIcons,
            )
        }
    }
}