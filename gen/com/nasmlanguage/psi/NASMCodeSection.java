// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMCodeSection extends PsiElement {

  @NotNull
  List<NASMCommand> getCommandList();

  @NotNull
  List<NASMDirective> getDirectiveList();

  @NotNull
  PsiElement getCodeSectionName();

  @NotNull
  PsiElement getSectionTag();

}
