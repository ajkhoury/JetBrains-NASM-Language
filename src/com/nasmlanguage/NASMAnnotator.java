package com.nasmlanguage;

import com.intellij.lang.annotation.*;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.nasmlanguage.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Arrays;

public class NASMAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof NASMMacroCall) {
            NASMMacroCall macroCall = (NASMMacroCall)element;
            String macroCallText = macroCall.getText();
            if (macroCallText != null && macroCallText.length() > 0) {
                Project project = element.getProject();
                int identifierLength = macroCallText.indexOf('(');
                String macroIdentifier = macroCallText.substring(0, identifierLength);
                List<PsiElement> macros = NASMUtil.findPreprocessorMacros(project);
                for (PsiElement macro : macros) {
                    String macroText = macro.getText();
                    if (macroText != null) {
                        int identifierIdx = macroText.indexOf(macroIdentifier);
                        if (identifierIdx != -1) {
                            highlightTextRange(
                                    element.getTextRange().getStartOffset(),
                                    identifierLength,
                                    NASMSyntaxHighlighter.MACRO_CALL,
                                    holder
                            );
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
                    int identifierIdx = macro.getText().indexOf(macroIdentifier);
                    if (identifierIdx != -1) {
                        highlightTextRange(
                                element.getTextRange().getStartOffset() + identifierIdx,
                                macroIdentifier.length(),
                                NASMSyntaxHighlighter.MACRO_CALL,
                                holder
                        );
                    }
                }
            }
            else {
                NASMDefine define = nasmPreprocessor.getDefine();
                if (define != null) {
                    String defineIdentifier = define.getDefineIdentifier();
                    if (defineIdentifier != null) {
                        int identifierIdx = define.getText().indexOf(defineIdentifier);
                        if (identifierIdx != -1) {
                            highlightTextRange(
                                    element.getTextRange().getStartOffset() + identifierIdx,
                                    defineIdentifier.length(),
                                    NASMSyntaxHighlighter.MACRO_CALL,
                                    holder
                            );
                        }
                    }
                }
            }
        } else if (element instanceof NASMInstruction) {
            NASMInstruction nasmInstruction = (NASMInstruction)element;
            String instructionText = nasmInstruction.getText();
            if (instructionText != null && instructionText.length() > 0) {
                List<NASMLabel> labels = NASMUtil.findLabels(element.getProject());
                for (NASMLabel label : labels) {
                    String labelIdentifier = label.getLabelIdentifier();
                    if (labelIdentifier != null) {
                        int identifierIdx = -1;
                        for (String word : instructionText.split("\\s+")) {
                            if (word.equals(labelIdentifier)) {
                                identifierIdx = instructionText.indexOf(labelIdentifier);
                                break;
                            }
                        }
                        if (identifierIdx != -1) {
                            highlightTextRange(
                                    element.getTextRange().getStartOffset() + identifierIdx,
                                    labelIdentifier.length(),
                                    NASMSyntaxHighlighter.LABEL,
                                    holder
                            );
                        }
                    }
                }
            }
        }
    }

    private void highlightTextRange(int startOffset, int length, @NotNull TextAttributesKey textAttributes, @NotNull AnnotationHolder holder) {
        TextRange range = new TextRange(startOffset, startOffset + length);
        Annotation annotation = holder.createInfoAnnotation(range, null);
        annotation.setTextAttributes(textAttributes);
    }

}
