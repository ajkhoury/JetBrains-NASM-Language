/*++

NASM Assembly Language Plugin
Copyright (c) 2017-2020 Aidan Khoury. All rights reserved.

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
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.SmartList;
import com.intellij.util.indexing.FileBasedIndex;
import com.nasmlanguage.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

class NASMUtil {

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

    static List<NASMIdentifier> findIdentifierReferences(PsiFile containingFile, NASMIdentifier identifier) {
        List<NASMIdentifier> result = new ArrayList<>();
        PsiElement targetIdentifierId = identifier.getNameIdentifier();
        // First check the containing file's identifiers
        Collection<NASMIdentifier> nasmIdentifiers = PsiTreeUtil.collectElementsOfType(containingFile, NASMIdentifier.class);
        for (NASMIdentifier nasmIdentifier : nasmIdentifiers) {
            if (nasmIdentifier != identifier) {
                if (targetIdentifierId.getText().equals(nasmIdentifier.getNameIdentifier().getText())) {
                    result.add(nasmIdentifier);
                }
            }
        }
        return result;
    }

    static List<NASMIdentifier> findIdentifierReferencesById(PsiFile containingFile, String targetIdentifierId) {
        List<NASMIdentifier> result = null;
        // First check the containing file's identifiers
        Collection<NASMIdentifier> identifiers = PsiTreeUtil.collectElementsOfType(containingFile, NASMIdentifier.class);
        for (NASMIdentifier identifier : identifiers) {
            if (targetIdentifierId.equals(identifier.getId().getText())) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                result.add(identifier);
            }
        }

        return result != null ? result : Collections.emptyList();
    }

    static List<NASMIdentifier> findIdentifierReferencesByIdInProject(Project project, String targetIdentifierId) {
        List<NASMIdentifier> result = null;
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(NASMFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            NASMFile simpleFile = (NASMFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                NASMIdentifier[] identifiers = PsiTreeUtil.getChildrenOfType(simpleFile, NASMIdentifier.class);
                if (identifiers != null) {
                    for (NASMIdentifier identifier : identifiers) {
                        if (targetIdentifierId.equals(identifier.getId().getText())) {
                            if (result == null) {
                                result = new ArrayList<NASMIdentifier>();
                            }
                            result.add(identifier);
                        }
                    }
                }
            }
        }
        return result != null ? result : Collections.<NASMIdentifier>emptyList();
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public static <T extends PsiElement> List<T> findAllChildrenOfTypeAsList(@Nullable PsiElement element, @NotNull Class<T> aClass, int depth) {
        // Don't search over a depth of 3, for performance reasons.
        if (element == null || depth > 3)
            return Collections.emptyList();

        List<T> result = null;
        for(PsiElement child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (aClass.isInstance(child)) {
                if (result == null) {
                    result = new SmartList();
                }
                result.add(aClass.cast(child));
            } else {
                // Recurse search into child element
                List<T> subResult = findAllChildrenOfTypeAsList(child, aClass, depth + 1);
                if (!subResult.isEmpty()) {
                    if (result == null) {
                        result = new SmartList();
                    }
                    result.addAll(subResult);
                }
            }
        }

        return result == null ? Collections.emptyList() : result;
    }

    public static <T extends PsiElement> T[] findAllChildrenOfType(@Nullable PsiElement element, @NotNull Class<T> aClass) {
        if (element == null)
            return null;
        List<T> result = findAllChildrenOfTypeAsList(element, aClass, 0);
        return result.isEmpty() ? null : (T[]) ArrayUtil.toObjectArray(result, aClass);
    }

    static List<NASMLabel> findLabelReferencesByIdInProject(Project project, String targetLabelId) {
        List<NASMLabel> result = null;
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(NASMFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            NASMFile simpleFile = (NASMFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {

                NASMLabel[] labels = findAllChildrenOfType(simpleFile, NASMLabel.class); /*PsiTreeUtil.getChildrenOfType*/
                if (labels != null) {
                    for (NASMLabel label : labels) {
                        if (targetLabelId.equals(label.getName())) {
                            if (result == null) {
                                result = new ArrayList<NASMLabel>();
                            }
                            result.add(label);
                        }
                    }
                }
            }
        }

        return result != null ? result : Collections.<NASMLabel>emptyList();
    }

    static List<NASMIdentifier> findIdentifierReferencesInProject(Project project) {
        List<NASMIdentifier> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME,
                NASMFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            NASMFile nasmFile = (NASMFile)PsiManager.getInstance(project).findFile(virtualFile);
            if (nasmFile != null) {
                Collection<NASMIdentifier> nasmIdentifiers = PsiTreeUtil.collectElementsOfType(nasmFile, NASMIdentifier.class);
                result.addAll(nasmIdentifiers);
            }
        }
        return result;
    }

}
