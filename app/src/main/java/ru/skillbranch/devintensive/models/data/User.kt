package ru.skillbranch.devintensive.models.data

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = Date(),
    var isOnline: Boolean = false
) {

    constructor(id: String, firstName: String?, lastName: String?) : this(id, firstName, lastName, null)

    companion object Factory {
        private var idLast: Int = -1

        fun makeUser(fullName: String?): User {
            idLast++
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(idLast.toString(), firstName, lastName)
        }
    }

    class Builder
        ( var id: String? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        var avatar: String? = null,
        var rating: Int = 0,
        var respect: Int = 0,
        var lastVisit: Date? = Date(),
        var isOnline: Boolean = false
        ) {
        fun id(id: String?) = apply { this.id = id }
        fun firstName(firstName: String?) = apply { this.firstName = firstName }
        fun lastName(lastName: String?) = apply { this.lastName = lastName }
        fun avatar(avatar: String?) = apply { this.avatar = avatar }
        fun rating(rating: Int = 0) = apply { this.rating = rating }
        fun respect(respect: Int = 0) = apply { this.respect = respect }
        fun lastVisit(lastVisit: Date? = Date()) = apply { this.lastVisit = lastVisit }
        fun isOnline(isOnline: Boolean = false) = apply { this.isOnline = isOnline }

        fun build(): User {
            return User(
                id
                    ?: (++idLast).toString(), firstName, lastName, avatar, rating, respect, lastVisit, isOnline
            )
        }
    }
}