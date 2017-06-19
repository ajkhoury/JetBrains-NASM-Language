// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMMacro extends PsiElement {

  @NotNull
  List<NASMData> getDataList();

  @NotNull
  List<NASMExpr> getExprList();

  @NotNull
  List<NASMInstruction> getInstructionList();

  @NotNull
  PsiElement getId();

  @NotNull
  PsiElement getMacroEndTag();

  @NotNull
  PsiElement getMacroTag();

  @Nullable
  PsiElement getRegister();

  @Nullable
  PsiElement getString();

  String getMacroIdentifier();

}
