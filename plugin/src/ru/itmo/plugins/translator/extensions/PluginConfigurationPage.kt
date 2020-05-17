package ru.itmo.plugins.translator.extensions

import com.intellij.openapi.options.Configurable
import ru.itmo.plugins.translator.constants.YANDEX_API_KEY
import ru.itmo.plugins.translator.services.PluginService
import ru.itmo.plugins.translator.source.SmartCodeTranslatorImpl
import javax.swing.JComponent

class PluginConfigurationPage : Configurable {
    private var component: SettingsComponent? = null
    private var yandexApiKey: String = ""

    override fun isModified() = true

    override fun getDisplayName() = "Translator Plugin Configuration"

    override fun apply() {
        yandexApiKey = component!!.text
        (PluginService.getInstance().smartTranslator as SmartCodeTranslatorImpl)
                .yandexTranslator.updateApiKey(yandexApiKey)
    }

    override fun createComponent(): JComponent? {
        component = SettingsComponent(yandexApiKey)
        return component!!.`$$$getRootComponent$$$`()
    }
}