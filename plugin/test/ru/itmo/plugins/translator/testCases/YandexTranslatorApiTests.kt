package ru.itmo.plugins.translator.testCases

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import ru.itmo.plugins.translator.testFramework.base.BaseTest
import ru.itmo.plugins.translator.testFramework.extensions.shouldBe

@RunWith(Parameterized::class)
class YandexTranslatorApiTests(
        private val lang: String,
        private val word: String,
        private val expected: String) : BaseTest() {

    @Test
    fun testBasicTranslation() {
        yandexTranslator.translate(lang, word) shouldBe expected
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): ArrayList<Array<String>> {
            return arrayListOf(
                    arrayOf("en", "Пёс", "Dog"),
                    arrayOf("en", "Мышь", "Mouse"),
                    arrayOf("es", "Пёс", "El perro"),
                    arrayOf("es", "Мышь", "El ratón"),
                    arrayOf("uk", "Пёс", "Пес"),
                    arrayOf("uk", "Мышь", "Миша")
            )
        }
    }
}