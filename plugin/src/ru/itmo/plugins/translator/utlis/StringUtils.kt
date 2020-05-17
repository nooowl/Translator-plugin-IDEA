package ru.itmo.plugins.translator.utlis

fun String.isUpper(): Boolean = checkWord(Char::isUpperCase)

fun String.isLower(): Boolean = checkWord(Char::isLowerCase)

fun String.checkWord(f: Char.() -> Boolean): Boolean {
    for (l in this)
        if (!l.f()) return false
    return true
}

operator fun String.set(i: Int, value: Char): String {
    val array = this.toCharArray()
    array[i] = value
    return array.joinToString("")
}