package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = Date(),
    var isOnline: Boolean = false
) {

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    fun showMe() {
        print("I'm $firstName by $lastName ($id) \n")
    }

    constructor(id: String) : this(id, "Firstname", "Lastname $id ")

    companion object Factory {
        private var idLast: Int = -1

        fun makeUser(fullName: String?): User {
            idLast++
//            fullName?.split(" ")
            val (firstName, lastName) = Utils.parseFullName(fullName)

            return User(idLast.toString(), firstName, lastName)
        }
    }

    class Builder {
        var id: String? = null
        var firstName: String? = null
        var lastName: String? = null
        var avatar: String? = null
        var rating: Int = 0
        var respect: Int = 0
        var lastVisit: Date? = Date()
        var isOnline: Boolean = false

//        fun id(id: String?) = apply { this.id = id }
//        fun firstName(firstName: String?) = apply { this.firstName = firstName }
//        fun lastName(lastName: String?) = apply { this.lastName = lastName }
//        fun avatar(avatar: String?) = apply { this.avatar = avatar }
//        fun rating(rating: Int = 0) = apply { this.rating = rating }
//        fun respect(respect: Int = 0) = apply { this.respect = respect }
//        fun lastVisit(lastVisit: Date? = Date()) = apply { this.lastVisit = lastVisit }
//        fun isOnline(isOnline: Boolean = false) = apply { this.isOnline = isOnline }

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun firstName(firstName: String?): Builder {
            this.firstName = firstName
            return this
        }

        fun lastName(lastName: String?): Builder {
            this.lastName = lastName
            return this
        }

        fun avatar(avatar: String?): Builder {
            this.avatar = avatar
            return this
        }

        fun rating(rating: Int): Builder {
            this.rating = rating
            return this
        }

        fun respect(respect: Int): Builder {
            this.respect = respect
            return this
        }

        fun lastVisit(lastVisit: Date?): Builder {
            this.lastVisit = lastVisit
            return this
        }

        fun isOnline(isOnline: Boolean): Builder {
            this.isOnline = isOnline
            return this
        }

        fun build(): User {
            return User(id
                ?: (++idLast).toString(), firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
        }
    }
}