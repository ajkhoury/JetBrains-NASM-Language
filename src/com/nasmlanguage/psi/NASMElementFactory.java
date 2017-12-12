package com.nasmlanguage.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.nasmlanguage.NASMFileType;

public class NASMElementFactory {
    public static NASMIdentifier createIdentifier(Project project, String name) {
        final NASMFile file = createFile(project, name);
        return (NASMIdentifier)file.getFirstChild();
    }

    public static NASMFile createFile(Project project, String text) {
        String name = "dummy.create.asm";
        return (NASMFile)PsiFileFactory.getInstance(project).createFileFromText(name, NASMFileType.INSTANCE, text);
    }
}
