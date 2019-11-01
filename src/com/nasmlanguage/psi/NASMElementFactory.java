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

package com.nasmlanguage.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import com.nasmlanguage.NASMFileType;

public class NASMElementFactory {

    public static PsiElement createIdentifier(Project project, String name) {
        final NASMFile file = createFile(project, name);
        return file.getFirstChild();
    }

    public static NASMLabel createLabel(Project project, String name) {
        final NASMFile file = createFile(project, name+":");
        return (NASMLabel)file.getFirstChild();
    }

    public static NASMLabelIdentifier createLabelIdentifierId(Project project, String name) {
        final NASMFile file = createFile(project, "db short "+name);
        return PsiTreeUtil.getChildOfType(file.getFirstChild(), NASMLabelIdentifier.class);
    }

    public static NASMLabelIdentifier createLabelIdentifierLbl(Project project, String name) {
        if (name.charAt(0) == '.') {
            name = name.substring(1);
        }
        final NASMFile file = createFile(project, "db ."+name);
        return PsiTreeUtil.getChildOfType(file.getFirstChild(), NASMLabelIdentifier.class);
    }

    private static NASMFile createFile(Project project, String text) {
        return (NASMFile)PsiFileFactory.getInstance(project).createFileFromText("dummy.create.asm", NASMFileType.INSTANCE, text);
    }
}
