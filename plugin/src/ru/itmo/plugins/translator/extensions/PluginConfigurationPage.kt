package ru.itmo.plugins.translator.extensions

import com.intellij.openapi.options.Configurable
import ru.itmo.plugins.translator.services.PluginService
import javax.swing.JComponent

class PluginConfigurationPage : Configurable {
    private var component: SettingsComponent? = null

    override fun isModified() = true

    override fun getDisplayName() = "Translator Plugin Configuration"

    override fun apply() {
        PluginService.getInstance()
                .yandexTranslator.updateApiKey(component!!.apiKey)
        PluginService.getInstance().defaultLanguage = component!!.defaultLanguage
    }

    override fun createComponent(): JComponent? {
        component = SettingsComponent(
                PluginService.getInstance().yandexTranslator.getApiKey(),
                PluginService.getInstance().defaultLanguage)
        return component!!.getRootComponent()
    }
}