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
            new AttributesDescriptor("Separator", NASMSyntaxHighlighter.NASM_SEPARATOR),
            new AttributesDescriptor("Register", NASMSyntaxHighlighter.NASM_REGISTER),
            new AttributesDescriptor("Operation", NASMSyntaxHighlighter.NASM_OPERATION),
            new AttributesDescriptor("String", NASMSyntaxHighlighter.NASM_STRING),
            new AttributesDescriptor("Macro", NASMSyntaxHighlighter.NASM_MACRO),
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
                "byteval2\tdb 0A1h\n" +
                "\n" +
                "section .text\n" +
                "\n" +
                "%macro text_macro 0\n" +
                "    mov\teax, ebx\n" +
                "%endmacro\n" +
                "\n" +
                "%define\tinimg\tqword [rbp-8]\n" +
                "%define\toutimg\tqword [rbp-16]\n" +
                "\n" +
                "func:\n" +
                "    text_macro()\n" +
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
