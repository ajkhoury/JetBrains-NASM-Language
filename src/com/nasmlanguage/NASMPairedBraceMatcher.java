/*++

NASM Assembly Language Plugin
Copyright (c) 2017-2019 Aidan Khoury. All rights reserved.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

--*/

package com.nasmlanguage;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.nasmlanguage.psi.NASMTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class NASMPairedBraceMatcher implements PairedBraceMatcher {
    private static final BracePair[] BRACE_PAIRS = new BracePair[]{
            new BracePair(NASMTypes.SQUARE_L, NASMTypes.SQUARE_R, true),
            new BracePair(NASMTypes.ROUND_L, NASMTypes.ROUND_R, false),
            new BracePair(NASMTypes.MACRO_TAG, NASMTypes.MACRO_END_TAG, true),
            new BracePair(NASMTypes.STRUC_TAG, NASMTypes.ENDSTRUC_TAG, true),
            new BracePair(NASMTypes.ISTRUC_TAG, NASMTypes.IEND_TAG, true),
            new BracePair(NASMTypes.IF_TAG, NASMTypes.ENDIF_TAG, true),
            new BracePair(NASMTypes.IFID_TAG, NASMTypes.ENDIF_TAG, true),
            new BracePair(NASMTypes.IFDEF_TAG, NASMTypes.ENDIF_TAG, true),
            new BracePair(NASMTypes.IFIDN_TAG, NASMTypes.ENDIF_TAG, true),
            new BracePair(NASMTypes.IFSTR_TAG, NASMTypes.ENDIF_TAG, true),
            new BracePair(NASMTypes.IFNUM_TAG, NASMTypes.ENDIF_TAG, true),
            new BracePair(NASMTypes.IFMACRO_TAG, NASMTypes.ENDIF_TAG, true)
            //new BracePair(NASMTypes.ELIF_TAG, NASMTypes.ENDIF_TAG, false),
            //new BracePair(NASMTypes.ELSE_TAG, NASMTypes.ENDIF_TAG, false)
    };

    private static final TokenSet[] InsertPairBraceBefore = {
            NASMParserDefinition.WHITESPACES,
            NASMParserDefinition.COMMENTS
    };

    @NotNull
    @Override
    public BracePair[] getPairs() {
        return BRACE_PAIRS;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        for (TokenSet tokens : InsertPairBraceBefore) {
            for (IElementType type : tokens.getTypes()) {
                if (type == contextType)
                    return true;
            }
        }
        return false;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
