// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.*;

public interface NASMIdentifier extends NASMExpr, NASMNamedElement {

  @Nullable
  PsiElement getId();

  @Nullable
  PsiElement getMacroParamRef();

  @Nullable
  PsiElement getMacroVarRef();

  String getName();

  PsiElement setName(String newName);

  @NotNull
  PsiElement getNameIdentifier();

  ItemPresentation getPresentation();

  @NotNull
  PsiReference[] getReferences();

}
