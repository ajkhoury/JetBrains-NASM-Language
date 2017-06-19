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
  @Nullable
  public NASMDataValue getDataValue() {
    return findChildByClass(NASMDataValue.class);
  }

  @Override
  @Nullable
  public NASMIStruc getIStruc() {
    return findChildByClass(NASMIStruc.class);
  }

  @Override
  @Nullable
  public NASMIdentifier getIdentifier() {
    return findChildByClass(NASMIdentifier.class);
  }

  @Override
  @Nullable
  public NASMNumericExpr getNumericExpr() {
    return findChildByClass(NASMNumericExpr.class);
  }

  @Override
  @Nullable
  public NASMStruc getStruc() {
    return findChildByClass(NASMStruc.class);
  }

  @Override
  @Nullable
  public PsiElement getDataOp() {
    return findChildByType(DATA_OP);
  }

  @Override
  @Nullable
  public PsiElement getEqu() {
    return findChildByType(EQU);
  }

  @Override
  @Nullable
  public PsiElement getId() {
    return findChildByType(ID);
  }

  @Override
  @Nullable
  public PsiElement getLblDef() {
    return findChildByType(LBL_DEF);
  }

}
