// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMLabelInstruction extends PsiElement {

  @Nullable
  NASMDirective getDirective();

  @NotNull
  List<NASMExpr> getExprList();

  @Nullable
  PsiElement getLblIns();

  String getLabelIdentifierString();

}
