package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {

    val timeThis = this.time
    val timeNow = date.time

    var differenceTime = timeNow - timeThis
    println("differenceTime = $differenceTime")

    differenceTime = fixDifferenceTime(differenceTime)

    println("differenceTime = $differenceTime")

    if (differenceTime >= 0) //now or future
        return when (differenceTime) {
            in 0 * SECOND..SECOND -> "только что"
            in 1 * SECOND..45 * SECOND -> "несколько секунд назад"
            in 46L * SECOND..75L * SECOND -> "минуту назад"

            in 75 * SECOND..45 * MINUTE -> createTimeCommentsPast(differenceTime / MINUTE, TimeUnits.MINUTE)
            in 45 * MINUTE..75 * MINUTE -> "час назад"
            in 75 * MINUTE..22 * HOUR -> createTimeCommentsPast(differenceTime / HOUR, TimeUnits.HOUR)
            in 22 * HOUR..26 * HOUR -> "день назад"
            in 26 * HOUR..360 * DAY -> createTimeCommentsPast(differenceTime / DAY, TimeUnits.DAY)
            else -> "более года назад"
        }
    else { //future
        differenceTime = abs(differenceTime)
        return when (differenceTime) {
            1 * SECOND -> "только что"
            in 1 * SECOND..45 * SECOND -> "через несколько секунд"
            in 45 * SECOND..75 * SECOND -> "через минуту"

            in 75 * SECOND..45 * MINUTE -> createTimeCommentsFuture(differenceTime / MINUTE, TimeUnits.MINUTE)
            in 45 * MINUTE..75 * MINUTE -> "через час"
            in 75 * MINUTE..22 * HOUR -> createTimeCommentsFuture(differenceTime / HOUR, TimeUnits.HOUR)
            in 22 * HOUR..26 * HOUR -> "через день"
            in 26 * HOUR..360 * DAY -> createTimeCommentsFuture(differenceTime / DAY, TimeUnits.DAY)
            else -> "более чем через год"
        }
    }
}

private fun fixDifferenceTime(value: Long) = value.div(1000) * 1000

