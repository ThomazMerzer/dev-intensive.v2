package ru.skillbranch.devintensive.models

import java.util.*

/**
 * Тестовая сущность для второго урока DevIntensive
 */

class User(val id: String,
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
        println("It's Alive! $id: $firstName $lastName | $avatar.$rating.$respect.$lastVisit.$isOnline\n")
    }

    fun printMe() {
        println("$id: $firstName $lastName | $avatar.$rating.$respect.$lastVisit.$isOnline")
    }

    companion object Factory {
        var lastId: Int = 0

        fun makeUser(fullName: String?): User {
            lastId = lastId.inc()

            val parts: List<String>? = fullName?.split(" ")
            val firstName= parts?.getOrNull(0)?.trim()
            val lastName = parts?.getOrNull(1)?.trim()

            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }
}