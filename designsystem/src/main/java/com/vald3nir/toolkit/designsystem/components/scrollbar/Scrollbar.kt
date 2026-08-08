package com.vald3nir.toolkit.designsystem.components.scrollbar

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.Orientation.Horizontal
import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.packFloats
import androidx.compose.ui.util.unpackFloat1
import androidx.compose.ui.util.unpackFloat2
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.time.Duration.Companion.milliseconds

private const val SCROLLBAR_PRESS_DELAY_MS = 10L
private const val SCROLLBAR_PRESS_DELTA_PCT = 0.02f

class ScrollbarState {
    private var packedValue by mutableLongStateOf(0L)

    internal fun onScroll(stateValue: ScrollbarStateValue) {
        packedValue = stateValue.packedValue
    }

    val thumbSizePercent
        get() = unpackFloat1(packedValue)

    val thumbMovedPercent
        get() = unpackFloat2(packedValue)

    val thumbTrackSizePercent
        get() = 1f - thumbSizePercent
}

private val ScrollbarTrack.size
    get() = unpackFloat2(packedValue) - unpackFloat1(packedValue)

private fun ScrollbarTrack.thumbPosition(dimension: Float): Float = max(a = min(a = dimension / size, b = 1f), b = 0f)

@Immutable
@JvmInline
value class ScrollbarStateValue internal constructor(
    internal val packedValue: Long,
)

@Immutable
@JvmInline
private value class ScrollbarTrack(val packedValue: Long) {
    constructor(max: Float, min: Float) : this(packFloats(max, min))
}

fun scrollbarStateValue(thumbSizePercent: Float, thumbMovedPercent: Float) = ScrollbarStateValue(packFloats(val1 = thumbSizePercent, val2 = thumbMovedPercent))

internal fun Orientation.valueOf(offset: Offset) = when (this) {
    Horizontal -> offset.x
    Vertical -> offset.y
}

internal fun Orientation.valueOf(intSize: IntSize) = when (this) {
    Horizontal -> intSize.width
    Vertical -> intSize.height
}

internal fun Orientation.valueOf(intOffset: IntOffset) = when (this) {
    Horizontal -> intOffset.x
    Vertical -> intOffset.y
}

