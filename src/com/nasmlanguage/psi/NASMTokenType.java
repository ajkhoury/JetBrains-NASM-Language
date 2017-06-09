package com.nasmlanguage.psi;

import com.intellij.psi.tree.IElementType;
import com.nasmlanguage.NASMLanguage;
import org.jetbrains.annotations.*;

public class NASMTokenType extends IElementType {
    public NASMTokenType(@NotNull @NonNls String debugName) {
        super(debugName, NASMLanguage.INSTANCE);
    }
    @Override
    public String toString() {
        return "NASMTokenType." + super.toString();
    }
}