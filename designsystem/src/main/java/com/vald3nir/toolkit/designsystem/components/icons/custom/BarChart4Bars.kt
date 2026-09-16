package com.vald3nir.toolkit.designsystem.components.icons.custom

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Suppress("CheckReturnValue")
val bar_chart_4_bars: ImageVector
    get() {
        _bar_chart_4_bars?.let { return it }

        val vector =
            ImageVector.Builder(
                name = "bar_chart_4_bars",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.Companion.NonZero,
                    ) {
                        moveTo(2f, 21f)
                        verticalLineTo(19f)
                        horizontalLineTo(22f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(2f)
                        close()
                        moveTo(3f, 18f)
                        verticalLineTo(11f)
                        horizontalLineTo(6f)
                        verticalLineToRelative(7f)
                        horizontalLineTo(3f)
                        close()
                        moveToRelative(5f, 0f)
                        verticalLineTo(6f)
                        horizontalLineToRelative(3f)
                        verticalLineTo(18f)
                        horizontalLineTo(8f)
                        close()
                        moveToRelative(5f, 0f)
                        verticalLineTo(9f)
                        horizontalLineToRelative(3f)
                        verticalLineToRelative(9f)
                        horizontalLineTo(13f)
                        close()
                        moveToRelative(5f, 0f)
                        verticalLineTo(3f)
                        horizontalLineToRelative(3f)
                        verticalLineTo(18f)
                        horizontalLineTo(18f)
                        close()
                    }
                }
                .build()
        _bar_chart_4_bars = vector
        return vector
    }

private var _bar_chart_4_bars: ImageVector? = null