package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val names: List<String>? = fullName?.split(" ")

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

    fun toInitials(firstName: String?, lastName: String?): String? {
        val first = firstName?.trim()?.capitalize()?.getOrNull(0)
        val last = lastName?.trim()?.capitalize()?.getOrNull(0)
        return if (first == null && last == null) null else "${first ?: ""}${last ?: ""}"
    }


    fun transliteration(payload: String?, divider: String = " "): String {
        var res = ""
        payload?.forEach {
            res += when {
                (it == ' ') -> divider
                it.isUpperCase() -> bukovkiEpt[it.toLowerCase()]?.capitalize() ?: it.toString()
                else -> bukovkiEpt[it] ?: it.toString()
            }
        }
        return res
    }


    private val bukovkiEpt = mapOf(
        'а' to "a", 'б' to "b", 'в' to "v", 'г' to "g", 'д' to "d", 'е' to "e", 'ё' to "e",
        'ж' to "zh", 'з' to "z", 'и' to "i", 'й' to "i", 'к' to "k", 'л' to "l", 'м' to "m",
        'н' to "n", 'о' to "o", 'п' to "p", 'р' to "r", 'с' to "s", 'т' to "t", 'у' to "u",
        'ф' to "f", 'х' to "h", 'ц' to "c", 'ч' to "ch", 'ш' to "sh", 'щ' to "sh'",
        'ъ' to "", 'ы' to "i", 'ь' to "", 'э' to "e", 'ю' to "yu", 'я' to "ya"
    )
}