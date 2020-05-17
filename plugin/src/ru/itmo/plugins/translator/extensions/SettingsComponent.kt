package ru.itmo.plugins.translator.extensions

import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import com.intellij.uiDesigner.core.Spacer
import java.awt.Dimension
import java.awt.Insets
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class SettingsComponent internal constructor(
        private val defaultApiKey: String?,
        private val defaultDefaultLanguage: String?) {
    private var rootPanel: JPanel? = null
    private var apiKeyField: JTextField? = null
    private var defaultLanguageField: JTextField? = null

    val apiKey: String
        get() = apiKeyField!!.text

    val defaultLanguage: String
        get() = defaultLanguageField!!.text

    @Suppress("DuplicatedCode")
    private fun setupUI() {
        rootPanel = JPanel()
        rootPanel!!.layout = GridLayoutManager(6, 2, Insets(0, 0, 0, 0), -1, -1)
        rootPanel!!.isRequestFocusEnabled = true
        val label1 = JLabel()
        label1.text = "Yandex API key"
        rootPanel!!.add(label1, GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, null, Dimension(80, 16), null, 0, false))
        apiKeyField = JTextField()
        apiKeyField!!.autoscrolls = true
        apiKeyField!!.isEditable = true
        apiKeyField!!.isEnabled = true
        apiKeyField!!.horizontalAlignment = 10
        apiKeyField!!.text = if (defaultApiKey == null || defaultApiKey.isEmpty()) "" else defaultApiKey
        rootPanel!!.add(apiKeyField!!, GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false))
        val label2 = JLabel()
        val label2text = "Enter your yandex api key"
        label2.text = label2text
        label2.verticalAlignment = 0
        rootPanel!!.add(label2, GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false))
        label1.labelFor = apiKeyField

        val label3 = JLabel()
        label3.text = "Default target language"
        rootPanel!!.add(label3, GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, null, Dimension(80, 16), null, 0, false))
        defaultLanguageField = JTextField()
        defaultLanguageField!!.autoscrolls = true
        defaultLanguageField!!.isEditable = true
        defaultLanguageField!!.isEnabled = true
        defaultLanguageField!!.horizontalAlignment = 10
        defaultLanguageField!!.text = if (defaultDefaultLanguage == null || defaultDefaultLanguage.isEmpty()) "" else defaultDefaultLanguage
        rootPanel!!.add(defaultLanguageField!!, GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false))
        val label4 = JLabel()
        val label4text = "Enter default target language"
        label4.text = label4text
        label4.verticalAlignment = 0
        rootPanel!!.add(label4, GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false))
        val spacer2 = Spacer()
        rootPanel!!.add(spacer2, GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false))
        label3.labelFor = defaultLanguageField
    }

    fun getRootComponent(): JComponent? {
        return rootPanel
    }

    init {
        setupUI()
    }
}