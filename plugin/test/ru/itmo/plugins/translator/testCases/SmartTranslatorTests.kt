package ru.itmo.plugins.translator.testCases

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import ru.itmo.plugins.translator.testFramework.base.BaseTest
import ru.itmo.plugins.translator.testFramework.extensions.shouldBe

@RunWith(Parameterized::class)
class SmartTranslatorTests(
        private val code: String,
        private val expected: String) : BaseTest() {

    @Test
    fun testSmartTranslation() {
        logger.info("Translate $code")
        smartCodeTranslator.translateCode(code) shouldBe expected
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): ArrayList<Array<String>> {
            return arrayListOf(
                    arrayOf("ПёсМышьКот", "DogMouseCat"),
                    arrayOf("пёс_кот_мышь", "dog_cat_mouse"),
                    arrayOf("МЫШь", "Mouse"),
                    arrayOf("МЫШЬ", "MOUSE"),
                    arrayOf("Мышь", "Mouse"),
                    arrayOf("пёсПёс_пёс_Пёс_ПЁС___ПЁсБОЛЬШОЙ_МЫШЬ", "dogDog_dog_Dog_DOG___DogLARGE_MOUSE"),
                    arrayOf("самолет", "plane"),
                    arrayOf("САМОлет", "Plane"),
                    arrayOf("САМОЛЕТ", "PLANE"),
                    arrayOf("чернобелый", "blackAndWhite"),
                    arrayOf("ЧЕРнобелый", "BlackAndWhite"),
                    arrayOf("ЧЕРНОБЕЛЫЙ", "BLACK_AND_WHITE"),
                    arrayOf("самообучение", "selfStudy"),
                    arrayOf("САмообучение", "SelfStudy"),
                    arrayOf("САМООБУЧЕНИЕ", "SELF_STUDY")
            )
        }
    }
}