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
        private var id: Int = -1

        fun makeUser(fullName: String?): User {
            id++
            fullName?.split(" ")
            val (firstName, lastName) = Utils.parseFullName(fullName)

            return User("$id", firstName, lastName)
        }
    }

    data class Builder(
        var id:String? = null,
        var firstName:String? = null,
        var lastName:String? = null,
        var avatar:String? = null,
        var rating:Int = 0,
        var respect:Int = 0,
        var lastVisit: Date? = Date(),
        var isOnline:Boolean = false
    ) {
        fun id(id: String?) = apply {this.id = id}
        fun firstName(firstName: String?) = apply {this.firstName = firstName}
        fun lastName(lastName: String?) = apply {this.lastName = lastName}
        fun avatar(avatar: String?) = apply {this.avatar = avatar}
        fun rating(rating:Int = 0) = apply {this.rating = rating}
        fun respect(respect:Int = 0) = apply {this.respect = respect}
        fun lastVisit(lastVisit: Date? = Date()) = apply {this.lastVisit = lastVisit}
        fun isOnline(isOnline:Boolean = false) = apply {this.isOnline = isOnline}

        fun build() = User ("1", firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
    }
}