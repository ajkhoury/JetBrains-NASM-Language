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

public class NASMMacroVarReferenceImpl extends NASMExprImpl implements NASMMacroVarReference {

  public NASMMacroVarReferenceImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull NASMVisitor visitor) {
    visitor.visitMacroVarReference(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof NASMVisitor) accept((NASMVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getMacroVarRef() {
    return findNotNullChildByType(MACRO_VAR_REF);
  }

  @Override
  @Nullable
  public PsiElement getSizeType() {
    return findChildByType(SIZE_TYPE);
  }

}
