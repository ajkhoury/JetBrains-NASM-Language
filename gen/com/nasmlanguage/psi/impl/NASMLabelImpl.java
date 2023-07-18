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

public class NASMLabelImpl extends NASMNamedElementImpl implements NASMLabel {

  public NASMLabelImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull NASMVisitor visitor) {
    visitor.visitLabel(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof NASMVisitor) accept((NASMVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public NASMDataElement getDataElement() {
    return findChildByClass(NASMDataElement.class);
  }

  @Override
  @Nullable
  public NASMExpr getExpr() {
    return findChildByClass(NASMExpr.class);
  }

  @Override
  @Nullable
  public NASMInstruction getInstruction() {
    return findChildByClass(NASMInstruction.class);
  }

  @Override
  @Nullable
  public NASMLabelDefMacro getLabelDefMacro() {
    return findChildByClass(NASMLabelDefMacro.class);
  }

  @Override
  @Nullable
  public NASMStructure getStructure() {
    return findChildByClass(NASMStructure.class);
  }

  @Override
  @Nullable
  public PsiElement getLbl() {
    return findChildByType(LBL);
  }

  @Override
  @Nullable
  public PsiElement getLblDef() {
    return findChildByType(LBL_DEF);
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
  public PsiElement getNameIdentifier() {
    return NASMPsiImplUtil.getNameIdentifier(this);
  }

}
