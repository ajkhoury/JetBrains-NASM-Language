/*++

NASM Assembly Language Plugin
Copyright (c) 2017-2018 Aidan Khoury. All rights reserved.

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

--*/

package com.nasmlanguage.psi;

import com.nasmlanguage.NASMFileType;
import com.nasmlanguage.NASMLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import javax.swing.Icon;

public class NASMFile extends PsiFileBase {
    private FileViewProvider fileViewProvider;

    public NASMFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, NASMLanguage.INSTANCE);
        fileViewProvider = viewProvider;
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return NASMFileType.INSTANCE;
    }

    @Override
    public String toString() {
        final VirtualFile virtualFile = fileViewProvider.isEventSystemEnabled() ?
                                                    fileViewProvider.getVirtualFile() : null;
        return "Assembly File: "  + (virtualFile != null ? virtualFile.getName() : "<unknown>");
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}
