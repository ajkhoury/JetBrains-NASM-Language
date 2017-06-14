package com.nasmlanguage;

import com.intellij.lang.annotation.*;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.nasmlanguage.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NASMAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof NASMMacroCall) {
            NASMMacroCall macroCall = (NASMMacroCall)element;
            String value = macroCall.getText();
            if (value != null && value.length() > 0) {
                Project project = element.getProject();
                int idxEnd = value.indexOf('(');
                String macroIdentifier = value.substring(0, idxEnd);
                System.out.println("macroIdentifier = \"" + macroIdentifier + "\"");
                List<NASMMacro> macros = NASMUtil.findPreprocessorMacros(project);
                for (NASMMacro macro : macros) {
                    System.out.println("macro = \"" + macro.getText() + "\"");
                    String macroText = macro.getText();
                    if (macroText != null) {
                        int identifierIdx = macroText.indexOf(macroIdentifier);
                        if (identifierIdx != -1) {
                            System.out.println("start offset = \"" + element.getTextRange().getStartOffset() + "\"");
                            TextRange range = new TextRange(
                                    element.getTextRange().getStartOffset(),
                                    element.getTextRange().getStartOffset() + idxEnd
                            );
                            Annotation annotation = holder.createInfoAnnotation(range, null);
                            annotation.setTextAttributes(NASMSyntaxHighlighter.MACRO_CALL);
                        }
                    }
                }
            }
        } else if (element instanceof NASMPreprocessor) {
            NASMPreprocessor nasmPreprocessor = (NASMPreprocessor)element;
            NASMMacro macro = nasmPreprocessor.getMacro();
            if (macro != null) {
                String macroIdentifier = macro.getMacroIdentifier();
                if (macroIdentifier != null) {
                    System.out.println("macroIdentifier = \"" + macroIdentifier + "\"");
                    int identifierIdx = macro.getText().indexOf(macroIdentifier);
                    if (identifierIdx != -1) {
                        int startOffset = element.getTextRange().getStartOffset() + identifierIdx;
                        TextRange range = new TextRange(
                                startOffset,
                                startOffset + macroIdentifier.length()
                        );
                        Annotation annotation = holder.createInfoAnnotation(range, null);
                        annotation.setTextAttributes(NASMSyntaxHighlighter.MACRO_CALL);
                    }
                }
            }
            //else {
            //    NASMDefine define = nasmPreprocessor.getDefine();
            //    if (define != null) {
            //        String defineIdentifier = define.getDefineIdentifier();
            //        if (defineIdentifier != null) {
            //            System.out.println("defineIdentifier = \"" + defineIdentifier + "\"");
            //        }
            //    }
            //}
        }
    }
}
