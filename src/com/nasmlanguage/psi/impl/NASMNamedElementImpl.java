package com.nasmlanguage.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.nasmlanguage.psi.NASMNamedElement;
import org.jetbrains.annotations.NotNull;

public abstract class NASMNamedElementImpl extends ASTWrapperPsiElement implements NASMNamedElement {
    public NASMNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
