package ru.skillbranch.devintensive.extensions

fun String.truncate(length: Int = 16): String {
    val size = trim().length
    var tmp = trim().take(length)

    return if (size != tmp.length) {
        if (tmp.toCharArray().last().isWhitespace()) {
            tmp = tmp.dropLast(1)
        }
        "$tmp..."
    } else {
        tmp
    }
}

