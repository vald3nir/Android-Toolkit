package com.vald3nir.toolkit.designsystem.components.texts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitBackground
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.theme.ToolkitTheme
import com.vald3nir.toolkit.designsystem.theme.text.ToolkitTypography

enum class ToolkitTextStyle(val textStyle: TextStyle) {
    DisplayLarge(ToolkitTypography.displayLarge),
    DisplayMedium(ToolkitTypography.displayMedium),
    DisplaySmall(ToolkitTypography.displaySmall),

    HeadlineLarge(ToolkitTypography.headlineLarge),
    HeadlineMedium(ToolkitTypography.headlineMedium),
    HeadlineSmall(ToolkitTypography.headlineSmall),

    TitleLarge(ToolkitTypography.titleLarge),
    TitleMedium(ToolkitTypography.titleMedium),
    TitleSmall(ToolkitTypography.titleSmall),

    BodyLarge(ToolkitTypography.bodyLarge),
    BodyMedium(ToolkitTypography.bodyMedium),
    BodySmall(ToolkitTypography.bodySmall),

    LabelLarge(ToolkitTypography.labelLarge),
    LabelMedium(ToolkitTypography.labelMedium),
    LabelSmall(ToolkitTypography.labelSmall),
}

@Composable
fun ToolkitText(
    text: String,
    style: ToolkitTextStyle,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color? = null,
) {
    style.textStyle.BuiltText(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        textColor = textColor
    )
}

@Composable
private fun TextStyle.BuiltText(modifier: Modifier = Modifier, text: String, textAlign: TextAlign, textColor: Color?) = Text(
    modifier = modifier,
    text = text,
    style = this.copy(textAlign = textAlign, color = textColor ?: this.color)
)

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            catalogTexts()
        }
    }
}

private fun LazyListScope.catalogTexts() {
    // ======================================================
    // Display
    // ======================================================
    item { ToolkitText(text = "DisplayLarge", style = ToolkitTextStyle.DisplayLarge) }
    item { ToolkitText(text = "DisplayMedium", style = ToolkitTextStyle.DisplayMedium) }
    item { ToolkitText(text = "DisplaySmall", style = ToolkitTextStyle.DisplaySmall) }

    // ======================================================
    // Headline
    // ======================================================
    item { ToolkitText(text = "HeadlineLarge", style = ToolkitTextStyle.HeadlineLarge) }
    item { ToolkitText(text = "HeadlineMedium", style = ToolkitTextStyle.HeadlineMedium) }
    item { ToolkitText(text = "HeadlineSmall", style = ToolkitTextStyle.HeadlineSmall) }

    // ======================================================
    // Title
    // ======================================================
    item { ToolkitText(text = "TitleLarge", style = ToolkitTextStyle.TitleLarge) }
    item { ToolkitText(text = "TitleMedium", style = ToolkitTextStyle.TitleMedium) }
    item { ToolkitText(text = "TitleSmall", style = ToolkitTextStyle.TitleSmall) }

    // ======================================================
    // Body
    // ======================================================
    item { ToolkitText(text = "BodyLarge", style = ToolkitTextStyle.BodyLarge) }
    item { ToolkitText(text = "BodyMedium", style = ToolkitTextStyle.BodyMedium) }
    item { ToolkitText(text = "BodySmall", style = ToolkitTextStyle.BodySmall) }

    // ======================================================
    // Label
    // ======================================================
    item { ToolkitText(text = "LabelLarge", style = ToolkitTextStyle.LabelLarge) }
    item { ToolkitText(text = "LabelMedium", style = ToolkitTextStyle.LabelMedium) }
    item { ToolkitText(text = "LabelSmall", style = ToolkitTextStyle.LabelSmall) }

    // ======================================================
    // Custom
    // ======================================================
    item { ToolkitText(text = "Link", style = ToolkitTextStyle.LabelMedium, textColor = Color.Blue) }
    item { ToolkitText(text = "Error message", style = ToolkitTextStyle.LabelLarge, textColor = Color.Red) }
}