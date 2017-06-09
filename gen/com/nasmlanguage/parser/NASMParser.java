// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.nasmlanguage.psi.NASMTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class NASMParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == ADDRESS) {
      r = Address(b, 0);
    }
    else if (t == CODE_SECTION) {
      r = CodeSection(b, 0);
    }
    else if (t == DATA) {
      r = Data(b, 0);
    }
    else if (t == DATA_SECTION) {
      r = DataSection(b, 0);
    }
    else if (t == DATA_VALUE) {
      r = DataValue(b, 0);
    }
    else if (t == DIRECTIVE) {
      r = Directive(b, 0);
    }
    else if (t == DIRECTIVE_ARG) {
      r = DirectiveArg(b, 0);
    }
    else if (t == INSTRUCTION) {
      r = Instruction(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return NASMFile(b, l + 1);
  }

  /* ********************************************************** */
  // SIZE? SQUARE_L AddressInternal SQUARE_R
  public static boolean Address(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Address")) return false;
    if (!nextTokenIs(b, "<address>", SIZE, SQUARE_L)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ADDRESS, "<address>");
    r = Address_0(b, l + 1);
    r = r && consumeToken(b, SQUARE_L);
    r = r && AddressInternal(b, l + 1);
    r = r && consumeToken(b, SQUARE_R);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SIZE?
  private static boolean Address_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Address_0")) return false;
    consumeToken(b, SIZE);
    return true;
  }

  /* ********************************************************** */
  // (REGISTER (PLUS (NUMBER TIMES)?(NUMBER|REGISTER))? ((PLUS|MINUS|TIMES) NUMBER)?) | LABEL
  static boolean AddressInternal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal")) return false;
    if (!nextTokenIs(b, "", LABEL, REGISTER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AddressInternal_0(b, l + 1);
    if (!r) r = consumeToken(b, LABEL);
    exit_section_(b, m, null, r);
    return r;
  }

  // REGISTER (PLUS (NUMBER TIMES)?(NUMBER|REGISTER))? ((PLUS|MINUS|TIMES) NUMBER)?
  private static boolean AddressInternal_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, REGISTER);
    r = r && AddressInternal_0_1(b, l + 1);
    r = r && AddressInternal_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (PLUS (NUMBER TIMES)?(NUMBER|REGISTER))?
  private static boolean AddressInternal_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_0_1")) return false;
    AddressInternal_0_1_0(b, l + 1);
    return true;
  }

  // PLUS (NUMBER TIMES)?(NUMBER|REGISTER)
  private static boolean AddressInternal_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUS);
    r = r && AddressInternal_0_1_0_1(b, l + 1);
    r = r && AddressInternal_0_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (NUMBER TIMES)?
  private static boolean AddressInternal_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_0_1_0_1")) return false;
    AddressInternal_0_1_0_1_0(b, l + 1);
    return true;
  }

  // NUMBER TIMES
  private static boolean AddressInternal_0_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_0_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, NUMBER, TIMES);
    exit_section_(b, m, null, r);
    return r;
  }

  // NUMBER|REGISTER
  private static boolean AddressInternal_0_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_0_1_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    if (!r) r = consumeToken(b, REGISTER);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((PLUS|MINUS|TIMES) NUMBER)?
  private static boolean AddressInternal_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_0_2")) return false;
    AddressInternal_0_2_0(b, l + 1);
    return true;
  }

  // (PLUS|MINUS|TIMES) NUMBER
  private static boolean AddressInternal_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AddressInternal_0_2_0_0(b, l + 1);
    r = r && consumeToken(b, NUMBER);
    exit_section_(b, m, null, r);
    return r;
  }

  // PLUS|MINUS|TIMES
  private static boolean AddressInternal_0_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_0_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, TIMES);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // SECTION_TAG CODE_SECTION_NAME (CRLF)* (Instruction | Directive)*
  public static boolean CodeSection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CodeSection")) return false;
    if (!nextTokenIs(b, SECTION_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SECTION_TAG, CODE_SECTION_NAME);
    r = r && CodeSection_2(b, l + 1);
    r = r && CodeSection_3(b, l + 1);
    exit_section_(b, m, CODE_SECTION, r);
    return r;
  }

  // (CRLF)*
  private static boolean CodeSection_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CodeSection_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "CodeSection_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (Instruction | Directive)*
  private static boolean CodeSection_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CodeSection_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!CodeSection_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CodeSection_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // Instruction | Directive
  private static boolean CodeSection_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CodeSection_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction(b, l + 1);
    if (!r) r = Directive(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (LABEL_DEF? DATA_OP DataValue?)|((LABEL EQU) (NUMBER)?)
  public static boolean Data(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Data")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DATA, "<data>");
    r = Data_0(b, l + 1);
    if (!r) r = Data_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LABEL_DEF? DATA_OP DataValue?
  private static boolean Data_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Data_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Data_0_0(b, l + 1);
    r = r && consumeToken(b, DATA_OP);
    r = r && Data_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LABEL_DEF?
  private static boolean Data_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Data_0_0")) return false;
    consumeToken(b, LABEL_DEF);
    return true;
  }

  // DataValue?
  private static boolean Data_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Data_0_2")) return false;
    DataValue(b, l + 1);
    return true;
  }

  // (LABEL EQU) (NUMBER)?
  private static boolean Data_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Data_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Data_1_0(b, l + 1);
    r = r && Data_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LABEL EQU
  private static boolean Data_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Data_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LABEL, EQU);
    exit_section_(b, m, null, r);
    return r;
  }

  // (NUMBER)?
  private static boolean Data_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Data_1_1")) return false;
    consumeToken(b, NUMBER);
    return true;
  }

  /* ********************************************************** */
  // SECTION_TAG DATA_SECTION_NAME (CRLF)* (Data | Directive)*
  public static boolean DataSection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataSection")) return false;
    if (!nextTokenIs(b, SECTION_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SECTION_TAG, DATA_SECTION_NAME);
    r = r && DataSection_2(b, l + 1);
    r = r && DataSection_3(b, l + 1);
    exit_section_(b, m, DATA_SECTION, r);
    return r;
  }

  // (CRLF)*
  private static boolean DataSection_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataSection_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "DataSection_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (Data | Directive)*
  private static boolean DataSection_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataSection_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!DataSection_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "DataSection_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // Data | Directive
  private static boolean DataSection_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataSection_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Data(b, l + 1);
    if (!r) r = Directive(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ((NUMBER SEPARATOR|NUMBER)|(STRING SEPARATOR|STRING))*
  public static boolean DataValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue")) return false;
    Marker m = enter_section_(b, l, _NONE_, DATA_VALUE, "<data value>");
    int c = current_position_(b);
    while (true) {
      if (!DataValue_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "DataValue", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // (NUMBER SEPARATOR|NUMBER)|(STRING SEPARATOR|STRING)
  private static boolean DataValue_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DataValue_0_0(b, l + 1);
    if (!r) r = DataValue_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NUMBER SEPARATOR|NUMBER
  private static boolean DataValue_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parseTokens(b, 0, NUMBER, SEPARATOR);
    if (!r) r = consumeToken(b, NUMBER);
    exit_section_(b, m, null, r);
    return r;
  }

  // STRING SEPARATOR|STRING
  private static boolean DataValue_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parseTokens(b, 0, STRING, SEPARATOR);
    if (!r) r = consumeToken(b, STRING);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DIRECTIVE_OP (DirectiveArg)*
  public static boolean Directive(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Directive")) return false;
    if (!nextTokenIs(b, DIRECTIVE_OP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DIRECTIVE_OP);
    r = r && Directive_1(b, l + 1);
    exit_section_(b, m, DIRECTIVE, r);
    return r;
  }

  // (DirectiveArg)*
  private static boolean Directive_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Directive_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Directive_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Directive_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (DirectiveArg)
  private static boolean Directive_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Directive_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DirectiveArg(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NUMBER | Address | LABEL
  public static boolean DirectiveArg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveArg")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DIRECTIVE_ARG, "<directive arg>");
    r = consumeToken(b, NUMBER);
    if (!r) r = Address(b, l + 1);
    if (!r) r = consumeToken(b, LABEL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // COMMENT | CRLF | COMMENT CRLF | Section | Directive | SQUARE_L Section SQUARE_R | SQUARE_L Directive SQUARE_R
  static boolean Element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Element")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, CRLF);
    if (!r) r = parseTokens(b, 0, COMMENT, CRLF);
    if (!r) r = Section(b, l + 1);
    if (!r) r = Directive(b, l + 1);
    if (!r) r = Element_5(b, l + 1);
    if (!r) r = Element_6(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SQUARE_L Section SQUARE_R
  private static boolean Element_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Element_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SQUARE_L);
    r = r && Section(b, l + 1);
    r = r && consumeToken(b, SQUARE_R);
    exit_section_(b, m, null, r);
    return r;
  }

  // SQUARE_L Directive SQUARE_R
  private static boolean Element_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Element_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SQUARE_L);
    r = r && Directive(b, l + 1);
    r = r && consumeToken(b, SQUARE_R);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (LABEL_DEF? MnemonicOperation ((MnemonicOperationArg SEPARATOR)* MnemonicOperationArg)? COMMENT?) | (LABEL_DEF [COMMENT])
  public static boolean Instruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction")) return false;
    if (!nextTokenIs(b, "<instruction>", LABEL_DEF, MNEMONIC_OP)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INSTRUCTION, "<instruction>");
    r = Instruction_0(b, l + 1);
    if (!r) r = Instruction_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LABEL_DEF? MnemonicOperation ((MnemonicOperationArg SEPARATOR)* MnemonicOperationArg)? COMMENT?
  private static boolean Instruction_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_0_0(b, l + 1);
    r = r && MnemonicOperation(b, l + 1);
    r = r && Instruction_0_2(b, l + 1);
    r = r && Instruction_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LABEL_DEF?
  private static boolean Instruction_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_0")) return false;
    consumeToken(b, LABEL_DEF);
    return true;
  }

  // ((MnemonicOperationArg SEPARATOR)* MnemonicOperationArg)?
  private static boolean Instruction_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_2")) return false;
    Instruction_0_2_0(b, l + 1);
    return true;
  }

  // (MnemonicOperationArg SEPARATOR)* MnemonicOperationArg
  private static boolean Instruction_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_0_2_0_0(b, l + 1);
    r = r && MnemonicOperationArg(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MnemonicOperationArg SEPARATOR)*
  private static boolean Instruction_0_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_2_0_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Instruction_0_2_0_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Instruction_0_2_0_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // MnemonicOperationArg SEPARATOR
  private static boolean Instruction_0_2_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_2_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MnemonicOperationArg(b, l + 1);
    r = r && consumeToken(b, SEPARATOR);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMENT?
  private static boolean Instruction_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_3")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  // LABEL_DEF [COMMENT]
  private static boolean Instruction_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LABEL_DEF);
    r = r && Instruction_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [COMMENT]
  private static boolean Instruction_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_1_1")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // MNEMONIC_OP
  static boolean MnemonicOperation(PsiBuilder b, int l) {
    return consumeToken(b, MNEMONIC_OP);
  }

  /* ********************************************************** */
  // REGISTER | NUMBER | Address | LABEL
  static boolean MnemonicOperationArg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MnemonicOperationArg")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, REGISTER);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = Address(b, l + 1);
    if (!r) r = consumeToken(b, LABEL);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Element*
  static boolean NASMFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NASMFile")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "NASMFile", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // DataSection | CodeSection
  static boolean Section(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Section")) return false;
    if (!nextTokenIs(b, SECTION_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DataSection(b, l + 1);
    if (!r) r = CodeSection(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

}
