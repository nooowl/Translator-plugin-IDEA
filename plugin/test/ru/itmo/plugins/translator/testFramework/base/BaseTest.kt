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
        lateinit var logger: Logger

        @BeforeClass
        fun setUp() {
            logger = Logger
            logger.debug("Logger initialized")
            logger.info("Starting tests up...")
        }

        @AfterClass
        fun tearDown() {
            logger.info("Tests are finished")
            logger.debug("Logger closed")
            logger.close()
        }
    }
}