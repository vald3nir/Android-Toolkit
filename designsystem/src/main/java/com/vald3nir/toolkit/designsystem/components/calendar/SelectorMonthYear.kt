package com.vald3nir.toolkit.designsystem.components.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLocale
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import java.time.LocalDate
import java.time.format.TextStyle

@Composable
fun ToolkitSelectorMonthYear(month: Int, year: Int, onMonthYearChange: (Int, Int) -> Unit) {
    val monthLabel = LocalDate
        .of(year, month, 1)
        .month
        .getDisplayName(TextStyle.FULL, LocalLocale.current.platformLocale)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(ToolkitSpacingSm),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ToolkitIcon(
            imageVector = ToolkitIconCatalog.ChevronLeft,
            onClick = {
                val (newMonth, newYear) = if (month == 1) 12 to (year - 1) else (month - 1) to year
                onMonthYearChange(newMonth, newYear)
            }
        )
        ToolkitText(text = "$monthLabel $year", style = ToolkitTextStyle.TitleMedium)
        ToolkitIcon(
            imageVector = ToolkitIconCatalog.ChevronRight,
            onClick = {
                val (newMonth, newYear) = if (month == 12) 1 to (year + 1) else (month + 1) to year
                onMonthYearChange(newMonth, newYear)
            }
        )
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(300.dp, 100.dp)) {
        var month by remember { mutableIntStateOf(LocalDate.now().monthValue) }
        var year by remember { mutableIntStateOf(LocalDate.now().year) }
        ToolkitSelectorMonthYear(
            month = month,
            year = year,
            onMonthYearChange = { newMonth, newYear ->
                month = newMonth
                year = newYear
            }
        )
    }
}