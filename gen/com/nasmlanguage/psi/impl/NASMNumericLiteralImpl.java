// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.nasmlanguage.psi.NASMTypes.*;
import com.nasmlanguage.psi.*;

public class NASMNumericLiteralImpl extends NASMExprImpl implements NASMNumericLiteral {

  public NASMNumericLiteralImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull NASMVisitor visitor) {
    visitor.visitNumericLiteral(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof NASMVisitor) accept((NASMVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getBinary() {
    return findChildByType(BINARY);
  }

  @Override
  @Nullable
  public PsiElement getCharacter() {
    return findChildByType(CHARACTER);
  }

  @Override
  @Nullable
  public PsiElement getDecimal() {
    return findChildByType(DECIMAL);
  }

  @Override
  @Nullable
  public PsiElement getHexadecimal() {
    return findChildByType(HEXADECIMAL);
  }

  @Override
  @Nullable
  public PsiElement getSizeType() {
    return findChildByType(SIZE_TYPE);
  }

  @Override
  @Nullable
  public PsiElement getZeroes() {
    return findChildByType(ZEROES);
  }

}
