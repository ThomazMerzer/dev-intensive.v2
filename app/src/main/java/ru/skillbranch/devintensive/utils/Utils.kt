package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {

        val parts: List<String>? = fullName?.split(" ")
        val firstName= parts?.getOrNull(0)?.trim()
        val lastName = parts?.getOrNull(1)?.trim()

        return firstName to lastName
    }

}