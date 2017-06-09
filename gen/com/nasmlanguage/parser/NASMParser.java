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
      r = address(b, 0);
    }
    else if (t == CODE_SECTION) {
      r = code_section(b, 0);
    }
    else if (t == COMMAND) {
      r = command(b, 0);
    }
    else if (t == DATA) {
      r = data(b, 0);
    }
    else if (t == DATA_SECTION) {
      r = data_section(b, 0);
    }
    else if (t == DATA_VALUE) {
      r = data_value(b, 0);
    }
    else if (t == DIRECTIVE) {
      r = directive(b, 0);
    }
    else if (t == DIRECTIVE_ARG) {
      r = directive_arg(b, 0);
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
  // item*
  static boolean NASMFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NASMFile")) return false;
    int c = current_position_(b);
    while (true) {
      if (!item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "NASMFile", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // SIZE? SQUARE_L address_internal SQUARE_R
  public static boolean address(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "address")) return false;
    if (!nextTokenIs(b, "<address>", SIZE, SQUARE_L)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ADDRESS, "<address>");
    r = address_0(b, l + 1);
    r = r && consumeToken(b, SQUARE_L);
    r = r && address_internal(b, l + 1);
    r = r && consumeToken(b, SQUARE_R);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SIZE?
  private static boolean address_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "address_0")) return false;
    consumeToken(b, SIZE);
    return true;
  }

  /* ********************************************************** */
  // (REGISTER (PLUS (NUMBER TIMES)?(NUMBER|REGISTER))? ((PLUS|MINUS|TIMES) NUMBER)?) | LABEL
  static boolean address_internal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "address_internal")) return false;
    if (!nextTokenIs(b, "", LABEL, REGISTER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = address_internal_0(b, l + 1);
    if (!r) r = consumeToken(b, LABEL);
    exit_section_(b, m, null, r);
    return r;
  }

  // REGISTER (PLUS (NUMBER TIMES)?(NUMBER|REGISTER))? ((PLUS|MINUS|TIMES) NUMBER)?
  private static boolean address_internal_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "address_internal_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, REGISTER);
    r = r && address_internal_0_1(b, l + 1);
    r = r && address_internal_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (PLUS (NUMBER TIMES)?(NUMBER|REGISTER))?
  private static boolean address_internal_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "address_internal_0_1")) return false;
    address_internal_0_1_0(b, l + 1);
    return true;
  }

  // PLUS (NUMBER TIMES)?(NUMBER|REGISTER)
  private static boolean address_internal_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "address_internal_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUS);
    r = r && address_internal_0_1_0_1(b, l + 1);
    r = r && address_internal_0_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (NUMBER TIMES)?
  private static boolean address_internal_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "address_internal_0_1_0_1")) return false;
    address_internal_0_1_0_1_0(b, l + 1);
    return true;
  }

  // NUMBER TIMES
  private static boolean address_internal_0_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "address_internal_0_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, NUMBER, TIMES);
    exit_section_(b, m, null, r);
    return r;
  }

  // NUMBER|REGISTER
  private static boolean address_internal_0_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "address_internal_0_1_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NUMBER);
    if (!r) r = consumeToken(b, REGISTER);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((PLUS|MINUS|TIMES) NUMBER)?
  private static boolean address_internal_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "address_internal_0_2")) return false;
    address_internal_0_2_0(b, l + 1);
    return true;
  }

  // (PLUS|MINUS|TIMES) NUMBER
  private static boolean address_internal_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "address_internal_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = address_internal_0_2_0_0(b, l + 1);
    r = r && consumeToken(b, NUMBER);
    exit_section_(b, m, null, r);
    return r;
  }

  // PLUS|MINUS|TIMES
  private static boolean address_internal_0_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "address_internal_0_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, TIMES);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // SECTION_TAG CODE_SECTION_NAME (CRLF)* (command | directive)*
  public static boolean code_section(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "code_section")) return false;
    if (!nextTokenIs(b, SECTION_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SECTION_TAG, CODE_SECTION_NAME);
    r = r && code_section_2(b, l + 1);
    r = r && code_section_3(b, l + 1);
    exit_section_(b, m, CODE_SECTION, r);
    return r;
  }

  // (CRLF)*
  private static boolean code_section_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "code_section_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "code_section_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (command | directive)*
  private static boolean code_section_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "code_section_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!code_section_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "code_section_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // command | directive
  private static boolean code_section_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "code_section_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = command(b, l + 1);
    if (!r) r = directive(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (LABEL_DEF? op ((op_arg SEPARATOR)*op_arg)? [COMMENT]) | (LABEL_DEF [COMMENT])
  public static boolean command(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command")) return false;
    if (!nextTokenIs(b, "<command>", LABEL_DEF, MNEMONIC_OP)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMMAND, "<command>");
    r = command_0(b, l + 1);
    if (!r) r = command_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LABEL_DEF? op ((op_arg SEPARATOR)*op_arg)? [COMMENT]
  private static boolean command_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = command_0_0(b, l + 1);
    r = r && op(b, l + 1);
    r = r && command_0_2(b, l + 1);
    r = r && command_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LABEL_DEF?
  private static boolean command_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_0_0")) return false;
    consumeToken(b, LABEL_DEF);
    return true;
  }

  // ((op_arg SEPARATOR)*op_arg)?
  private static boolean command_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_0_2")) return false;
    command_0_2_0(b, l + 1);
    return true;
  }

  // (op_arg SEPARATOR)*op_arg
  private static boolean command_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = command_0_2_0_0(b, l + 1);
    r = r && op_arg(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (op_arg SEPARATOR)*
  private static boolean command_0_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_0_2_0_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!command_0_2_0_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "command_0_2_0_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // op_arg SEPARATOR
  private static boolean command_0_2_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_0_2_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = op_arg(b, l + 1);
    r = r && consumeToken(b, SEPARATOR);
    exit_section_(b, m, null, r);
    return r;
  }

  // [COMMENT]
  private static boolean command_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_0_3")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  // LABEL_DEF [COMMENT]
  private static boolean command_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LABEL_DEF);
    r = r && command_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [COMMENT]
  private static boolean command_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_1_1")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // (LABEL_DEF? DATA_OP data_value?)|((LABEL EQU) (NUMBER)?)
  public static boolean data(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DATA, "<data>");
    r = data_0(b, l + 1);
    if (!r) r = data_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LABEL_DEF? DATA_OP data_value?
  private static boolean data_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = data_0_0(b, l + 1);
    r = r && consumeToken(b, DATA_OP);
    r = r && data_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LABEL_DEF?
  private static boolean data_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_0_0")) return false;
    consumeToken(b, LABEL_DEF);
    return true;
  }

  // data_value?
  private static boolean data_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_0_2")) return false;
    data_value(b, l + 1);
    return true;
  }

  // (LABEL EQU) (NUMBER)?
  private static boolean data_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = data_1_0(b, l + 1);
    r = r && data_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LABEL EQU
  private static boolean data_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LABEL, EQU);
    exit_section_(b, m, null, r);
    return r;
  }

  // (NUMBER)?
  private static boolean data_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_1_1")) return false;
    consumeToken(b, NUMBER);
    return true;
  }

  /* ********************************************************** */
  // SECTION_TAG DATA_SECTION_NAME (CRLF)* (data | directive)*
  public static boolean data_section(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_section")) return false;
    if (!nextTokenIs(b, SECTION_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SECTION_TAG, DATA_SECTION_NAME);
    r = r && data_section_2(b, l + 1);
    r = r && data_section_3(b, l + 1);
    exit_section_(b, m, DATA_SECTION, r);
    return r;
  }

  // (CRLF)*
  private static boolean data_section_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_section_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "data_section_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (data | directive)*
  private static boolean data_section_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_section_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!data_section_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "data_section_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // data | directive
  private static boolean data_section_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_section_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = data(b, l + 1);
    if (!r) r = directive(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ((NUMBER SEPARATOR|NUMBER)|(STRING SEPARATOR|STRING))*
  public static boolean data_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_value")) return false;
    Marker m = enter_section_(b, l, _NONE_, DATA_VALUE, "<data value>");
    int c = current_position_(b);
    while (true) {
      if (!data_value_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "data_value", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // (NUMBER SEPARATOR|NUMBER)|(STRING SEPARATOR|STRING)
  private static boolean data_value_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_value_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = data_value_0_0(b, l + 1);
    if (!r) r = data_value_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NUMBER SEPARATOR|NUMBER
  private static boolean data_value_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_value_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parseTokens(b, 0, NUMBER, SEPARATOR);
    if (!r) r = consumeToken(b, NUMBER);
    exit_section_(b, m, null, r);
    return r;
  }

  // STRING SEPARATOR|STRING
  private static boolean data_value_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "data_value_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parseTokens(b, 0, STRING, SEPARATOR);
    if (!r) r = consumeToken(b, STRING);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DIRECTIVE_OP (directive_arg)*
  public static boolean directive(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "directive")) return false;
    if (!nextTokenIs(b, DIRECTIVE_OP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DIRECTIVE_OP);
    r = r && directive_1(b, l + 1);
    exit_section_(b, m, DIRECTIVE, r);
    return r;
  }

  // (directive_arg)*
  private static boolean directive_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "directive_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!directive_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "directive_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (directive_arg)
  private static boolean directive_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "directive_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = directive_arg(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NUMBER | address | LABEL
  public static boolean directive_arg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "directive_arg")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DIRECTIVE_ARG, "<directive arg>");
    r = consumeToken(b, NUMBER);
    if (!r) r = address(b, l + 1);
    if (!r) r = consumeToken(b, LABEL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // COMMENT | CRLF | (COMMENT CRLF) | section | directive | SQUARE_L section SQUARE_R | SQUARE_L directive SQUARE_R
  static boolean item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, CRLF);
    if (!r) r = item_2(b, l + 1);
    if (!r) r = section(b, l + 1);
    if (!r) r = directive(b, l + 1);
    if (!r) r = item_5(b, l + 1);
    if (!r) r = item_6(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMENT CRLF
  private static boolean item_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMENT, CRLF);
    exit_section_(b, m, null, r);
    return r;
  }

  // SQUARE_L section SQUARE_R
  private static boolean item_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SQUARE_L);
    r = r && section(b, l + 1);
    r = r && consumeToken(b, SQUARE_R);
    exit_section_(b, m, null, r);
    return r;
  }

  // SQUARE_L directive SQUARE_R
  private static boolean item_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SQUARE_L);
    r = r && directive(b, l + 1);
    r = r && consumeToken(b, SQUARE_R);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // MNEMONIC_OP
  static boolean op(PsiBuilder b, int l) {
    return consumeToken(b, MNEMONIC_OP);
  }

  /* ********************************************************** */
  // REGISTER | NUMBER | address | LABEL
  static boolean op_arg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "op_arg")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, REGISTER);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = address(b, l + 1);
    if (!r) r = consumeToken(b, LABEL);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // data_section | code_section
  static boolean section(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "section")) return false;
    if (!nextTokenIs(b, SECTION_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = data_section(b, l + 1);
    if (!r) r = code_section(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

}
