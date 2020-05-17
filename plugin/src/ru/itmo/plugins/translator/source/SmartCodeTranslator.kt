package ru.itmo.plugins.translator.source

/**
 * @author nooowl
 */
interface SmartCodeTranslator {
    /**
     * Translate part of code to passed language
     * @param code string with part of code to translate it to lang
     * @param lang target language
     * @return translated code in same style as passed
     */
    fun translateCode(code: String, lang: String = "en"): String
}
