package ru.itmo.plugins.translator.source

interface SmartCodeTranslator {
    fun translateCode(code: String, lang: String = "en"): String
}
