package ru.itmo.plugins.translator.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ui.DialogBuilder
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.MessageType
import com.intellij.openapi.ui.popup.Balloon
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiNamedElement
import com.intellij.refactoring.actions.BaseRefactoringAction.getPsiElementArray
import com.intellij.refactoring.rename.RenameDialog
import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import ru.itmo.plugins.translator.services.PluginService
import java.awt.Dimension
import java.awt.Insets
import java.io.IOException
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField


class TranslateAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val dataContext = e.dataContext
        val editor: Editor = e.getData(CommonDataKeys.EDITOR)!!

        PsiDocumentManager.getInstance(project).commitAllDocuments()

        val textField = JTextField()
        val languageDialog = createLanguageDialog(textField)
        var targetLanguage: String
        val isOk = languageDialog.show() == DialogWrapper.OK_EXIT_CODE
        if (isOk) {
            targetLanguage = textField.text
        } else {
            return
        }

        val psiNamedElem = getPsiElementArray(dataContext)[0] as PsiNamedElement
        val originalName = psiNamedElem.name!!
        val translatedName: String
        try {
            val service = PluginService.getInstance()
            translatedName = service.smartTranslator
                    .translateCode(originalName, targetLanguage)
        } catch (ex: IOException) {
            if (ex.message!!.split("http")[0].contains("400"))
                createWarning("There are no language: '$targetLanguage'. Use short language form")
                        .showInCenterOf(editor.component)
            else
                createWarning("Please, set correct 'yandexApiKey' in Settings->Translator Plugin Configuration")
                        .showInCenterOf(editor.component)
            return
        }

        RenameDialog(project, psiNamedElem, psiNamedElem, editor).performRename(translatedName)
    }

    private fun createWarning(text: String): Balloon =
            JBPopupFactory.getInstance()
                    .createHtmlTextBalloonBuilder(
                            text,
                            MessageType.WARNING,
                            null)
                    .createBalloon()

    private fun createLanguageDialog(textField: JTextField): DialogBuilder {
        val rootPanel = JPanel()
        rootPanel.layout = GridLayoutManager(3, 2, Insets(0, 0, 0, 0), -1, -1)
        rootPanel.isRequestFocusEnabled = true
        val label = JLabel()
        label.text = "Target language"
        rootPanel.add(label, GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                null, Dimension(80, 16), null, 0, false))
        textField.autoscrolls = true
        textField.isEditable = true
        textField.isEnabled = true
        textField.horizontalAlignment = 10
        textField.text = PluginService.getInstance().defaultLanguage
        rootPanel.add(textField, GridConstraints(0, 1, 1, 1,
                GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null, 0, false))
        label.labelFor = textField

        val builder = DialogBuilder()
        builder.setCenterPanel(rootPanel)
        builder.setDimensionServiceKey("LanguageDialog")
        builder.setTitle("Target language settings")
        builder.removeAllActions()
        builder.addOkAction()
        builder.addCancelAction()
        return builder
    }

}