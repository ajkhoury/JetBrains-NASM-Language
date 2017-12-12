package com.nasmlanguage;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.patterns.ElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import com.nasmlanguage.psi.NASMTypes;
import com.nasmlanguage.psi.NASMIdentifier;
import org.jetbrains.annotations.NotNull;

public class NASMCompletionContributor extends DefaultCompletionContributor {

    public NASMCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(NASMTypes.IDENTIFIER).withLanguage(NASMLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("mov"));
                    }
                }
        );
    }
}