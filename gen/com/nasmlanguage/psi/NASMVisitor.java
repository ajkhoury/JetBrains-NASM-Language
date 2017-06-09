// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class NASMVisitor extends PsiElementVisitor {

  public void visitAddress(@NotNull NASMAddress o) {
    visitPsiElement(o);
  }

  public void visitCodeSection(@NotNull NASMCodeSection o) {
    visitPsiElement(o);
  }

  public void visitData(@NotNull NASMData o) {
    visitPsiElement(o);
  }

  public void visitDataSection(@NotNull NASMDataSection o) {
    visitPsiElement(o);
  }

  public void visitDataValue(@NotNull NASMDataValue o) {
    visitPsiElement(o);
  }

  public void visitDirective(@NotNull NASMDirective o) {
    visitPsiElement(o);
  }

  public void visitDirectiveArg(@NotNull NASMDirectiveArg o) {
    visitPsiElement(o);
  }

  public void visitInstruction(@NotNull NASMInstruction o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
