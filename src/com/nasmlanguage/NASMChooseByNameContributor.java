package com.nasmlanguage;

import java.util.*;

import org.jetbrains.annotations.NotNull;
import com.intellij.navigation.*;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;

import com.nasmlanguage.psi.NASMIdentifier;

public class NASMChooseByNameContributor implements ChooseByNameContributor {
    @NotNull
    @Override
    public String[] getNames(Project project, boolean includeNonProjectItems) {
        List<NASMIdentifier> identifiers = NASMUtil.findIdentifierReferencesInProject(project);
        List<String> names = new ArrayList<>(identifiers.size());
        for (NASMIdentifier identifier : identifiers) {
            PsiElement idElement = identifier.getId();
            if (idElement != null) {
                String identifierString = idElement.getText();
                if (identifierString != null && identifierString.length() > 0) {
                    names.add(identifierString);
                }
            }
        }
        return names.toArray(new String[names.size()]);
    }

    @NotNull
    @Override
    public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
        List<NASMIdentifier> identifiers = NASMUtil.findIdentifierReferencesByIdInProject(project, name);
        return identifiers.toArray(new NavigationItem[identifiers.size()]);
    }
}
