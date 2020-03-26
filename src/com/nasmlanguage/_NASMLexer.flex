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

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.nasmlanguage.psi.NASMTypes.*;
import static com.nasmlanguage.NASMParserDefinition.*;

%%

%{
  public _NASMLexer() {
    this((java.io.Reader)null);
  }
%}

%class _NASMLexer
%implements FlexLexer
%unicode
%public

%function advance
%type IElementType

NL=\R
WHITE_SPACE=[ \t\x0B\f]+

LINE_COMMENT=";" [^\r\n]*

EQU=([eE][qQ][uU])
SEGMENT_ADDR_L=((0[xX][0-9a-fA-F]+|0[hH][0-9a-fA-F]+|\$[0-9]+[0-9a-fA-F]*|[0-9]+[0-9a-fA-F]*[hH])|[0]*):
INCLUDE_TAG=(([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)([iI][nN][cC][lL][uU][dD][eE])
DEFINE_TAG=(([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)(([xX]|[iI])?[dD][eE][fF][iI][nN][eE])
ASSIGN_TAG=(([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)([iI]?[aA][sS][sS][iI][gG][nN])
MACRO_TAG=(([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)([iI]?[mM][aA][cC][rR][oO])
MACRO_END_TAG=(([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)([iI]?[eE][nN][dD][mM][aA][cC][rR][oO])
MACRO_PARAM_REF=((%|%%)([1-9][0-9]*))
MACRO_PARAM_COUNT=(%0)
MACRO_NOLIST_QUAL=(\.)([Nn][Oo][Ll][Ii][Ss][Tt])
MACRO_VAR_REF=(%%)([a-zA-Z0-9$._?][a-zA-Z0-9$._?#@]*)
MACRO_PARAM_LBL_DEF=((((%|%%)([a-zA-Z0-9$._?][a-zA-Z0-9$._]*))|(([a-zA-Z0-9$._?][a-zA-Z0-9$._]*)((%)[0-9]+)[a-zA-Z0-9$._]*)):)
IF_TAG=(([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)(([iI][fF][iI][dD][nN])|([iI][fF][nN]?([dD][eE][fF])?))
IFID_TAG=(([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)([iI][fF][iI][dD])
IFDEF_TAG=(([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)([iI][fF][nN]?[dD][eE][fF])
IFIDN_TAG=(([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)([iI][fF][iI][dD][nN][iI]?)
IFNUM_TAG=(([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)([iI][fF][nN][uU][mM])
IFSTR_TAG=(([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)([iI][fF][sS][tT][rR])
IFCTX_TAG=(([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)([iI][fF][cC][tT][xX])
IFMACRO_TAG=(([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)([iI][fF][nN]?[mM][aA][cC][rR][oO])
ELIF_TAG=(([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)(([eE][lL][iI][fF][iI][dD][nN][iI]?)|([eE][lL][iI][fF][nN]?([dD][eE][fF])?))
ELSE_TAG=(([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)([eE][lL][sS][eE])
ENDIF_TAG=(([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)([eE][nN][dD][iI][fF])
STRLEN_TAG=((([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)([sS][tT][rR][lL][eE][nN]))
ERROR_TAG=((([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)([eE][rR][rR][oO][rR])).*
SECTION=(([sS][eE][cC][tT][iI][oO][nN])([ \t\n\x0B\f\r]+)((\.)([a-zA-Z_]+[a-zA-Z0-9_]*)))
SEGMENT=(([sS][eE][gG][mM][eE][nN][tT])([ \t\n\x0B\f\r]+)([a-zA-Z_]+[a-zA-Z0-9_]*))
MAP_OPTIONS=((all|ALL)|(brief|BRIEF)|(sections|SECTIONS)|(segments|SEGMENTS)|(symbols|SYMBOLS))
MAP_FILE=(([a-zA-Z0-9_.]+)(\.)[mM][aA][pP])
STRUC_TAG=([sS][tT][rR][uU][cC])
ENDSTRUC_TAG=([eE][nN][dD][sS][tT][rR][uU][cC])
ISTRUC_TAG=([iI][sS][tT][rR][uU][cC])
IEND_TAG=([iI][eE][nN][dD])
AT_TAG=([aA][tT])
STRUCT_FIELD=(([a-zA-Z_]+[a-zA-Z0-9_]*)(\.)([a-zA-Z_]+[a-zA-Z0-9_]*))
DIRECTIVE_OP=([bB][iI][tT][sS]|[uU][sS][eE]16|[uU][sS][eE]32|[cC][oO][dD][eE]16|[cC][oO][dD][eE]32|[aA][bB][sS][oO][lL][uU][tT][eE]|[eE][xX][tT][eE][rR][nN]|[gG][lL][oO][bB][aA][lL]|[oO][rR][gG]|[aA][lL][iI][gG][nN]|[sS][tT][rR][uU][cC]|[eE][nN][dD][sS][tT][rR][uU][cC]|[cC][oO][mM][mM][oO][nN]|[cC][pP][uU]|[gG][rR][oO][uU][pP]|[uU][pP][pP][eE][rR][cC][aA][sS][eE]|[iI][mM][pP][oO][rR][tT]|[eE][xX][pP][oO][rR][tT]|[lL][iI][bB][rR][aA][rR][yY]|[mM][oO][dD][uU][lL][eE])
END_DIRECTIVE_OP=([eE][nN][dD])
PREPROCESSOR_OP=(([ \t\n\x0B\f\r]+)?[#%]([ \t\n\x0B\f\r]+)?)([xX]?[iI]?[dD][eE][fF][iI][nN][eE]|[uU][nN][dD][eE][fF]|[aA][sS][sS][iI][gG][nN]|[iI]?[dD][eE][fF][sS][tT][rR]|[iI]?[dD][eE][fF][tT][oO][kK]|[sS][tT][rR][cC][aA][tT]|[sS][tT][rR][lL][eE][nN]|[sS][uU][bB][sS][tT][rR]|[iI]?[mM][aA][cC][rR][oO]|[eE][nN][dD][mM][aA][cC][rR][oO]|[rR][oO][tT][aA][tT][eE]|[rR][eE][pP]|[eE][nN][dD][rR][eE][pP])
DATA_OP=([rR][eE][sS][bBwWdDqQtToOyYzZ]|[dD][bBwWdDqQtToOyYzZ]|[tT][iI][mM][eE][sS])
INS_DATA_TRANS_MOV=((mov([sz]x)?|cmov(n?[abceglopsz]|n?[abgl]e|p[eo]))|(MOV([SZ]X)?|CMOV(N?[ABCEGLOPSZ]|N?[ABGL]E|P[EO])))
INS_DATA_TRANS_XCHG=((xchg|bswap|xadd|cmpxchg(8b)?)|(XCHG|BSWAP|XADD|CMPXCHG(8[Bb])?))
INS_DATA_TRANS_OTHER=((push|pop)(ad?)?|cwde?|cdq|cbw)|((PUSH|POP)(AD?)?|CWDE?|CDQ|CBW)
INS_DECIMAL_ARITH=(daa|das|aaa|aas|aam|aad)|(DAA|DAS|AAA|AAS|AAM|AAD)
INS_BINARY_ARITH=(adcx?|adox|add|sub|sbb|i?mul|i?div|inc|dec|neg|cmp)|(ADCX?|ADOX|ADD|SUB|SBB|I?MUL|I?DIV|INC|DEC|NEG|CMP)
INS_BINARY_LOGICAL=(and|x?or|not)|(AND|X?OR|NOT)
INS_BINARY_ROTATE=(s[ah][rl]|sh[rl]d|r[co][rl])|(S[AH][RL]|SH[RL]D|R[CO][RL])
INS_BINARY_SET=(set(n?[abceglopsz]|n?[abgl]e|p[eo]))|(SET(N?[ABCEGLOPSZ]|N?[ABGL]E|P[EO]))
INS_BINARY_OTHER=(bt[crs]?|bs[fr]|test|crc32|popcnt)|(BT[CRS]?|BS[FR]|TEST|CRC32|POPCNT)
INS_CONTROL_TRANS=((jmp|jn?[abceglopsz]|jn?[abgl]e|jp[eo]|j[er]?cxz)|(loop(n?[ez])?|call|ret[fn]?|iret[dq]?|into?|bound|enter|leave))|((JMP|JN?[ABCEGLOPSZ]|JN?[ABGL]E|JP[EO]|J[ER]?CXZ)|(LOOP(N?[EZ])?|CALL|RET[FN]?|IRET[DQ]?|INTO?|BOUND|ENTER|LEAVE))
INS_STRING_DATA=((mov|cmp|sca|lod|sto)(s[bdw]?)|rep(n?[ez])?)|((MOV|CMP|SCA|LOD|STO)(S[BDW]?)|REP(N?[EZ])?)
INS_INPUT_OUTPUT=((in|out)(s[bdw]?)?)|((IN|OUT)(S[BDW]?)?)
INS_FLAG_CONTROL=((st|cl)[cdi]|cmc|[ls]ahf|(push|pop)f[dq]?)|((ST|CL)[CDI]|CMC|[LS]AHF|(PUSH|POP)F[DQ]?)
INS_SEG_REGS=(l[defgs]s)|(L[DEFGS]S)
INS_MISC_OTHER=(lea|nop|ud2|xlatb?|cpuid|movbe)|(LEA|NOP|UD2|XLATB?|CPUID|MOVBE)
INS_RNG_RAND=(rdrand|rdseed)|(RDRAND|RDSEED)
INS_BIT_MANIPULATION=(andn|bextr|bls(i|r|msk)|bzhi|pdep|pext|[lt]zcnt|(mul|ror|sar|shl|shr)x)|(ANDN|BEXTR|BLS(I|R|MSK)|BZHI|PDEP|PEXT|[LT]ZCNT|(MUL|ROR|SAR|SHL|SHR)X)
INS_SYSTEM=(((cl|st)ac|[ls]([gli]dt|tr|msw)|clts|arpl|lar|lsl|ver[rw]|inv(d|lpga?|pcid)|wbinvd)|(lock|hlt|rsm|(rd|wr)(msr|pkru|[fg]sbase)|rd(pmc|tscp?)|sys(enter|exit))|(x((save(c|opt|s)?|rstors?)(64)?|[gs]etbv)))|(((CL|ST)AC|[LS]([GLI]DT|TR|MSW)|CLTS|ARPL|LAR|LSL|VER[RW]|INV(D|LPGA?|PCID)|WBINVD)|(LOCK|HLT|RSM|(RD|WR)(MSR|PKRU|[FG]SBASE)|RD(PMC|TSCP?)|SYS(ENTER|EXIT))|(X((SAVE(C|OPT|S)?|RSTORS?)(64)?|[GS]ETBV)))
INS_INTEL_VMX=(vm(ptr(ld|st)|clear|read|write|launch|resume|xo(ff|n)|call|func)|inv(ept|vpid))|(VM(PTR(LD|ST)|CLEAR|READ|WRITE|LAUNCH|RESUME|XO(FF|N)|CALL|FUNC)|INV(EPT|VPID))
INS_AMD_SVM=(vm(run|load|save|mcall)|stgi|clgi|skinit)|(VM(RUN|LOAD|SAVE|MCALL)|STGI|CLGI|SKINIT)
INS_64_BIT=(cdqe|cqo|(cmp|lod|mov|sto)sq|cmpxchg16b|mov(ntq|sxd)|scasq|swapgs|sys(call|ret))|(CDQE|CQO|(CMP|LOD|MOV|STO)SQ|CMPXCHG16[Bb]|MOV(NTQ|SXD)|SCASQ|SWAPGS|SYS(CALL|RET))
INS_FPU_DATA_TRANS=((fcmov(n?([beu]|be)))|(f(i?(ld|stp?)|b(ld|stp)|xch)))|((FCMOV(N?([BEU]|BE)))|(F(I?(LD|STP?)|B(LD|STP)|XCH)))
INS_FPU_BASIC_ARITH=((f((add|div|mul|sub)p?|i(add|div|mul|sub)|(div|sub)rp?|i(div|sub)r))|(f(prem1?|abs|chs|rndint|scale|sqrt|xtract)))|((F((ADD|DIV|MUL|SUB)P?|I(ADD|DIV|MUL|SUB)|(DIV|SUB)RP?|I(DIV|SUB)R))|(F(PREML?|ABS|CHS|RNDINT|SCALE|SQRT|XTRACT)))
INS_FPU_COMPARISON=(f(u?com[ip]?p?|icomp?|tst|xam))|(F(U?COM[IP]?P?|ICOMP?|TST|XAM))
INS_FPU_TRANSCEND=(f(sin|cos|sincos|pa?tan|2xm1|yl2x(p1)?))|(F(SIN|COS|SINCOS|PA?TAN|2XML|YL2X(PL)?))
INS_FPU_LOAD=(fld(1|z|pi|l2[et]|l[ng]2))|(FLD(L|Z|PI|L2[ET]|L[NG]2))
INS_FPU_CONTROL=(f((inc|dec)stp|free|n?(init|clex|st[cs]w|stenv|save)|ld(cw|env)|rstor|nop)|f?wait)|(F((INC|DEC)STP|FREE|N?(INIT|CLEX|ST[CS]W|STENV|SAVE)|LD(CW|ENV)|RSTOR|NOP)|F?WAIT)
INS_FPU_STATE=(fx(save|rstor)(64)?)|(FX(SAVE|RSTOR)(64)?)
INS_MMX_DATA_TRANS=(mov[dq])|(MOV[DQ])
INS_MMX_CONVERSION=(pack(ssdw|[su]swb)|punpck[hl](bw|dq|wd))|(PACK(SSDW|[SU]SWB)|PUNPCK[HL](BW|DQ|WD))
INS_MMX_ARITH=(p(((add|sub)(d|(u?s)?[bw]))|maddwd|mul[lh]w))|(P(((ADD|SUB)(D|(U?S)?[BW]))|MADDWD|MUL[LH]W))
INS_MMX_COMPARISON=(pcmp((eq|gt)[bdw]))|(PCMP((EQ|GT)[BDW]))
INS_MMX_LOGICAL=(pandn?|px?or)|(PANDN?|PX?OR)
INS_MMX_ROTATE=(ps([rl]l[dwq]|raw|rad))|(PS([RL]L[DWQ]|RAW|RAD))
INS_MMX_STATE=(emms)|(EMMS)
INS_SSE_DATA_TRANS=(mov(([ahlu]|hl|lh|msk)ps|ss))|(MOV(([AHLU]|HL|LH|MSK)PS|SS))
INS_SSE_ARITH=((add|div|max|min|mul|rcp|r?sqrt|sub)[ps]s)|((ADD|DIV|MAX|MIN|MUL|RCP|R?SQRT|SUB)[PS]S)
INS_SSE_COMPARISON=(cmp[ps]s|u?comiss)|(CMP[PS]S|U?COMISS)
INS_SSE_LOGICAL=((andn?|x?or)ps)|((ANDN?|X?OR)PS)
INS_SSE_OTHER=((shuf|unpck[hl])ps)|((SHUF|UNPCK[HL])PS)
INS_SSE_CONVERSION=(cvt(pi2ps|si2ss|ps2pi|tps2pi|ss2si|tss2si))|(CVT(PI2PS|SI2SS|PS2PI|TPS2PI|SS2SI|TSS2SI))
INS_SSE_STATE=((ld|st)mxcsr)|((LD|ST)MXCSR)
INS_SSE_SIMD_INT=(p(avg[bw]|extrw|insrw|(max|min)(sw|ub)|sadbw|shufw|mulhuw|movmskb))|(P(AVG[BW]|EXTRW|INSRW|(MAX|MIN)(SW|UB)|SADBW|SHUFW|MULHUW|MOVMSKB))
INS_SSE_CACHE_CTRL=(maskmovq|movntps|sfence)|(MASKMOVQ|MOVNTPS|SFENCE)
INS_SSE_PREFETCH=(prefetch(nta|t[0-2]|w(t1)?))|(PREFECTH(NTA|T[0-2]|W(TL)?))
INS_SSE2_DATA_TRANS=(mov([auhl]|msk)pd)|(MOV([AUHL]|MSK)PD)
INS_SSE2_ARITH=((add|div|max|min|mul|sub|sqrt)[ps]d)|((ADD|DIV|MAX|MIN|MUL|SUB|SQRT)[PS]D)
INS_SSE2_LOGICAL=((andn?|x?or)pd)|((ANDN?|X?OR)PD)
INS_SSE2_COMPARISON=((cmpp|u?comis)d)|((CMPP|U?COMIS)D)
INS_SSE2_OTHER=((shuf|unpck[hl])pd)|((SHUF|UNPCK[HL])PD)
INS_SSE2_CONVERSION=((cvt(dq2pd|pi2pd|ps2pd|pd2ps|si2sd|sd2ss|ss2sd|t?(pd2dq|pd2pi|sd2si)))|(cvt(dq2ps|ps2dq|tps2dq)))|((CVT(DQ2PD|PI2PD|PS2PD|PD2PS|SI2SD|SD2SS|SS2SD|T?(PD2DQ|PD2PI|SD2SI)))|(CVT(DQ2PS|PS2DQ|TPS2DQ)))
INS_SSE2_SIMD_INT=((mov(dq[au]|q2dq|dq2q))|(p((add|sub|(s[lr]l|mulu|unpck[hl]q)d)q|shuf(d|[hl]w))))|((MOV(DQ[AU]|Q2DQ|DQ2Q))|(P((ADD|SUB|(S[LR]L|MULU|UNPCK[HL]Q)D)Q|SHUF(D|[HL]W))))
INS_SSE2_CACHE_CTRL=(clflush|[lm]fence|pause|maskmovdqu|movnt(dq|i|pd))|(CLFLUSH|[LM]FENCE|PAUSE|MASKMOVDQU|MOVNT(DQ|I|PD))
INS_SSE3_GENERAL=(fisttp|lddqu|(addsub|h(add|sub))p[sd]|mov(sh|sl|d)dup|monitor|mwait)|(FISTTP|LDDQU|(ADDSUB|H(ADD|SUB))P[SD]|MOV(SH|SL|D)DUP|MONITOR|MWAIT)
INS_SSE3_ARITH=(ph(add|sub)(s?w|d))|(PH(ADD|SUB)(S?W|D))
INS_SSE3_OTHER=(p((abs|sign)[bdw]|maddubsw|mulhrsw|shufb|alignr))|(P((ABS|SIGN)[BDW]|MADDUBSW|MULHRSW|SHUFB|ALIGNR))
INS_SSE4_ARITH=(pmul(ld|dq)|dpp[ds])|(PMUL(LD|DQ)|DPP[DS])
INS_SSE4_DATA_TRANS=(movntdqa)|(MOVNTDQA)
INS_SSE4_BLEND=(blendv?p[ds]|pblend(vb|w))|(BLENDV?P[DS]|PBLEND(VB|W))
INS_SSE4_PACKED_INT=(p(min|max)(u[dw]|s[bd]))|(P(MIN|MAX)(U[DW]|S[BD]))
INS_SSE4_PACKED_FP=(round[ps][sd])|(ROUND[PS][SD])
INS_SSE4_INS_EXT=((extract|insert)ps|p((ins|ext)(r[bdq])))|((EXTRACT|INSERT)PS|P((INS|EXT)(R[BDQ])))
INS_SSE4_CONVERSION=(pmov([sz]x(b[dqw]|dq|wd|wq)))|(PMOV([SZ]X(B[DQW]|DQ|WD|WQ)))
INS_SSE4_OTHER=((mpsadbw|phminposuw|ptest|pcmpeqq|packusdw)|(pcmp([ei]str[im]|gtq)))|((MPSADBW|PHMINPOSUW|PTEST|PCMPEQQ|PACKUSDW)|(PCMP([EI]STR[IM]|GTQ)))
INS_AVX_GENERAL=(v((test|permil|maskmov)p[ds]|zero(all|upper)|(perm2|insert|extract|broadcast)f128|broadcasts[ds]))|(V((TEST|PERMIL|MASKMOV)P[DS]|ZERO(ALL|UPPER)|(PERM2|INSERT|EXTRACT|BROADCAST)F128|BROADCASTS[DS]))
INS_AVX_AES=(vaes((dec|enc)(last)?|imc|keygenassist)|vpclmulqdq)|(VAES((DEC|ENC)(LAST)?|IMC|KEYGENASSIST)|VPCLMULQDQ)
INS_AVX_COMPARISON=(v((cmp[ps]|u?comis)[ds]|pcmp([ei]str[im]|(eq|gt)[bdqw])))|(V((CMP[PS]|U?COMIS)[DS]|PCMP([EI]STR[IM]|(EQ|GT)[BDQW])))
INS_AVX_CONVERSION=((v(cvt(dq2pd|dq2ps|pd2ps|ps2pd|sd2ss|si2sd|si2ss|ss2sd|t?(pd2dq|ps2dq|sd2si|ss2si))))|(vcvt(ph2ps|ps2ph)))|((V(CVT(DQ2PD|DQ2PS|PD2PS|PS2PD|SD2SS|SI2SD|SI2SS|SS2SD|T?(PD2DQ|PS2DQ|SD2SI|SS2SI))))|(VCVT(PH2PS|PS2PH)))
INS_AVX_LOGICAL=((v((andn?|x?or)p[ds]))|(vp(andn?|x?or)))|((V((ANDN?|X?OR)P[DS]))|(VP(ANDN?|X?OR)))
INS_AVX_MOV=((v(mov(([ahl]|msk|nt|u)p[ds]|(hl|lh)ps|s([ds]|[hl]dup)|q)))|(vpmov(mskb|[sz]x(b[dqw]|w[dq]|dq)))|(vmov(d(dup|qa|qu)?)))|((V(MOV(([AHL]|MSK|NT|U)P[DS]|(HL|LH)PS|S([DS]|[HL]DUP)|Q)))|(VPMOV(MSKB|[SZ]X(B[DQW]|W[DQ]|DQ)))|(VMOV(D(DUP|QA|QU)?)))
INS_AVX_ARITH=((v((add|div|mul|sub|max|min|round|sqrt)[ps][ds]|(addsub|dp)p[ds]|(rcp|rsqrt)[ps]s))|(vh((add|sub)p[ds])|vph((add|sub)([dw]|sw)|minposuw)))|((V((ADD|DIV|MUL|SUB|MAX|MIN|ROUND|SQRT)[PS][DS]|(ADDSUB|DP)P[DS]|(RCP|RSQRT)[PS]S))|(VH((ADD|SUB)P[DS])|VPH((ADD|SUB)([DW]|SW)|MINPOSUW)))
INS_AVX_PACKED=((v(pack[su]s(dw|wb)|punpck[hl](bw|dq|wd|qdq)|unpck[hl]p[ds]))|(vp(shuf([bd]|[hl]w))|vshufp[ds])|(vp((abs|sign|(max|min)[su])[bdw]|(add|sub)([bdqw]|u?s[bw])|avg[bw]|extr[bdqw]|madd(wd|ubsw)|mul(hu?w|hrsw|l[dw]|u?dq)|sadbw)))|((V(PACK[SU]S(DW|WB)|PUNPCK[HL](BW|DQ|WD|QDQ)|UNPCK[HL]P[DS]))|(VP(SHUF([BD]|[HL]W))|VSHUFP[DS])|(VP((ABS|SIGN|(MAX|MIN)[SU])[BDW]|(ADD|SUB)([BDQW]|U?S[BW])|AVG[BW]|EXTR[BDQW]|MADD(WD|UBSW)|MUL(HU?W|HRSW|L[DW]|U?DQ)|SADBW)))
INS_AVX_BLEND=((vpblend(vb|w))|(vblendv?p[ds]))|((VPBLEND(VB|W))|(VBLENDV?P[DS]))
INS_AVX_CACHE=(v(maskmovdqu|movntdqa?))|(V(MASKMOVDQU|MOVNTDQA?))
INS_AVX_FMA3=(vfn?m((add|sub)(132|213|231)[ps][ds])|vfm((addsub|subadd)(132|213|231)p[ds]))|(VFN?M((ADD|SUB)(132|213|231)[PS][DS])|VFM((ADDSUB|SUBADD)(132|213|231)P[DS]))
INS_AVX_OTHER=((vp(test|alignr))|(v((extract|insert)ps|lddqu|(ld|st)mxcsr|mpsadbw)))|((VP(TEST|ALIGNR))|(V((EXTRACT|INSERT)PS|LDDQU|(LD|ST)MXCSR|MPSADBW)))
INS_AVX2_SIMD=(v((extract|insert|perm2)i128|pmaskmov[dq]|perm([dsq]|p[sd])))|(V((EXTRACT|INSERT|PERM2)I128|PMASKMOV[DQ]|PERM([DSQ]|P[SD])))
INS_AVX2_BROADCAST=(vpbroadcast[qwbd]|vbroadcast(sd|ss)|vbroadcasti128)|(VPBROADCAST[QWBD]|VBROADCAST(SD|SS)|VBROADCASTI128)
INS_AVX2_BLEND=(vp(blendd|s[lr]lv[dq]|sravd))|(VP(BLENDD|S[LR]LV[DQ]|SRAVD))
INS_AVX2_GATHER=(vp?gather[dq][dq]|vgather([dq]|dq)p[ds])|(VP?GATHER[DQ][DQ]|VGATHER([DQ]|DQ)P[DS])
INS_AVX512_BLEND=(vblendm(pd|ps)|vpblendm[bdqw])|(VBLENDM(PD|PS)|VPBLENDM[BDQW])
INS_AVX512_BROADCAST=(vbroadcast[fi](32x[248]|64x[24])|vpbroadcastm(b2q|w2d))|(VBROADCAST[FI](32X[248]|64X[24])|VPBROADCASTM(B2Q|W2D))
INS_AVX512_MOV=(vmovdq(a(32|64)|u(8|16|32|64))|v(extract|insert)[fi](32x[48]|64x[24])|vshuf[fi](32x4|64x2))|(VMOVDQ(A(32|64)|U(8|16|32|64))|V(EXTRACT|INSERT)[FI](32X[48]|64X[24])|VSHUF[FI](32X4|64X2))
INS_AVX512_COMPRESS=(v(compress|expand)p[ds]|vp(compress|expand|conflict)[dq])|(V(COMPRESS|EXPAND)P[DS]|VP(COMPRESS|EXPAND|CONFLICT)[DQ])
INS_AVX512_CONV=(vcvt(t?p[sd]2(udq|u?qq)|(udq|u?qq)2p[ds]|t?s[ds]2usi|usi2s[ds]))|(VCVT(T?P[SD]2(UDQ|U?QQ)|(UDQ|U?QQ)2P[DS]|T?S[DS]2USI|USI2S[DS]))
INS_AVX512_MATH=((v(fixupimm|fpclass|get(exp|mant)|range|(rcp|rsqrt)(14|28)|reduce|rndscale|scalef)([ps][ds]))|(v(exp2p[ds]|(scatter|(gather|scatter)pf[01])[dq]p[ds])))|((V(FIXUPIMM|FPCLASS|GET(EXP|MANT)|RANGE|(RCP|RSQRT)(14|28)|REDUCE|RNDSCALE|SCALEF)([PS][DS]))|(V(EXP2P[DS]|(SCATTER|(GATHER|SCATTER)PF[01])[DQ]P[DS])))
INS_AVX512_LOGICAL=(vp(andn?|x?or)[dq])|(VP(ANDN?|X?OR)[DQ])
INS_AVX512_COMPARE=(vpcmpu?[dqw])|(VPCMPU?[DQW])
INS_AVX512_PACKED=((vp(absq|(lzcnt|ternlog)[dq]|madd52[lh]uq|(max|min)[su]q|mullq))|(vpmov(m2[bdqw]|[bdqw]2m|(u?s)?([qd][bw]|qd|wb)))|(vp(ro[rl]v?[dq]|scatter[dq][dq]))|(vptestn?m[bdqw]))|((VP(ABSQ|(LZCNT|TERNLOG)[DQ]|MADD52[LH]UQ|(MAX|MIN)[SU]Q|MULLQ))|(VPMOV(M2[BDQW]|[BDQW]2M|(U?S)?([QD][BW]|QD|WB)))|(VP(RO[RL]V?[DQ]|SCATTER[DQ][DQ]))|(VPTESTN?M[BDQW]))
INS_AVX512_PERM=(vperm([bdw]|[it]2([bdwq]|p[ds])))|(VPERM([BDW]|[IT]2([BDWQ]|P[DS])))
INS_AVX512_OTHER=(valign[dq]|vdbpsadbw|vpmultishiftqb|vpsrav[dqw])|(VALIGN[DQ]|VDBPSADBW|VPMULTISHIFTQB|VPSRAV[DQW])
OP_PREFIX=((rep(n?[ez])|rep)|lock|xacquire|xrelease)|((REP(N?[EZ])|REP)|LOCK|XACQUIRE|XRELEASE)
GENERAL_OP={INS_DATA_TRANS_MOV}|{INS_DATA_TRANS_XCHG}|{INS_DATA_TRANS_OTHER}|{INS_DECIMAL_ARITH}|{INS_BINARY_ARITH}|{INS_BINARY_LOGICAL}|{INS_BINARY_ROTATE}|{INS_BINARY_SET}|{INS_BINARY_OTHER}|{INS_CONTROL_TRANS}|{INS_STRING_DATA}|{INS_INPUT_OUTPUT}|{INS_FLAG_CONTROL}|{INS_SEG_REGS}|{INS_MISC_OTHER}|{INS_RNG_RAND}|{INS_BIT_MANIPULATION}
SYSTEM_OP={INS_SYSTEM}
VIRTUALIZATION_OP={INS_INTEL_VMX}|{INS_AMD_SVM}
X64_OP={INS_64_BIT}
FPU_OP={INS_FPU_DATA_TRANS}|{INS_FPU_BASIC_ARITH}|{INS_FPU_COMPARISON}|{INS_FPU_TRANSCEND}|{INS_FPU_LOAD}|{INS_FPU_CONTROL}|{INS_FPU_STATE}
MMX_OP={INS_MMX_DATA_TRANS}|{INS_MMX_CONVERSION}|{INS_MMX_ARITH}|{INS_MMX_COMPARISON}|{INS_MMX_LOGICAL}|{INS_MMX_ROTATE}|{INS_MMX_STATE}
SSE_OP={INS_SSE_DATA_TRANS}|{INS_SSE_ARITH}|{INS_SSE_COMPARISON}|{INS_SSE_LOGICAL}|{INS_SSE_OTHER}|{INS_SSE_CONVERSION}|{INS_SSE_STATE}|{INS_SSE_SIMD_INT}|{INS_SSE_CACHE_CTRL}|{INS_SSE_PREFETCH}
SSE2_OP={INS_SSE2_DATA_TRANS}|{INS_SSE2_ARITH}|{INS_SSE2_LOGICAL}|{INS_SSE2_COMPARISON}|{INS_SSE2_OTHER}|{INS_SSE2_CONVERSION}|{INS_SSE2_SIMD_INT}|{INS_SSE2_CACHE_CTRL}
SSE3_OP={INS_SSE3_GENERAL}|{INS_SSE3_ARITH}|{INS_SSE3_OTHER}
SSE4_OP={INS_SSE4_ARITH}|{INS_SSE4_DATA_TRANS}|{INS_SSE4_BLEND}|{INS_SSE4_PACKED_INT}|{INS_SSE4_PACKED_FP}|{INS_SSE4_INS_EXT}|{INS_SSE4_CONVERSION}|{INS_SSE4_OTHER}
AVX_OP={INS_AVX_GENERAL}|{INS_AVX_AES}|{INS_AVX_COMPARISON}|{INS_AVX_CONVERSION}|{INS_AVX_LOGICAL}|{INS_AVX_MOV}|{INS_AVX_ARITH}|{INS_AVX_PACKED}|{INS_AVX_BLEND}|{INS_AVX_CACHE}|{INS_AVX_FMA3}|{INS_AVX_OTHER}
AVX2_OP={INS_AVX2_SIMD}|{INS_AVX2_BROADCAST}|{INS_AVX2_BLEND}|{INS_AVX2_GATHER}
AVX512_OP={INS_AVX512_BLEND}|{INS_AVX512_BROADCAST}|{INS_AVX512_MOV}|{INS_AVX512_COMPRESS}|{INS_AVX512_CONV}|{INS_AVX512_MATH}|{INS_AVX512_LOGICAL}|{INS_AVX512_COMPARE}|{INS_AVX512_PACKED}|{INS_AVX512_PERM}|{INS_AVX512_OTHER}
REGISTER=(%)?((([c-gs]s):)?(([abcd][hl])|([er]?[abcd]x)|([er]?[sb]p)|([er]?[sd]i|dil|sil|bpl|spl)|([er]?ip)|(r(8|9|1[0-5])[bdlw]?)|([er]?flags)|(cr[0-8])|(d[rb][0-367]|dr([89]|1[0-5]))|(tr[3-7])|(([gil]dt)r?|tr)|(bnd([0-3]|cfg[su]|status))|(efer|tpr|syscfg)|((mm|st|fpr)[0-7])|([xy]mm([0-9]|1[0-5])|mxcsr)|(zmm([12]?[0-9]|30|31))))|((([C-GS]S):)?(([ABCD][HL])|([ER]?[ABCD]X)|([ER]?[SB]P)|([ER]?[SD]I|DIL|SIL|BPL|SPL)|([ER]?IP)|(R(8|9|1[0-5])[BDLW]?)|([ER]?FLAGS)|(CR[0-8])|(D[RB][0-367]|DR([89]|1[0-5]))|(TR[3-7])|(([GIL]DT)R?|TR)|(BND([0-3]|CFG[SU]|STATUS))|(EFER|TPR|SYSCFG)|((MM|ST|FPR)[0-7])|([XY]MM([0-9]|1[0-5])|MXCSR)|(ZMM([12]?[0-9]|30|31))))
SEGMENT_REGISTER=([c-gs]s)|([C-GS]S)
SIZE_TYPE=[sS][hH][oO][rR][tT]|[lL][oO][nN][gG]|[nN][eE][aA][rR]|[fF][aA][rR]|(((([dDqQoOtTyYzZ]|[xX][mM][mM])?[wW][oO][rR][dD])|[bB][yY][tT][eE])(([ \t\x0B\f]+)[pP][tT][rR])?)
ID=([a-zA-Z_?]+[a-zA-Z0-9_$.#@~?]*)
LBL_DEF=([a-zA-Z$._?#@]+[a-zA-Z0-9$._~]*):
LBL=([a-zA-Z$._?#@]+[a-zA-Z0-9$._~]*)
BINARY=(0[bB][0-1]+|0[yY][0-1]+|[0-1][0-1]*[bB]|[0-1][0-1]*[yY])
HEXADECIMAL=(0[xX][0-9a-fA-F]+|0[hH][0-9a-fA-F]+|\$[0-9]+[0-9a-fA-F]*|[0-9]+[0-9a-fA-F]*[hH])
ZEROES=[0]+
//DECIMAL=((([1-9][0-9]*\.?[0-9]*)|(\.[0-9]+))([Ee][+-]?[0-9]+)?|0[dD][0-9]+|[0-9]+)
DECIMAL=((0[dD])?[0-9]+)
FLOAT_DECIMAL=((([0-9]*\.([^A-Za-z])[0-9]*)|(\.[0-9]+))([Ee][+-]?[0-9]+)?)
CHARACTER=(`([^`\\]|\\.)`|'([^'\\]|\\.)')
STRING=(`([^`\\]|\\.)*`|'([^'\\]|\\.)*'|\"([^\"\\]|\\.)*\")

%%

<YYINITIAL> {
{NL}                        { return NL; }
{WHITE_SPACE}               { return WHITE_SPACE; }

";"|{LINE_COMMENT}          { return COMMENT; }


":"                         { return COLON; }
"["                         { return SQUARE_L; }
"]"                         { return SQUARE_R; }
"("                         { return ROUND_L; }
")"                         { return ROUND_R; }
","                         { return SEPARATOR; }
"."                         { return DOT; }
"?"                         { return QUESTION; }
"="                         { return EQUAL; }
"=="                        { return EQUALEQUAL; }
"!="                        { return NOTEQUAL; }
">"                         { return GREATERTHAN; }
"<"                         { return LESSTHAN; }
">="                        { return GREATERTHANOREQUAL; }
"<="                        { return LESSTHANOREQUAL; }
"+"                         { return PLUS; }
"-"                         { return MINUS; }
"*"                         { return TIMES; }
"/"                         { return DIVIDE; }
"<<"                        { return BITSHIFT_L; }
">>"                        { return BITSHIFT_R; }
"&"                         { return BITWISE_AND; }
"|"                         { return BITWISE_OR; }
"^"                         { return BITWISE_XOR; }
"~"                         { return BITWISE_NOT; }
"&&"                        { return LOGICAL_AND; }
"||"                        { return LOGICAL_OR; }
"^^"                        { return LOGICAL_XOR; }
"$"                         { return DOLLARSIGN; }
"$$"                        { return DOLLARSIGN2; }
"%"                         { return PERCENT; }
"%%"                        { return PERCENT2; }
"%+"                        { return TOKEN_CONCAT; }

{EQU}                       { return EQU; }
{SEGMENT_ADDR_L}            { return SEGMENT_ADDR_L; }
{INCLUDE_TAG}               { return INCLUDE_TAG; }
{DEFINE_TAG}                { return DEFINE_TAG; }
{ASSIGN_TAG}                { return ASSIGN_TAG; }
{MACRO_TAG}                 { return MACRO_TAG; }
{MACRO_END_TAG}             { return MACRO_END_TAG; }
{MACRO_PARAM_REF}           { return MACRO_PARAM_REF; }
{MACRO_PARAM_COUNT}         { return MACRO_PARAM_COUNT; }
{MACRO_NOLIST_QUAL}         { return MACRO_NOLIST_QUAL; }
{MACRO_VAR_REF}             { return MACRO_VAR_REF; }
{MACRO_PARAM_LBL_DEF}       { return MACRO_PARAM_LBL_DEF; }
{IFMACRO_TAG}               { return IFMACRO_TAG; }
{IFCTX_TAG}                 { return IFCTX_TAG; }
{IFNUM_TAG}                 { return IFNUM_TAG; }
{IFSTR_TAG}                 { return IFSTR_TAG; }
{IFIDN_TAG}                 { return IFIDN_TAG; }
{IFDEF_TAG}                 { return IFDEF_TAG; }
{IFID_TAG}                  { return IFID_TAG; }
{IF_TAG}                    { return IF_TAG; }
{ELIF_TAG}                  { return ELIF_TAG; }
{ELSE_TAG}                  { return ELSE_TAG; }
{ENDIF_TAG}                 { return ENDIF_TAG; }
{STRLEN_TAG}                { return STRLEN_TAG; }
{ERROR_TAG}                 { return ERROR_TAG; }
{SECTION}                   { return SECTION; }
{SEGMENT}                   { return SEGMENT; }
{MAP_OPTIONS}               { return MAP_OPTIONS; }
{MAP_FILE}                  { return MAP_FILE; }
{STRUC_TAG}                 { return STRUC_TAG; }
{ENDSTRUC_TAG}              { return ENDSTRUC_TAG; }
{ISTRUC_TAG}                { return ISTRUC_TAG; }
{IEND_TAG}                  { return IEND_TAG; }
{AT_TAG}                    { return AT_TAG; }
{STRUCT_FIELD}              { return STRUCT_FIELD; }
{DIRECTIVE_OP}              { return DIRECTIVE_OP; }
{END_DIRECTIVE_OP}          { return END_DIRECTIVE_OP; }
{PREPROCESSOR_OP}           { return PREPROCESSOR_OP; }
{DATA_OP}                   { return DATA_OP; }
{OP_PREFIX}                 { return OP_PREFIX; }
{GENERAL_OP}                { return GENERAL_OP; }
{SYSTEM_OP}                 { return SYSTEM_OP; }
{VIRTUALIZATION_OP}         { return VIRTUALIZATION_OP; }
{X64_OP}                    { return X64_OP; }
{FPU_OP}                    { return FPU_OP; }
{MMX_OP}                    { return MMX_OP; }
{SSE_OP}                    { return SSE_OP; }
{SSE2_OP}                   { return SSE2_OP; }
{SSE3_OP}                   { return SSE3_OP; }
{SSE4_OP}                   { return SSE4_OP; }
{AVX_OP}                    { return AVX_OP; }
{AVX2_OP}                   { return AVX2_OP; }
{AVX512_OP}                 { return AVX512_OP; }
{REGISTER}                  { return REGISTER; }
{SEGMENT_REGISTER}          { return SEGMENT_REGISTER; }
{SIZE_TYPE}                 { return SIZE_TYPE; }
{ID}                        { return ID; }
{LBL_DEF}                   { return LBL_DEF; }
{LBL}                       { return LBL; }
{BINARY}                    { return BINARY; }
{HEXADECIMAL}               { return HEXADECIMAL; }
{ZEROES}                    { return ZEROES; }
{DECIMAL}                   { return DECIMAL; }
{FLOAT_DECIMAL}             { return FLOAT_DECIMAL; }
{CHARACTER}                 { return CHARACTER; }
{STRING}                    { return STRING; }

}

{NL}+                       { yybegin(YYINITIAL); return NL; }

. { return BAD_CHARACTER; }
