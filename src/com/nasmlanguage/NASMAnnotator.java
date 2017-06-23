package com.nasmlanguage;

import com.intellij.lang.ASTNode;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
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
                    List<NASMIdentifier> identifierRefs = NASMUtil.findIdentifierReferences(parentElement.getContainingFile(), nasmIdentifier);
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
                    List<NASMIdentifier> identifierRefs = NASMUtil.findIdentifierReferences(parentElement.getContainingFile(), nasmIdentifier);
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
                    List<NASMLabel> labels = NASMUtil.findLabels(parentElement.getContainingFile());
                    for (NASMLabel label : labels) {
                        String labelIdentifierText = label.getLabelIdentifierString();
                        if (labelIdentifierText != null && labelIdentifierText.equals(nasmIdentifier.getText())) {
                            TextRange tr = nasmIdentifier.getTextRange();
                            highlightTextRange(tr.getStartOffset(), tr.getLength(), NASMSyntaxHighlighter.NASM_LABEL, holder);
                        }
                    }
                }

            }
        } else if (element instanceof NASMLabelIdentifier) {
            NASMLabelIdentifier nasmLabelIdentifier = (NASMLabelIdentifier)element;
            PsiElement parentElement = nasmLabelIdentifier.getParent();
            if (!((parentElement instanceof NASMStruc) || (parentElement instanceof NASMIStruc))) {
                PsiElement labelIdElement = nasmLabelIdentifier.getId();
                if (labelIdElement != null) {
                    TextRange tr = labelIdElement.getTextRange();
                    highlightTextRange(tr.getStartOffset(), tr.getLength(), NASMSyntaxHighlighter.NASM_LABEL, holder);
                }
            }
        } else if (element instanceof NASMStructureField) {
            PsiElement structField = ((NASMStructureField)element).getStructField();
            String structFieldText = structField.getText();
            int separatorIdx = structFieldText.indexOf('.');
            if (separatorIdx != -1) {
                String fieldText = structFieldText.substring(separatorIdx + 1);
                TextRange tr = structField.getTextRange();
                highlightTextRange(tr.getStartOffset() + separatorIdx, 1, NASMSyntaxHighlighter.NASM_SEPARATOR, holder);
                highlightTextRange(tr.getStartOffset() + separatorIdx + 1, fieldText.length(), NASMSyntaxHighlighter.NASM_LABEL, holder);
            }
        } else if (element instanceof NASMSegmentAddress) {
            NASMSegmentAddress nasmSegmentAddress = (NASMSegmentAddress)element;
            //System.out.println("nasmSegmentAddress=" + nasmSegmentAddress.getText());
            // Handle segment (left side) value
            PsiElement segmentElement = nasmSegmentAddress.getSegmentAddrL();
            if (segmentElement != null) { // Its a number on the left
                String segAddrText = segmentElement.getText();
                int separatorIdx = segAddrText.indexOf(':');
                TextRange tr = segmentElement.getTextRange();
                highlightTextRange(tr.getStartOffset() + separatorIdx, 1, NASMSyntaxHighlighter.NASM_SEPARATOR, holder);
            } else {
                segmentElement = nasmSegmentAddress.getLblDef();
                if (segmentElement != null) { // Its an identifer on the left
                    String lblDefText = segmentElement.getText();
                    int separatorIdx = lblDefText.indexOf(':');
                    TextRange tr = segmentElement.getTextRange();
                    highlightTextRange(tr.getStartOffset() + separatorIdx, 1, NASMSyntaxHighlighter.NASM_SEPARATOR, holder);
                    String identifierText = lblDefText.substring(0, separatorIdx).trim();
                    boolean found = false;
                    // Search for a constant
                    List<NASMConstant> constants = NASMUtil.findConstants(element.getContainingFile());
                    if (!constants.isEmpty()) {
                        for (NASMConstant constant : constants) {
                            String constantIdentifier = constant.getConstantIdentifierString();
                            if (constantIdentifier.equals(identifierText)) {
                                found = true;
                                highlightTextRange(tr.getStartOffset(), identifierText.length(),
                                        NASMSyntaxHighlighter.NASM_CONSTANT, holder);
                                break;
                            }
                        }
                    }
                    // Search for a label
                    if (!found) {
                        List<NASMLabel> labels = NASMUtil.findLabels(element.getContainingFile());
                        if (!labels.isEmpty()) {
                            for (NASMLabel label : labels) {
                                String labelIdentifier = label.getLabelIdentifierString();
                                if (labelIdentifier.equals(identifierText)) {
                                    found = true;
                                    highlightTextRange(tr.getStartOffset(), identifierText.length(),
                                            NASMSyntaxHighlighter.NASM_LABEL, holder);
                                    break;
                                }
                            }
                        }
                    }
                    // If a match wasnt found, color it a generic identifier color
                    if (!found) {
                        highlightTextRange(tr.getStartOffset(), identifierText.length(),
                                NASMSyntaxHighlighter.NASM_IDENTIFIER, holder);
                    }
                }
            }
            // Handle address (right side) value
            PsiElement addrIdentifier = nasmSegmentAddress.getId();
            if (addrIdentifier != null) { // if it is not null that means the address value is an identifier
                String addrIdentifierText = addrIdentifier.getText();
                TextRange tr = addrIdentifier.getTextRange();
                boolean found = false;
                // Search for a constant
                List<NASMConstant> constants = NASMUtil.findConstants(element.getContainingFile());
                if (!constants.isEmpty()) {
                    for (NASMConstant constant : constants) {
                        String constantIdentifier = constant.getConstantIdentifierString();
                        if (constantIdentifier.equals(addrIdentifierText)) {
                            found = true;
                            highlightTextRange(tr.getStartOffset(), addrIdentifierText.length(),
                                    NASMSyntaxHighlighter.NASM_CONSTANT, holder);
                            break;
                        }
                    }
                }
                // Search for a label
                if (!found) {
                    List<NASMLabel> labels = NASMUtil.findLabels(element.getContainingFile());
                    if (!labels.isEmpty()) {
                        for (NASMLabel label : labels) {
                            String labelIdentifier = label.getLabelIdentifierString();
                            if (labelIdentifier.equals(addrIdentifierText)) {
                                found = true;
                                highlightTextRange(tr.getStartOffset(), addrIdentifierText.length(),
                                        NASMSyntaxHighlighter.NASM_LABEL, holder);
                                break;
                            }
                        }
                    }
                }
                // If a match wasnt found, color it a generic identifier color
                if (!found) {
                    highlightTextRange(tr.getStartOffset(), addrIdentifierText.length(),
                            NASMSyntaxHighlighter.NASM_IDENTIFIER, holder);
                }
            } // Otherwise hexadecimal values are already highlighted properly
        }
    }

    private void highlightTextRange(int startOffset, int length, @NotNull TextAttributesKey textAttributes, @NotNull AnnotationHolder holder) {
        TextRange range = new TextRange(startOffset, startOffset + length);
        Annotation annotation = holder.createInfoAnnotation(range, null);
        annotation.setTextAttributes(textAttributes);
    }

}
