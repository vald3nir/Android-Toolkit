package com.vald3nir.toolkit.core.utils.extensions

import android.text.Editable
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.Locale

fun Double.formatCurrency(): String {
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return format.format(this)
}

fun Int.formatNumberWithZeroPrefix(): String = if (this < 10) "0$this" else "$this"

fun Double?.toIntString(): String = if (this == null) "" else "${this.toInt()}"

fun Float?.toDoubleOrZero(): Double = this?.toDouble() ?: 0.0
fun Double?.toFloatOrZero(): Float = this?.toFloat() ?: 0f

fun String?.toDoubleOrZero(): Double = if (this.isNullOrBlank()) 0.0 else this.toDouble()
fun String?.toIntOrZero(): Int = if (this.isNullOrBlank()) 0 else this.toInt()

fun Double?.toIntOrZero() = this?.toInt() ?: 0
fun Double?.orZero() = this ?: 0.0
fun Int?.orZero() = this ?: 0
fun Long?.orZero() = this ?: 0
fun Float?.orZero() = this ?: 0.0f

fun Float?.toMoney(): String {
    val ptBr = Locale("pt", "BR")
    return NumberFormat.getCurrencyInstance(ptBr).format(this)
}

fun Double?.toMoney(): String {
    val ptBr = Locale("pt", "BR")
    return NumberFormat.getCurrencyInstance(ptBr).format(this)
}

fun Int?.formatString(): String {
    return this?.toString().orEmpty()
}

fun Float?.formatString(): String {
    return this?.toString().orEmpty()
}

fun Int.formatDecimal(): String {
    return if (this <= 9) "0$this" else "$this"
}

fun Float?.format(): String {
    val bd = BigDecimal(this?.toDouble() ?: 0.0f.toDouble())
    return bd.setScale(2, RoundingMode.HALF_UP).toString()
}

fun Editable?.format(): String {
    return this.toString().trim()
}

fun createNumbersArray(start: Int, end: Int): ArrayList<String> {
    val numbers = arrayListOf<String>()
    for (i in start..end) numbers.add(i.toString())
    return numbers
}

fun Boolean?.orFalse() = this ?: false

fun String?.getLastXLetters(x: Int): String = if (this.isNullOrEmpty() || this.length < x) "" else this.takeLast(2)

fun String.limitToX(x: Int = 10): String = if (length > x) take(x) + "..." else this

fun String.cap(): String = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
}

fun String.monetaryToDouble(): Double = this.replace("R$", "").replace(".", "").replace(",", ".").trim().toDouble()