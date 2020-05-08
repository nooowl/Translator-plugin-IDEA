package ru.itmo.plugins.translator.source

interface SmartCodeTranslator {
    fun translate(text: String, lang: String = "en"): String
}