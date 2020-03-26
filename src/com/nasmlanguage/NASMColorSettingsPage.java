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

import com.intellij.application.options.colors.InspectionColorSettingsPage;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.*;
import org.jetbrains.annotations.*;

import javax.swing.*;
import java.util.Map;

public class NASMColorSettingsPage implements ColorSettingsPage, InspectionColorSettingsPage {

    private static final AttributesDescriptor[] NASM_ATTRIBUTE_DESCRIPTORS = new AttributesDescriptor[] {
            new AttributesDescriptor("Constant", NASMSyntaxHighlighter.NASM_CONSTANT),
            new AttributesDescriptor("Instruction Mnemonic", NASMSyntaxHighlighter.NASM_OPERATION),
            new AttributesDescriptor("Instruction Prefix", NASMSyntaxHighlighter.NASM_OP_PREFIX),
            new AttributesDescriptor("Label", NASMSyntaxHighlighter.NASM_LABEL),
            new AttributesDescriptor("Macro Identifier", NASMSyntaxHighlighter.NASM_MACRO),
            new AttributesDescriptor("Macro Parameter Reference", NASMSyntaxHighlighter.NASM_MACRO_PARAM_REF),
            new AttributesDescriptor("Macro Variable Reference", NASMSyntaxHighlighter.NASM_MACRO_VAR_REF),
            new AttributesDescriptor("Macro Label", NASMSyntaxHighlighter.NASM_MACRO_LABEL),
            new AttributesDescriptor("Number", NASMSyntaxHighlighter.NASM_NUMBER),
            new AttributesDescriptor("Register", NASMSyntaxHighlighter.NASM_REGISTER),
            new AttributesDescriptor("Segment Register", NASMSyntaxHighlighter.NASM_SEGMENT_REGISTER),
            new AttributesDescriptor("Separator", NASMSyntaxHighlighter.NASM_SEPARATOR),
            new AttributesDescriptor("Size Type", NASMSyntaxHighlighter.NASM_SIZE_TYPE),
            new AttributesDescriptor("String", NASMSyntaxHighlighter.NASM_STRING),
            new AttributesDescriptor("Structure", NASMSyntaxHighlighter.NASM_STRUCTURE),
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return NASMIcons.ASM_FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new NASMSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "; Sample NASM File\n" +
                "section .text\n" +
                "\n" +
                "example_constant equ 45\n" +
                "%macro multi_line_macro 1\n" +
                "    mov    %1, ebx\n" +
                "%endmacro\n" +
                "%macro retz 0 \n" +
                "        jnz %%skip \n" +
                "        ret \n" +
                "    %%skip: \n" +
                "%endmacro\n" +
                "%define single_line_macro(x)   (x+5)\n" +
                "\n" +
                "global func\n" +
                "func:\n" +
                "       multi_line_macro eax\n" +
                "       mov     eax, single_line_macro(5)\n" +
                "       xor     ax, ax\n" +
                "       mov     ss, ax ; Set segments\n" +
                "       mov     ds, ax\n" +
                "       mov     es, ax\n" +
                "       fadd    S(0) ; FPU instruction\n" +
                "       pxor    xmm0, xmm0 ; MMX instruction\n" +
                "       cvtsi2ss xmm0, rax\n" +
                "       mov     eax, dword [ebp + 4*eax - 12h]\n" +
                "       mov     eax, 'str'\n" +
                "       mov     eax, partition.sizeof\n" +
                "       repz ret\n" +
                "\n" +
                "section .data\n" +
                "\n" +
                "; Format of fdisk partition entry.\n" +
                "struc partition\n" +
                "    .bootid  resb 1 ; bootable or not\n" +
                "    .head    resb 1 ; starting head, sector, cylinder\n" +
                "    .sect    resb 1 ;\n" +
                "    .cyl     resb 1 ;\n" +
                "    .type    resb 1 ; partition type\n" +
                "    .endhead resb 1 ; ending head, sector, cylinder\n" +
                "    .endsect resb 1 ;\n" +
                "    .endcyl  resb 1 ;\n" +
                "    .lba     resd 1 ; starting lba\n" +
                "    .sectors resd 1 ; size in sectors\n" +
                "    .sizeof\n" +
                "endstruc\n" +
                "%macro silly 2\n" +
                "    %2: db %1\n" +
                "%endmacro\n" +
                "silly \"example string 1\" exampleString1\n" +
                "exampleString2:db \"example string 2\"\n" +
                "floatval:      dd 3.14159\n" +
                "byteval1:      db 0xFF\n" +
                "byteval2:      db 0A1h\n" +
                "binaryval1:    db 0b0101\n" +
                "\n" +
                "       END\n";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return NASM_ATTRIBUTE_DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "NASM";
    }
}
