// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMConditional extends PsiElement {

  @NotNull
  List<NASMAddress> getAddressList();

  @NotNull
  List<NASMData> getDataList();

  @NotNull
  List<NASMDirective> getDirectiveList();

  @NotNull
  List<NASMInstruction> getInstructionList();

  @NotNull
  List<NASMMacroCall> getMacroCallList();

  @NotNull
  List<NASMPreprocessor> getPreprocessorList();

  @NotNull
  PsiElement getEndifTag();

  @Nullable
  PsiElement getIfmacroTag();

  @Nullable
  PsiElement getIfTag();

  @Nullable
  PsiElement getString();

}
