package com.vald3nir.toolkit.designsystem.components.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolkitDrawer(
    contentTitle: String,
    content: @Composable (PaddingValues) -> Unit,
    menuContent: @Composable ColumnScope.() -> Unit,
    initialDrawerState: DrawerValue = DrawerValue.Closed,
) {
    val drawerState = rememberDrawerState(initialValue = initialDrawerState)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent = { ModalDrawerSheet(content = menuContent) },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(contentTitle) },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { innerPadding ->
            content(innerPadding)
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        ToolkitDrawer(
            contentTitle = "Drawer Title",
            initialDrawerState = DrawerValue.Open,
            menuContent = drawerPreviewContent(),
            content = { }
        )
    }
}

private fun drawerPreviewContent(): @Composable ColumnScope.() -> Unit = {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(12.dp))
        Text("Drawer Title", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
        ToolkitSpaceHeight()

        Text("Menu", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
        NavigationDrawerItem(
            label = { Text("Home") },
            selected = true,
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            onClick = { }
        )
        NavigationDrawerItem(
            label = { Text("Perfil") },
            selected = false,
            icon = { Icon(Icons.Filled.Person, contentDescription = null) },
            onClick = { }
        )
        NavigationDrawerItem(
            label = { Text("Configurações") },
            selected = false,
            icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
            onClick = { }
        )
        Spacer(Modifier.height(12.dp))
    }
}