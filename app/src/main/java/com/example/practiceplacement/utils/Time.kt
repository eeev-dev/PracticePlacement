package com.example.practiceplacement.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun isDatePassed(date: LocalDate): Boolean {
    return date.isBefore(LocalDate.now())
}

@RequiresApi(Build.VERSION_CODES.O)
fun parseDeadlineToLocalDate(deadlineStr: String): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
    val zonedDateTime = ZonedDateTime.parse(deadlineStr, formatter)
    return zonedDateTime.toLocalDate()
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateRussian(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))
    return date.format(formatter) + " год"
}
