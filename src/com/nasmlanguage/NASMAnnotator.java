/*++

NASM Assembly Language Plugin
Copyright (c) 2017-2020 Aidan Khoury. All rights reserved.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

--*/

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
            NASMIdentifier nasmIdentifier = (NASMIdentifier) element;
            PsiElement parentElement = nasmIdentifier.getParent();
            if (parentElement != null) {
                if (parentElement instanceof NASMIStruc || parentElement instanceof NASMStruc) {
                    highlightTextRange(nasmIdentifier.getTextRange(), NASMSyntaxHighlighter.NASM_STRUCTURE, holder);
                    NASMLabelIdentifier[] labelIdentifiers = PsiTreeUtil.getChildrenOfType(parentElement, NASMLabelIdentifier.class);
                    if (labelIdentifiers != null) {
                        String identifierText = nasmIdentifier.getText();
                        if (identifierText != null) {
                            for (NASMLabelIdentifier labelIdentifier : labelIdentifiers) {
                                if (labelIdentifier.getText().contains(identifierText)) {
                                    TextRange tr = labelIdentifier.getTextRange();
                                    int len = identifierText.length();
                                    highlightTextRange(tr, NASMSyntaxHighlighter.NASM_STRUCTURE, holder);
                                    highlightTextRange(tr.getStartOffset() + len, 1, NASMSyntaxHighlighter.NASM_SEPARATOR, holder);
                                }
                            }
                        }
                    }
                    List<NASMIdentifier> identifierRefs = NASMUtil.findIdentifierReferences(parentElement.getContainingFile(), nasmIdentifier);
                    for (NASMIdentifier identifierRef : identifierRefs) {
                        //NASMInstruction parentInstruction = PsiTreeUtil.getParentOfType(identifierRef, NASMInstruction.class);
                        //if (parentInstruction != null)
                        highlightTextRange(identifierRef.getTextRange(), NASMSyntaxHighlighter.NASM_STRUCTURE, holder);
                    }
                } else if (parentElement instanceof NASMMacro ||
                        parentElement instanceof NASMMacroCall ||
                        parentElement instanceof NASMAssign ||
                        parentElement instanceof NASMStrlen) {
                    ASTNode identifierNode = parentElement.getNode().findChildByType(NASMTypes.IDENTIFIER);
                    if (identifierNode != null) {
                        highlightTextRange(identifierNode.getTextRange(), NASMSyntaxHighlighter.NASM_MACRO, holder);
                    }
                    //List<NASMIdentifier> identifierRefs = NASMUtil.findIdentifierReferences(parentElement.getContainingFile(), nasmIdentifier);
                    //for (NASMIdentifier identifierRef : identifierRefs) {
                    //    highlightTextRange(identifierRef.getTextRange(), NASMSyntaxHighlighter.NASM_MACRO, holder);
                    //}
                } else if (parentElement instanceof NASMInstruction) {
                    List<NASMLabel> labels = NASMUtil.findLabels(parentElement.getContainingFile());
                    for (NASMLabel label : labels) {
                        String labelIdentifierText = label.getName();
                        if (labelIdentifierText != null && labelIdentifierText.equals(nasmIdentifier.getText())) {
                            TextRange tr = nasmIdentifier.getTextRange();
                            highlightTextRange(tr.getStartOffset(), tr.getLength(), NASMSyntaxHighlighter.NASM_LABEL, holder);
                        }
                    }
                }
            }
        } else if (element instanceof NASMConstant) {
            NASMConstant nasmConstant = (NASMConstant) element;
            PsiElement idElement = nasmConstant.getId();
            highlightTextRange(idElement.getTextRange(), NASMSyntaxHighlighter.NASM_CONSTANT, holder);
            List<NASMIdentifier> identifierRefs = NASMUtil.findIdentifierReferencesById(element.getContainingFile(), idElement.getText());
            for (NASMIdentifier identifierRef : identifierRefs) {
                highlightTextRange(identifierRef.getTextRange(), NASMSyntaxHighlighter.NASM_CONSTANT, holder);
            }
        } else if (element instanceof NASMDefine) {
            NASMDefine nasmDefine = (NASMDefine) element;
            NASMIdentifier defineIdentifier = nasmDefine.getDefineIdentifier();
            highlightTextRange(defineIdentifier.getTextRange(), NASMSyntaxHighlighter.NASM_MACRO, holder);
            List<NASMIdentifier> identifierRefs = NASMUtil.findIdentifierReferences(element.getContainingFile(), defineIdentifier);
            for (NASMIdentifier identifierRef : identifierRefs) {
                PsiElement identifierParent = identifierRef.getParent();
                if (identifierParent != null &&
                        !(identifierParent instanceof NASMDefine) && !(identifierParent instanceof NASMMacroCall)) {
                    highlightTextRange(identifierRef.getTextRange(), NASMSyntaxHighlighter.NASM_MACRO, holder);
                }
            }
        } else if (element instanceof NASMLabel) {
            NASMLabel nasmLabel = (NASMLabel) element;
            NASMLabelDefMacro nasmLabelDefMacro = nasmLabel.getLabelDefMacro();
            if (nasmLabelDefMacro != null) {
                PsiElement labelDefId = nasmLabelDefMacro.getId();
                highlightTextRange(labelDefId.getTextRange(), NASMSyntaxHighlighter.NASM_MACRO, holder);
                List<NASMExpr> nasmExprList = nasmLabelDefMacro.getExprList();
                if (nasmExprList.size() == 1) {
                    NASMExpr nasmLabelDefMacroExpr = nasmExprList.get(0);
                    highlightTextRange(nasmLabelDefMacroExpr.getTextRange(), NASMSyntaxHighlighter.NASM_LABEL, holder);
                }
            }
        } else if (element instanceof NASMLabelIdentifier) {
            NASMLabelIdentifier nasmLabelIdentifier = (NASMLabelIdentifier) element;
            PsiElement parentElement = nasmLabelIdentifier.getParent();
            if (!((parentElement instanceof NASMStruc) || (parentElement instanceof NASMIStruc))) {
                PsiElement labelIdElement = nasmLabelIdentifier.getId();
                if (labelIdElement != null) {
                    highlightTextRange(labelIdElement.getTextRange(), NASMSyntaxHighlighter.NASM_LABEL, holder);
                }
            }
        } else if (element instanceof NASMStructureField) {
            PsiElement structField = ((NASMStructureField) element).getIdExtension();
            String structFieldText = structField.getText();
            int separatorIdx = structFieldText.indexOf('.');
            if (separatorIdx != -1) {
                String fieldText = structFieldText.substring(separatorIdx + 1);
                TextRange tr = structField.getTextRange();
                highlightTextRange(tr.getStartOffset(), separatorIdx, NASMSyntaxHighlighter.NASM_STRUCTURE_FIELD, holder);
                highlightTextRange(tr.getStartOffset() + separatorIdx, 1, NASMSyntaxHighlighter.NASM_SEPARATOR, holder);
                highlightTextRange(tr.getStartOffset() + separatorIdx + 1, fieldText.length(), NASMSyntaxHighlighter.NASM_LABEL, holder);
            }
        } else if (element instanceof NASMSegmentAddress) {
            NASMSegmentAddress nasmSegmentAddress = (NASMSegmentAddress) element;
            //System.out.println("nasmSegmentAddress=" + nasmSegmentAddress.getText());
            // Handle segment (left side) value
            PsiElement segmentElement = nasmSegmentAddress.getSegmentAddrL();
            if (segmentElement != null) { // Its a number on the left
                String segAddrText = segmentElement.getText();
                int separatorIdx = segAddrText.indexOf(':');
                TextRange tr = segmentElement.getTextRange();
                highlightTextRange(tr.getStartOffset() + separatorIdx, 1, NASMSyntaxHighlighter.NASM_SEPARATOR, holder);
            }
            // Label def
            else {
                segmentElement = nasmSegmentAddress.getLblDef();
                if (segmentElement != null) { // Its an identifer on the left
                    String lblDefText = segmentElement.getText();
                    int separatorIdx = lblDefText.indexOf(':');
                    TextRange tr = segmentElement.getTextRange();
                    highlightTextRange(tr.getStartOffset() + separatorIdx, 1, NASMSyntaxHighlighter.NASM_SEPARATOR, holder);
                    String lblDefIdentifierText = lblDefText.substring(0, separatorIdx).trim();
                    boolean found = false;
                    // Search for a constant
                    List<NASMConstant> constants = NASMUtil.findConstants(element.getContainingFile());
                    for (NASMConstant constant : constants) {
                        String constantIdentifier = constant.getId().getText();
                        if (constantIdentifier != null && constantIdentifier.equals(lblDefIdentifierText)) {
                            found = true;
                            highlightTextRange(tr.getStartOffset(), lblDefIdentifierText.length(),
                                    NASMSyntaxHighlighter.NASM_CONSTANT, holder);
                            break;
                        }
                    }
                    // Search for a preprocessor define
                    if (!found) {
                        List<NASMDefine> defines = NASMUtil.findPreprocessorDefines(element.getContainingFile());
                        for (NASMDefine define : defines) {
                            String defineIdentifier = define.getDefineIdentifierString();
                            if (defineIdentifier != null && defineIdentifier.equals(lblDefIdentifierText)) {
                                found = true;
                                highlightTextRange(tr.getStartOffset(), lblDefIdentifierText.length(), NASMSyntaxHighlighter.NASM_CONSTANT, holder);
                                break;
                            }
                        }
                    }
                    // Search for a label
                    if (!found) {
                        List<NASMLabel> labels = NASMUtil.findLabels(element.getContainingFile());
                        for (NASMLabel label : labels) {
                            String labelName = label.getName();
                            if (labelName != null && labelName.equals(lblDefIdentifierText)) {
                                found = true;
                                highlightTextRange(tr.getStartOffset(), lblDefIdentifierText.length(),
                                        NASMSyntaxHighlighter.NASM_LABEL, holder);
                                break;
                            }
                        }
                    }
                    // If a match wasnt found, color it a generic identifier color
                    if (!found) {
                        highlightTextRange(tr.getStartOffset(), lblDefIdentifierText.length(),
                                NASMSyntaxHighlighter.NASM_IDENTIFIER, holder);
                    }
                }
                // Label def macro
                else {
                    segmentElement = nasmSegmentAddress.getLabelDefMacro();
                    if (segmentElement != null) { // Its an macro on the left
                        TextRange tr = segmentElement.getTextRange();
                        highlightTextRange(tr.getStartOffset() + tr.getLength() - 1, 1,
                                NASMSyntaxHighlighter.NASM_SEPARATOR, holder);
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
                for (NASMConstant constant : constants) {
                    String constantIdentifier = constant.getId().getText();
                    if (constantIdentifier.equals(addrIdentifierText)) {
                        found = true;
                        highlightTextRange(tr, NASMSyntaxHighlighter.NASM_CONSTANT, holder);
                        break;
                    }
                }
                // Search for a preprocessor define
                if (!found) {
                    List<NASMDefine> defines = NASMUtil.findPreprocessorDefines(element.getContainingFile());
                    for (NASMDefine define : defines) {
                        String defineIdentifier = define.getDefineIdentifierString();
                        if (defineIdentifier != null && defineIdentifier.equals(addrIdentifierText)) {
                            found = true;
                            highlightTextRange(tr, NASMSyntaxHighlighter.NASM_CONSTANT, holder);
                            break;
                        }
                    }
                }
                // Search for a label
                if (!found) {
                    List<NASMLabel> labels = NASMUtil.findLabels(element.getContainingFile());
                    for (NASMLabel label : labels) {
                        String labelName = label.getName();
                        if (labelName != null && labelName.equals(addrIdentifierText)) {
                            found = true;
                            highlightTextRange(tr, NASMSyntaxHighlighter.NASM_LABEL, holder);
                            break;
                        }
                    }
                }
                // If a match wasnt found, color it a generic identifier color
                if (!found) {
                    highlightTextRange(tr, NASMSyntaxHighlighter.NASM_IDENTIFIER, holder);
                }
            } // Otherwise hexadecimal values are already highlighted properly
        }
    }

    private void highlightTextRange(int startOffset, int length, @NotNull TextAttributesKey textAttributes, @NotNull AnnotationHolder holder) {
        TextRange range = new TextRange(startOffset, startOffset + length);
        Annotation annotation = holder.createInfoAnnotation(range, null);
        annotation.setTextAttributes(textAttributes);
    }

    private void highlightTextRange(TextRange range, @NotNull TextAttributesKey textAttributes, @NotNull AnnotationHolder holder) {
        Annotation annotation = holder.createInfoAnnotation(range, null);
        annotation.setTextAttributes(textAttributes);
    }

}
