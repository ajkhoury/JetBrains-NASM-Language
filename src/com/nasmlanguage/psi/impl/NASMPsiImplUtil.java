/*++

NASM Assembly Language Plugin
Copyright (c) 2017-2018 Aidan Khoury

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

package com.nasmlanguage.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import com.nasmlanguage.NASMIcons;
import com.nasmlanguage.NASMReference;
import com.nasmlanguage.psi.*;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.swing.*;

public class NASMPsiImplUtil {

    public static String getIncludeString(NASMInclude element) {
        ASTNode includeString = element.getNode().findChildByType(NASMTypes.STRING);
        if (includeString != null) {
            return includeString.getText()
                    .replace("\"", "")
                    .replace("'", "");
        }
        return null;
    }

    public static String getDefineIdentifierString(NASMDefine element) {
        ASTNode defineIdentifier = element.getNode().findChildByType(NASMTypes.IDENTIFIER);
        if (defineIdentifier != null)
            return defineIdentifier.getText();
        return null;
    }

    public static NASMIdentifier getDefineIdentifier(NASMDefine element) {
        ASTNode defineIdentifier = element.getNode().findChildByType(NASMTypes.IDENTIFIER);
        if (defineIdentifier != null)
            return (NASMIdentifier)defineIdentifier.getPsi();
        return null;
    }

    public static String getMacroIdentifier(NASMMacro element) {
        ASTNode macroIdentifier = element.getNode().findChildByType(NASMTypes.IDENTIFIER);
        if (macroIdentifier != null)
            return macroIdentifier.getText();
        return null;
    }

    @SuppressWarnings("ConstantConditions")
    public static String getLabelIdentifierString(NASMLabel element) {
        PsiElement labelDef = element.getLblDef();
        if (labelDef != null) {
            String labelDefString = labelDef.getText();
            return labelDefString.substring(0, labelDefString.indexOf(':')).trim();
        }
        return null;
    }

    @SuppressWarnings("ConstantConditions")
    public static String getConstantIdentifierString(NASMConstant element) {
        PsiElement identifier = element.getIdentifier().getId();
        if (identifier != null)
            return identifier.getText();
        return null;
    }

    public static String getName(NASMIdentifier element) {
        return element.getId().getText();
    }

    public static PsiElement setName(NASMIdentifier element, String newName) {
        ASTNode keyNode = element.getId().getNode();
        if (keyNode != null) {
            NASMIdentifier property = NASMElementFactory.createIdentifier(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(NASMIdentifier element) {
        return element.getId();
    }

    @NotNull
    public static PsiReference[] getReferences(@NotNull NASMIdentifier element) {
        return ReferenceProvidersRegistry.getReferencesFromProviders(element);
    }

    //@NotNull
    //public static PsiReference getReference(@NotNull NASMIdentifier element) {
    //    return new NASMReference( element.getId(), element.getId().getTextRange());
    //}

    public static ItemPresentation getPresentation(final NASMIdentifier element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getName();
            }

            @Nullable
            @Override
            public String getLocationString() {
                return element.getContainingFile().getName();
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return NASMIcons.ASM_FILE;
            }
        };
    }

    //public static String getLabelIdentifierString(NASMLabelInstruction element) {
    //    PsiElement labelIns = element.getLblIns();
    //    if (labelIns != null) {
    //        String labelInsString = labelIns.getText();
    //        return labelInsString.substring(0, labelInsString.indexOf(':')).trim();
    //    }
    //    return null;
    //}
    //public static String getLabelIdentifierString(NASMLabelData element) {
    //    PsiElement lblData = element.getLblData();
    //    if (lblData != null) {
    //        String lblDataString = lblData.getText();
    //        return lblDataString.substring(0, lblDataString.indexOf(':')).trim();
    //    }
    //    return null;
    //}


}
