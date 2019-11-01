package com.nasmlanguage;

import com.intellij.lang.refactoring.NamesValidator;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class NASMNamesValidator implements NamesValidator {
    private static final Pattern LBL_IDENTIFIER_PATTERN = Pattern.compile("([a-zA-Z$._?#@~]+[a-zA-Z0-9_]*)");

    @Override
    public boolean isKeyword(@NotNull final String name, final Project project) {
        // TODO: Check if given name is a mnemonic?
        return false;
    }

    @Override
    public boolean isIdentifier(@NotNull final String name, final Project project) {
        return LBL_IDENTIFIER_PATTERN.matcher(name).matches();
    }
}
