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

public class NASMLabelInstructionImpl extends ASTWrapperPsiElement implements NASMLabelInstruction {

  public NASMLabelInstructionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull NASMVisitor visitor) {
    visitor.visitLabelInstruction(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof NASMVisitor) accept((NASMVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public NASMDirective getDirective() {
    return findChildByClass(NASMDirective.class);
  }

  @Override
  @NotNull
  public List<NASMExpr> getExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, NASMExpr.class);
  }

  @Override
  @Nullable
  public PsiElement getLblIns() {
    return findChildByType(LBL_INS);
  }

  public String getLabelIdentifierString() {
    return NASMPsiImplUtil.getLabelIdentifierString(this);
  }

}
