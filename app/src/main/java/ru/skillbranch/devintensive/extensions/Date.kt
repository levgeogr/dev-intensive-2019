package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
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
    }
    this.time = currentTime
    return this
}

fun Date.shortFormat(): String? {
//    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    return ""
}

fun Date.humanizeDiff(date: Date = Date()): String {

    return when {
        (abs(date.time - this.time) in 0..SECOND) -> "только что"
        (time + 360 * DAY < date.time) -> "более года назад"
        (time - 360 * DAY > date.time) -> "более чем через год"
        else -> {
            var template: String = if (this > date) "через %s" else "%s назад"
            var seconds = (abs(date.time - this.time) / 1000).toInt()
            template.format(
                when {
                    seconds in 1..45 -> "несколько секунд"
                    seconds in 45..75 -> "минуту"
                    seconds in 75..45 * 60 -> TimeUnits.MINUTE.plural(seconds / 60) //magic numbers, да

                    seconds in 45 * 60..75 * 60 -> "час"
                    seconds in 75 * 60..22 * 3600 -> TimeUnits.HOUR.plural(seconds / 3600)

                    seconds in 22 * 3600..26 * 3600 -> "день"
                    seconds in 26 * 3600 ..360 * 86400  -> TimeUnits.DAY.plural(seconds / 86400)

                    else -> IllegalStateException()
                }
            )
        }
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    private fun getOnePlural() = when (this) {
        SECOND -> "секунду"
        MINUTE -> "минуту"
        HOUR -> "час"
        DAY -> "день"
    }

    private fun getTwoThreeFourPlural() = when (this) {
        SECOND -> "секунды"
        MINUTE -> "минуты"
        HOUR -> "часа"
        DAY -> "дня"
    }

    private fun getDefaultPlural() = when (this) {
        SECOND -> "секунд"
        MINUTE -> "минут"
        HOUR -> "часов"
        DAY -> "дней"
    }

    fun plural(value: Int): String {

        val ones = value % 10
        val dec = value % 100
        return when {
            ones == 1 && dec != 11 -> "$value " + getOnePlural()
            (ones in 2..4 && (dec < 12 || 14 < dec)) -> "$value " + getTwoThreeFourPlural()
            else -> "$value " + getDefaultPlural()
        }
    }

}