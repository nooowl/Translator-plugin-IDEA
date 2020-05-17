package ru.itmo.plugins.translator.testFramework.extensions

import ru.itmo.plugins.translator.testFramework.testLogger.Logger
import kotlin.test.assertEquals

infix fun <T> T.shouldBe(other: T) {
    Logger.info("Check is $this equals to $other")
    try {
        assertEquals(other, this)
    } catch (e: AssertionError) {
        Logger.error("Items are different (${e.message})")
        throw e
    }
    Logger.info("Items are identical")
}