package ru.itmo.plugins.translator.api

interface YandexTranslator {
    fun translate(language: String, text: String): String

    /**
     * Update API key with passed value
     * @param newKey new API key
     */
    fun updateApiKey(newKey: String)
}