package ru.itmo.plugins.translator.testFramework.base

import org.junit.AfterClass
import org.junit.BeforeClass
import ru.itmo.plugins.translator.api.YandexTranslator
import ru.itmo.plugins.translator.api.YandexTranslatorImpl
import ru.itmo.plugins.translator.source.SmartCodeTranslator
import ru.itmo.plugins.translator.source.SmartCodeTranslatorImpl
import ru.itmo.plugins.translator.testFramework.testLogger.Logger

open class BaseTest {
    companion object {
        @JvmStatic
        var yandexTranslator: YandexTranslator = YandexTranslatorImpl()
        @JvmStatic
        var smartCodeTranslator: SmartCodeTranslator = SmartCodeTranslatorImpl(yandexTranslator)
        @JvmStatic
        var logger: Logger = Logger
    }
}