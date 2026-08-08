package com.vald3nir.toolkit.core.utils.extensions

import com.vald3nir.toolkit.core.services.analytics.notifyLog
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.abs

fun getSizeDaysInMonth(month: Int, year: Int): Int {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month - 1)
    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
}

fun Int.getMonthReduced(): String {
    val months = listOf("Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez")
    return if (this in 1..12) months[this - 1] else ""
}

fun String?.getMonthReduced(): String {
    return if (this.isNullOrEmpty()) "" else this.toInt().getMonthReduced()
}

fun String.sanitize(): String {
    val regex = Regex("[^a-zA-Z0-9]")
    return this.replace(regex, "_")
}

fun String?.toDateReduced(): String {
    return try {
        val parsedDate = ZonedDateTime.parse(this)
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        parsedDate.format(formatter)
    } catch (e: Exception) {
        e.notifyLog()
        getShortDate()
    }
}

fun getISODate() = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.'Z'").withZone(ZoneOffset.UTC).format(Instant.now())

fun getShortDate(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(Date())
}

fun String.getAge(): Int {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val birthday = LocalDate.parse(this, formatter)
    val today = LocalDate.now()
    return Period.between(birthday, today).years
}

fun String.getElapsedTimeText(): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val informedDate = LocalDate.parse(this, formatter)
    val period = Period.between(informedDate, LocalDate.now())
    return "${period.years} anos, ${period.months} meses e ${period.days} dias"
}

fun String?.isValidBirthdate(maxYearsDifference: Long = 120): Boolean {
    if(isNullOrEmpty()) return false
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return runCatching {
        val informedDate = LocalDate.parse(this, formatter)
        val today = LocalDate.now()
        if (informedDate.isAfter(today)) return@runCatching false
        val diff = abs(ChronoUnit.YEARS.between(informedDate, today))
        diff <= maxYearsDifference
    }.getOrDefault(false)
}
