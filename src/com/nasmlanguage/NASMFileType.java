package com.nasmlanguage;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.*;

import javax.swing.*;

public class NASMFileType extends LanguageFileType {
    public static final NASMFileType INSTANCE = new NASMFileType();

    private NASMFileType() {
        super(NASMLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Assembly File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Assembly language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "asm";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return NASMIcons.FILE;
    }

}
