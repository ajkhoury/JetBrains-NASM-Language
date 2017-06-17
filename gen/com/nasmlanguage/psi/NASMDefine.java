// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface NASMDefine extends PsiElement {

  @NotNull
  List<NASMAddress> getAddressList();

  @NotNull
  List<NASMMacroCall> getMacroCallList();

  @NotNull
  PsiElement getDefineTag();

  String getDefineIdentifier();

}
