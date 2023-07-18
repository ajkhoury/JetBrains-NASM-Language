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

public class NASMDirectiveImpl extends ASTWrapperPsiElement implements NASMDirective {

  public NASMDirectiveImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull NASMVisitor visitor) {
    visitor.visitDirective(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof NASMVisitor) accept((NASMVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<NASMExpr> getExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, NASMExpr.class);
  }

  @Override
  @Nullable
  public NASMMapOption getMapOption() {
    return findChildByClass(NASMMapOption.class);
  }

  @Override
  @Nullable
  public PsiElement getDirectiveOp() {
    return findChildByType(DIRECTIVE_OP);
  }

}
