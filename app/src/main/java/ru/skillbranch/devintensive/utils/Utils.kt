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
        val map = hashMapOf<Char, String>('а' to "a", 'б' to "b", 'в' to "v", 'г' to "g",
            'д' to "d", 'е' to "e", 'ё' to "e", 'ж' to "zh", 'з' to "z", 'и' to "i", 'й' to "i", 'к' to "k", 'л' to "l",
            'м' to "m", 'н' to "n", 'о' to "o", 'г' to "g", 'п' to "p", 'р' to "r", 'с' to "s", 'т' to "t", 'у' to "u", 'ф' to "f",
            'х' to "h", 'ц' to "c", 'ч' to "ch", 'ш' to "sh", 'щ' to "sh'", 'ъ' to "", 'ы' to "i", 'ь' to "", 'э' to "e",
            'ю' to "yu", 'я' to "ya")

        val newPayload = StringBuilder()
        payload.toCharArray().forEach { char ->
            if(char.isWhitespace()) {
                newPayload.append(divider)
                return@forEach
            }

            if(!map.keys.contains(char.toLowerCase())) {
                newPayload.append(char)
                return@forEach
            }

            val toAdd = map[char.toLowerCase()]?.run {
                if(char.isUpperCase()) {
                    if(length <=1 ) {
                        toUpperCase()
                    } else {
                        first().toUpperCase().plus(drop(1))
                    }
                } else this
            }
            newPayload.append(toAdd)
        }
        return newPayload.toString()
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