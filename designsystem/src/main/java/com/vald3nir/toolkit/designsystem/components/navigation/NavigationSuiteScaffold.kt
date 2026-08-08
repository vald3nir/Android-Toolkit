package com.vald3nir.toolkit.designsystem.components.navigation

import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitNavigationSuiteScaffold(
    navigationSuiteItems: ToolkitNavigationSuiteScope.() -> Unit,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
    content: @Composable () -> Unit,
) {
    val layoutType = NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(windowAdaptiveInfo)
    val selectedColor = NavigationDefaults.navigationSelectedItemColor()
    val contentColor = NavigationDefaults.navigationContentColor()
    val indicatorColor = NavigationDefaults.navigationIndicatorColor()
    val navigationSuiteItemColors = NavigationSuiteItemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = selectedColor,
            unselectedIconColor = contentColor,
            selectedTextColor = selectedColor,
            unselectedTextColor = contentColor,
            indicatorColor = indicatorColor,
        ),
        navigationRailItemColors = NavigationRailItemDefaults.colors(
            selectedIconColor = selectedColor,
            unselectedIconColor = contentColor,
            selectedTextColor = selectedColor,
            unselectedTextColor = contentColor,
            indicatorColor = indicatorColor,
        ),
        navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
            selectedIconColor = selectedColor,
            unselectedIconColor = contentColor,
            selectedTextColor = selectedColor,
            unselectedTextColor = contentColor,
        ),
    )

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            ToolkitNavigationSuiteScope(navigationSuiteScope = this, navigationSuiteItemColors = navigationSuiteItemColors).run(navigationSuiteItems)
        },
        layoutType = layoutType,
        containerColor = Color.Transparent,
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContentColor = NavigationDefaults.navigationContentColor(),
            navigationRailContainerColor = Color.Transparent,
        ),
        modifier = modifier,
    ) {
        content()
    }
}

class ToolkitNavigationSuiteScope internal constructor(
    private val navigationSuiteScope: NavigationSuiteScope,
    private val navigationSuiteItemColors: NavigationSuiteItemColors,
) {
    fun item(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        icon: @Composable () -> Unit,
        selectedIcon: @Composable () -> Unit = icon,
        label: @Composable (() -> Unit)? = null,
    ) = navigationSuiteScope.item(
        selected = selected,
        onClick = onClick,
        icon = {
            if (selected) {
                selectedIcon()
            } else {
                icon()
            }
        },
        label = label,
        colors = navigationSuiteItemColors,
        modifier = modifier,
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 300.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            ToolkitNavigationSuiteScaffold(
                navigationSuiteItems = {
                    item(
                        selected = true,
                        onClick = {},
                        icon = {
                            ToolkitIcon(
                                imageVector = ToolkitIconCatalog.Home,
                                contentDescription = "Home"
                            )
                        },
                        label = { Text("Home") }
                    )
                    item(
                        selected = false,
                        onClick = {},
                        icon = {
                            ToolkitIcon(
                                imageVector = ToolkitIconCatalog.AccountCircle,
                                contentDescription = "Perfil"
                            )
                        },
                        label = { Text("Perfil") }
                    )
                    item(
                        selected = false,
                        onClick = {},
                        icon = {
                            ToolkitIcon(
                                imageVector = ToolkitIconCatalog.Settings,
                                contentDescription = "Configurações"
                            )
                        },
                        label = { Text("Configurações") }
                    )
                }
            ) {
                Text("Content")
            }
        }
    }
}