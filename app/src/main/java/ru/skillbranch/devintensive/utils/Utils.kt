package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        var names: List<String>? = fullName?.split(" ")

        var firstName = names?.getOrNull(0)
        var lastName = names?.getOrNull(1)

        when (firstName) {
            "" -> firstName = null
        }

        when (lastName) {
            "" -> lastName = null
        }

        return Pair(firstName, lastName)
    }

    fun toInitials(firstName: String?, lastName: String?) {

    }


    fun transliteration(payload: String?, divider: String = " ") {

    }


}