// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMDirective extends PsiElement {

  @NotNull
  List<NASMExpr> getExprList();

  @Nullable
  NASMMapOption getMapOption();

  @Nullable
  PsiElement getDirectiveOp();

}
