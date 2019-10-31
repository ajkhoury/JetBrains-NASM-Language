// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;

public interface NASMLabelIdentifier extends NASMExpr, NASMNamedElement {

  @Nullable
  PsiElement getId();

  @Nullable
  PsiElement getLbl();

  @Nullable
  PsiElement getSizeType();

  String getName();

  PsiElement setName(String newName);

  PsiElement getNameIdentifier();

  ItemPresentation getPresentation();

  @NotNull
  PsiReference[] getReferences();

}
