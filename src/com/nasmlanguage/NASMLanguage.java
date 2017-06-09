package com.nasmlanguage;

import com.intellij.lang.Language;

public class NASMLanguage extends Language {
    public static final NASMLanguage INSTANCE = new NASMLanguage();
    private NASMLanguage() {
        super("NASM");
    }
}
