package com.nasmlanguage;

import com.intellij.codeInsight.lookup.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.nasmlanguage.psi.NASMIdentifier;
import org.jetbrains.annotations.*;

import java.util.*;

public class NASMReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private String id;

    public NASMReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        id = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final List<NASMIdentifier> identifiers = NASMUtil.findIdentifierReferencesByStringInProject(project, id);
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        for (NASMIdentifier identifier : identifiers) {
            results.add(new PsiElementResolveResult(identifier));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
        List<NASMIdentifier> identifiers = NASMUtil.findIdentifierReferencesInProject(project);
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for (final NASMIdentifier identifier : identifiers) {
            String identifierText = identifier.getId().getText();
            if (identifierText != null && identifierText.length() > 0) {
                variants.add(LookupElementBuilder.create(identifier).
                        withIcon(NASMIcons.ASM_FILE).
                        withTypeText(identifier.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }

}
