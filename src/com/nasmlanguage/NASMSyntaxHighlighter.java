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

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.nasmlanguage.psi.NASMTypes;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class NASMSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey NASM_REGISTER = TextAttributesKey.createTextAttributesKey(
            "NASM_REGISTER",
            DefaultLanguageHighlighterColors.LOCAL_VARIABLE
    );
    public static final TextAttributesKey NASM_SEGMENT_REGISTER = TextAttributesKey.createTextAttributesKey(
            "NASM_SEGMENT_REGISTER",
            DefaultLanguageHighlighterColors.GLOBAL_VARIABLE
    );
    public static final TextAttributesKey NASM_OPERATION = TextAttributesKey.createTextAttributesKey(
            "NASM_OPERATION",
            DefaultLanguageHighlighterColors.KEYWORD
    );
    public static final TextAttributesKey NASM_SEPARATOR = TextAttributesKey.createTextAttributesKey(
            "NASM_SEPARATOR",
            DefaultLanguageHighlighterColors.COMMA
    );
    public static final TextAttributesKey NASM_NUMBER = TextAttributesKey.createTextAttributesKey(
            "NASM_NUMBER",
            DefaultLanguageHighlighterColors.NUMBER
    );
    public static final TextAttributesKey NASM_SIZE_TYPE = TextAttributesKey.createTextAttributesKey(
            "NASM_SIZE_TYPE",
            DefaultLanguageHighlighterColors.METADATA
    );
    public static final TextAttributesKey NASM_OP_PREFIX = TextAttributesKey.createTextAttributesKey(
            "NASM_OP_PREFIX",
            DefaultLanguageHighlighterColors.METADATA
    );
    public static final TextAttributesKey NASM_COMMENT = TextAttributesKey.createTextAttributesKey(
            "NASM_COMMENT",
            DefaultLanguageHighlighterColors.LINE_COMMENT
    );
    public static final TextAttributesKey NASM_LABEL = TextAttributesKey.createTextAttributesKey(
            "NASM_LABEL",
            DefaultLanguageHighlighterColors.FUNCTION_DECLARATION
    );
    public static final TextAttributesKey NASM_IDENTIFIER = TextAttributesKey.createTextAttributesKey(
            "NASM_IDENTIFIER",
            DefaultLanguageHighlighterColors.IDENTIFIER
    );
    public static final TextAttributesKey NASM_MACRO = TextAttributesKey.createTextAttributesKey(
            "NASM_MACRO",
            DefaultLanguageHighlighterColors.CONSTANT
    );
    public static final TextAttributesKey NASM_MACRO_PARAM_REF = TextAttributesKey.createTextAttributesKey(
            "NASM_MACRO_PARAM_REF",
            DefaultLanguageHighlighterColors.PARAMETER
    );
    public static final TextAttributesKey NASM_MACRO_VAR_REF = TextAttributesKey.createTextAttributesKey(
            "NASM_MACRO_VAR_REF",
            NASMSyntaxHighlighter.NASM_MACRO_PARAM_REF
    );
    public static final TextAttributesKey NASM_MACRO_LABEL = TextAttributesKey.createTextAttributesKey(
            "NASM_MACRO_LABEL",
            NASMSyntaxHighlighter.NASM_LABEL
    );
    public static final TextAttributesKey NASM_DIRECTIVE = TextAttributesKey.createTextAttributesKey(
            "NASM_DIRECTIVE",
            DefaultLanguageHighlighterColors.KEYWORD
    );
    public static final TextAttributesKey NASM_STRING = TextAttributesKey.createTextAttributesKey(
            "NASM_STRING",
            DefaultLanguageHighlighterColors.STRING
    );
    public static final TextAttributesKey NASM_CONSTANT = TextAttributesKey.createTextAttributesKey(
            "NASM_CONSTANT",
            DefaultLanguageHighlighterColors.CONSTANT
    );
    public static final TextAttributesKey NASM_STRUCTURE = TextAttributesKey.createTextAttributesKey(
            "NASM_STRUCTURE",
            DefaultLanguageHighlighterColors.CLASS_NAME
    );
    public static final TextAttributesKey NASM_STRUCTURE_FIELD = TextAttributesKey.createTextAttributesKey(
            "NASM_STRUCTURE_FIELD",
            NASMSyntaxHighlighter.NASM_STRUCTURE
    );
    public static final TextAttributesKey NASM_BAD_CHARACTER = TextAttributesKey.createTextAttributesKey(
            "NASM_BAD_CHARACTER",
            HighlighterColors.BAD_CHARACTER
    );

    private static final Map<IElementType, TextAttributesKey> AttributeKeys;
    static {
        AttributeKeys = new THashMap<>();

        AttributeKeys.put(NASMTypes.REGISTER, NASM_REGISTER);
        AttributeKeys.put(NASMTypes.SEGMENT_REGISTER, NASM_SEGMENT_REGISTER);

        AttributeKeys.put(NASMTypes.SEPARATOR, NASM_SEPARATOR);

        AttributeKeys.put(NASMTypes.SIZE_TYPE, NASM_SIZE_TYPE);

        AttributeKeys.put(NASMTypes.MACRO, NASM_MACRO);
        AttributeKeys.put(NASMTypes.MACRO_CALL, NASM_MACRO);
        AttributeKeys.put(NASMTypes.MACRO_PARAM_REF, NASM_MACRO_PARAM_REF);
        AttributeKeys.put(NASMTypes.MACRO_VAR_REF, NASM_MACRO_VAR_REF);
        AttributeKeys.put(NASMTypes.MACRO_PARAM_LBL_DEF, NASM_MACRO_LABEL);

        AttributeKeys.put(NASMTypes.OP_PREFIX, NASM_OP_PREFIX);

        AttributeKeys.put(NASMTypes.GENERAL_OP, NASM_OPERATION);
        AttributeKeys.put(NASMTypes.SYSTEM_OP, NASM_OPERATION);
        AttributeKeys.put(NASMTypes.VIRTUALIZATION_OP, NASM_OPERATION);
        AttributeKeys.put(NASMTypes.X64_OP, NASM_OPERATION);
        AttributeKeys.put(NASMTypes.FPU_OP, NASM_OPERATION);
        AttributeKeys.put(NASMTypes.MMX_OP, NASM_OPERATION);
        AttributeKeys.put(NASMTypes.SSE_OP, NASM_OPERATION);
        AttributeKeys.put(NASMTypes.SSE2_OP, NASM_OPERATION);
        AttributeKeys.put(NASMTypes.SSE3_OP, NASM_OPERATION);
        AttributeKeys.put(NASMTypes.SSE4_OP, NASM_OPERATION);
        AttributeKeys.put(NASMTypes.AVX_OP, NASM_OPERATION);
        AttributeKeys.put(NASMTypes.AVX2_OP, NASM_OPERATION);
        AttributeKeys.put(NASMTypes.AVX512_OP, NASM_OPERATION);

        AttributeKeys.put(NASMTypes.DATA_OP, NASM_OPERATION);

        AttributeKeys.put(NASMTypes.CONSTANT, NASM_CONSTANT);

        AttributeKeys.put(NASMTypes.DIRECTIVE_OP, NASM_DIRECTIVE);
        AttributeKeys.put(NASMTypes.END_DIRECTIVE_OP, NASM_DIRECTIVE);
        AttributeKeys.put(NASMTypes.SECTION, NASM_DIRECTIVE);
        AttributeKeys.put(NASMTypes.SEGMENT, NASM_DIRECTIVE);
        AttributeKeys.put(NASMTypes.EQU, NASM_DIRECTIVE);

        AttributeKeys.put(NASMTypes.STRING, NASM_STRING);
        AttributeKeys.put(NASMTypes.CHARACTER, NASM_STRING);

        AttributeKeys.put(NASMTypes.STRUCTURE, NASM_STRUCTURE);
        AttributeKeys.put(NASMTypes.STRUCT_FIELD, NASM_STRUCTURE_FIELD);

        AttributeKeys.put(NASMTypes.NUMERIC_LITERAL, NASM_NUMBER);
        AttributeKeys.put(NASMTypes.BINARY, NASM_NUMBER);
        AttributeKeys.put(NASMTypes.HEXADECIMAL, NASM_NUMBER);
        AttributeKeys.put(NASMTypes.ZEROES, NASM_NUMBER);
        AttributeKeys.put(NASMTypes.DECIMAL, NASM_NUMBER);
        AttributeKeys.put(NASMTypes.SEGMENT_ADDR_L, NASM_NUMBER);

        AttributeKeys.put(NASMTypes.LBL, NASM_LABEL);
        AttributeKeys.put(NASMTypes.LBL_DEF, NASM_LABEL);
        //AttributeKeys.put(NASMTypes.LBL_INS, NASM_LABEL);
        //AttributeKeys.put(NASMTypes.LBL_DATA, NASM_LABEL);

        AttributeKeys.put(NASMTypes.IDENTIFIER, NASM_IDENTIFIER);

        AttributeKeys.put(NASMTypes.COMMENT, NASM_COMMENT);
        AttributeKeys.put(NASMTypes.SEMICOLON, NASM_COMMENT);

        AttributeKeys.put(TokenType.BAD_CHARACTER, NASM_BAD_CHARACTER);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new NASMLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return SyntaxHighlighterBase.pack(AttributeKeys.get(tokenType));
    }
}
