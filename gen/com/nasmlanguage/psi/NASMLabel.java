// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMLabel extends PsiElement {

  @Nullable
  NASMData getData();

  @Nullable
  NASMInstruction getInstruction();

  @NotNull
  PsiElement getLblDef();

  String getLabelIdentifier();

}
