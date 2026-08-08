package com.vald3nir.toolkit.designsystem.components.icons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitIconAvatar(
    userImageUrl: String? = null,
    iconSize: Dp = 42.dp,
    contentDescription: String? = "Avatar",
    tint: Color = LocalContentColor.current,
    onClick: () -> Unit = {},
) {
    val modifier = Modifier
        .size(iconSize)
        .clip(CircleShape)
    IconButton(onClick = onClick, modifier = modifier) {
        SubcomposeAsyncImage(
            model = userImageUrl,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = modifier.clip(CircleShape),
            loading = { AvatarFallbackIcon(contentDescription = contentDescription, tint = tint) },
            error = { AvatarFallbackIcon(contentDescription = contentDescription, tint = tint) }
        )
    }
}

@Composable
private fun AvatarFallbackIcon(contentDescription: String?, tint: Color) {
    Icon(
        imageVector = ToolkitIconCatalog.AccountCircle,
        contentDescription = contentDescription,
        tint = tint
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(200.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            ToolkitIconAvatar(iconSize = 100.dp)
        }
    }
}