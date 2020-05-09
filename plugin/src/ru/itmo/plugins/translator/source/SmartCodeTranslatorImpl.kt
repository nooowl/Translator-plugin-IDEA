package ru.itmo.plugins.translator.source

import ru.itmo.plugins.translator.api.YandexTranslator
import ru.itmo.plugins.translator.api.YandexTranslatorImpl

class SmartCodeTranslatorImpl(
        private val yandexTranslator: YandexTranslator = YandexTranslatorImpl())
    : SmartCodeTranslator {

    override fun translateCode(code: String, lang: String): String {
        val words = code.split("_")
        val translatedWords = words.map { s -> yandexTranslator.translate(lang, s) }

        return translatedWords.joinToString(separator = "_")
    }
}