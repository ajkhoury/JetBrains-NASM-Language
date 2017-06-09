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
    public static final TextAttributesKey BAD_OPERATION = createTextAttributesKey("NASM_BAD_OPERATION", HighlighterColors.BAD_CHARACTER);
    public static final TextAttributesKey NUMBER = createTextAttributesKey("NASM_HEX", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey SIZE = createTextAttributesKey("NASM_SIZE", DefaultLanguageHighlighterColors.METADATA);
    public static final TextAttributesKey COMMENT = createTextAttributesKey("NASM_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey LABEL = createTextAttributesKey("NASM_LABEL", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey DIRECTIVE = createTextAttributesKey("NASM_DIRECTIVE", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey STRING = createTextAttributesKey("NASM_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("NASM_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] BAD_CHAR_KEYS = { BAD_CHARACTER };
    private static final TextAttributesKey[] REGISTER_KEYS = { REGISTER };
    private static final TextAttributesKey[] OPERATION_KEYS = { OPERATION };
    private static final TextAttributesKey[] DIRECTIVE_KEYS = { DIRECTIVE };
    private static final TextAttributesKey[] STRING_KEYS = { STRING };
    private static final TextAttributesKey[] BAD_OPERATION_KEYS = { BAD_OPERATION };
    private static final TextAttributesKey[] NUMBER_KEYS = { NUMBER };
    private static final TextAttributesKey[] SIZE_KEYS = { SIZE };
    private static final TextAttributesKey[] COMMENT_KEYS = { COMMENT };
    private static final TextAttributesKey[] LABEL_KEYS = { LABEL };
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
        } else if ((tokenType.equals(NASMTypes.MNEMONIC_OP)) || (tokenType.equals(NASMTypes.DATA_OP)))  {
            return OPERATION_KEYS;
        } else if ((tokenType.equals(NASMTypes.DIRECTIVE_OP)) || (tokenType.equals(NASMTypes.SECTION_TAG)) || (tokenType.equals(NASMTypes.EQU)))  {
            return DIRECTIVE_KEYS;
        } else if (tokenType.equals(NASMTypes.STRING)) {
            return STRING_KEYS;
        } else if (tokenType.equals(NASMTypes.NUMBER)) {
            return NUMBER_KEYS;
        } else if ((tokenType.equals(NASMTypes.LABEL)) || (tokenType.equals(NASMTypes.LABEL_DEF))) {
            return LABEL_KEYS;
        } else if (tokenType.equals(NASMTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}
