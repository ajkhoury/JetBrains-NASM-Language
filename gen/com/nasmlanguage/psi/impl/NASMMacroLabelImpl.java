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

public class NASMMacroLabelImpl extends ASTWrapperPsiElement implements NASMMacroLabel {

  public NASMMacroLabelImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull NASMVisitor visitor) {
    visitor.visitMacroLabel(this);
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
  public NASMInstruction getInstruction() {
    return findChildByClass(NASMInstruction.class);
  }

  @Override
  @NotNull
  public PsiElement getMacroParamLblDef() {
    return findNotNullChildByType(MACRO_PARAM_LBL_DEF);
  }

}
