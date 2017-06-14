// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMInstruction extends PsiElement {

  @NotNull
  List<NASMAddress> getAddressList();

  @Nullable
  NASMDirective getDirective();

  @NotNull
  List<NASMMacroCall> getMacroCallList();

  @Nullable
  PsiElement getComment();

  @Nullable
  PsiElement getFpuOp();

  @Nullable
  PsiElement getGeneralOp();

  @Nullable
  PsiElement getLabelDef();

  @Nullable
  PsiElement getMmxOp();

  @Nullable
  PsiElement getOpPrefix();

  @Nullable
  PsiElement getX64Op();

}
