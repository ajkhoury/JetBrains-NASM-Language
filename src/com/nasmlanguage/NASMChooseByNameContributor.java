package com.nasmlanguage;

import com.intellij.navigation.*;
import com.intellij.openapi.project.Project;
import com.nasmlanguage.psi.NASMIdentifier;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class NASMChooseByNameContributor implements ChooseByNameContributor {
    @NotNull
    @Override
    public String[] getNames(Project project, boolean includeNonProjectItems) {
        List<NASMIdentifier> identifiers = NASMUtil.findIdentifierReferencesInProject(project);
        List<String> names = new ArrayList<String>(identifiers.size());
        for (NASMIdentifier identifier : identifiers) {
            String identifierString = identifier.getId().getText();
            if (identifierString != null && identifierString.length() > 0) {
                names.add(identifierString);
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
