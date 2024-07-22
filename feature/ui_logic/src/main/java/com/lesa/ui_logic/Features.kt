package com.lesa.ui_logic

import java.text.NumberFormat
import java.util.Locale

internal fun String.parseToDouble(): Double {
    val format = NumberFormat.getInstance(Locale.getDefault())
    format.maximumFractionDigits = 2
    return try {
        format.parse(this)?.toDouble() ?: 0.0
    } catch (e: Exception) {
        0.0
    }
}

internal fun Double.parseToString(): String {
    val format = NumberFormat.getInstance(Locale.getDefault())
    format.maximumFractionDigits = 2
    return format.format(this)
}
