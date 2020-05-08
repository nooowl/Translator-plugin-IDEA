package ru.itmo.plugins.translator.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiNamedElement
import com.intellij.refactoring.actions.BaseRefactoringAction.getPsiElementArray
import com.intellij.refactoring.rename.RenameDialog
import ru.itmo.plugins.translator.source.SmartCodeTranslator
import ru.itmo.plugins.translator.source.SmartCodeTranslatorImpl


class TranslateAction : AnAction() {
    private val smartTranslator : SmartCodeTranslator = SmartCodeTranslatorImpl()

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val dataContext = e.dataContext

        PsiDocumentManager.getInstance(project).commitAllDocuments()

        val psiNamedElem = getPsiElementArray(dataContext)[0] as PsiNamedElement
        val originalName = psiNamedElem.name!!
        val translatedName = smartTranslator.translate(originalName)
        val editor: Editor = e.getData(CommonDataKeys.EDITOR)!!

        RenameDialog(project, psiNamedElem, psiNamedElem, editor).performRename(translatedName)
    }
}