package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {

        val parts: List<String>? = fullName?.split(" ")
        val firstName= parts?.getOrNull(0)?.trim()
        val lastName = parts?.getOrNull(1)?.trim()

        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        return this.toString()
    }

    fun toInitials(str1: String? = null, str2: String? = null): String {
        return this.toString()
    }

}