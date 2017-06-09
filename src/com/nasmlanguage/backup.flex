package com.nasmlanguage;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.nasmlanguage.psi.NASMTypes.*;

%%

%{
  public _NASMLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _NASMLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode


EOL=\R
CRLF=\n|\r|\r\n
WHITE_SPACE=[\ \t\f]
COMMENT=;.*
SECTION_TAG=[sS][eE][cC][tT][iI][oO][nN]
CODE_SECTION_NAME=\.[tT][eE][xX][tT]
DATA_SECTION_NAME=\.[dD][aA][tT][aA]
BSS_SECTION_NAME=\.[bB][sS][sS]
DIRECTIVE_OP="BITS"|"USE16"|"USE32"|"SECTION"|"SEGMENT"|"ABSOLUTE"|"EXTERN"|"GLOBAL"|"ORG"|"ALIGN"|"STRUC"|"ENDSTRUC"|"COMMON"|"CPU"|"GROUP"|"UPPERCASE"|"IMPORT"|"EXPORT"|"LIBRARY"|"MODULE"|
             "bits"|"use16"|"use32"|"section"|"segment"|"absolute"|"extern"|"global"|"org"|"align"|"struc"|"endstruc"|"common"|"cpu"|"group"|"uppercase"|"import"|"export"|"library"|"module"
SQUARE_L="["
SQUARE_R="]"


%%

<YYINITIAL> {
    {CRLF}                  { return CRLF; }
    {WHITE_SPACE}           { return WHITE_SPACE; }
    {COMMENT}               { return COMMENT; }
    {SECTION_TAG}           { return SECTION_TAG; }
    {DATA_SECTION_NAME}     { return DATA_SECTION_NAME; }
    {CODE_SECTION_NAME}     { return CODE_SECTION_NAME; }
    {BSS_SECTION_NAME}      { return BSS_SECTION_NAME; }
    {DIRECTIVE_OP}          { return DIRECTIVE_OP; }
    {SQUARE_L}              { return SQUARE_L; }
    {SQUARE_R}              { return SQUARE_R; }
    "LABEL"                 { return LABEL; }
    "NUMBER"                { return NUMBER; }
    "DATA_LABEL"            { return DATA_LABEL; }
    "DATA_OP"               { return DATA_OP; }
    "DATA_STRING_L"         { return DATA_STRING_L; }
    "STRING_CHAR"           { return STRING_CHAR; }
    "DATA_STRING_R"         { return DATA_STRING_R; }
    "LABEL_DEF"             { return LABEL_DEF; }
    "SEPARATOR"             { return SEPARATOR; }
    "OPERATION"             { return OPERATION; }
    "BAD_OPERATION"         { return BAD_OPERATION; }
    "REGISTER"              { return REGISTER; }
    "SIZE"                  { return SIZE; }
    "PLUS"                  { return PLUS; }
    "TIMES"                 { return TIMES; }
    "MINUS"                 { return MINUS; }
}

[^] { return BAD_CHARACTER; }
