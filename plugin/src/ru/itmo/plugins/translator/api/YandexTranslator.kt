package ru.itmo.plugins.translator.api

/**
 * @author nooowl
 */
interface YandexTranslator {
    /**
     * Translate text to passed language using 'Yandex' API
     * @param language target language
     * @param text text to translate
     * @return translated text
     */
    fun translate(language: String, text: String): String

    /**
     * Update API key with passed value
     * @param newKey new API key
     */
    fun updateApiKey(newKey: String)

    /**
     * @return current API key value
     */
    fun getApiKey(): String
}