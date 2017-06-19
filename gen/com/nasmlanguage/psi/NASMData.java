// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMData extends PsiElement {

  @Nullable
  NASMDataValue getDataValue();

  @Nullable
  NASMIStruc getIStruc();

  @Nullable
  NASMIdentifier getIdentifier();

  @Nullable
  NASMNumericExpr getNumericExpr();

  @Nullable
  NASMStruc getStruc();

  @Nullable
  PsiElement getDataOp();

  @Nullable
  PsiElement getEqu();

  @Nullable
  PsiElement getId();

  @Nullable
  PsiElement getLblDef();

}
