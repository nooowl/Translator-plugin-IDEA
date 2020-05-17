package ru.itmo.plugins.translator.services

import ru.itmo.plugins.translator.api.YandexTranslatorImpl
import ru.itmo.plugins.translator.constants.YANDEX_API_KEY
import ru.itmo.plugins.translator.source.SmartCodeTranslator
import ru.itmo.plugins.translator.source.SmartCodeTranslatorImpl

class PluginService private constructor() {
    companion object {
        private var instance: PluginService? = null

        fun getInstance(): PluginService {
            if (instance == null) {
                instance = PluginService()
            }
            return instance!!
        }
    }

    val smartTranslator: SmartCodeTranslator = SmartCodeTranslatorImpl(
            YandexTranslatorImpl(YANDEX_API_KEY))
}