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
WHITE_SPACE=\s+

CRLF=\R|\n|\r\n
COMMENT=;.*
EQU=[eE][qQ][uU]
SECTION_TAG=[sS][eE][cC][tT][iI][oO][nN]
INCLUDE_TAG=\%[iI][nN][cC][lL][uU][dD][eE]
DEFINE_TAG=\%([xX]?[iI]?[dD][eE][fF][iI][nN][eE])
MACRO_TAG=\%([iI]?[mM][aA][cC][rR][oO])
CODE_SECTION_NAME=\.[tT][eE][xX][tT]
DATA_SECTION_NAME=\.[dD][aA][tT][aA]
BSS_SECTION_NAME=\.[bB][sS][sS]
DIRECTIVE_OP=[bB][iI][tT][sS]|[uU][sS][eE]16|[uU][sS][eE]32|[sS][eE][cC][tT][iI][oO][nN]|[sS][eE][gG][mM][eE][nN][tT]|[aA][bB][sS][oO][lL][uU][tT][eE]|[eE][xX][tT][eE][rR][nN]|[gG][lL][oO][bB][aA][lL]|[oO][rR][gG]|[aA][lL][iI][gG][nN]|[sS][tT][rR][uU][cC]|[eE][nN][dD][sS][tT][rR][uU][cC]|[cC][oO][mM][mM][oO][nN]|[cC][pP][uU]|[gG][rR][oO][uU][pP]|[uU][pP][pP][eE][rR][cC][aA][sS][eE]|[iI][mM][pP][oO][rR][tT]|[eE][xX][pP][oO][rR][tT]|[lL][iI][bB][rR][aA][rR][yY]|[mM][oO][dD][uU][lL][eE]
PREPROCESSOR_OP=\%([xX]?[iI]?[dD][eE][fF][iI][nN][eE]|[uU][nN][dD][eE][fF]|[aA][sS][sS][iI][gG][nN]|[iI]?[dD][eE][fF][sS][tT][rR]|[iI]?[dD][eE][fF][tT][oO][kK]|[sS][tT][rR][cC][aA][tT]|[sS][tT][rR][lL][eE][nN]|[sS][uU][bB][sS][tT][rR]|[iI]?[mM][aA][cC][rR][oO]|[eE][nN][dD][mM][aA][cC][rR][oO]|[rR][oO][tT][aA][tT][eE]|[rR][eE][pP]|[eE][nN][dD][rR][eE][pP])
DATA_OP=(res|d)[bwdqt]|[tT][iI][mM][eE][sS]
INS_DATA_TRANS_MOV=(mov([sz]x)?|cmov(n?[abceglopsz]|n?[abgl]e|p[eo]))|(xchg|bswap|xadd|cmpxchg(8b)?)
INS_DATA_TRANS_XCHG=(xchg|bswap|xadd|cmpxchg(8b)?)
INS_DATA_TRANS_OTHER=((push|pop)(ad?)?|cwde?|cdq|cbw)
INS_DECIMAL_ARITH=(daa|das|aaa|aas|aam|aad)
INS_BINARY_ARITH=(adcx?|adox|add|sub|sbb|i?mul|i?div|inc|dec|neg|cmp)
INS_BINARY_LOGICAL=(and|x?or|not)
INS_BINARY_ROTATE=(s[ah][rl]|sh[rl]d|r[co][rl])
INS_BINARY_SET=(set(n?[abceglopsz]|n?[abgl]e|p[eo]))
INS_BINARY_OTHER=(bt[crs]?|bs[fr]|test|crc32|popcnt)
INS_CONTROL_TRANS=(jmp|jn?[abceglopsz]|jn?[abgl]e|jp[eo]|j[er]?cxz)|(loop(n?[ez])?|call|ret|iret[dq]?|into?|bound|enter|leave)
INS_STRING_DATA=((mov|cmp|sca|lod|sto)(s[bdw]?)|rep(n?[ez])?)
INS_INPUT_OUTPUT=((in|out)(s[bdw]?)?)
INS_FLAG_CONTROL=((st|cl)[cdi]|cmc|[ls]ahf|(push|pop)f[dq]?)
INS_SEG_REGS=(l[defgs]s)
INS_MISC_OTHER=(lea|nop|ud2|xlatb?|cpuid|movbe)
INS_RNG_RAND=(rdrand|rdseed)
INS_BIT_MANIPULATION=(andn|bextr|bls(i|r|msk)|bzhi|pdep|pext|[lt]zcnt|(mul|ror|sar|shl|shr)x)
INS_64_BIT=(cdqe|cqo|(cmp|lod|mov|sto)sq|cmpxchg16b|mov(ntq|sxd)|scasq|swapgs|sys(call|ret))
MNEMONIC_OP={INS_DATA_TRANS_MOV}|{INS_DATA_TRANS_XCHG}|{INS_DATA_TRANS_OTHER}|{INS_DECIMAL_ARITH}|{INS_BINARY_ARITH}|{INS_BINARY_LOGICAL}|{INS_BINARY_ROTATE}|{INS_BINARY_SET}|{INS_BINARY_OTHER}|{INS_CONTROL_TRANS}|{INS_STRING_DATA}|{INS_INPUT_OUTPUT}|{INS_FLAG_CONTROL}|{INS_SEG_REGS}|{INS_MISC_OTHER}|{INS_RNG_RAND}|{INS_BIT_MANIPULATION}|{INS_64_BIT}
REGISTER=[a-d][lh]|e?[a-d]x|e?[sb]p|e?[sd]i|[c-gs]s|st[0-7]|mm[0-7]|cr[0-4]|dr[0-367]|tr[3-7]
SIZE=byte|[dq]?word
NUMBER=0b[0-1]+|0y[0-1]+[0-1]*b|[0-1]*y|0[xX][0-9a-fA-F]+|0[hH][0-9a-fA-F]+|\$[0-9]+[0-9a-fA-F]*|[0-9]+[0-9a-fA-F]*h|0d[0-9]+|[0-9]+
STRING=('([^'\\]|\\.)*'|\"([^\"\\]|\\.)*\")
LABEL_DEF=[a-zA-Z$._?][a-zA-Z0-9$._?#@\126]*:
IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_]*
LABEL=[a-zA-Z$._?][a-zA-Z0-9$._?#@\126]*

%%
<YYINITIAL> {
  {WHITE_SPACE}               { return WHITE_SPACE; }

  ":"                         { return COLON; }
  "["                         { return SQUARE_L; }
  "]"                         { return SQUARE_R; }
  "("                         { return ROUND_L; }
  ")"                         { return ROUND_R; }
  ","                         { return SEPARATOR; }
  "+"                         { return PLUS; }
  "-"                         { return MINUS; }
  "*"                         { return TIMES; }
  "\\"                        { return DIVIDE; }
  "$"                         { return DOLLARSIGN; }
  "$$"                        { return DOLLARSIGN2; }
  "%"                         { return PERCENT; }

  {CRLF}                      { return CRLF; }
  {COMMENT}                   { return COMMENT; }
  {EQU}                       { return EQU; }
  {SECTION_TAG}               { return SECTION_TAG; }
  {INCLUDE_TAG}               { return INCLUDE_TAG; }
  {DEFINE_TAG}                { return DEFINE_TAG; }
  {MACRO_TAG}                 { return MACRO_TAG; }
  {CODE_SECTION_NAME}         { return CODE_SECTION_NAME; }
  {DATA_SECTION_NAME}         { return DATA_SECTION_NAME; }
  {BSS_SECTION_NAME}          { return BSS_SECTION_NAME; }
  {DIRECTIVE_OP}              { return DIRECTIVE_OP; }
  {PREPROCESSOR_OP}           { return PREPROCESSOR_OP; }
  {DATA_OP}                   { return DATA_OP; }

  {MNEMONIC_OP}               { return MNEMONIC_OP; }
  {REGISTER}                  { return REGISTER; }
  {SIZE}                      { return SIZE; }
  {NUMBER}                    { return NUMBER; }
  {STRING}                    { return STRING; }
  {LABEL_DEF}                 { return LABEL_DEF; }
  {IDENTIFIER}                { return IDENTIFIER; }
  {LABEL}                     { return LABEL; }

}

[^] { return BAD_CHARACTER; }
