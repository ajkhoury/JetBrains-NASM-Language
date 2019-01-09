/*++

NASM Assembly Language Plugin
Copyright (c) 2017-2019 Aidan Khoury. All rights reserved.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

--*/

package com.nasmlanguage;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
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

    static List<NASMDefine> findPreprocessorDefines(PsiFile containingFile) {
        List<NASMDefine> result = new ArrayList<>();

        // Check the containing file's preprocessor defines
        Collection<NASMDefine> nasmDefines = PsiTreeUtil.collectElementsOfType(containingFile, NASMDefine.class);
        if (!nasmDefines.isEmpty())
            result.addAll(nasmDefines);

        // Makes this plugin perform like shit
        //Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(
        //        FileTypeIndex.NAME, NASMFileType.INSTANCE, GlobalSearchScope.allScope(project)
        //);
        //for (VirtualFile virtualFile : virtualFiles) {
        //    NASMFile assemblyFile = (NASMFile)PsiManager.getInstance(project).findFile(virtualFile);
        //    if (assemblyFile != null) {
        //        Collection<NASMPreprocessor> nasmPreprocessors = PsiTreeUtil.collectElementsOfType(assemblyFile, NASMPreprocessor.class);
        //        if (!nasmPreprocessors.isEmpty()) {
        //            for (NASMPreprocessor nasmPreprocessor : nasmPreprocessors) {
        //                NASMDefine define = nasmPreprocessor.getDefine();
        //                if (define != null)
        //                    result.add(define);
        //            }
        //        }
        //    }
        //}

        return result;
    }

    static List<NASMLabel> findLabels(PsiFile containingFile) {
        List<NASMLabel> result = new ArrayList<>();

        // Check the containing file's labels
        Collection<NASMLabel> nasmLabels = PsiTreeUtil.collectElementsOfType(containingFile, NASMLabel.class);
        if (!nasmLabels.isEmpty())
            result.addAll(nasmLabels);

        // Makes this plugin perform like shit
        //Project project = containingFile.getProject();
        //// Then check each include file for labels
        //Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(
        //        FileTypeIndex.NAME, NASMFileType.INSTANCE, GlobalSearchScope.allScope(project)
        //);
        //Collection<NASMInclude> includes = PsiTreeUtil.collectElementsOfType(containingFile, NASMInclude.class);
        //for (NASMInclude include : includes) {
        //    String includeFileName = include.getIncludeString();
        //    for (VirtualFile virtualFile : virtualFiles) {
        //        String virtFileName = virtualFile.getName();
        //        if (virtFileName.equals(includeFileName)) {
        //            NASMFile assemblyFile = (NASMFile)PsiManager.getInstance(project).findFile(virtualFile);
        //            if (assemblyFile != null) {
        //                nasmLabels = PsiTreeUtil.collectElementsOfType(assemblyFile, NASMLabel.class);
        //                if (!nasmLabels.isEmpty()) {
        //                    result.addAll(nasmLabels);
        //                }
        //            }
        //        }
        //    }
        //}

        return result;
    }

    static List<NASMConstant> findConstants(PsiFile containingFile) {
        List<NASMConstant> result = new ArrayList<>();

        // First check the containing file's constants
        Collection<NASMConstant> nasmConstants = PsiTreeUtil.collectElementsOfType(containingFile, NASMConstant.class);
        if (!nasmConstants.isEmpty())
            result.addAll(nasmConstants);

        return result;
    }

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

    @SuppressWarnings("ConstantConditions")
    static List<NASMIdentifier> findIdentifierReferences(PsiFile containingFile, NASMIdentifier identifier) {

        List<NASMIdentifier> result = new ArrayList<>();
        PsiElement targetIdentifierId = identifier.getId();
        if (targetIdentifierId != null) {
            // First check the containing file's identifiers
            Collection<NASMIdentifier> nasmIdentifiers = PsiTreeUtil.collectElementsOfType(containingFile, NASMIdentifier.class);
            for (NASMIdentifier nasmIdentifier : nasmIdentifiers) {
                if (nasmIdentifier != identifier) {
                    PsiElement nasmIdentifierId = nasmIdentifier.getId();
                    if (nasmIdentifierId != null) {
                        if (nasmIdentifierId.getText().equals(targetIdentifierId.getText())) {
                            result.add(nasmIdentifier);
                        }
                    }
                }
            }
        }

        return result;
    }

    @SuppressWarnings("ConstantConditions")
    static List<NASMIdentifier> findIdentifierReferencesByString(PsiFile containingFile, String targetIdentifierId) {
        List<NASMIdentifier> result = null;
        // First check the containing file's identifiers
        Collection<NASMIdentifier> nasmIdentifiers = PsiTreeUtil.collectElementsOfType(containingFile, NASMIdentifier.class);
        for (NASMIdentifier nasmIdentifier : nasmIdentifiers) {
            if (targetIdentifierId.equals(nasmIdentifier.getName())) {
                if (result == null)
                    result = new ArrayList<>();
                result.add(nasmIdentifier);
            }
        }

        return result != null ? result : Collections.emptyList();
    }

    @SuppressWarnings("ConstantConditions")
    static List<NASMIdentifier> findIdentifierReferencesByStringInProject(Project project, String targetIdentifierId) {
        List<NASMIdentifier> result = null;
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME,
                NASMFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            NASMFile nasmFile = (NASMFile)PsiManager.getInstance(project).findFile(virtualFile);
            if (nasmFile != null) {
                Collection<NASMIdentifier> nasmIdentifiers = PsiTreeUtil.collectElementsOfType(nasmFile, NASMIdentifier.class);
                if (nasmIdentifiers != null) {
                    for (NASMIdentifier identifier : nasmIdentifiers) {
                        if (targetIdentifierId.equals(identifier.getName())) {
                            if (result == null) {
                                result = new ArrayList<>();
                            }
                            result.add(identifier);
                        }
                    }
                }
            }
        }
        return result != null ? result : Collections.emptyList();
    }

    @SuppressWarnings("ConstantConditions")
    static List<NASMIdentifier> findIdentifierReferencesInProject(Project project) {
        List<NASMIdentifier> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME,
                NASMFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            NASMFile nasmFile = (NASMFile)PsiManager.getInstance(project).findFile(virtualFile);
            if (nasmFile != null) {
                Collection<NASMIdentifier> nasmIdentifiers = PsiTreeUtil.collectElementsOfType(nasmFile, NASMIdentifier.class);
                result.addAll( nasmIdentifiers );
            }
        }
        return result;
    }

}
