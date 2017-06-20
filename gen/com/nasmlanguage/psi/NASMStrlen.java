// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMStrlen extends PsiElement {

  @NotNull
  List<NASMIdentifier> getIdentifierList();

  @Nullable
  PsiElement getString();

  @NotNull
  PsiElement getStrlenTag();

}
