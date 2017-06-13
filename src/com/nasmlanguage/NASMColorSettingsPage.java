package com.nasmlanguage;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.*;
import org.jetbrains.annotations.*;

import javax.swing.*;
import java.util.Map;

public class NASMColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] NASM_DESCRIPTORS = new AttributesDescriptor[] {
            new AttributesDescriptor("Number", NASMSyntaxHighlighter.NUMBER),
            new AttributesDescriptor("Separator", NASMSyntaxHighlighter.SEPARATOR),
            new AttributesDescriptor("Register", NASMSyntaxHighlighter.REGISTER),
            new AttributesDescriptor("Operation", NASMSyntaxHighlighter.OPERATION),
            new AttributesDescriptor("Label", NASMSyntaxHighlighter.LABEL),
            new AttributesDescriptor("Size Type", NASMSyntaxHighlighter.SIZE_TYPE),
            new AttributesDescriptor("Instruction Prefix", NASMSyntaxHighlighter.INS_PREFIX),
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
        return "; sample NASM File\n" +
                "global\tfunc\n" +
                "section\t.data\n" +
                "value\tdd 1.23423\n" +
                "fl1\tdb 0xFF\n" +
                "fl2\tdb A1h\n" +
                "section .text\n" +
                "%define\tinimg\tqword [rbp-8]\n" +
                "%define\toutimg\tqword [rbp-16]\n" + "" +
                "func:\n" + "" +
                "mov\teax, ebx\n" +
                "fadd\tS(0)\n" +
                "pxor\txmm0, xmm0\n" +
                "cvtsi2ss\txmm0, rax\n" +
                "mov\teax, dword [ebp + 4*eax - 12h]\n";
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
