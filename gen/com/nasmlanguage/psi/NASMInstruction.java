// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMInstruction extends PsiElement {

  @Nullable
  NASMDirective getDirective();

  @NotNull
  List<NASMExpr> getExprList();

  @Nullable
  PsiElement getAvx2Op();

  @Nullable
  PsiElement getAvx512Op();

  @Nullable
  PsiElement getAvxOp();

  @Nullable
  PsiElement getFpuOp();

  @Nullable
  PsiElement getGeneralOp();

  @Nullable
  PsiElement getMmxOp();

  @Nullable
  PsiElement getOpPrefix();

  @Nullable
  PsiElement getSizeType();

  @Nullable
  PsiElement getSse2Op();

  @Nullable
  PsiElement getSse3Op();

  @Nullable
  PsiElement getSse4Op();

  @Nullable
  PsiElement getSseOp();

  @Nullable
  PsiElement getSystemOp();

  @Nullable
  PsiElement getVirtualizationOp();

  @Nullable
  PsiElement getX64Op();

}
