package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils

data class Profile(
    var firstName: String,
    var lastName: String,
    var about: String,
    var repository: String,
    var respect: Int = 0,
    var rating: Int = 0
) {

    var rank: String = "Junior Android Developer"
    var nickName: String = Utils.transliteration("$firstName $lastName", "_")


    fun toMap(): Map<String, Any> = mapOf(
        "nickname" to nickName,
        "firstname" to firstName,
        "lastname" to lastName,
        "rank" to rank,
        "rating" to rating,
        "respect" to respect,
        "about" to about,
        "repository" to repository
    )
}