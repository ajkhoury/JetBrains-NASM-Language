// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMStruc extends PsiElement {

  @NotNull
  List<NASMDataValue> getDataValueList();

  @NotNull
  PsiElement getEndstrucTag();

  @NotNull
  PsiElement getIdentifier();

  @NotNull
  PsiElement getStrucTag();

}
