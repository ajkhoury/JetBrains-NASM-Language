// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMInclude extends PsiElement {

  @Nullable
  NASMIdentifier getIdentifier();

  @NotNull
  PsiElement getIncludeTag();

  @Nullable
  PsiElement getString();

  String getIncludeString();

}
