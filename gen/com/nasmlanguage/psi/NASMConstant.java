// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMConstant extends PsiElement {

  @NotNull
  List<NASMNumericExpr> getNumericExprList();

  @NotNull
  PsiElement getEqu();

  @NotNull
  PsiElement getId();

  String getConstantIdentifierString();

}
