// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class NASMVisitor extends PsiElementVisitor {

  public void visitAddress(@NotNull NASMAddress o) {
    visitExpr(o);
  }

  public void visitAssign(@NotNull NASMAssign o) {
    visitPsiElement(o);
  }

  public void visitBitShiftLExpr(@NotNull NASMBitShiftLExpr o) {
    visitExpr(o);
  }

  public void visitBitShiftRExpr(@NotNull NASMBitShiftRExpr o) {
    visitExpr(o);
  }

  public void visitBitwiseANDExpr(@NotNull NASMBitwiseANDExpr o) {
    visitExpr(o);
  }

  public void visitBitwiseNOTExpr(@NotNull NASMBitwiseNOTExpr o) {
    visitExpr(o);
  }

  public void visitBitwiseORExpr(@NotNull NASMBitwiseORExpr o) {
    visitExpr(o);
  }

  public void visitBitwiseXORExpr(@NotNull NASMBitwiseXORExpr o) {
    visitExpr(o);
  }

  public void visitConditional(@NotNull NASMConditional o) {
    visitPsiElement(o);
  }

  public void visitConstant(@NotNull NASMConstant o) {
    visitPsiElement(o);
  }

  public void visitData(@NotNull NASMData o) {
    visitPsiElement(o);
  }

  public void visitDefine(@NotNull NASMDefine o) {
    visitPsiElement(o);
  }

  public void visitDirective(@NotNull NASMDirective o) {
    visitPsiElement(o);
  }

  public void visitDirectiveArg(@NotNull NASMDirectiveArg o) {
    visitPsiElement(o);
  }

  public void visitDivExpr(@NotNull NASMDivExpr o) {
    visitExpr(o);
  }

  public void visitEndDirective(@NotNull NASMEndDirective o) {
    visitExpr(o);
  }

  public void visitError(@NotNull NASMError o) {
    visitPsiElement(o);
  }

  public void visitExpr(@NotNull NASMExpr o) {
    visitPsiElement(o);
  }

  public void visitIStruc(@NotNull NASMIStruc o) {
    visitPsiElement(o);
  }

  public void visitIdentifier(@NotNull NASMIdentifier o) {
    visitExpr(o);
    // visitNamedElement(o);
  }

  public void visitInclude(@NotNull NASMInclude o) {
    visitPsiElement(o);
  }

  public void visitInstruction(@NotNull NASMInstruction o) {
    visitPsiElement(o);
  }

  public void visitLabel(@NotNull NASMLabel o) {
    visitNamedElement(o);
  }

  public void visitLabelDefMacro(@NotNull NASMLabelDefMacro o) {
    visitPsiElement(o);
  }

  public void visitLabelIdentifier(@NotNull NASMLabelIdentifier o) {
    visitExpr(o);
    // visitNamedElement(o);
  }

  public void visitLogicalANDExpr(@NotNull NASMLogicalANDExpr o) {
    visitExpr(o);
  }

  public void visitLogicalORExpr(@NotNull NASMLogicalORExpr o) {
    visitExpr(o);
  }

  public void visitLogicalXORExpr(@NotNull NASMLogicalXORExpr o) {
    visitExpr(o);
  }

  public void visitMacro(@NotNull NASMMacro o) {
    visitPsiElement(o);
  }

  public void visitMacroCall(@NotNull NASMMacroCall o) {
    visitExpr(o);
  }

  public void visitMacroLabel(@NotNull NASMMacroLabel o) {
    visitPsiElement(o);
  }

  public void visitMacroParamReference(@NotNull NASMMacroParamReference o) {
    visitExpr(o);
  }

  public void visitMacroParenthesis(@NotNull NASMMacroParenthesis o) {
    visitPsiElement(o);
  }

  public void visitMacroVarReference(@NotNull NASMMacroVarReference o) {
    visitExpr(o);
  }

  public void visitMapOption(@NotNull NASMMapOption o) {
    visitPsiElement(o);
  }

  public void visitMinusExpr(@NotNull NASMMinusExpr o) {
    visitExpr(o);
  }

  public void visitModulusExpr(@NotNull NASMModulusExpr o) {
    visitExpr(o);
  }

  public void visitMulExpr(@NotNull NASMMulExpr o) {
    visitExpr(o);
  }

  public void visitNumericLiteral(@NotNull NASMNumericLiteral o) {
    visitExpr(o);
  }

  public void visitParenthesisExpr(@NotNull NASMParenthesisExpr o) {
    visitExpr(o);
  }

  public void visitPlusExpr(@NotNull NASMPlusExpr o) {
    visitExpr(o);
  }

  public void visitPreprocessor(@NotNull NASMPreprocessor o) {
    visitPsiElement(o);
  }

  public void visitReg(@NotNull NASMReg o) {
    visitExpr(o);
  }

  public void visitSeg(@NotNull NASMSeg o) {
    visitExpr(o);
  }

  public void visitSegmentAddress(@NotNull NASMSegmentAddress o) {
    visitExpr(o);
  }

  public void visitStr(@NotNull NASMStr o) {
    visitExpr(o);
  }

  public void visitStrlen(@NotNull NASMStrlen o) {
    visitPsiElement(o);
  }

  public void visitStruc(@NotNull NASMStruc o) {
    visitPsiElement(o);
  }

  public void visitStructure(@NotNull NASMStructure o) {
    visitPsiElement(o);
  }

  public void visitStructureField(@NotNull NASMStructureField o) {
    visitExpr(o);
  }

  public void visitNamedElement(@NotNull NASMNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
