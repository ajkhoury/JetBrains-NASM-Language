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

package com.nasmlanguage;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.*;

import javax.swing.*;

public class NASMFileType extends LanguageFileType {

    public static final String FILE_EXTENSION = "asm";

    public static final NASMFileType INSTANCE = new NASMFileType();

    private NASMFileType() {
        super(NASMLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Assembly File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Assembly Language";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return FILE_EXTENSION;
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return NASMIcons.ASM_FILE;
    }

}
