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

package com.nasmlanguage.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import com.nasmlanguage.NASMIcons;
import com.nasmlanguage.psi.*;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.swing.*;

public class NASMPsiImplUtil {

    public static String getIncludeString(NASMInclude element) {
        ASTNode includeId = element.getNode().findChildByType(NASMTypes.STRING);
        if (includeId != null) {
            return includeId.getText()
                    .replace("\"", "")
                    .replace("'", "");
        } else {
            includeId = element.getNode().findChildByType(NASMTypes.IDENTIFIER);
            if (includeId != null) {
                return includeId.getText();
            }
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

    public static String getMacroIdentifierString(NASMMacro element) {
        ASTNode macroIdentifier = element.getNode().findChildByType(NASMTypes.IDENTIFIER);
        if (macroIdentifier != null)
            return macroIdentifier.getText();
        return null;
    }

    public static PsiElement setName(NASMIdentifier identifier, String newName) {
        ASTNode idNode = getNameIdentifier(identifier).getNode();
        if (idNode != null) {
            PsiElement newId = NASMElementFactory.createIdentifier(identifier.getProject(), newName);
            identifier.getNode().replaceChild(idNode, newId.getNode());
        }
        return identifier;
    }

    @NotNull
    public static PsiElement getNameIdentifier(NASMIdentifier identifier) {
        PsiElement element = identifier.getId();
        if (element == null) {
            element = identifier.getMacroParamRef();
            if (element == null) {
                element = identifier.getMacroVarRef();
            }
        }
        assert element != null;
        return element;
    }

    public static String getName(NASMIdentifier identifier) {
        return getNameIdentifier(identifier).getText();
    }

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

    @NotNull
    public static PsiReference[] getReferences(@NotNull NASMIdentifier element) {
        return ReferenceProvidersRegistry.getReferencesFromProviders(element);
    }

    public static String getName(NASMLabelIdentifier element) {
        PsiElement id = element.getId();
        if (id != null) {
            return id.getText();
        }
        PsiElement lbl = element.getLbl();
        if (lbl != null) {
            return lbl.getText();
        }
        return null;
    }

    public static PsiElement setName(NASMLabelIdentifier element, String newName) {
        ASTNode idNode = element.getNode().findChildByType(NASMTypes.ID);
        if (idNode != null) {
            NASMLabelIdentifier newLabelIdentifier = NASMElementFactory.createLabelIdentifierId(element.getProject(), newName);
            ASTNode newIdNode = newLabelIdentifier.getNode().findChildByType(NASMTypes.ID);
            assert newIdNode != null;
            element.getNode().replaceChild(idNode, newIdNode);
        }
        ASTNode lblNode = element.getNode().findChildByType(NASMTypes.LBL);
        if (lblNode != null) {
            NASMLabelIdentifier newLabelIdentifier = NASMElementFactory.createLabelIdentifierLbl(element.getProject(), newName);
            ASTNode newLblNode = newLabelIdentifier.getNode().findChildByType(NASMTypes.LBL);
            assert newLblNode != null;
            element.getNode().replaceChild(lblNode, newLblNode);
        }
        return element;
    }

    public static ItemPresentation getPresentation(final NASMLabelIdentifier element) {
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

    @NotNull
    public static PsiReference[] getReferences(@NotNull NASMLabelIdentifier element) {
        return ReferenceProvidersRegistry.getReferencesFromProviders(element);
    }

    public static PsiElement getNameIdentifier(NASMLabelIdentifier element) {
        ASTNode idNode = element.getNode().findChildByType(NASMTypes.ID);
        if (idNode != null) {
            return idNode.getPsi();
        }
        ASTNode lblNode = element.getNode().findChildByType(NASMTypes.LBL);
        if (lblNode != null) {
            return lblNode.getPsi();
        }
        return null;
    }

    public static String getName(NASMLabel element) {
        PsiElement lblDef = element.getLblDef();
        if (lblDef != null) {
            String lblDefText = lblDef.getText();
            return lblDefText.substring(0, lblDefText.indexOf(':')).trim();
        }
        return null;
    }

    public static PsiElement setName(NASMLabel element, String newName) {
        ASTNode lblDefNode = element.getNode().findChildByType(NASMTypes.LBL_DEF);
        if (lblDefNode != null) {
            NASMLabel newLabel = NASMElementFactory.createLabel(element.getProject(), newName);
            ASTNode newLblDefNode = newLabel.getNode().findChildByType(NASMTypes.LBL_DEF);
            assert newLblDefNode != null;
            element.getNode().replaceChild(lblDefNode, newLblDefNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(NASMLabel element) {
        ASTNode keyNode = element.getNode().findChildByType(NASMTypes.LBL_DEF);
        if (keyNode != null) {
            return keyNode.getPsi();
        }
        return null;
    }
}
