// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMData extends PsiElement {

  @Nullable
  NASMDataValue getDataValue();

  @NotNull
  List<NASMMacroCall> getMacroCallList();

  @Nullable
  PsiElement getDataOp();

  @Nullable
  PsiElement getEqu();

  @Nullable
  PsiElement getLabel();

  @Nullable
  PsiElement getLabelDef();

  @Nullable
  PsiElement getSizeType();

}
