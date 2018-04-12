package com.nasmlanguage;

import com.intellij.lang.cacheBuilder.*;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.usageView.UsageViewTypeLocation;
import com.intellij.usageView.UsageViewLongNameLocation;
import com.intellij.usageView.UsageViewNodeTextLocation;
import com.intellij.psi.*;
import com.intellij.psi.tree.TokenSet;
import com.nasmlanguage.psi.*;
import org.jetbrains.annotations.*;

public class NASMFindUsagesProvider implements FindUsagesProvider {
    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return new DefaultWordsScanner(new NASMLexerAdapter(),
                TokenSet.create(NASMTypes.IDENTIFIER),
                TokenSet.create(NASMTypes.COMMENT),
                TokenSet.create(NASMTypes.NUMERIC_LITERAL));
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        if (psiElement instanceof PsiNamedElement) {
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return null;
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        if (element instanceof NASMIdentifier) {
            return "NASM identifier";
        } else {
            return "";
        }
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        String elementString = ((NASMIdentifier)element).getName();
        return elementString;
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        String elementString = ((NASMIdentifier)element).getName();
        return elementString;
    }

}
