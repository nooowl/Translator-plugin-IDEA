package ru.itmo.plugins.translator.testFramework.testLogger

import java.io.File
import java.util.*

object Logger {
    private var logsFile: File? = File("test.log")

    fun info(info: Any) = log(LoggerMode.INFO, info)

    fun error(info: Any) = log(LoggerMode.ERROR, info)

    fun warn(info: Any) = log(LoggerMode.WARN, info)

    fun debug(info: Any) = log(LoggerMode.DEBUG, info)

    fun close() {
        logsFile = null
    }

    private fun log(mode: LoggerMode, toLog: Any) {
        val formatted = formattedLogText(mode, toLog)
        print(formatted)
        logsFile?.appendText(formatted)
    }

    private fun formattedLogText(mode: LoggerMode, toLog: Any): String =
            "[${Calendar.getInstance().time}] $mode :: $toLog\r\n"
}