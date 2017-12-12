package com.nasmlanguage;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.nasmlanguage.psi.NASMTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NASMPairedBraceMatcher implements PairedBraceMatcher {
    private static final BracePair[] bracePairs = new BracePair[]{
            new BracePair(NASMTypes.SQUARE_L, NASMTypes.SQUARE_R, true),
            new BracePair(NASMTypes.ROUND_L, NASMTypes.ROUND_R, false),
            new BracePair(NASMTypes.MACRO_TAG, NASMTypes.MACRO_END_TAG, true),
            new BracePair(NASMTypes.STRUC_TAG, NASMTypes.ENDSTRUC_TAG, true),
            new BracePair(NASMTypes.ISTRUC_TAG, NASMTypes.IEND_TAG, true),
            new BracePair(NASMTypes.IF_TAG, NASMTypes.ENDIF_TAG, true),
            new BracePair(NASMTypes.IFMACRO_TAG, NASMTypes.ENDIF_TAG, true)
            //new BracePair(NASMTypes.ELIF_TAG, NASMTypes.ENDIF_TAG, false),
            //new BracePair(NASMTypes.ELSE_TAG, NASMTypes.ENDIF_TAG, false)
    };

    @NotNull
    @Override
    public BracePair[] getPairs() {
        return bracePairs;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType type) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
