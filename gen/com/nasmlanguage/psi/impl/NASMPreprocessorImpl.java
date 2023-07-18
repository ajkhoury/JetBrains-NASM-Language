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

public class NASMPreprocessorImpl extends ASTWrapperPsiElement implements NASMPreprocessor {

  public NASMPreprocessorImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull NASMVisitor visitor) {
    visitor.visitPreprocessor(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof NASMVisitor) accept((NASMVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public NASMAssign getAssign() {
    return findChildByClass(NASMAssign.class);
  }

  @Override
  @Nullable
  public NASMConditional getConditional() {
    return findChildByClass(NASMConditional.class);
  }

  @Override
  @Nullable
  public NASMDefine getDefine() {
    return findChildByClass(NASMDefine.class);
  }

  @Override
  @Nullable
  public NASMError getError() {
    return findChildByClass(NASMError.class);
  }

  @Override
  @Nullable
  public NASMExpr getExpr() {
    return findChildByClass(NASMExpr.class);
  }

  @Override
  @Nullable
  public NASMInclude getInclude() {
    return findChildByClass(NASMInclude.class);
  }

  @Override
  @Nullable
  public NASMMacro getMacro() {
    return findChildByClass(NASMMacro.class);
  }

  @Override
  @Nullable
  public NASMStrlen getStrlen() {
    return findChildByClass(NASMStrlen.class);
  }

  @Override
  @Nullable
  public PsiElement getPreprocessorOp() {
    return findChildByType(PREPROCESSOR_OP);
  }

}
