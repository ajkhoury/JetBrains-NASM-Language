package com.nasmlanguage;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.nasmlanguage.psi.NASMTypes;
import com.intellij.psi.TokenType;


%%

%class NASMLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

%{
private int section = 0;
%}

CRLF=\n|\r|\r\n
WHITESPACE=[\ \n\t\f]
COMMENT=;.*
COMMA=","
SQUARE_L="["
SQUARE_R="]"

DIRECTIVE="BITS"|"USE16"|"USE32"|"SECTION"|"SEGMENT"|"ABSOLUTE"|"EXTERN"|"GLOBAL"|"ORG"|"ALIGN"|"STRUC"|"ENDSTRUC"|"COMMON"|"CPU"|"GROUP"|"UPPERCASE"|"IMPORT"|"EXPORT"|"LIBRARY"|"MODULE"|
          "bits"|"use16"|"use32"|"section"|"segment"|"absolute"|"extern"|"global"|"org"|"align"|"struc"|"endstruc"|"common"|"cpu"|"group"|"uppercase"|"import"|"export"|"library"|"module"

IDENTIFIER=[a-zA-Z$._?][a-zA-Z0-9$._?#@\126]*
LABEL={IDENTIFIER}":"

MNEMONICS_GENERAL_PURPOSE=(mov([sz]x)?|cmov(n?[abceglopsz]|n?[abgl]e|p[eo]))|
                          (xchg|bswap|xadd|cmpxchg(8b)?)|
                          ((push|pop)(ad?)?|cwde?|cdq|cbw)|
                          (adcx?|adox|add|sub|sbb|i?mul|i?div|inc|dec|neg|cmp)|
                          (daa|das|aaa|aas|aam|aad)|
                          (and|x?or|not)|
                          (s[ah][rl]|sh[rl]d|r[co][rl])|
                          (set(n?[abceglopsz]|n?[abgl]e|p[eo]))|
                          (bt[crs]?|bs[fr]|test|crc32|popcnt)|
                          (jmp|jn?[abceglopsz]|jn?[abgl]e|jp[eo]|j[er]?cxz)|
                          (loop(n?[ez])?|call|ret|iret[dq]?|into?|bound|enter|leave)|
                          ((mov|cmp|sca|lod|sto)(s[bdw]?)|rep(n?[ez])?)|
                          ((in|out)(s[bdw]?)?)|
                          ((st|cl)[cdi]|cmc|[ls]ahf|(push|pop)f[dq]?)|
                          (l[defgs]s)|
                          (lea|nop|ud2|xlatb?|cpuid|movbe)|
                          (rdrand|rdseed)|
                          (andn|bextr|bls(i|r|msk)|bzhi|pdep|pext|[lt]zcnt|(mul|ror|sar|shl|shr)x)
MNEMONICS_64BIT=(cdqe|cqo|(cmp|lod|mov|sto)sq|cmpxchg16b|mov(ntq|sxd)|scasq|swapgs|sys(call|ret))
OPERATION={MNEMONICS_GENERAL_PURPOSE}|{MNEMONICS_64BIT}

BINARY=0b[0-1]+|0y[0-1]+[0-1]*b|[0-1]*y
HEXADECIMAL=0[xX][0-9a-fA-F]+|0[hH][0-9a-fA-F]+|\$[0-9]+[0-9a-fA-F]*|[0-9]+[0-9a-fA-F]*h
DECIMAL=0d[0-9]+|[0-9]+
NUMERIC_LITERAL={BINARY}|{HEXADECIMAL}|{DECIMAL}


%state DIRECTIVE_ARG
%state OPERAND1
%state OPERAND2
%state SQUARE_L
%state SQUARE_R
%state COMMA
%state ADDRESS
%xstate STRING

%%

<YYINITIAL> {
    {DIRECTIVE}						{ yybegin(DIRECTIVE_ARG); return NASMTypes.DIRECTIVE; }
    {LABEL}							{ yybegin(YYINITIAL); return NASMTypes.LABEL; }
    {OPERATION} 					{ yybegin(OPERAND); return NASMTypes.OPERATION; }
}

<DIRECTIVE_ARG> {
    {NUMERIC_LITERAL}               { yybegin(YYINITIAL); return NASMTypes.NUMBER; }
    \"								{ yybegin(STRING); return NASMTypes.STRING_L; }

    {WHITESPACE}+					{ return TokenType.WHITE_SPACE; }
    .							    { yybegin(YYINITIAL); yypushback(1); }
}

<OPERAND1> {
	{ACCUMULATOR_OPERAND}				{ yybegin(YYINITIAL); return Asm6502Types.ACCUMULATOR_OPERAND; }
	{LABEL_OPERAND}						{ yybegin(YYINITIAL); return Asm6502Types.LABEL_OPERAND; }
	{BINARY_OPERAND} 					{ yybegin(YYINITIAL); return Asm6502Types.BINARY_OPERAND; }
	{DECIMAL_OPERAND} 					{ yybegin(YYINITIAL); return Asm6502Types.DECIMAL_OPERAND; }
	{HEXADECIMAL_OPERAND} 				{ yybegin(YYINITIAL); return Asm6502Types.HEXADECIMAL_OPERAND; }

	{ZERO_PAGE_VALUE}					{ pushState(YYINITIAL); yybegin(ADDRESS); return Asm6502Types.ZERO_PAGE_VALUE; }
	{ABSOLUTE_VALUE}					{ pushState(YYINITIAL); yybegin(ADDRESS); return Asm6502Types.ABSOLUTE_VALUE; }

	{OPEN_PAREN}						{ yybegin(OPEN_PAREN); return Asm6502Types.OPEN_PAREN; }

	{WHITESPACE}+						{ return TokenType.WHITE_SPACE; }
	.									{ yybegin(YYINITIAL); yypushback(1); }
}

/** White space */
{COMMENT}							{ yybegin(YYINITIAL); return NASMTypes.COMMENT; }
{CRLF}({CRLF}|{WHITE_SPACE})+       { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
{WHITE_SPACE}+                      { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
({CRLF}|{WHITE_SPACE})+             { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
{CRLF}								{ yybegin(YYINITIAL); return NASMTypes.CRLF; }
.                                   { return TokenType.BAD_CHARACTER; }