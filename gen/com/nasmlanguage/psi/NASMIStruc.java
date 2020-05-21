// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMIStruc extends PsiElement {

  @NotNull
  List<NASMConditional> getConditionalList();

  @NotNull
  List<NASMExpr> getExprList();

  @NotNull
  PsiElement getIendTag();

  @NotNull
  PsiElement getIstrucTag();

}
