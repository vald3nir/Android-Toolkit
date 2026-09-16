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
val wifi_device: ImageVector
    get() {
        _wifi_device?.let { return it }

        val vector =
            ImageVector.Builder(
                name = "wifi_device",
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
                        moveTo(7f, 19f)
                        quadTo(6.18f, 19f, 5.59f, 18.41f)
                        reflectiveQuadTo(5f, 17f)
                        verticalLineTo(7f)
                        quadTo(5f, 6.18f, 5.59f, 5.59f)
                        reflectiveQuadTo(7f, 5f)
                        horizontalLineTo(17f)
                        quadToRelative(0.82f, 0f, 1.41f, 0.59f)
                        quadTo(19f, 6.18f, 19f, 7f)
                        verticalLineTo(17f)
                        quadToRelative(0f, 0.82f, -0.59f, 1.41f)
                        reflectiveQuadTo(17f, 19f)
                        horizontalLineTo(7f)
                        close()
                        moveToRelative(0f, 2f)
                        horizontalLineTo(17f)
                        quadToRelative(1.65f, 0f, 2.83f, -1.18f)
                        reflectiveQuadTo(21f, 17f)
                        verticalLineTo(7f)
                        quadTo(21f, 5.35f, 19.83f, 4.17f)
                        reflectiveQuadTo(17f, 3f)
                        horizontalLineTo(7f)
                        quadTo(5.35f, 3f, 4.18f, 4.17f)
                        reflectiveQuadTo(3f, 7f)
                        verticalLineTo(17f)
                        quadToRelative(0f, 1.65f, 1.17f, 2.82f)
                        reflectiveQuadTo(7f, 21f)
                        close()
                        moveTo(7.28f, 11.52f)
                        lineTo(5.9f, 10.15f)
                        quadTo(6.95f, 8.88f, 8.61f, 8.19f)
                        reflectiveQuadTo(12f, 7.5f)
                        quadToRelative(1.68f, 0f, 3.35f, 0.69f)
                        reflectiveQuadToRelative(2.72f, 1.96f)
                        lineTo(16.7f, 11.52f)
                        quadTo(15.83f, 10.58f, 14.61f, 10.04f)
                        reflectiveQuadTo(12f, 9.5f)
                        reflectiveQuadTo(9.39f, 10.04f)
                        reflectiveQuadTo(7.28f, 11.52f)
                        close()
                        moveToRelative(2.85f, 2.85f)
                        lineTo(8.7f, 12.95f)
                        quadToRelative(0.65f, -0.7f, 1.51f, -1.07f)
                        reflectiveQuadTo(12.03f, 11.5f)
                        reflectiveQuadToRelative(1.79f, 0.38f)
                        reflectiveQuadToRelative(1.49f, 1.07f)
                        lineToRelative(-1.4f, 1.43f)
                        quadTo(13.55f, 14f, 13.04f, 13.75f)
                        reflectiveQuadTo(12f, 13.5f)
                        reflectiveQuadToRelative(-1.02f, 0.25f)
                        reflectiveQuadToRelative(-0.85f, 0.63f)
                        close()
                        moveToRelative(1.16f, 2.34f)
                        quadTo(11f, 16.43f, 11f, 16f)
                        reflectiveQuadToRelative(0.29f, -0.71f)
                        reflectiveQuadTo(12f, 15f)
                        reflectiveQuadToRelative(0.71f, 0.29f)
                        reflectiveQuadTo(13f, 16f)
                        reflectiveQuadToRelative(-0.29f, 0.71f)
                        reflectiveQuadTo(12f, 17f)
                        reflectiveQuadTo(11.29f, 16.71f)
                        close()
                        moveTo(12f, 12f)
                        close()
                    }
                }
                .build()
        _wifi_device = vector
        return vector
    }

private var _wifi_device: ImageVector? = null