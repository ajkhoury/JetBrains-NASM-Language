// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.nasmlanguage.psi.NASMTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.nasmlanguage.psi.*;

public class NASMAssignImpl extends ASTWrapperPsiElement implements NASMAssign {

  public NASMAssignImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull NASMVisitor visitor) {
    visitor.visitAssign(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof NASMVisitor) accept((NASMVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public NASMExpr getExpr() {
    return findNotNullChildByClass(NASMExpr.class);
  }

  @Override
  @NotNull
  public PsiElement getAssignTag() {
    return findNotNullChildByType(ASSIGN_TAG);
  }

  @Override
  @NotNull
  public PsiElement getId() {
    return findNotNullChildByType(ID);
  }

}
