// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.nasmlanguage.psi.NASMTypes.*;
import com.nasmlanguage.psi.*;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;

public class NASMIdentifierImpl extends NASMNamedElementImpl implements NASMIdentifier {

  public NASMIdentifierImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull NASMVisitor visitor) {
    visitor.visitIdentifier(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof NASMVisitor) accept((NASMVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getId() {
    return findNotNullChildByType(ID);
  }

  public String getName() {
    return NASMPsiImplUtil.getName(this);
  }

  public PsiElement setName(String newName) {
    return NASMPsiImplUtil.setName(this, newName);
  }

  public PsiElement getNameIdentifier() {
    return NASMPsiImplUtil.getNameIdentifier(this);
  }

  @NotNull
  public PsiReference[] getReferences() {
    return NASMPsiImplUtil.getReferences(this);
  }

  public ItemPresentation getPresentation() {
    return NASMPsiImplUtil.getPresentation(this);
  }

}
