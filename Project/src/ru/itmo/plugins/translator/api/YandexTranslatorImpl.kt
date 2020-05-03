package ru.itmo.plugins.translator.api

import com.google.gson.Gson
import java.io.FileInputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.*

class YandexTranslatorImpl : YandexTranslator {
    private val apiUrl: String = "https://translate.yandex.net/api/v1.5/tr.json/translate"
    private val apiKey: String

    init {
        val properties = Properties()
        properties.load(FileInputStream("resources/api.properties"))
        apiKey = when (val tmp: String? = System.getenv("apiKey")) {
            null -> properties.getProperty("apiKey")
            else -> tmp
        }
    }

    override fun translate(language: String, text: String): String {
        val requestURL = createRequestURL(mapOf("lang" to language, "text" to text))
        val response = parseJson(sendRequestAndGetResponse(requestURL))
        return response.text.first()
    }

    private fun parseJson(text: String): TranslatorResponse {
        return Gson().fromJson(text, TranslatorResponse::class.java)
    }

    private fun sendRequestAndGetResponse(requestURL: URL): String {
        var result: String
        with(requestURL.openConnection() as HttpURLConnection) {
            requestMethod = "GET"
            result = inputStream.bufferedReader().readText()
        }
        return result
    }

    private fun createRequestURL(args: Map<String, String>): URL {
        var request = "$apiUrl?key=$apiKey&"
        request += args.entries.stream()
                .map { e -> "${e.key}=${URLEncoder.encode(e.value, "UTF-8")}" }
                .toArray().joinToString("&")
        return URL(request)
    }
}


fun main() {
    val result = YandexTranslatorImpl().translate("en", "Пёс дурак!")
    print(result)
}