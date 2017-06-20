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

public class NASMDataImpl extends ASTWrapperPsiElement implements NASMData {

  public NASMDataImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull NASMVisitor visitor) {
    visitor.visitData(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof NASMVisitor) accept((NASMVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<NASMLabelIdentifier> getLabelIdentifierList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, NASMLabelIdentifier.class);
  }

  @Override
  @NotNull
  public List<NASMNumericExpr> getNumericExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, NASMNumericExpr.class);
  }

  @Override
  @Nullable
  public PsiElement getComment() {
    return findChildByType(COMMENT);
  }

  @Override
  @NotNull
  public PsiElement getDataOp() {
    return findNotNullChildByType(DATA_OP);
  }

}
