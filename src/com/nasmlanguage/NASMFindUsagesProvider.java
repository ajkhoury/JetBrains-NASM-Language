/*++

NASM Assembly Language Plugin
Copyright (c) 2017-2020 Aidan Khoury. All rights reserved.

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

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;
import com.nasmlanguage.psi.NASMIdentifier;
import com.nasmlanguage.psi.NASMLabel;
import com.nasmlanguage.psi.NASMLabelIdentifier;
import com.nasmlanguage.psi.NASMTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NASMFindUsagesProvider implements FindUsagesProvider {
    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return new DefaultWordsScanner(new NASMLexer(),
                TokenSet.create(NASMTypes.LBL),
                TokenSet.create(NASMTypes.COMMENT),
                TokenSet.EMPTY);
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement element) {
        return (element instanceof NASMIdentifier || element instanceof NASMLabelIdentifier);
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement element) {
        return null;
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        if (element instanceof NASMIdentifier) {
            return "identifier";
        } else if (element instanceof NASMLabelIdentifier) {
            return "label identifier";
        } else if (element instanceof NASMLabel) {
            return "label";
        }
        return "";
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        String elementString = null;

        if (element instanceof PsiNamedElement) {
            elementString = ((PsiNamedElement) element).getName();
        } else {
            Class<?> elementClass = element.getClass();
            System.out.println("[getDescriptiveName] elementClass="+elementClass);
        }

        if (elementString == null) {
            elementString = "";
        }

        return elementString;
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        String elementString = null;

        if (element instanceof PsiNamedElement) {
            elementString = ((PsiNamedElement) element).getName();
        } else {
            Class<?> elementClass = element.getClass();
            System.out.println("[getNodeText] elementClass="+elementClass);
        }

        if (elementString == null) {
            elementString = "";
        }

        return elementString;
    }

}
