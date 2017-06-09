package com.nasmlanguage.psi;

import com.intellij.psi.tree.IElementType;
import com.nasmlanguage.NASMLanguage;
import org.jetbrains.annotations.*;

public class NASMElementType extends IElementType {
    public NASMElementType(@NotNull @NonNls String debugName) {
        super(debugName, NASMLanguage.INSTANCE);
    }
}
