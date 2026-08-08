package com.vald3nir.toolkit.designsystem.components.menus

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitHorizontalSelector(
    modifier: Modifier = Modifier,
    title: String,
    options: List<String>,
    optionUnselectedTextColor: Color = Color.Black,
    optionSelectedTextColor: Color = Color.White,
    optionSelectedColor: Color = Color.Blue,
    optionUnselectedColor: Color = Color.LightGray,
    response: (String) -> Unit = {}
) {
    var selectedOption by remember(options) { mutableStateOf(options.firstOrNull()) }
    val limitItemsNoScroll = 3

    Column(modifier = modifier) {
        ToolkitText(text = title, style = ToolkitTextStyle.TitleSmall)
        if (options.isEmpty()) return@Column
        ToolkitSpaceHeight()
        Row(
            modifier = if (options.size > limitItemsNoScroll) Modifier.horizontalScroll(rememberScrollState()) else Modifier,
            horizontalArrangement = Arrangement.spacedBy(ToolkitSpacingSm),
        ) {
            val buttonsModifier: Modifier = if (options.size > limitItemsNoScroll) Modifier else Modifier.weight(1f)
            options.forEach { option ->
                val isSelected = selectedOption == option
                Button(
                    onClick = {
                        selectedOption = option
                        response(option)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) optionSelectedColor else optionUnselectedColor
                    ),
                    modifier = buttonsModifier
                ) {
                    Text(text = option, color = if (isSelected) optionSelectedTextColor else optionUnselectedTextColor)
                }
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 300.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ToolkitSpacingMd),
        ) {
            ToolkitHorizontalSelector(
                title = "Titulo",
                options = listOf("Opção 1", "Opção 2", "Opção 3")
            )
            ToolkitSpaceHeight()
            ToolkitHorizontalSelector(
                title = "Titulo 2",
                options = listOf("Opção 1", "Opção 2", "Opção 3", "Opção 4", "Opção 5")
            )
        }
    }
}