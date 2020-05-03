package ru.itmo.plugins.translator.api

interface YandexTranslator {
    fun translate(language: String, text: String): String
}