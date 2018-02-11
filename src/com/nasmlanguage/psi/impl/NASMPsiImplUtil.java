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

package com.nasmlanguage.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.nasmlanguage.psi.*;

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
