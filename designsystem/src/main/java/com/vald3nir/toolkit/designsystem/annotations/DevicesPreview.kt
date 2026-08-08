package com.vald3nir.toolkit.designsystem.annotations

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Phone - Light",
    device = "spec:width=360dp,height=640dp,dpi=480",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = false
)
@Preview(
    name = "Phone - Dark",
    device = "spec:width=360dp,height=640dp,dpi=480",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = false
)
@Preview(
    name = "Landscape - Light",
    device = "spec:width=640dp,height=360dp,dpi=480",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = false
)
@Preview(
    name = "Foldable - Light",
    device = "spec:width=673dp,height=841dp,dpi=480",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = false
)
@Preview(
    name = "Tablet - Light",
    device = "spec:width=1280dp,height=800dp,dpi=480",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = false
)
annotation class DevicesPreview