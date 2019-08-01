package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 * Тестовая сущность для второго урока DevIntensive
 */

data class User(val id: String,
           var firstName: String?,
           var lastName: String?,
           var avatar: String?,
           var rating: Int = 0,
           var respect: Int = 0,
           val lastVisit: Date? = null,
           val isOnline: Boolean = false
) {
    constructor(id: String, firstName: String?, lastName: String?): this(id, firstName, lastName, null)
    constructor(id: String): this(id, null, null)

    init {
        println("id:$id, $firstName $lastName | $avatar.$rating.$respect.$lastVisit.$isOnline\n")
    }

    fun printMe() {
        println("$id: $firstName $lastName | $avatar.$rating.$respect.$lastVisit.$isOnline")
    }

    companion object Factory {
        var lastId: Int = -1

        fun makeUser(fullName: String?): User {

            lastId = lastId.inc()
            val firstName = Utils.parseFullName(fullName).first
            val lastName = Utils.parseFullName(fullName).second

            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }
}