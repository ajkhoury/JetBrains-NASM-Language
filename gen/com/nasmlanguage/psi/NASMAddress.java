// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMAddress extends NASMExpr {

  @Nullable
  NASMExpr getExpr();

  @Nullable
  PsiElement getSegmentAddr();

  @Nullable
  PsiElement getSizeType();

}
