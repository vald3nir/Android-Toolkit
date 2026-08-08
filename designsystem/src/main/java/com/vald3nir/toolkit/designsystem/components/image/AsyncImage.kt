package com.vald3nir.toolkit.designsystem.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.theme.providers.LocalTintTheme

@Composable
fun ToolkitAsyncImage(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    contentDescription: String? = "",
    placeholder: Painter = basePlaceholder(),
) {
    val iconTint = LocalTintTheme.current.iconTint
    var imageState by remember { mutableStateOf<AsyncImagePainter.State>(AsyncImagePainter.State.Empty) }
    val imageLoader = rememberAsyncImagePainter(
        model = imageUrl,
        onState = { state -> imageState = state }
    )
    val isLocalInspection = LocalInspectionMode.current
    val isLoading = imageState is AsyncImagePainter.State.Loading
    val isError = imageState is AsyncImagePainter.State.Error

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading && !isLocalInspection) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(80.dp),
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
        Image(
            contentScale = ContentScale.Crop,
            painter = if (isError.not() && !isLocalInspection) imageLoader else placeholder,
            contentDescription = contentDescription,
            colorFilter = if (iconTint != Unspecified) ColorFilter.tint(iconTint) else null,
        )
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(120.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            ToolkitAsyncImage(
                modifier = Modifier.size(96.dp),
                imageUrl = "https://picsum.photos/200/200",
                contentDescription = "Placeholder image"
            )
        }
    }
}