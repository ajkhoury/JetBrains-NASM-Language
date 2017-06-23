package com.nasmlanguage;

import com.intellij.lang.ASTNode;
import com.intellij.lang.annotation.*;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtil;
import com.nasmlanguage.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NASMAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {

        if (element instanceof NASMIdentifier) {
            NASMIdentifier nasmIdentifier = (NASMIdentifier)element;
            PsiElement parentElement = nasmIdentifier.getParent();
            if (parentElement != null) {
                if ((parentElement instanceof NASMIStruc) || (parentElement instanceof NASMStruc)) {
                    TextRange tr = nasmIdentifier.getTextRange();
                    highlightTextRange(tr.getStartOffset(), tr.getLength(), NASMSyntaxHighlighter.NASM_STRUCTURE, holder);
                    NASMLabelIdentifier[] labelIdentifiers = PsiTreeUtil.getChildrenOfType(parentElement, NASMLabelIdentifier.class);
                    if (labelIdentifiers != null) {
                        String identifierText = nasmIdentifier.getText();
                        if (identifierText != null) {
                            for (NASMLabelIdentifier labelIdentifier : labelIdentifiers) {
                                if (labelIdentifier.getText().contains(identifierText)) {
                                    tr = labelIdentifier.getTextRange();
                                    int len = identifierText.length();
                                    highlightTextRange(tr.getStartOffset(), len, NASMSyntaxHighlighter.NASM_STRUCTURE, holder);
                                    highlightTextRange(tr.getStartOffset() + len, 1, NASMSyntaxHighlighter.NASM_SEPARATOR, holder);
                                }
                            }
                        }
                    }
                    List<NASMIdentifier> identifierRefs = NASMUtil.findIdentifierReferences(parentElement.getProject(), nasmIdentifier);
                    for (NASMIdentifier identifierRef : identifierRefs) {
                        NASMInstruction parentInstruction = PsiTreeUtil.getParentOfType(identifierRef, NASMInstruction.class);
                        if (parentInstruction != null) {
                            tr = identifierRef.getTextRange();
                            highlightTextRange(tr.getStartOffset(), tr.getLength(), NASMSyntaxHighlighter.NASM_STRUCTURE, holder);
                        }
                    }
                } else if (parentElement instanceof NASMConstant) {
                    TextRange tr = nasmIdentifier.getTextRange();
                    highlightTextRange(tr.getStartOffset(), tr.getLength(), NASMSyntaxHighlighter.NASM_CONSTANT, holder);
                    List<NASMIdentifier> identifierRefs = NASMUtil.findIdentifierReferences(parentElement.getProject(), nasmIdentifier);
                    for (NASMIdentifier identifierRef : identifierRefs) {
                        tr = identifierRef.getTextRange();
                        highlightTextRange(tr.getStartOffset(), tr.getLength(), NASMSyntaxHighlighter.NASM_CONSTANT, holder);
                    }
                } else if ((parentElement instanceof NASMMacro)
                        || (parentElement instanceof NASMMacroCall)
                        || (parentElement instanceof NASMDefine)
                        || (parentElement instanceof NASMAssign)
                        || (parentElement instanceof NASMStrlen)) {
                    ASTNode identifierNode = parentElement.getNode().findChildByType(NASMTypes.IDENTIFIER);
                    if (identifierNode != null) {
                        TextRange tr = identifierNode.getTextRange();
                        highlightTextRange(tr.getStartOffset(), tr.getLength(), NASMSyntaxHighlighter.NASM_MACRO, holder);
                    }
                } else if (parentElement instanceof NASMInstruction) {
                    List<NASMLabel> labels = NASMUtil.findLabels(parentElement.getProject());
                    for (NASMLabel label : labels) {
                        String labelIdentifierText = label.getLabelIdentifierString();
                        if (labelIdentifierText != null && labelIdentifierText.equals(nasmIdentifier.getText())) {
                            TextRange tr = nasmIdentifier.getTextRange();
                            highlightTextRange(tr.getStartOffset(), tr.getLength(), NASMSyntaxHighlighter.NASM_LABEL, holder);
                        }
                    }
                    //List<NASMLabelInstruction> labelInstructions = NASMUtil.findLabelInstructions(parentElement.getProject());
                    //for (NASMLabelInstruction labelIns : labelInstructions) {
                    //    String labelIdentifierText = labelIns.getLabelIdentifierString();
                    //    if (labelIdentifierText != null && labelIdentifierText.equals(nasmIdentifier.getText())) {
                    //        TextRange tr = nasmIdentifier.getTextRange();
                    //        highlightTextRange(tr.getStartOffset(), tr.getLength(), NASMSyntaxHighlighter.NASM_LABEL, holder);
                    //    }
                    //}
                    //List<NASMLabelData> labelDatas = NASMUtil.findLabelDatas(parentElement.getProject());
                    //for (NASMLabelData labelData : labelDatas) {
                    //    String labelIdentifierText = labelData.getLabelIdentifierString();
                    //    if (labelIdentifierText != null && labelIdentifierText.equals(nasmIdentifier.getText())) {
                    //        TextRange tr = nasmIdentifier.getTextRange();
                    //        highlightTextRange(tr.getStartOffset(), tr.getLength(), NASMSyntaxHighlighter.NASM_LABEL, holder);
                    //    }
                    //}
                }

            }
        }

        //if (element instanceof NASMLabelInstruction) {
        //    NASMLabelInstruction nasmLabelInstruction = (NASMLabelInstruction)element;
        //    PsiElement labelInstruction = nasmLabelInstruction.getLblIns();
        //    if (labelInstruction != null ) {
        //        String labelInstructionText = labelInstruction.getText();
        //        if (labelInstructionText != null) {
        //            int labelIdx = labelInstructionText.indexOf(':');
        //            if (labelIdx != -1) {
        //                String instruction = labelInstructionText.substring(labelIdx);
        //                TextRange tr = labelInstruction.getTextRange();
        //                highlightTextRange(tr.getStartOffset(), labelIdx + 1, NASMSyntaxHighlighter.NASM_LABEL, holder);
        //                highlightTextRange(tr.getStartOffset() + labelIdx + 1, instruction.length(), NASMSyntaxHighlighter.NASM_OPERATION, holder);
        //            }
        //        }
        //    }
        //}

        if (element instanceof NASMLabelIdentifier) {
            NASMLabelIdentifier nasmLabelIdentifier = (NASMLabelIdentifier)element;
            PsiElement parentElement = nasmLabelIdentifier.getParent();
            if (!((parentElement instanceof NASMStruc) || (parentElement instanceof NASMIStruc))) {
                PsiElement labelIdElement = nasmLabelIdentifier.getId();
                if (labelIdElement != null) {
                    TextRange tr = labelIdElement.getTextRange();
                    highlightTextRange(tr.getStartOffset(), tr.getLength(), NASMSyntaxHighlighter.NASM_LABEL, holder);
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
