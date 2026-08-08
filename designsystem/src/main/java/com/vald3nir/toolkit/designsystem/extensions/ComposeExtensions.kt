package com.vald3nir.toolkit.designsystem.extensions

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import kotlin.random.Random

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun ScreenSizeInDp(): Pair<Float, Float> {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp
    return Pair(screenWidthDp.toFloat(), screenHeightDp.toFloat())
}

fun getRandomColors(size: Int): List<Color> {
    return (1..size).map {
        Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f)
    }
}