private fun createTimeCommentsPast(valueTime: Long, typeOfTime: TimeUnits): String {
    return when (typeOfTime) {
        TimeUnits.SECOND -> {
            if (valueTime < 21) {
                return when (valueTime) {
                    1L -> "1 секунду назад"
                    in 2L..4L -> "$valueTime секунды назад"
                    else -> "$valueTime секунд назад"
                }
            } else {
                if (valueTime < 100) {
                    return when (valueTime.rem(10)) {
                        1L -> "$valueTime секунду назад"
                        in 2L..4L -> "$valueTime секунды назад"
                        else -> "$valueTime секунд назад"
                    }
                }
                return if (valueTime < 1000) {
                    when (valueTime.rem(100)) {
                        1L -> "$valueTime секунду назад"
                        in 2L..4L -> "$valueTime секунды назад"
                        else -> "$valueTime секунд назад"
                    }
                } else {
                    when (valueTime.rem(1000)) {
                        1L -> "$valueTime секунду назад"
                        in 2L..4L -> "$valueTime секунды назад"
                        else -> "$valueTime секунд назад"
                    }
                }
            }
        }
        TimeUnits.MINUTE -> {
            /**
             * 1 минуту назад
             * 2, 3, 4 минуты
             * 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 минут
             * 21 минуту
             */
            if (valueTime < 21) {
                return when (valueTime) {
                    1L -> "$valueTime минуту назад"
                    in 2L..4L -> "$valueTime минуты назад"
                    else -> "$valueTime минут назад"
                }
            } else {
                if (valueTime < 100) {
                    return when (valueTime.rem(10)) {
                        1L -> "$valueTime минуту назад"
                        in 2L..4L -> "$valueTime минуты назад"
                        else -> "$valueTime минут назад"
                    }
                }
                return if (valueTime < 1000) {
                    when (valueTime.rem(100)) {
                        1L -> "$valueTime минуту назад"
                        in 2L..4L -> "$valueTime минуты назад"
                        else -> "$valueTime минут назад"
                    }
                } else {
                    when (valueTime.rem(1000)) {
                        1L -> "$valueTime минуту назад"
                        in 2L..4L -> "$valueTime минуты назад"
                        else -> "$valueTime минут назад"
                    }
                }
            }
        }
        TimeUnits.HOUR -> {
            /**
             * 1 час назад
             * 2, 3, 4 часа
             * 5, 6, 7, 8, 9, 10 часов
             */
            if (valueTime < 21) {
                return when (valueTime) {
                    1L -> "1 час назад"
                    in 2L..4L -> "$valueTime часа назад"
                    else -> "$valueTime часов назад"
                }
            } else {
                if (valueTime < 100) {
                    return when (valueTime.rem(10)) {
                        1L -> "$valueTime час назад"
                        in 2L..4L -> "$valueTime часа назад"
                        else -> "$valueTime часов назад"
                    }
                }
                return if (valueTime < 1000) {
                    when (valueTime.rem(100)) {
                        1L -> "$valueTime час назад"
                        in 2L..4L -> "$valueTime часа назад"
                        else -> "$valueTime часов назад"
                    }
                } else {
                    when (valueTime.rem(1000)) {
                        1L -> "$valueTime час назад"
                        in 2L..4L -> "$valueTime часа назад"
                        else -> "$valueTime часов назад"
                    }
                }
            }
        }
        TimeUnits.DAY -> {
            /**
             * 1 день
             * 2, 3, 4 дня
             * 5, 6, 7, 8, 9, 10 и т.д дней
             */
            if (valueTime < 21) {
                return when (valueTime) {
                    1L -> "1 день назад"
                    in 2L..4L -> "$valueTime дня назад"
                    else -> "$valueTime дней назад"
                }
            } else {
                if (valueTime < 100) {
                    return when (valueTime.rem(10)) {
                        1L -> "1 день назад"
                        in 2L..4L -> "$valueTime дня назад"
                        else -> "$valueTime дней назад"
                    }
                }
                return if (valueTime < 1000) {
                    when (valueTime.rem(100)) {
                        1L -> "1 день назад"
                        in 2L..4L -> "$valueTime дня назад"
                        else -> "$valueTime дней назад"
                    }
                } else {
                    when (valueTime.rem(1000)) {
                        1L -> "1 день назад"
                        in 2L..4L -> "$valueTime дня назад"
                        else -> "$valueTime дней назад"
                    }
                }
            }
        }
    }
}

