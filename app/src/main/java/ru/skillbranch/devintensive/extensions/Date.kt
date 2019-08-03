package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;
}

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
        else -> throw IllegalStateException("invalid unit")
    }
    this.time = time

    return this
}

fun TimeUnits.plural(value: Int): String {
    var tempStr = ""

    var one = arrayOf(1, 21, 31, 41, 51, 61, 71, 81, 91, 101, 121, 131, 141, 151, 161, 171, 181, 191, 201,
        221, 231, 241, 251, 261, 271, 281, 291, 301, 321, 331, 341, 351, 361)
    val twoToFour = arrayOf(2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54, 222)
    val five = arrayOf(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 25, 26, 27,
        28, 29, 30, 35, 36, 37, 38, 39, 40, 45, 46, 47, 48, 49, 50, 55, 56, 57, 58, 59, 60)

    val timeUnit = this.name

    tempStr = when (timeUnit) {
        "SECOND" -> if (value in one) "секунду" else if (value in twoToFour) "секунды" else "секунд"
        "MINUTE" -> if (value in one) "минуту" else if (value in twoToFour) "минуты" else "минут"
        "HOUR" -> if (value in one) "час" else if (value in twoToFour) "часа" else "часов"
        "DAY" -> if (value in one) "день" else if (value in twoToFour) "дня" else "дней"
        else -> ""
    }

    return "$value $tempStr"
}