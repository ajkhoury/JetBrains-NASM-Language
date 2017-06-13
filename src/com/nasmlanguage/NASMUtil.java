package com.nasmlanguage;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import com.nasmlanguage.psi.*;

import java.util.*;

public class NASMUtil {

    public static List<NASMMacro> findMacros(Project project) {
        List<NASMMacro> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(
            FileTypeIndex.NAME, NASMFileType.INSTANCE, GlobalSearchScope.allScope(project)
        );

        for (VirtualFile virtualFile : virtualFiles) {
            NASMFile simpleFile = (NASMFile)PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                NASMMacro[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, NASMMacro.class);
                if (properties != null) {
                    Collections.addAll(result, properties);
                }
            }
        }

        return result;
    }

}
