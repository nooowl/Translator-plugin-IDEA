package ru.itmo.plugins.translator.api

class TranslatorResponse(val code: String, val lang: String, val text: List<String>)