private fun createTimeCommentsFuture(valueTime: Long, typeOfTime: TimeUnits): String {
    return when (typeOfTime) {
        TimeUnits.SECOND -> {
            if (valueTime in 1..45)
                return "через несколько секунд"
            if (valueTime in 46..99) {
                return when (valueTime.rem(10)) {
                    1L -> "$valueTime секунду назад"
                    in 2L..4L -> "$valueTime секунды назад"
                    else -> "$valueTime секунд назад"
                }
            }
            return if (valueTime < 1000) {
                when (valueTime.rem(100)) {
                    1L -> "через $valueTime секунду"
                    in 2L..4L -> "через $valueTime секунды"
                    else -> "через $valueTime секунд"
                }
            } else {
                when (valueTime.rem(1000)) {
                    1L -> "через $valueTime секунду"
                    in 2L..4L -> "через $valueTime секунды"
                    else -> "через $valueTime секунд"
                }
            }
        }
        TimeUnits.MINUTE -> {
            if (valueTime < 21) {
                return when (valueTime) {
                    1L -> "через $valueTime минуту"
                    in 2L..4L -> "через $valueTime минуты"
                    else -> "через $valueTime минут"
                }
            } else {
                if (valueTime < 100) {
                    return when (valueTime.rem(10)) {
                        1L -> "через $valueTime минуту"
                        in 2L..4L -> "через $valueTime минуты"
                        else -> "через $valueTime минут"
                    }
                }
                return if (valueTime < 1000) {
                    when (valueTime.rem(100)) {
                        1L -> "через $valueTime минуту"
                        in 2L..4L -> "через $valueTime минуты"
                        else -> "через $valueTime минут"
                    }
                } else {
                    when (valueTime.rem(1000)) {
                        1L -> "через $valueTime минуту"
                        in 2L..4L -> "через $valueTime минуты"
                        else -> "через $valueTime минут"
                    }
                }
            }
        }
        TimeUnits.HOUR -> {
            if (valueTime < 21) {
                return when (valueTime) {
                    1L -> "через $valueTime час"
                    in 2L..4L -> "через $valueTime часа"
                    else -> "через $valueTime часов"
                }
            } else {
                if (valueTime < 100) {
                    return when (valueTime.rem(10)) {
                        1L -> "через $valueTime час"
                        in 2L..4L -> "через $valueTime часа"
                        else -> "через $valueTime часов"
                    }
                }
                return if (valueTime < 1000) {
                    when (valueTime.rem(100)) {
                        1L -> "через $valueTime час"
                        in 2L..4L -> "через $valueTime часа"
                        else -> "через $valueTime часов"
                    }
                } else {
                    when (valueTime.rem(1000)) {
                        1L -> "через $valueTime час"
                        in 2L..4L -> "через $valueTime часа"
                        else -> "через $valueTime часов"
                    }
                }
            }
        }
        TimeUnits.DAY -> {
            if (valueTime < 21) {
                return when (valueTime) {
                    1L -> "через $valueTime день"
                    in 2L..4L -> "через $valueTime дня"
                    else -> "через $valueTime дней"
                }
            } else {
                if (valueTime < 100) {
                    return when (valueTime.rem(10)) {
                        1L -> "через $valueTime день"
                        in 2L..4L -> "через $valueTime дня"
                        else -> "через $valueTime дней"
                    }
                }
                return if (valueTime < 1000) {
                    when (valueTime.rem(100)) {
                        1L -> "через $valueTime день"
                        in 2L..4L -> "через $valueTime дня"
                        else -> "через $valueTime дней"
                    }
                } else {
                    when (valueTime.rem(1000)) {
                        1L -> "через $valueTime день"
                        in 2L..4L -> "через $valueTime дня"
                        else -> "через $valueTime дней"
                    }
                }
            }
        }
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        println("$this")

        var innerValue: Int = if (value >= 100)
            value.rem(100)
        else
            value
        if (innerValue > 20) {
            innerValue = innerValue.rem(10)
        }

        return when (this) {
            SECOND -> {
                return when (innerValue) {
                    1 -> "$value секунду"
                    in 2..4 -> "$value секунды"
                    else -> "$value секунд"
                }
            }
            MINUTE -> {
                return when (innerValue) {
                    1 -> "$value минуту"
                    in 2..4 -> "$value минуты"
                    else -> "$value минут"
                }
            }
            HOUR -> {
                return when (innerValue) {
                    1 -> "$value час"
                    in 2..4 -> "$value часа"
                    else -> "$value часов"
                }
            }
            DAY -> {
                return when (innerValue) {
                    1 -> "$value день"
                    in 2..4 -> "$value дня"
                    else -> "$value дней"
                }
            }
        }
    }
}

fun TimeUnits.plurals(value: Int): String {
    var tempStr = ""

    var one = arrayOf(1, 21, 31, 41, 51)
    val twoToFour = arrayOf(2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54)
    val five = arrayOf(
        5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 25, 26, 27,
        28, 29, 30, 35, 36, 37, 38, 39, 40, 45, 46, 47, 48, 49, 50, 55, 56, 57, 58, 59, 60
    )

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