package ru.itmo.plugins.translator.testCases

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import ru.itmo.plugins.translator.testFramework.base.BaseTest
import ru.itmo.plugins.translator.testFramework.extensions.shouldBe

@RunWith(Parameterized::class)
class SmartTranslatorTests(
        private val name: String,
        private val expected: String) : BaseTest() {

    @Test
    fun testSmartTranslation() {
        smartCodeTranslator.translate(name) shouldBe expected
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): ArrayList<Array<String>> {
            return arrayListOf(
                    arrayOf("ПёсМышьКот", "DogMouseCat"),
                    arrayOf("пёс_кот_мышь", "dog_cat_mouse"),
                    arrayOf("САМОЛЕт", "Plane"),
                    arrayOf("САМОЛЕТ", "PLANE"),
                    arrayOf("Самолет", "Plane"),
                    arrayOf("пёсПёс_пёс_Пёс_ПЁС___ПЁсБОЛЬШОЙ_МЫШЬ", "dogDog_dog_Dog_DOG___DogBIG_MOUSE")
            )
        }
    }
}