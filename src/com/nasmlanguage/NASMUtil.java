package com.nasmlanguage;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import com.nasmlanguage.psi.*;

import java.util.*;

class NASMUtil {

    static List<PsiElement> findPreprocessorMacrosAndDefines(Project project) {
        List<PsiElement> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(
                FileTypeIndex.NAME, NASMFileType.INSTANCE, GlobalSearchScope.allScope(project)
        );
        for (VirtualFile virtualFile : virtualFiles) {
            NASMFile assemblyFile = (NASMFile)PsiManager.getInstance(project).findFile(virtualFile);
            if (assemblyFile != null) {
                Collection<NASMPreprocessor> nasmPreprocessors = PsiTreeUtil.collectElementsOfType(assemblyFile, NASMPreprocessor.class);
                if (!nasmPreprocessors.isEmpty()) {
                    for (NASMPreprocessor nasmPreprocessor : nasmPreprocessors) {
                        NASMMacro macro = nasmPreprocessor.getMacro();
                        if (macro != null) {
                            result.add(macro);
                            continue;
                        }
                        NASMDefine define = nasmPreprocessor.getDefine();
                        if (define != null) {
                            result.add(define);
                        }
                    }
                }
            }
        }
        return result;
    }

    static List<NASMMacro> findPreprocessorMacros(Project project) {
        List<NASMMacro> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(
            FileTypeIndex.NAME, NASMFileType.INSTANCE, GlobalSearchScope.allScope(project)
        );
        for (VirtualFile virtualFile : virtualFiles) {
            NASMFile assemblyFile = (NASMFile)PsiManager.getInstance(project).findFile(virtualFile);
            if (assemblyFile != null) {
                Collection<NASMPreprocessor> nasmPreprocessors = PsiTreeUtil.collectElementsOfType(assemblyFile, NASMPreprocessor.class);
                if (!nasmPreprocessors.isEmpty()) {
                    for (NASMPreprocessor nasmPreprocessor : nasmPreprocessors) {
                        NASMMacro macro = nasmPreprocessor.getMacro();
                        if (macro != null)
                            result.add(macro);
                    }
                }
            }
        }
        return result;
    }

    static List<NASMDefine> findPreprocessorDefines(Project project) {
        List<NASMDefine> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(
                FileTypeIndex.NAME, NASMFileType.INSTANCE, GlobalSearchScope.allScope(project)
        );
        for (VirtualFile virtualFile : virtualFiles) {
            NASMFile assemblyFile = (NASMFile)PsiManager.getInstance(project).findFile(virtualFile);
            if (assemblyFile != null) {
                Collection<NASMPreprocessor> nasmPreprocessors = PsiTreeUtil.collectElementsOfType(assemblyFile, NASMPreprocessor.class);
                if (!nasmPreprocessors.isEmpty()) {
                    for (NASMPreprocessor nasmPreprocessor : nasmPreprocessors) {
                        NASMDefine define = nasmPreprocessor.getDefine();
                        if (define != null)
                            result.add(define);
                    }
                }
            }
        }
        return result;
    }

    static List<NASMLabel> findLabels(Project project) {
        List<NASMLabel> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(
                FileTypeIndex.NAME, NASMFileType.INSTANCE, GlobalSearchScope.allScope(project)
        );
        for (VirtualFile virtualFile : virtualFiles) {
            NASMFile assemblyFile = (NASMFile)PsiManager.getInstance(project).findFile(virtualFile);
            if (assemblyFile != null) {
                Collection<NASMLabel> nasmLabels = PsiTreeUtil.collectElementsOfType(assemblyFile, NASMLabel.class);
                if (!nasmLabels.isEmpty()) {
                    for (NASMLabel nasmLabel : nasmLabels) {
                        if (nasmLabel != null)
                            result.add(nasmLabel);
                    }
                }
            }
        }
        return result;
    }

}
