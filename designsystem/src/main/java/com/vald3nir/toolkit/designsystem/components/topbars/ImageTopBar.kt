package com.vald3nir.toolkit.designsystem.components.topbars

import android.R
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.icons.BuildIconButton
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolkitImageTopBar(
    modifier: Modifier = Modifier,
    topAppBarColors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
    iconTint: Color = MaterialTheme.colorScheme.onSurface,
    title: String? = null,
    @StringRes titleRes: Int? = null,
    textCenterAligned: Boolean = false,
    leftIcon: ImageVector? = ToolkitIconCatalog.ArrowBack,
    onClickLeftIcon: () -> Unit = {},
    image: Painter,
    imageSize: Dp = 40.dp,
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
        Image(
            painter = image,
            contentDescription = "Image top bar",
            contentScale = ContentScale.Fit, // Mantém a proporção original da imagem
            modifier = Modifier.size(imageSize)  // Define largura e altura fixas para manter o tamanho estável
        )
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
        ToolkitImageTopBar(
            title = "Exemplo",
            textCenterAligned = true,
            image = painterResource(id = R.drawable.ic_menu_add),
        )
    }
}