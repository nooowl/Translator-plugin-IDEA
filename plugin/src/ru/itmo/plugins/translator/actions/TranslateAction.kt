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
import ru.itmo.plugins.translator.services.PluginService
import java.io.IOException


class TranslateAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val dataContext = e.dataContext
        val editor: Editor = e.getData(CommonDataKeys.EDITOR)!!

        PsiDocumentManager.getInstance(project).commitAllDocuments()

        val psiNamedElem = getPsiElementArray(dataContext)[0] as PsiNamedElement
        val originalName = psiNamedElem.name!!
        val translatedName: String
        try {
            translatedName = PluginService.getInstance()
                    .smartTranslator.translateCode(originalName)
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