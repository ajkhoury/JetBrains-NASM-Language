/*++

NASM Assembly Language Plugin
Copyright (c) 2017-2018 Aidan Khoury. All rights reserved.

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

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
            NASMIdentifier nasmIdentifier = (NASMIdentifier)element;
            PsiElement parentElement = nasmIdentifier.getParent();
            if (parentElement != null) {
                if (parentElement instanceof NASMIStruc ||
                    parentElement instanceof NASMStruc) {
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
                } else if (parentElement instanceof NASMConstant) {
                    highlightTextRange(nasmIdentifier.getTextRange(), NASMSyntaxHighlighter.NASM_CONSTANT, holder);
                    List<NASMIdentifier> identifierRefs = NASMUtil.findIdentifierReferences(parentElement.getContainingFile(), nasmIdentifier);
                    for (NASMIdentifier identifierRef : identifierRefs)
                        highlightTextRange(identifierRef.getTextRange(), NASMSyntaxHighlighter.NASM_CONSTANT, holder);
                } else if (parentElement instanceof NASMMacro ||
                           parentElement instanceof NASMMacroCall ||
                           parentElement instanceof NASMAssign ||
                           parentElement instanceof NASMStrlen) {
                    ASTNode identifierNode = parentElement.getNode().findChildByType(NASMTypes.IDENTIFIER);
                    if (identifierNode != null) {
                        highlightTextRange(identifierNode.getTextRange(), NASMSyntaxHighlighter.NASM_MACRO, holder);
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
        } else if (element instanceof NASMDefine) {
            NASMDefine nasmDefine = (NASMDefine)element;
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
            NASMLabel nasmLabel = (NASMLabel)element;
            NASMLabelDefMacro nasmLabelDefMacro = nasmLabel.getLabelDefMacro();
            if (nasmLabelDefMacro != null) {
                List<NASMNumericExpr> nasmNumericExprList = nasmLabelDefMacro.getMacroCall().getNumericExprList();
                if (nasmNumericExprList.size() == 1) {
                    NASMNumericExpr nasmLabelDefMacroExpr = nasmNumericExprList.get(0);
                    highlightTextRange(nasmLabelDefMacroExpr.getTextRange(), NASMSyntaxHighlighter.NASM_LABEL, holder);
                }
            }
        } else if (element instanceof NASMLabelIdentifier) {
            NASMLabelIdentifier nasmLabelIdentifier = (NASMLabelIdentifier)element;
            PsiElement parentElement = nasmLabelIdentifier.getParent();
            if (!((parentElement instanceof NASMStruc) || (parentElement instanceof NASMIStruc))) {
                PsiElement labelIdElement = nasmLabelIdentifier.getId();
                if (labelIdElement != null) {
                    highlightTextRange(labelIdElement.getTextRange(), NASMSyntaxHighlighter.NASM_LABEL, holder);
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
            }
            // Label def
            else {
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
                    for (NASMConstant constant : constants) {
                        String constantIdentifier = constant.getConstantIdentifierString();
                        if (constantIdentifier != null && constantIdentifier.equals(identifierText)) {
                            found = true;
                            highlightTextRange(tr.getStartOffset(), identifierText.length(),
                                    NASMSyntaxHighlighter.NASM_CONSTANT, holder);
                            break;
                        }
                    }
                    // Search for a preprocessor define
                    if (!found) {
                        List<NASMDefine> defines = NASMUtil.findPreprocessorDefines(element.getContainingFile());
                        for (NASMDefine define : defines) {
                            String defineIdentifier = define.getDefineIdentifierString();
                            if (defineIdentifier != null && defineIdentifier.equals(identifierText)) {
                                found = true;
                                highlightTextRange(tr.getStartOffset(), identifierText.length(), NASMSyntaxHighlighter.NASM_CONSTANT, holder);
                                break;
                            }
                        }
                    }
                    // Search for a label
                    if (!found) {
                        List<NASMLabel> labels = NASMUtil.findLabels(element.getContainingFile());
                        for (NASMLabel label : labels) {
                            String labelIdentifier = label.getLabelIdentifierString();
                            if (labelIdentifier != null && labelIdentifier.equals(identifierText)) {
                                found = true;
                                highlightTextRange(tr.getStartOffset(), identifierText.length(),
                                        NASMSyntaxHighlighter.NASM_LABEL, holder);
                                break;
                            }
                        }
                    }
                    // If a match wasnt found, color it a generic identifier color
                    if (!found) {
                        highlightTextRange(tr.getStartOffset(), identifierText.length(),
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
                    String constantIdentifier = constant.getConstantIdentifierString();
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
                        String labelIdentifier = label.getLabelIdentifierString();
                        if (labelIdentifier != null && labelIdentifier.equals(addrIdentifierText)) {
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
