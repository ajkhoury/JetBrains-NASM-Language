package com.nasmlanguage;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class NASMRefactoringSupportProvider extends RefactoringSupportProvider {
    @Override
    public boolean isAvailable(@NotNull PsiElement context) {
        // TODO: Add proper refactoring support.
        return false;
    }

    @Override
    public boolean isInplaceRenameAvailable(@NotNull PsiElement element, PsiElement context) {
        // TODO: Add proper refactoring support.
        return false;
    }

    @Override
    public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement element, PsiElement context) {
        // TODO: Add proper refactoring support.
        return false;
    }
}
