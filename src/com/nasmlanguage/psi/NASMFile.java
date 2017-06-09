package com.nasmlanguage.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.nasmlanguage.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class NASMFile extends PsiFileBase {
    public NASMFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, NASMLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return NASMFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Assembly File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}
