package ru.itmo.plugins.translator.source

import ru.itmo.plugins.translator.api.YandexTranslator
import ru.itmo.plugins.translator.api.YandexTranslatorImpl

class SmartCodeTranslatorImpl(
        private val yandexTranslator: YandexTranslator = YandexTranslatorImpl())
    : SmartCodeTranslator {

    override fun translate(text: String, lang: String): String {
        val words = mySplit(text).split("_")
        val translatedWords = words.map { s -> yandexTranslator.translate(lang, s) }

        return translatedWords.joinToString(separator = "_").replace(" ", "")
    }

    private fun mySplit(text: String): String {
        var words = text
        for (i in 0..words.length - 2) {
            if (text[i].isLowerCase() && text[i + 1].isUpperCase()) {
                words = words.replaceRange(i, i + 1, "${text[i]}${text[i + 1]}")
            }
        }
        return words
    }
}