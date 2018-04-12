package com.nasmlanguage;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import com.nasmlanguage.psi.NASMIdentifier;
import com.nasmlanguage.psi.NASMTypes;
import org.jetbrains.annotations.NotNull;

public class NASMReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull final PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(PsiNamedElement.class),
            new PsiReferenceProvider() {
                @NotNull
                @Override
                public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                             @NotNull ProcessingContext context) {
                    if (element instanceof NASMIdentifier) {
                        NASMIdentifier nasmIdentifier = (NASMIdentifier)element;
                        return new PsiReference[]{
                                new NASMReference(nasmIdentifier.getId(), nasmIdentifier.getId().getTextRange())
                        };
                    } else {
                        return PsiReference.EMPTY_ARRAY;
                    }
                }
            });
    }
}