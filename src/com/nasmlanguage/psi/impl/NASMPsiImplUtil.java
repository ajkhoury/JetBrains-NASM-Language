package com.nasmlanguage.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.*;
import com.nasmlanguage.NASMIcons;
import com.nasmlanguage.psi.*;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NASMPsiImplUtil {

    public static String getIncludeString(NASMInclude element) {
        ASTNode includeString = element.getNode().findChildByType(NASMTypes.STRING);
        if (includeString != null)
            return includeString.getText();
        return null;
    }

    public static String getDefineIdentifier(NASMDefine element) {
        ASTNode defineIdentifier = element.getNode().findChildByType(NASMTypes.IDENTIFIER);
        if (defineIdentifier != null)
            return defineIdentifier.getText();
        return null;
    }

    public static String getMacroIdentifier(NASMMacro element) {
        ASTNode macroIdentifier = element.getNode().findChildByType(NASMTypes.IDENTIFIER);
        if (macroIdentifier != null)
            return macroIdentifier.getText();
        return null;
    }

    public static String getLabelIdentifierString(NASMLabel element) {
        PsiElement labelDef = element.getLblDef();
        if (labelDef != null) {
            String labelDefString = labelDef.getText();
            return labelDefString.substring(0, labelDefString.indexOf(':')).trim();
        }
        return null;
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
