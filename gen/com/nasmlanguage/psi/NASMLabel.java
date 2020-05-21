// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMLabel extends NASMNamedElement {

  @Nullable
  NASMDataElement getDataElement();

  @Nullable
  NASMInstruction getInstruction();

  @Nullable
  NASMLabelDefMacro getLabelDefMacro();

  @Nullable
  NASMStructure getStructure();

  @Nullable
  PsiElement getLblDef();

  String getName();

  PsiElement setName(String newName);

  PsiElement getNameIdentifier();

}
