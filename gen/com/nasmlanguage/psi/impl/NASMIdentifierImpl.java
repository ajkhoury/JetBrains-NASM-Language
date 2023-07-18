// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi.impl;

import java.util.List;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;

import static com.nasmlanguage.psi.NASMTypes.*;
import com.nasmlanguage.psi.*;

public class NASMIdentifierImpl extends NASMNamedElementImpl implements NASMIdentifier {

  public NASMIdentifierImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull NASMVisitor visitor) {
    visitor.visitIdentifier(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof NASMVisitor) accept((NASMVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getId() {
    return findChildByType(ID);
  }

  @Override
  @Nullable
  public PsiElement getMacroParamRef() {
    return findChildByType(MACRO_PARAM_REF);
  }

  @Override
  @Nullable
  public PsiElement getMacroVarRef() {
    return findChildByType(MACRO_VAR_REF);
  }

  @Override
  public String getName() {
    return NASMPsiImplUtil.getName(this);
  }

  @Override
  public PsiElement setName(String newName) {
    return NASMPsiImplUtil.setName(this, newName);
  }

  @Override
  @NotNull
  public PsiElement getNameIdentifier() {
    return NASMPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  public ItemPresentation getPresentation() {
    return NASMPsiImplUtil.getPresentation(this);
  }

  @Override
  @NotNull
  public PsiReference[] getReferences() {
    return NASMPsiImplUtil.getReferences(this);
  }

}
