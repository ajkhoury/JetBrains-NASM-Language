package com.nasmlanguage;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.nasmlanguage.psi.NASMTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class NASMSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey REGISTER = createTextAttributesKey("NASM_REGISTER", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey OPERATION = createTextAttributesKey("NASM_OPERATION", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey SEPARATOR = createTextAttributesKey("NASM_SEPARATOR", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey BAD_OP = createTextAttributesKey("NASM_BAD_OP", HighlighterColors.BAD_CHARACTER);
    public static final TextAttributesKey NUMBER = createTextAttributesKey("NASM_HEX", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey SIZE_TYPE = createTextAttributesKey("NASM_SIZE_TYPE", DefaultLanguageHighlighterColors.METADATA);
    public static final TextAttributesKey OP_PREFIX = createTextAttributesKey("NASM_OP_PREFIX", DefaultLanguageHighlighterColors.METADATA);
    public static final TextAttributesKey COMMENT = createTextAttributesKey("NASM_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey LABEL = createTextAttributesKey("NASM_LABEL", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey IDENTIFIER = createTextAttributesKey("NASM_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey MACRO_CALL = createTextAttributesKey("NASM_MACRO_CALL", DefaultLanguageHighlighterColors.FUNCTION_CALL);
    public static final TextAttributesKey DIRECTIVE = createTextAttributesKey("NASM_DIRECTIVE", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey STRING = createTextAttributesKey("NASM_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("NASM_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);


    private static final TextAttributesKey[] BAD_CHAR_KEYS = { BAD_CHARACTER };
    private static final TextAttributesKey[] SEPARATOR_KEYS = { SEPARATOR };
    private static final TextAttributesKey[] REGISTER_KEYS = { REGISTER };
    private static final TextAttributesKey[] OPERATION_KEYS = { OPERATION };
    private static final TextAttributesKey[] DIRECTIVE_KEYS = { DIRECTIVE };
    private static final TextAttributesKey[] STRING_KEYS = { STRING };
    private static final TextAttributesKey[] BAD_OP_KEYS = { BAD_OP };
    private static final TextAttributesKey[] NUMBER_KEYS = { NUMBER };
    private static final TextAttributesKey[] SIZE_TYPE_KEYS = { SIZE_TYPE };
    private static final TextAttributesKey[] OP_PREFIX_KEYS = { OP_PREFIX };
    private static final TextAttributesKey[] COMMENT_KEYS = { COMMENT };
    private static final TextAttributesKey[] LABEL_KEYS = { LABEL };
    private static final TextAttributesKey[] IDENTIFIER_KEYS = { IDENTIFIER };
    private static final TextAttributesKey[] MACRO_CALL_KEYS = { MACRO_CALL };
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new NASMLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(NASMTypes.REGISTER)) {
            return REGISTER_KEYS;
        } else if (tokenType.equals(NASMTypes.SEPARATOR)) {
            return SEPARATOR_KEYS;
        } else if (tokenType.equals(NASMTypes.SIZE_TYPE)) {
            return SIZE_TYPE_KEYS;
        } else if (tokenType.equals(NASMTypes.MACRO_CALL)) {
            return MACRO_CALL_KEYS;
        } else if (tokenType.equals(NASMTypes.OP_PREFIX)) {
            return OP_PREFIX_KEYS;
        } else if (tokenType.equals(NASMTypes.GENERAL_OP)
                || tokenType.equals(NASMTypes.X64_OP)
                || tokenType.equals(NASMTypes.FPU_OP)
                || tokenType.equals(NASMTypes.MMX_OP)
                || tokenType.equals(NASMTypes.SSE_OP)
                || tokenType.equals(NASMTypes.SSE2_OP)
                || tokenType.equals(NASMTypes.SSE3_OP)
                || tokenType.equals(NASMTypes.SSE4_OP)
                || tokenType.equals(NASMTypes.DATA_OP)) {
            return OPERATION_KEYS;
        } else if (tokenType.equals(NASMTypes.DIRECTIVE_OP) || tokenType.equals(NASMTypes.SECTION_TAG) || tokenType.equals(NASMTypes.EQU))  {
            return DIRECTIVE_KEYS;
        } else if (tokenType.equals(NASMTypes.STRING)) {
            return STRING_KEYS;
        } else if (tokenType.equals(NASMTypes.BINARY) || tokenType.equals(NASMTypes.HEXADECIMAL) || tokenType.equals(NASMTypes.DECIMAL)) {
            return NUMBER_KEYS;
        } else if (tokenType.equals(NASMTypes.LBL_DEF) || tokenType.equals(NASMTypes.LBL)) {
            return LABEL_KEYS;
        } else if (tokenType.equals(NASMTypes.IDENTIFIER)) {
            return IDENTIFIER_KEYS;
        } else if (tokenType.equals(NASMTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}
