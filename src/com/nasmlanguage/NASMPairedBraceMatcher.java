/*++

NASM Assembly Language Plugin
Copyright (c) 2017-2018 Aidan Khoury. All rights reserved.

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

--*/

package com.nasmlanguage;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.nasmlanguage.psi.NASMTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NASMPairedBraceMatcher implements PairedBraceMatcher {
    private static final BracePair[] BRACE_PAIRS = new BracePair[]{
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
        return BRACE_PAIRS;
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
