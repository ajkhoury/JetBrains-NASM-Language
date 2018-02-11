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

import com.intellij.psi.tree.IElementType;
import com.nasmlanguage.NASMLanguage;
import org.jetbrains.annotations.*;

public class NASMElementType extends IElementType {

    public NASMElementType(@NotNull @NonNls String debugName) {
        super(debugName, NASMLanguage.INSTANCE);
    }
}
