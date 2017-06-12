// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.nasmlanguage.psi.NASMTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.nasmlanguage.psi.*;

public class NASMInstructionImpl extends ASTWrapperPsiElement implements NASMInstruction {

  public NASMInstructionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull NASMVisitor visitor) {
    visitor.visitInstruction(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof NASMVisitor) accept((NASMVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<NASMAddress> getAddressList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, NASMAddress.class);
  }

  @Override
  @Nullable
  public NASMDirective getDirective() {
    return findChildByClass(NASMDirective.class);
  }

  @Override
  @Nullable
  public PsiElement getComment() {
    return findChildByType(COMMENT);
  }

  @Override
  @Nullable
  public PsiElement getLabelDef() {
    return findChildByType(LABEL_DEF);
  }

  @Override
  @Nullable
  public PsiElement getMnemonicOp() {
    return findChildByType(MNEMONIC_OP);
  }

}
