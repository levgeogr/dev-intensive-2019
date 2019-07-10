package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60
const val DAY = HOUR * 24

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val date = SimpleDateFormat(pattern, Locale("ru"))
    return date.format(this)
}

fun Date.add(valueAdd: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var currentTime = this.time

    currentTime += when (units) {
        TimeUnits.SECOND -> valueAdd * SECOND
        TimeUnits.MINUTE -> valueAdd * MINUTE
        TimeUnits.HOUR -> valueAdd * HOUR
        TimeUnits.DAY -> valueAdd * DAY
        else -> throw IllegalStateException("invalid TimeUnit")
    }
    this.time = currentTime
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
   return ""
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}