package com.vald3nir.toolkit.designsystem.components.topbars

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceWidth
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.icons.BuildIconButton
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconAvatar
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolkitTopBarWithAvatar(
    modifier: Modifier = Modifier,
    topAppBarColors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
    iconTint: Color = MaterialTheme.colorScheme.onSurface,
    title: String? = null,
    @StringRes titleRes: Int? = null,
    userImageUrl: String?,
    textCenterAligned: Boolean = false,
    leftIcon: ImageVector? = null,
    onClickLeftIcon: () -> Unit = {},
    onAvatarClick: () -> Unit = {},
) {
    val resolvedTitle = titleRes?.let { stringResource(it) } ?: title
    val titleContent: @Composable () -> Unit = {
        resolvedTitle?.let {
            ToolkitText(text = it, style = ToolkitTextStyle.TitleMedium)
        }
    }

    val leftIconContent: @Composable () -> Unit = {
        leftIcon?.BuildIconButton(
            onClick = onClickLeftIcon,
            tint = iconTint,
        )
    }

    val rightIconContent: @Composable RowScope.() -> Unit = {
        ToolkitIconAvatar(
            iconSize = 42.dp,
            userImageUrl = userImageUrl,
            tint = iconTint,
            onClick = onAvatarClick
        )
        ToolkitSpaceWidth(ToolkitSpacingSm)
    }

    if (textCenterAligned) {
        CenterAlignedTopAppBar(
            title = titleContent,
            navigationIcon = leftIconContent,
            actions = rightIconContent,
            colors = topAppBarColors,
            modifier = modifier,
        )
    } else {
        TopAppBar(
            title = titleContent,
            navigationIcon = leftIconContent,
            actions = rightIconContent,
            colors = topAppBarColors,
            modifier = modifier,
        )
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 100.dp)) {
        ToolkitTopBarWithAvatar(
            title = "Exemplo com avatar",
            textCenterAligned = true,
            leftIcon = ToolkitIconCatalog.ArrowBack,
            userImageUrl = null,
        )
    }
}