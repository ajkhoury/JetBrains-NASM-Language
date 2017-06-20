// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMData extends PsiElement {

  @NotNull
  List<NASMLabelIdentifier> getLabelIdentifierList();

  @NotNull
  List<NASMNumericExpr> getNumericExprList();

  @Nullable
  PsiElement getComment();

  @NotNull
  PsiElement getDataOp();

}
