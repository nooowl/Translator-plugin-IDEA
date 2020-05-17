package ru.itmo.plugins.translator.api

import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class YandexTranslatorImpl(
        private var apiKey: String = ""
) : YandexTranslator {
    private val apiUrl: String = "https://translate.yandex.net/api/v1.5/tr.json/translate"

    init {
        if (apiKey.isEmpty()) {
            try {
                apiKey = System.getenv("yandexApiKey")
            } catch (ignored: Exception) {
                // No operations.
            }
        }
    }

    override fun translate(language: String, text: String): String {
        val requestURL = createRequestURL(mapOf("lang" to language, "text" to text))
        val response = sendRequestAndGetResponse(requestURL)
        val parsedResponse = parseJson(response)
        return parsedResponse.text.first()
    }

    override fun updateApiKey(newKey: String) {
        apiKey = newKey
    }

    override fun getApiKey(): String {
        return apiKey
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
