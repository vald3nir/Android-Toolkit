package com.vald3nir.toolkit.designsystem.components.scrollbar

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.Orientation.Horizontal
import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

private const val SCROLLBAR_INACTIVE_TO_DORMANT_TIME_IN_MS = 2_000L

@Composable
fun ScrollableState.DraggableScrollbar(state: ScrollbarState, orientation: Orientation, onThumbMoved: (Float) -> Unit, modifier: Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }
    BaseScrollbar(
        modifier = modifier,
        orientation = orientation,
        interactionSource = interactionSource,
        state = state,
        thumb = { DraggableScrollbarThumb(interactionSource = interactionSource, orientation = orientation) },
        onThumbMoved = onThumbMoved,
    )
}

@Composable
fun ScrollableState.DecorativeScrollbar(state: ScrollbarState, orientation: Orientation, modifier: Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }
    BaseScrollbar(
        modifier = modifier,
        orientation = orientation,
        interactionSource = interactionSource,
        state = state,
        thumb = { DecorativeScrollbarThumb(interactionSource = interactionSource, orientation = orientation) },
    )
}

@Composable
private fun ScrollableState.DraggableScrollbarThumb(interactionSource: InteractionSource, orientation: Orientation) {
    Box(
        modifier = Modifier
            .thumbSize(orientation = orientation, thickness = 12.dp)
            .scrollThumb(this, interactionSource),
    )
}

@Composable
private fun ScrollableState.DecorativeScrollbarThumb(interactionSource: InteractionSource, orientation: Orientation) {
    Box(
        modifier = Modifier
            .thumbSize(orientation = orientation, thickness = 2.dp)
            .scrollThumb(this, interactionSource),
    )
}

private fun Modifier.thumbSize(orientation: Orientation, thickness: androidx.compose.ui.unit.Dp): Modifier = when (orientation) {
    Vertical -> width(thickness).fillMaxHeight()
    Horizontal -> height(thickness).fillMaxWidth()
}

@SuppressLint("ComposableModifierFactory")
@Composable
private fun Modifier.scrollThumb(scrollableState: ScrollableState, interactionSource: InteractionSource): Modifier {
    val colorState = scrollbarThumbColor(scrollableState, interactionSource)
    return this then ScrollThumbElement { colorState.value }
}

private data class ScrollThumbElement(val colorProducer: ColorProducer) : ModifierNodeElement<ScrollThumbNode>() {
    override fun create(): ScrollThumbNode = ScrollThumbNode(colorProducer)
    override fun update(node: ScrollThumbNode) {
        node.colorProducer = colorProducer
        node.invalidateDraw()
    }
}

private class ScrollThumbNode(var colorProducer: ColorProducer) : DrawModifierNode, Modifier.Node() {
    private val shape = RoundedCornerShape(16.dp)
    private var lastSize: Size? = null
    private var lastLayoutDirection: LayoutDirection? = null
    private var lastOutline: Outline? = null

    override fun ContentDrawScope.draw() {
        val color = colorProducer()
        val outline = if (size == lastSize && layoutDirection == lastLayoutDirection) {
            lastOutline
        } else {
            shape.createOutline(size, layoutDirection, this)
        }
        if (color != Color.Unspecified && outline != null) drawOutline(outline, color = color)
        lastOutline = outline
        lastSize = size
        lastLayoutDirection = layoutDirection
    }
}

@Composable
private fun scrollbarThumbColor(scrollableState: ScrollableState, interactionSource: InteractionSource): State<Color> {
    var state by remember { mutableStateOf(ThumbState.Dormant) }
    val pressed by interactionSource.collectIsPressedAsState()
    val hovered by interactionSource.collectIsHoveredAsState()
    val dragged by interactionSource.collectIsDraggedAsState()
    val active = (scrollableState.canScrollForward || scrollableState.canScrollBackward) && (pressed || hovered || dragged || scrollableState.isScrollInProgress)
    val color = animateColorAsState(
        targetValue = when (state) {
            ThumbState.Active -> MaterialTheme.colorScheme.onSurface.copy(0.5f)
            ThumbState.Inactive -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
            ThumbState.Dormant -> Color.Transparent
        },
        animationSpec = SpringSpec(
            stiffness = Spring.StiffnessLow,
        ),
        label = "Scrollbar thumb color",
    )
    LaunchedEffect(active) {
        when (active) {
            true -> state = ThumbState.Active
            false -> if (state == ThumbState.Active) {
                state = ThumbState.Inactive
                delay(SCROLLBAR_INACTIVE_TO_DORMANT_TIME_IN_MS.milliseconds)
                state = ThumbState.Dormant
            }
        }
    }
    return color
}

private enum class ThumbState { Active, Inactive, Dormant }

@ThemePreviews
@Composable
private fun Preview() {
    val decorativeState = remember { ScrollbarState().apply { onScroll(scrollbarStateValue(thumbSizePercent = 0.25f, thumbMovedPercent = 0.1f)) } }
    val draggableState = remember { ScrollbarState().apply { onScroll(scrollbarStateValue(thumbSizePercent = 0.35f, thumbMovedPercent = 0.4f)) } }
    val scrollableState = rememberScrollState()

    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 300.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.size(24.dp, 180.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                with(scrollableState) {
                    DecorativeScrollbar(
                        state = decorativeState,
                        orientation = Vertical,
                        modifier = Modifier.size(24.dp, 86.dp)
                    )
                    DraggableScrollbar(
                        state = draggableState,
                        orientation = Vertical,
                        onThumbMoved = {},
                        modifier = Modifier.size(24.dp, 86.dp)
                    )
                }
            }
        }
    }
}
