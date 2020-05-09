package ru.itmo.plugins.translator.source

import ru.itmo.plugins.translator.api.YandexTranslator
import ru.itmo.plugins.translator.api.YandexTranslatorImpl
import ru.itmo.plugins.translator.utlis.isLower
import ru.itmo.plugins.translator.utlis.isUpper
import ru.itmo.plugins.translator.utlis.set

class SmartCodeTranslatorImpl(
        private val yandexTranslator: YandexTranslator = YandexTranslatorImpl())
    : SmartCodeTranslator {

    override fun translateCode(code: String, lang: String): String {
        val words = smartSplit(code)
        val translatedWords = words.map { s ->
            when {
                s.isEmpty() || s == "_" -> s
                else -> smartTranslate(lang, s)
            }
        }

        return translatedWords.joinToString(separator = "")
    }

    private fun smartSplit(text: String): List<String> {
        val words = ArrayList<String>()

        val sb: StringBuilder = StringBuilder()
        for (i in 0..text.length - 2) {
            sb.append(text[i])
            if (text[i].isLowerCase() && text[i + 1].isUpperCase() || text[i + 1] == '_' || text[i] == '_') {
                words.add(sb.toString())
                sb.clear()
            }
        }
        sb.append(text.last())
        words.add(sb.toString())

        return words
    }

    private fun smartTranslate(lang: String, word: String): String {
        val articles: HashSet<String> = HashSet()
        articles.addAll(listOf("the", "a", "an"))

        val translated = yandexTranslator.translate(lang, word)
        var translatedWords = translated
                .replace("\'", "")
                .split(' ', '-')
                .filter { s -> s.isNotEmpty() }
        if (translatedWords.size == 1) return translatedWords.first()
        translatedWords = translatedWords.filter { s -> !articles.contains(s.toLowerCase()) }

        if (word.isUpper()) return translatedWords.joinToString("_") { s -> s.toUpperCase() }
        var result = translatedWords.joinToString("") { s -> s.capitalize() }
        if (word.isLower()) result = (result.set(0, result[0].toLowerCase()))
        return result
    }
}
