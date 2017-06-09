// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMDataSection extends PsiElement {

  @NotNull
  List<NASMData> getDataList();

  @NotNull
  List<NASMDirective> getDirectiveList();

  @NotNull
  PsiElement getDataSectionName();

  @NotNull
  PsiElement getSectionTag();

}