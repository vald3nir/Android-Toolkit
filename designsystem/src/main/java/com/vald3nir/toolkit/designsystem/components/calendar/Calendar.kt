package com.vald3nir.toolkit.designsystem.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingXs
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import java.time.LocalDate

@Composable
fun ToolkitCalendar(
    modifier: Modifier = Modifier,
    month: Int,
    year: Int,
    selectedDay: Int = -1,
    labels: List<String> = emptyList(),
    onMonthYearChanged: (Int, Int) -> Unit,
    onDaySelected: (Int) -> Unit
) {
    val totalDays = getSizeDaysInMonth(month, year)
    val firstDayOfWeekOffset = LocalDate.of(year, month, 1).dayOfWeek.value % 7 // 0 for sunday
    val days = List(firstDayOfWeekOffset) { null } + (1..totalDays).toList()

    Column(modifier = modifier.padding(ToolkitSpacingSm)) {
        ToolkitSelectorMonthYear(month = month, year = year, onMonthYearChange = onMonthYearChanged)
        ToolkitSpaceHeight()
        StripeDayOfWeek()
        ToolkitSpaceHeight(ToolkitSpacingSm)
        LazyVerticalGrid(columns = GridCells.Fixed(7), modifier = Modifier.fillMaxWidth()) {
            items(days.size) { index ->
                val day = days[index] ?: return@items
                CalendarCell(
                    day = day,
                    text = labels.getOrElse(day - 1) { "" },
                    isSelected = day == selectedDay,
                    onDaySelected = onDaySelected
                )
            }
        }
    }
}

@Composable
private fun StripeDayOfWeek() {
    val daysOfWeek = listOf("D", "S", "T", "Q", "Q", "S", "S")
    Row(modifier = Modifier.fillMaxWidth()) {
        daysOfWeek.forEach { day ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(ToolkitSpacingXs),
                contentAlignment = Alignment.Center
            ) {
                ToolkitText(text = day, style = ToolkitTextStyle.LabelSmall, textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
private fun CalendarCell(day: Int, text: String, isSelected: Boolean, onDaySelected: (Int) -> Unit) {
    val backgroundColor = if (isSelected) Color.Blue else Color.LightGray
    val textColor = if (isSelected) Color.White else Color.Black
    Column(
        modifier = Modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ToolkitText(text = day.toString(), style = ToolkitTextStyle.LabelSmall, textAlign = TextAlign.Center)
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = backgroundColor, shape = RoundedCornerShape(ToolkitSpacingSm)
                )
                .clickable { onDaySelected(day) },
            contentAlignment = Alignment.Center
        ) {
            ToolkitText(text = text, style = ToolkitTextStyle.LabelSmall, textAlign = TextAlign.Center, textColor = textColor)
        }
    }
}

private fun getSizeDaysInMonth(month: Int, year: Int): Int {
    return LocalDate.of(year, month, 1).lengthOfMonth()
}

@ThemePreviews
@Composable
private fun ToolkitCalendarPreview() {
    var month by remember { mutableIntStateOf(LocalDate.now().monthValue) }
    var year by remember { mutableIntStateOf(LocalDate.now().year) }
    var selectedDay by remember { mutableIntStateOf(5) }

    ToolkitPreviewContainer(modifier = Modifier.size(500.dp, 750.dp)) {
        ToolkitCalendar(
            month = month,
            year = year,
            selectedDay = selectedDay,
            labels = List(getSizeDaysInMonth(month, year)) { index -> if ((index + 1) % 5 == 0) "X" else "" },
            modifier = Modifier.fillMaxWidth(),
            onMonthYearChanged = { newMonth, newYear ->
                month = newMonth
                year = newYear
                selectedDay = -1
            },
            onDaySelected = { selectedDay = it }
        )
    }
}