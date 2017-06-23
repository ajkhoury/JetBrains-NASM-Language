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
                if (!nasmLabels.isEmpty())
                    result.addAll(nasmLabels);
            }
        }
        return result;
    }

    //static List<NASMLabelInstruction> findLabelInstructions(Project project) {
    //    List<NASMLabelInstruction> result = new ArrayList<>();
    //    Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(
    //            FileTypeIndex.NAME, NASMFileType.INSTANCE, GlobalSearchScope.allScope(project)
    //    );
    //    for (VirtualFile virtualFile : virtualFiles) {
    //        NASMFile assemblyFile = (NASMFile)PsiManager.getInstance(project).findFile(virtualFile);
    //        if (assemblyFile != null) {
    //            Collection<NASMLabelInstruction> nasmLabelInstructions = PsiTreeUtil.collectElementsOfType(assemblyFile, NASMLabelInstruction.class);
    //            if (!nasmLabelInstructions.isEmpty())
    //                result.addAll(nasmLabelInstructions);
    //        }
    //    }
    //    return result;
    //}
    //static List<NASMLabelData> findLabelDatas(Project project) {
    //    List<NASMLabelData> result = new ArrayList<>();
    //    Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(
    //            FileTypeIndex.NAME, NASMFileType.INSTANCE, GlobalSearchScope.allScope(project)
    //    );
    //    for (VirtualFile virtualFile : virtualFiles) {
    //        NASMFile assemblyFile = (NASMFile)PsiManager.getInstance(project).findFile(virtualFile);
    //        if (assemblyFile != null) {
    //            Collection<NASMLabelData> nasmLabelDatas = PsiTreeUtil.collectElementsOfType(assemblyFile, NASMLabelData.class);
    //            if (!nasmLabelDatas.isEmpty())
    //                result.addAll(nasmLabelDatas);
    //        }
    //    }
    //    return result;
    //}

    static List<NASMStructure> findStructures(Project project) {
        List<NASMStructure> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(
                FileTypeIndex.NAME, NASMFileType.INSTANCE, GlobalSearchScope.allScope(project)
        );
        for (VirtualFile virtualFile : virtualFiles) {
            NASMFile assemblyFile = (NASMFile)PsiManager.getInstance(project).findFile(virtualFile);
            if (assemblyFile != null) {
                Collection<NASMStructure> nasmStructs = PsiTreeUtil.collectElementsOfType(assemblyFile, NASMStructure.class);
                if (!nasmStructs.isEmpty()) {
                    result.addAll(nasmStructs);
                }
            }
        }
        return result;
    }

    static List<NASMIdentifier> findIdentifierReferences(Project project, NASMIdentifier identifier) {
        List<NASMIdentifier> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(
                FileTypeIndex.NAME, NASMFileType.INSTANCE, GlobalSearchScope.allScope(project)
        );
        PsiElement targetIdentifierId = identifier.getId();
        if (targetIdentifierId != null) {
            for (VirtualFile virtualFile : virtualFiles) {
                NASMFile assemblyFile = (NASMFile) PsiManager.getInstance(project).findFile(virtualFile);
                if (assemblyFile != null) {
                    Collection<NASMIdentifier> nasmIdentifiers = PsiTreeUtil.collectElementsOfType(assemblyFile, NASMIdentifier.class);
                    if (!nasmIdentifiers.isEmpty()) {
                        for (NASMIdentifier nasmIdentifier : nasmIdentifiers) {
                            if (nasmIdentifier == identifier)
                                continue;
                            PsiElement nasmIdentifierId = nasmIdentifier.getId();
                            if (nasmIdentifierId != null) {
                                if (nasmIdentifierId.getText().equals(targetIdentifierId.getText())) {
                                    result.add(nasmIdentifier);
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

}
