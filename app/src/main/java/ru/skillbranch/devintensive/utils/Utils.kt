package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {

        val parts: List<String>? = fullName?.split(" ")
        val firstName= parts?.getOrNull(0)?.trim()
        val lastName = parts?.getOrNull(1)?.trim()

        return if(firstName.isNullOrEmpty() && lastName.isNullOrEmpty()) {
            null to null
        } else firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        return this.toString()
    }

    fun toInitials(firstName: String? = null, lastName: String? = null): String? {

        val firstInitial = firstName?.trim()?.take(1) ?: ""
        val secondInitial = lastName?.trim()?.take(1) ?: ""

        val initials = "$firstInitial$secondInitial"

        return if(initials.isNotEmpty()) {
            initials.toUpperCase()
        } else null
    }
}