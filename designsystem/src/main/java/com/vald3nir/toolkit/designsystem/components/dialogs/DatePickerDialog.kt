package com.vald3nir.toolkit.designsystem.components.dialogs

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitLinkButton
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun toolkitDatePickerDialog(context: Context, onSelect: (String) -> Unit): DatePickerDialog {
    val calendar = Calendar.getInstance()
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return DatePickerDialog(
        context, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val selectedCal = Calendar.getInstance()
            selectedCal.set(year, month, dayOfMonth)
            onSelect(dateFormatter.format(selectedCal.time))
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    var selectedDate by remember { mutableStateOf("") }
    val context = LocalContext.current
    val datePickerDialog = remember(context) {
        toolkitDatePickerDialog(context = context, onSelect = { selectedDate = it })
    }
    ToolkitPreviewContainer(modifier = Modifier.size(200.dp)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ToolkitText(
                text = if (selectedDate.isNotBlank()) "Data selecionada: $selectedDate" else "Nenhuma data selecionada",
                style = ToolkitTextStyle.TitleSmall
            )
            ToolkitSpaceHeight()
            ToolkitLinkButton(
                onClick = { datePickerDialog.show() },
                label = "Selecionar data"
            )
        }
    }
}