@Composable
fun BaseScrollbar(
    orientation: Orientation,
    state: ScrollbarState,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource? = null,
    minThumbSize: Dp = 40.dp,
    onThumbMoved: ((Float) -> Unit)? = null,
    thumb: @Composable () -> Unit,
) {
    // Using Offset.Unspecified and Float.NaN instead of null
    // to prevent unnecessary boxing of primitives
    var pressedOffset by remember { mutableStateOf(Offset.Unspecified) }
    var draggedOffset by remember { mutableStateOf(Offset.Unspecified) }

    // Used to immediately show drag feedback in the UI while the scrolling implementation
    // catches up
    var interactionThumbTravelPercent by remember { mutableFloatStateOf(Float.NaN) }

    var track by remember { mutableStateOf(ScrollbarTrack(packedValue = 0)) }

    // scrollbar track container
    Box(
        modifier = modifier
            .run {
                val withHover = interactionSource?.let(::hoverable) ?: this
                when (orientation) {
                    Vertical -> withHover.fillMaxHeight()
                    Horizontal -> withHover.fillMaxWidth()
                }
            }
            .onGloballyPositioned { coordinates ->
                val scrollbarStartCoordinate = orientation.valueOf(coordinates.positionInRoot())
                track = ScrollbarTrack(
                    max = scrollbarStartCoordinate,
                    min = scrollbarStartCoordinate + orientation.valueOf(coordinates.size),
                )
            }
            // Process scrollbar presses
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { offset ->
                        try {
                            // Wait for a long press before scrolling
                            withTimeout(viewConfiguration.longPressTimeoutMillis.milliseconds) {
                                tryAwaitRelease()
                            }
                        } catch (_: TimeoutCancellationException) {
                            // Start the press triggered scroll
                            val initialPress = PressInteraction.Press(offset)
                            interactionSource?.tryEmit(initialPress)

                            pressedOffset = offset
                            interactionSource?.tryEmit(
                                when {
                                    tryAwaitRelease() -> PressInteraction.Release(initialPress)
                                    else -> PressInteraction.Cancel(initialPress)
                                },
                            )

                            // End the press
                            pressedOffset = Offset.Unspecified
                        }
                    },
                )
            }
            // Process scrollbar drags
            .pointerInput(Unit) {
                var dragInteraction: DragInteraction.Start? = null
                val onDragStart: (Offset) -> Unit = { offset ->
                    val start = DragInteraction.Start()
                    dragInteraction = start
                    interactionSource?.tryEmit(start)
                    draggedOffset = offset
                }
                val onDragEnd: () -> Unit = {
                    dragInteraction?.let { interactionSource?.tryEmit(DragInteraction.Stop(it)) }
                    draggedOffset = Offset.Unspecified
                }
                val onDragCancel = {
                    dragInteraction?.let { interactionSource?.tryEmit(DragInteraction.Cancel(it)) }
                    draggedOffset = Offset.Unspecified
                }
                val onDrag: (change: PointerInputChange, dragAmount: Float) -> Unit =
                    onDrag@{ _, delta ->
                        if (draggedOffset == Offset.Unspecified) return@onDrag
                        draggedOffset = when (orientation) {
                            Vertical -> draggedOffset.copy(
                                y = draggedOffset.y + delta,
                            )

                            Horizontal -> draggedOffset.copy(
                                x = draggedOffset.x + delta,
                            )
                        }
                    }

                when (orientation) {
                    Horizontal -> detectHorizontalDragGestures(
                        onDragStart = onDragStart,
                        onDragEnd = onDragEnd,
                        onDragCancel = { onDragCancel() },
                        onHorizontalDrag = onDrag,
                    )

                    Vertical -> detectVerticalDragGestures(
                        onDragStart = onDragStart,
                        onDragEnd = onDragEnd,
                        onDragCancel = { onDragCancel() },
                        onVerticalDrag = onDrag,
                    )
                }
            },
    ) {
        // scrollbar thumb container
        Layout(content = { thumb() }) { measurables, constraints ->
            val measurable = measurables.first()

            val thumbSizePx = max(
                a = state.thumbSizePercent * track.size,
                b = minThumbSize.toPx(),
            )

            val trackSizePx = when (state.thumbTrackSizePercent) {
                0f -> track.size
                else -> (track.size - thumbSizePx) / state.thumbTrackSizePercent
            }

            val thumbTravelPercent = max(
                a = min(
                    a = when {
                        interactionThumbTravelPercent.isNaN() -> state.thumbMovedPercent
                        else -> interactionThumbTravelPercent
                    },
                    b = state.thumbTrackSizePercent,
                ),
                b = 0f,
            )

            val thumbMovedPx = trackSizePx * thumbTravelPercent

            val y = when (orientation) {
                Horizontal -> 0
                Vertical -> thumbMovedPx.roundToInt()
            }
            val x = when (orientation) {
                Horizontal -> thumbMovedPx.roundToInt()
                Vertical -> 0
            }

            val updatedConstraints = when (orientation) {
                Horizontal -> {
                    constraints.copy(
                        minWidth = thumbSizePx.roundToInt(),
                        maxWidth = thumbSizePx.roundToInt(),
                    )
                }

                Vertical -> {
                    constraints.copy(
                        minHeight = thumbSizePx.roundToInt(),
                        maxHeight = thumbSizePx.roundToInt(),
                    )
                }
            }

            val placeable = measurable.measure(updatedConstraints)
            layout(placeable.width, placeable.height) {
                placeable.place(x, y)
            }
        }
    }

    if (onThumbMoved == null) return

    // Process presses
    LaunchedEffect(Unit) {
        snapshotFlow { pressedOffset }.collect { pressedOffset ->
            // Press ended, reset interactionThumbTravelPercent
            if (pressedOffset == Offset.Unspecified) {
                interactionThumbTravelPercent = Float.NaN
                return@collect
            }

            var currentThumbMovedPercent = state.thumbMovedPercent
            val destinationThumbMovedPercent = track.thumbPosition(
                dimension = orientation.valueOf(pressedOffset),
            )
            val isPositive = currentThumbMovedPercent < destinationThumbMovedPercent
            val delta = SCROLLBAR_PRESS_DELTA_PCT * if (isPositive) 1f else -1f

            while (currentThumbMovedPercent != destinationThumbMovedPercent) {
                currentThumbMovedPercent = when {
                    isPositive -> min(
                        a = currentThumbMovedPercent + delta,
                        b = destinationThumbMovedPercent,
                    )

                    else -> max(
                        a = currentThumbMovedPercent + delta,
                        b = destinationThumbMovedPercent,
                    )
                }
                onThumbMoved(currentThumbMovedPercent)
                interactionThumbTravelPercent = currentThumbMovedPercent
                delay(SCROLLBAR_PRESS_DELAY_MS.milliseconds)
            }
        }

    }

    // Process drags
    LaunchedEffect(Unit) {
        snapshotFlow { draggedOffset }.collect { draggedOffset ->
            if (draggedOffset == Offset.Unspecified) {
                interactionThumbTravelPercent = Float.NaN
                return@collect
            }
            val currentTravel = track.thumbPosition(
                dimension = orientation.valueOf(draggedOffset),
            )
            onThumbMoved(currentTravel)
            interactionThumbTravelPercent = currentTravel
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    val state = remember {
        ScrollbarState().apply {
            onScroll(scrollbarStateValue(thumbSizePercent = 0.35f, thumbMovedPercent = 0.25f))
        }
    }
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 300.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            BaseScrollbar(
                orientation = Vertical,
                state = state,
                modifier = Modifier.size(12.dp, 220.dp),
                thumb = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(androidx.compose.material3.MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
                    )
                }
            )
        }
    }
}
