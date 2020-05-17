package ru.itmo.plugins.translator.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ui.MessageType
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiNamedElement
import com.intellij.refactoring.actions.BaseRefactoringAction.getPsiElementArray
import com.intellij.refactoring.rename.RenameDialog
import com.intellij.ui.awt.RelativePoint
import ru.itmo.plugins.translator.api.YandexTranslatorImpl
import ru.itmo.plugins.translator.constants.YANDEX_API_KEY
import ru.itmo.plugins.translator.source.SmartCodeTranslator
import ru.itmo.plugins.translator.source.SmartCodeTranslatorImpl
import java.awt.MouseInfo
import java.io.IOException


class TranslateAction : AnAction() {
    private val smartTranslator: SmartCodeTranslator = SmartCodeTranslatorImpl(
            YandexTranslatorImpl(YANDEX_API_KEY + "32")
    )

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val dataContext = e.dataContext
        val editor: Editor = e.getData(CommonDataKeys.EDITOR)!!

        PsiDocumentManager.getInstance(project).commitAllDocuments()

        val psiNamedElem = getPsiElementArray(dataContext)[0] as PsiNamedElement
        val originalName = psiNamedElem.name!!
        val translatedName: String
        try {
            translatedName = smartTranslator.translateCode(originalName)
        } catch (ignored: IOException) {
            val warning = JBPopupFactory.getInstance()
                    .createHtmlTextBalloonBuilder(
                            "Please, set correct 'yandexApiKey' in Settings",
                            MessageType.WARNING,
                            null)
                    .createBalloon()
            warning.showInCenterOf(editor.component)
            return
        }

        RenameDialog(project, psiNamedElem, psiNamedElem, editor).performRename(translatedName)
    }
}