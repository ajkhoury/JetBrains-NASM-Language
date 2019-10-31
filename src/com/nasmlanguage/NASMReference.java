package com.nasmlanguage;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.nasmlanguage.psi.NASMIdentifier;
import com.nasmlanguage.psi.NASMLabel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NASMReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private String myReferenceId;

    public NASMReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
        myReferenceId = element.getText().substring(rangeInElement.getStartOffset(), rangeInElement.getEndOffset());
        //System.out.println("myReferenceId="+myReferenceId);
    }

    @SuppressWarnings("ToArrayCallWithZeroLengthArrayArgument")
    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        List<NASMLabel> labels = NASMUtil.findLabelReferencesByIdInProject(myElement.getProject(), myReferenceId);
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        for (NASMLabel label : labels) {
            results.add(new PsiElementResolveResult(label));
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
        List<NASMIdentifier> identifiers = NASMUtil.findIdentifierReferencesInProject(myElement.getProject());
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for (final NASMIdentifier identifier : identifiers) {
            String identifierText = identifier.getId().getText();
            if (identifierText != null && identifierText.length() > 0) {
                variants.add(LookupElementBuilder.create(identifier.getId()).
                        withIcon(NASMIcons.ASM_FILE).
                        withTypeText(identifier.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }

}
