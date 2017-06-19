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

public class NASMConditionalImpl extends ASTWrapperPsiElement implements NASMConditional {

  public NASMConditionalImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull NASMVisitor visitor) {
    visitor.visitConditional(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof NASMVisitor) accept((NASMVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<NASMData> getDataList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, NASMData.class);
  }

  @Override
  @NotNull
  public List<NASMDirective> getDirectiveList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, NASMDirective.class);
  }

  @Override
  @NotNull
  public List<NASMExpr> getExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, NASMExpr.class);
  }

  @Override
  @NotNull
  public List<NASMInstruction> getInstructionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, NASMInstruction.class);
  }

  @Override
  @NotNull
  public List<NASMPreprocessor> getPreprocessorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, NASMPreprocessor.class);
  }

  @Override
  @NotNull
  public PsiElement getEndifTag() {
    return findNotNullChildByType(ENDIF_TAG);
  }

  @Override
  @Nullable
  public PsiElement getId() {
    return findChildByType(ID);
  }

  @Override
  @Nullable
  public PsiElement getIfmacroTag() {
    return findChildByType(IFMACRO_TAG);
  }

  @Override
  @Nullable
  public PsiElement getIfTag() {
    return findChildByType(IF_TAG);
  }

  @Override
  @Nullable
  public PsiElement getRegister() {
    return findChildByType(REGISTER);
  }

  @Override
  @Nullable
  public PsiElement getString() {
    return findChildByType(STRING);
  }

}
