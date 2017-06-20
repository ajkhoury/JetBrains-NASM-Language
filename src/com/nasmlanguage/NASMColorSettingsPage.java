package com.nasmlanguage;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.*;
import org.jetbrains.annotations.*;

import javax.swing.*;
import java.util.Map;

public class NASMColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] NASM_DESCRIPTORS = new AttributesDescriptor[] {
            new AttributesDescriptor("Number", NASMSyntaxHighlighter.NASM_NUMBER),
            new AttributesDescriptor("Constant", NASMSyntaxHighlighter.NASM_CONSTANT),
            new AttributesDescriptor("Separator", NASMSyntaxHighlighter.NASM_SEPARATOR),
            new AttributesDescriptor("Register", NASMSyntaxHighlighter.NASM_REGISTER),
            new AttributesDescriptor("Segment", NASMSyntaxHighlighter.NASM_SEGMENT),
            new AttributesDescriptor("Operation", NASMSyntaxHighlighter.NASM_OPERATION),
            new AttributesDescriptor("String", NASMSyntaxHighlighter.NASM_STRING),
            new AttributesDescriptor("Macro Identifier", NASMSyntaxHighlighter.NASM_MACRO),
            new AttributesDescriptor("Macro Parameter Reference", NASMSyntaxHighlighter.NASM_MACRO_PARAM_REF),
            new AttributesDescriptor("Macro Label", NASMSyntaxHighlighter.NASM_MACRO_LABEL),
            new AttributesDescriptor("Label", NASMSyntaxHighlighter.NASM_LABEL),
            new AttributesDescriptor("Structure", NASMSyntaxHighlighter.NASM_STRUCTURE),
            new AttributesDescriptor("Size Type", NASMSyntaxHighlighter.NASM_SIZE_TYPE),
            new AttributesDescriptor("Instruction Prefix", NASMSyntaxHighlighter.NASM_OP_PREFIX),
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return NASMIcons.FILE;
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
                "\n" +
                "global\tfunc\n" +
                "\n" +
                "section\t.data\n" +
                "\n" +
                "floatval\tdd 3.14159\n" +
                "byteval1\tdb 0xFF\n" +
                "byteval2\tdb 0A1h\n" +
                "binaryval1\tdb 0b0101\n" +
                "\n" +
                "section .text\n" +
                "\n" +
                "example_constant equ 45\n" +
                "\n" +
                "%macro multi_line_macro 1\n" +
                "    mov\t%1, ebx\n" +
                "%endmacro\n" +
                "%macro silly 2\n" +
                "    %2: db %1\n" +
                "%endmacro\n" +
                "\n" +
                "%define\tsingle_line_macro(x)\t(x+5)\n" +
                "\n" +
                "func:\n" +
                "    multi_line_macro(eax)\n" +
                "    mov\teax, single_line_macro(5)\n" +
                "    xor\tax, ax\n" +
                "    mov\tss, ax ; Set segments\n" +
                "    mov\tds, ax\n" +
                "    mov\tes, ax\n" +
                "    fadd\tS(0) ; FPU instruction\n" +
                "    pxor\txmm0, xmm0 ; MMX instruction\n" +
                "    cvtsi2ss\txmm0, rax\n" +
                "    mov\teax, dword [ebp + 4*eax - 12h]\n" +
                "    repz ret\n";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return NASM_DESCRIPTORS;
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
