package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

enum class TimeUnits {
    SECONDS,
    MINUTES,
    HOURS,
    DAYS;
}

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECONDS): Date {
    var time = this.time

    time += when(units) {
        TimeUnits.SECONDS -> value * SECOND
        TimeUnits.MINUTES -> value * MINUTE
        TimeUnits.HOURS -> value * HOUR
        TimeUnits.DAYS -> value * DAY
        else -> throw IllegalStateException("invalid unit")
    }
    this.time = time

    return this
}