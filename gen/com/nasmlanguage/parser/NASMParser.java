// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.nasmlanguage.psi.NASMTypes.*;
import static com.nasmlanguage.parser.NASMParserUtil.*;
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
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return NASMFile(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(ADDRESS, BITWISE_AND_EXPR, BITWISE_NOT_EXPR, BITWISE_OR_EXPR,
      BITWISE_XOR_EXPR, BIT_SHIFT_L_EXPR, BIT_SHIFT_R_EXPR, DIV_EXPR,
      END_DIRECTIVE, EXPR, IDENTIFIER, LABEL_IDENTIFIER,
      LOGICAL_AND_EXPR, LOGICAL_OR_EXPR, LOGICAL_XOR_EXPR, MACRO_CALL,
      MACRO_DEF_CALL, MACRO_PARAM_COUNTER, MACRO_PARAM_REFERENCE, MACRO_VAR_REFERENCE,
      MINUS_EXPR, MNEMONIC, MODULUS_EXPR, MUL_EXPR,
      NUMERIC_LITERAL, PARENTHESIS_EXPR, PLUS_EXPR, REG,
      SEG, SEGMENT_ADDRESS, STR, STRUCTURE_FIELD,
      TOKEN_CONCAT_EXPR),
  };

  /* ********************************************************** */
  // SQUARE_L MINUS? Expr SQUARE_R
  static boolean AddressInternal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal")) return false;
    if (!nextTokenIs(b, SQUARE_L)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, SQUARE_L);
    p = r; // pin = 1
    r = r && report_error_(b, AddressInternal_1(b, l + 1));
    r = p && report_error_(b, Expr(b, l + 1, -1)) && r;
    r = p && consumeToken(b, SQUARE_R) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // MINUS?
  private static boolean AddressInternal_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_1")) return false;
    consumeToken(b, MINUS);
    return true;
  }

  /* ********************************************************** */
  // ASSIGN_TAG Identifier Expr
  public static boolean Assign(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Assign")) return false;
    if (!nextTokenIs(b, ASSIGN_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ASSIGN_TAG);
    r = r && Identifier(b, l + 1);
    r = r && Expr(b, l + 1, -1);
    exit_section_(b, m, ASSIGN, r);
    return r;
  }

  /* ********************************************************** */
  // Expr (EQUALEQUAL|NOTEQUAL|GREATERTHAN|LESSTHAN|GREATERTHANOREQUAL|LESSTHANOREQUAL|SEPARATOR) Expr
  static boolean Condition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Condition")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expr(b, l + 1, -1);
    r = r && Condition_1(b, l + 1);
    r = r && Expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // EQUALEQUAL|NOTEQUAL|GREATERTHAN|LESSTHAN|GREATERTHANOREQUAL|LESSTHANOREQUAL|SEPARATOR
  private static boolean Condition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Condition_1")) return false;
    boolean r;
    r = consumeToken(b, EQUALEQUAL);
    if (!r) r = consumeToken(b, NOTEQUAL);
    if (!r) r = consumeToken(b, GREATERTHAN);
    if (!r) r = consumeToken(b, LESSTHAN);
    if (!r) r = consumeToken(b, GREATERTHANOREQUAL);
    if (!r) r = consumeToken(b, LESSTHANOREQUAL);
    if (!r) r = consumeToken(b, SEPARATOR);
    return r;
  }

  /* ********************************************************** */
  // (IfConditionTag (Condition|MacroCondition|Expr) NL* Element* (((ELIF_TAG (Condition|MacroCondition|Expr))|ELSE_TAG) NL* Element*)*) ENDIF_TAG NL*
  public static boolean Conditional(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONDITIONAL, "<conditional>");
    r = Conditional_0(b, l + 1);
    r = r && consumeToken(b, ENDIF_TAG);
    r = r && Conditional_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // IfConditionTag (Condition|MacroCondition|Expr) NL* Element* (((ELIF_TAG (Condition|MacroCondition|Expr))|ELSE_TAG) NL* Element*)*
  private static boolean Conditional_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IfConditionTag(b, l + 1);
    r = r && Conditional_0_1(b, l + 1);
    r = r && Conditional_0_2(b, l + 1);
    r = r && Conditional_0_3(b, l + 1);
    r = r && Conditional_0_4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Condition|MacroCondition|Expr
  private static boolean Conditional_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_1")) return false;
    boolean r;
    r = Condition(b, l + 1);
    if (!r) r = MacroCondition(b, l + 1);
    if (!r) r = Expr(b, l + 1, -1);
    return r;
  }

  // NL*
  private static boolean Conditional_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "Conditional_0_2", c)) break;
    }
    return true;
  }

  // Element*
  private static boolean Conditional_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Conditional_0_3", c)) break;
    }
    return true;
  }

  // (((ELIF_TAG (Condition|MacroCondition|Expr))|ELSE_TAG) NL* Element*)*
  private static boolean Conditional_0_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Conditional_0_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Conditional_0_4", c)) break;
    }
    return true;
  }

  // ((ELIF_TAG (Condition|MacroCondition|Expr))|ELSE_TAG) NL* Element*
  private static boolean Conditional_0_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Conditional_0_4_0_0(b, l + 1);
    r = r && Conditional_0_4_0_1(b, l + 1);
    r = r && Conditional_0_4_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (ELIF_TAG (Condition|MacroCondition|Expr))|ELSE_TAG
  private static boolean Conditional_0_4_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_4_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Conditional_0_4_0_0_0(b, l + 1);
    if (!r) r = consumeToken(b, ELSE_TAG);
    exit_section_(b, m, null, r);
    return r;
  }

  // ELIF_TAG (Condition|MacroCondition|Expr)
  private static boolean Conditional_0_4_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_4_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELIF_TAG);
    r = r && Conditional_0_4_0_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Condition|MacroCondition|Expr
  private static boolean Conditional_0_4_0_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_4_0_0_0_1")) return false;
    boolean r;
    r = Condition(b, l + 1);
    if (!r) r = MacroCondition(b, l + 1);
    if (!r) r = Expr(b, l + 1, -1);
    return r;
  }

  // NL*
  private static boolean Conditional_0_4_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_4_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "Conditional_0_4_0_1", c)) break;
    }
    return true;
  }

  // Element*
  private static boolean Conditional_0_4_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_4_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Conditional_0_4_0_2", c)) break;
    }
    return true;
  }

  // NL*
  private static boolean Conditional_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "Conditional_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ID EQU ((MINUS|PLUS|BITWISE_NOT)? Expr)* NL*
  public static boolean Constant(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Constant")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ID, EQU);
    r = r && Constant_2(b, l + 1);
    r = r && Constant_3(b, l + 1);
    exit_section_(b, m, CONSTANT, r);
    return r;
  }

  // ((MINUS|PLUS|BITWISE_NOT)? Expr)*
  private static boolean Constant_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Constant_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Constant_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Constant_2", c)) break;
    }
    return true;
  }

  // (MINUS|PLUS|BITWISE_NOT)? Expr
  private static boolean Constant_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Constant_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Constant_2_0_0(b, l + 1);
    r = r && Expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MINUS|PLUS|BITWISE_NOT)?
  private static boolean Constant_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Constant_2_0_0")) return false;
    Constant_2_0_0_0(b, l + 1);
    return true;
  }

  // MINUS|PLUS|BITWISE_NOT
  private static boolean Constant_2_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Constant_2_0_0_0")) return false;
    boolean r;
    r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, BITWISE_NOT);
    return r;
  }

  // NL*
  private static boolean Constant_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Constant_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "Constant_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ID? DATA_OP DataValue NL*
  public static boolean DataElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataElement")) return false;
    if (!nextTokenIs(b, "<data element>", DATA_OP, ID)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DATA_ELEMENT, "<data element>");
    r = DataElement_0(b, l + 1);
    r = r && consumeToken(b, DATA_OP);
    r = r && DataValue(b, l + 1);
    r = r && DataElement_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ID?
  private static boolean DataElement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataElement_0")) return false;
    consumeToken(b, ID);
    return true;
  }

  // NL*
  private static boolean DataElement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataElement_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "DataElement_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // (MINUS|PLUS|BITWISE_NOT)? (Expr|STRING|LabelIdentifier) (SEPARATOR ((MINUS|PLUS)? (Expr|STRING|LabelIdentifier)))*
  static boolean DataValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DataValue_0(b, l + 1);
    r = r && DataValue_1(b, l + 1);
    r = r && DataValue_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MINUS|PLUS|BITWISE_NOT)?
  private static boolean DataValue_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_0")) return false;
    DataValue_0_0(b, l + 1);
    return true;
  }

  // MINUS|PLUS|BITWISE_NOT
  private static boolean DataValue_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_0_0")) return false;
    boolean r;
    r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, BITWISE_NOT);
    return r;
  }

  // Expr|STRING|LabelIdentifier
  private static boolean DataValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_1")) return false;
    boolean r;
    r = Expr(b, l + 1, -1);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = LabelIdentifier(b, l + 1);
    return r;
  }

  // (SEPARATOR ((MINUS|PLUS)? (Expr|STRING|LabelIdentifier)))*
  private static boolean DataValue_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!DataValue_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "DataValue_2", c)) break;
    }
    return true;
  }

  // SEPARATOR ((MINUS|PLUS)? (Expr|STRING|LabelIdentifier))
  private static boolean DataValue_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEPARATOR);
    r = r && DataValue_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MINUS|PLUS)? (Expr|STRING|LabelIdentifier)
  private static boolean DataValue_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_2_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DataValue_2_0_1_0(b, l + 1);
    r = r && DataValue_2_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MINUS|PLUS)?
  private static boolean DataValue_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_2_0_1_0")) return false;
    DataValue_2_0_1_0_0(b, l + 1);
    return true;
  }

  // MINUS|PLUS
  private static boolean DataValue_2_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_2_0_1_0_0")) return false;
    boolean r;
    r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, PLUS);
    return r;
  }

  // Expr|STRING|LabelIdentifier
  private static boolean DataValue_2_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_2_0_1_1")) return false;
    boolean r;
    r = Expr(b, l + 1, -1);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = LabelIdentifier(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ((DEFINE_TAG Identifier ROUND_L (Expr (SEPARATOR Expr)*)? ROUND_R Expr)
  //         | (DEFINE_TAG Identifier (Expr|(PERCENT SQUARE_L Expr SQUARE_R))?)) SEPARATOR? NL*
  public static boolean Define(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define")) return false;
    if (!nextTokenIs(b, DEFINE_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Define_0(b, l + 1);
    r = r && Define_1(b, l + 1);
    r = r && Define_2(b, l + 1);
    exit_section_(b, m, DEFINE, r);
    return r;
  }

  // (DEFINE_TAG Identifier ROUND_L (Expr (SEPARATOR Expr)*)? ROUND_R Expr)
  //         | (DEFINE_TAG Identifier (Expr|(PERCENT SQUARE_L Expr SQUARE_R))?)
  private static boolean Define_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Define_0_0(b, l + 1);
    if (!r) r = Define_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DEFINE_TAG Identifier ROUND_L (Expr (SEPARATOR Expr)*)? ROUND_R Expr
  private static boolean Define_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DEFINE_TAG);
    r = r && Identifier(b, l + 1);
    r = r && consumeToken(b, ROUND_L);
    r = r && Define_0_0_3(b, l + 1);
    r = r && consumeToken(b, ROUND_R);
    r = r && Expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (Expr (SEPARATOR Expr)*)?
  private static boolean Define_0_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_0_3")) return false;
    Define_0_0_3_0(b, l + 1);
    return true;
  }

  // Expr (SEPARATOR Expr)*
  private static boolean Define_0_0_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_0_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expr(b, l + 1, -1);
    r = r && Define_0_0_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (SEPARATOR Expr)*
  private static boolean Define_0_0_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_0_3_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Define_0_0_3_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Define_0_0_3_0_1", c)) break;
    }
    return true;
  }

  // SEPARATOR Expr
  private static boolean Define_0_0_3_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_0_3_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEPARATOR);
    r = r && Expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DEFINE_TAG Identifier (Expr|(PERCENT SQUARE_L Expr SQUARE_R))?
  private static boolean Define_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DEFINE_TAG);
    r = r && Identifier(b, l + 1);
    r = r && Define_0_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (Expr|(PERCENT SQUARE_L Expr SQUARE_R))?
  private static boolean Define_0_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_1_2")) return false;
    Define_0_1_2_0(b, l + 1);
    return true;
  }

  // Expr|(PERCENT SQUARE_L Expr SQUARE_R)
  private static boolean Define_0_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expr(b, l + 1, -1);
    if (!r) r = Define_0_1_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PERCENT SQUARE_L Expr SQUARE_R
  private static boolean Define_0_1_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_1_2_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PERCENT, SQUARE_L);
    r = r && Expr(b, l + 1, -1);
    r = r && consumeToken(b, SQUARE_R);
    exit_section_(b, m, null, r);
    return r;
  }

  // SEPARATOR?
  private static boolean Define_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_1")) return false;
    consumeToken(b, SEPARATOR);
    return true;
  }

  // NL*
  private static boolean Define_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "Define_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // (DirectiveDecl|DirectiveDeclBrackets|MapOption) NL*
  public static boolean Directive(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Directive")) return false;
    if (!nextTokenIs(b, "<directive>", DIRECTIVE_OP, SQUARE_L)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DIRECTIVE, "<directive>");
    r = Directive_0(b, l + 1);
    r = r && Directive_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // DirectiveDecl|DirectiveDeclBrackets|MapOption
  private static boolean Directive_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Directive_0")) return false;
    boolean r;
    r = DirectiveDecl(b, l + 1);
    if (!r) r = DirectiveDeclBrackets(b, l + 1);
    if (!r) r = MapOption(b, l + 1);
    return r;
  }

  // NL*
  private static boolean Directive_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Directive_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "Directive_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // DIRECTIVE_OP (Expr SEPARATOR?)*
  static boolean DirectiveDecl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveDecl")) return false;
    if (!nextTokenIs(b, DIRECTIVE_OP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DIRECTIVE_OP);
    r = r && DirectiveDecl_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (Expr SEPARATOR?)*
  private static boolean DirectiveDecl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveDecl_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!DirectiveDecl_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "DirectiveDecl_1", c)) break;
    }
    return true;
  }

  // Expr SEPARATOR?
  private static boolean DirectiveDecl_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveDecl_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expr(b, l + 1, -1);
    r = r && DirectiveDecl_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SEPARATOR?
  private static boolean DirectiveDecl_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveDecl_1_0_1")) return false;
    consumeToken(b, SEPARATOR);
    return true;
  }

  /* ********************************************************** */
  // SQUARE_L DIRECTIVE_OP Expr* SQUARE_R
  static boolean DirectiveDeclBrackets(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveDeclBrackets")) return false;
    if (!nextTokenIs(b, SQUARE_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SQUARE_L, DIRECTIVE_OP);
    r = r && DirectiveDeclBrackets_2(b, l + 1);
    r = r && consumeToken(b, SQUARE_R);
    exit_section_(b, m, null, r);
    return r;
  }

  // Expr*
  private static boolean DirectiveDeclBrackets_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveDeclBrackets_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Expr(b, l + 1, -1)) break;
      if (!empty_element_parsed_guard_(b, "DirectiveDeclBrackets_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // COMMENT
  //                 | Section
  //                 | Segment
  //                 | Label
  //                 | Preprocessor
  //                 | Directive
  //                 | EndDirective
  //                 | Constant
  //                 | Structure
  //                 | Instruction
  //                 | DataElement
  //                 | IStrucElement
  //                 | NL
  //                 | ID
  static boolean Element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Element")) return false;
    boolean r;
    r = consumeToken(b, COMMENT);
    if (!r) r = Section(b, l + 1);
    if (!r) r = Segment(b, l + 1);
    if (!r) r = Label(b, l + 1);
    if (!r) r = Preprocessor(b, l + 1);
    if (!r) r = Directive(b, l + 1);
    if (!r) r = EndDirective(b, l + 1);
    if (!r) r = Constant(b, l + 1);
    if (!r) r = Structure(b, l + 1);
    if (!r) r = Instruction(b, l + 1);
    if (!r) r = DataElement(b, l + 1);
    if (!r) r = IStrucElement(b, l + 1);
    if (!r) r = consumeToken(b, NL);
    if (!r) r = consumeToken(b, ID);
    return r;
  }

  /* ********************************************************** */
  // ERROR_TAG NL*
  public static boolean Error(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Error")) return false;
    if (!nextTokenIs(b, ERROR_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ERROR_TAG);
    r = r && Error_1(b, l + 1);
    exit_section_(b, m, ERROR, r);
    return r;
  }

  // NL*
  private static boolean Error_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Error_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "Error_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ISTRUC_TAG Identifier NL* ((IStrucElement|Conditional) NL*)* IEND_TAG
  public static boolean IStruc(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStruc")) return false;
    if (!nextTokenIs(b, ISTRUC_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ISTRUC_TAG);
    r = r && Identifier(b, l + 1);
    r = r && IStruc_2(b, l + 1);
    r = r && IStruc_3(b, l + 1);
    r = r && consumeToken(b, IEND_TAG);
    exit_section_(b, m, I_STRUC, r);
    return r;
  }

  // NL*
  private static boolean IStruc_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStruc_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "IStruc_2", c)) break;
    }
    return true;
  }

  // ((IStrucElement|Conditional) NL*)*
  private static boolean IStruc_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStruc_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!IStruc_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "IStruc_3", c)) break;
    }
    return true;
  }

  // (IStrucElement|Conditional) NL*
  private static boolean IStruc_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStruc_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = IStruc_3_0_0(b, l + 1);
    r = r && IStruc_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IStrucElement|Conditional
  private static boolean IStruc_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStruc_3_0_0")) return false;
    boolean r;
    r = IStrucElement(b, l + 1);
    if (!r) r = Conditional(b, l + 1);
    return r;
  }

  // NL*
  private static boolean IStruc_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStruc_3_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "IStruc_3_0_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // AT_TAG (StructureField|LabelIdentifier|Identifier) SEPARATOR DATA_OP DataValue
  static boolean IStrucElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStrucElement")) return false;
    if (!nextTokenIs(b, AT_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AT_TAG);
    r = r && IStrucElement_1(b, l + 1);
    r = r && consumeTokens(b, 0, SEPARATOR, DATA_OP);
    r = r && DataValue(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // StructureField|LabelIdentifier|Identifier
  private static boolean IStrucElement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStrucElement_1")) return false;
    boolean r;
    r = StructureField(b, l + 1);
    if (!r) r = LabelIdentifier(b, l + 1);
    if (!r) r = Identifier(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // IFMACRO_TAG|IFCTX_TAG|IFNUM_TAG|IFSTR_TAG|IFIDN_TAG|IFDEF_TAG|IFID_TAG|IF_TAG
  static boolean IfConditionTag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfConditionTag")) return false;
    boolean r;
    r = consumeToken(b, IFMACRO_TAG);
    if (!r) r = consumeToken(b, IFCTX_TAG);
    if (!r) r = consumeToken(b, IFNUM_TAG);
    if (!r) r = consumeToken(b, IFSTR_TAG);
    if (!r) r = consumeToken(b, IFIDN_TAG);
    if (!r) r = consumeToken(b, IFDEF_TAG);
    if (!r) r = consumeToken(b, IFID_TAG);
    if (!r) r = consumeToken(b, IF_TAG);
    return r;
  }

  /* ********************************************************** */
  // INCLUDE_TAG (ID_EXTENSION|STRING)
  public static boolean Include(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Include")) return false;
    if (!nextTokenIs(b, INCLUDE_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INCLUDE_TAG);
    r = r && Include_1(b, l + 1);
    exit_section_(b, m, INCLUDE, r);
    return r;
  }

  // ID_EXTENSION|STRING
  private static boolean Include_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Include_1")) return false;
    boolean r;
    r = consumeToken(b, ID_EXTENSION);
    if (!r) r = consumeToken(b, STRING);
    return r;
  }

  /* ********************************************************** */
  // Prefix* (Mnemonic|(ID COMMENT)) (((((BITWISE_NOT|MINUS|PLUS)? Expr) SEPARATOR)* ((BITWISE_NOT|MINUS|PLUS)? Expr))|(SIZE_TYPE (BITWISE_NOT|MINUS|PLUS)? ROUND_L Expr ROUND_R))?
  public static boolean Instruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INSTRUCTION, "<instruction>");
    r = Instruction_0(b, l + 1);
    r = r && Instruction_1(b, l + 1);
    r = r && Instruction_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Prefix*
  private static boolean Instruction_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Prefix(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Instruction_0", c)) break;
    }
    return true;
  }

  // Mnemonic|(ID COMMENT)
  private static boolean Instruction_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Mnemonic(b, l + 1);
    if (!r) r = Instruction_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ID COMMENT
  private static boolean Instruction_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ID, COMMENT);
    exit_section_(b, m, null, r);
    return r;
  }

  // (((((BITWISE_NOT|MINUS|PLUS)? Expr) SEPARATOR)* ((BITWISE_NOT|MINUS|PLUS)? Expr))|(SIZE_TYPE (BITWISE_NOT|MINUS|PLUS)? ROUND_L Expr ROUND_R))?
  private static boolean Instruction_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_2")) return false;
    Instruction_2_0(b, l + 1);
    return true;
  }

  // ((((BITWISE_NOT|MINUS|PLUS)? Expr) SEPARATOR)* ((BITWISE_NOT|MINUS|PLUS)? Expr))|(SIZE_TYPE (BITWISE_NOT|MINUS|PLUS)? ROUND_L Expr ROUND_R)
  private static boolean Instruction_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_2_0_0(b, l + 1);
    if (!r) r = Instruction_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (((BITWISE_NOT|MINUS|PLUS)? Expr) SEPARATOR)* ((BITWISE_NOT|MINUS|PLUS)? Expr)
  private static boolean Instruction_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_2_0_0_0(b, l + 1);
    r = r && Instruction_2_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (((BITWISE_NOT|MINUS|PLUS)? Expr) SEPARATOR)*
  private static boolean Instruction_2_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_2_0_0_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Instruction_2_0_0_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Instruction_2_0_0_0", c)) break;
    }
    return true;
  }

  // ((BITWISE_NOT|MINUS|PLUS)? Expr) SEPARATOR
  private static boolean Instruction_2_0_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_2_0_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_2_0_0_0_0_0(b, l + 1);
    r = r && consumeToken(b, SEPARATOR);
    exit_section_(b, m, null, r);
    return r;
  }

  // (BITWISE_NOT|MINUS|PLUS)? Expr
  private static boolean Instruction_2_0_0_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_2_0_0_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_2_0_0_0_0_0_0(b, l + 1);
    r = r && Expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (BITWISE_NOT|MINUS|PLUS)?
  private static boolean Instruction_2_0_0_0_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_2_0_0_0_0_0_0")) return false;
    Instruction_2_0_0_0_0_0_0_0(b, l + 1);
    return true;
  }

  // BITWISE_NOT|MINUS|PLUS
  private static boolean Instruction_2_0_0_0_0_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_2_0_0_0_0_0_0_0")) return false;
    boolean r;
    r = consumeToken(b, BITWISE_NOT);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, PLUS);
    return r;
  }

  // (BITWISE_NOT|MINUS|PLUS)? Expr
  private static boolean Instruction_2_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_2_0_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_2_0_0_1_0(b, l + 1);
    r = r && Expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (BITWISE_NOT|MINUS|PLUS)?
  private static boolean Instruction_2_0_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_2_0_0_1_0")) return false;
    Instruction_2_0_0_1_0_0(b, l + 1);
    return true;
  }

  // BITWISE_NOT|MINUS|PLUS
  private static boolean Instruction_2_0_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_2_0_0_1_0_0")) return false;
    boolean r;
    r = consumeToken(b, BITWISE_NOT);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, PLUS);
    return r;
  }

  // SIZE_TYPE (BITWISE_NOT|MINUS|PLUS)? ROUND_L Expr ROUND_R
  private static boolean Instruction_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_2_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SIZE_TYPE);
    r = r && Instruction_2_0_1_1(b, l + 1);
    r = r && consumeToken(b, ROUND_L);
    r = r && Expr(b, l + 1, -1);
    r = r && consumeToken(b, ROUND_R);
    exit_section_(b, m, null, r);
    return r;
  }

  // (BITWISE_NOT|MINUS|PLUS)?
  private static boolean Instruction_2_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_2_0_1_1")) return false;
    Instruction_2_0_1_1_0(b, l + 1);
    return true;
  }

  // BITWISE_NOT|MINUS|PLUS
  private static boolean Instruction_2_0_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_2_0_1_1_0")) return false;
    boolean r;
    r = consumeToken(b, BITWISE_NOT);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, PLUS);
    return r;
  }

  /* ********************************************************** */
  // (LBL_DEF (Instruction|DataElement|Structure)? NL*) | (LabelDefMacro (Instruction|DataElement|Structure)? NL*) | (LBL (Instruction|MacroCall|MacroDefCall) NL*)
  public static boolean Label(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LABEL, "<label>");
    r = Label_0(b, l + 1);
    if (!r) r = Label_1(b, l + 1);
    if (!r) r = Label_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LBL_DEF (Instruction|DataElement|Structure)? NL*
  private static boolean Label_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBL_DEF);
    r = r && Label_0_1(b, l + 1);
    r = r && Label_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (Instruction|DataElement|Structure)?
  private static boolean Label_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_0_1")) return false;
    Label_0_1_0(b, l + 1);
    return true;
  }

  // Instruction|DataElement|Structure
  private static boolean Label_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_0_1_0")) return false;
    boolean r;
    r = Instruction(b, l + 1);
    if (!r) r = DataElement(b, l + 1);
    if (!r) r = Structure(b, l + 1);
    return r;
  }

  // NL*
  private static boolean Label_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "Label_0_2", c)) break;
    }
    return true;
  }

  // LabelDefMacro (Instruction|DataElement|Structure)? NL*
  private static boolean Label_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LabelDefMacro(b, l + 1);
    r = r && Label_1_1(b, l + 1);
    r = r && Label_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (Instruction|DataElement|Structure)?
  private static boolean Label_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_1_1")) return false;
    Label_1_1_0(b, l + 1);
    return true;
  }

  // Instruction|DataElement|Structure
  private static boolean Label_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_1_1_0")) return false;
    boolean r;
    r = Instruction(b, l + 1);
    if (!r) r = DataElement(b, l + 1);
    if (!r) r = Structure(b, l + 1);
    return r;
  }

  // NL*
  private static boolean Label_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_1_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "Label_1_2", c)) break;
    }
    return true;
  }

  // LBL (Instruction|MacroCall|MacroDefCall) NL*
  private static boolean Label_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBL);
    r = r && Label_2_1(b, l + 1);
    r = r && Label_2_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Instruction|MacroCall|MacroDefCall
  private static boolean Label_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_2_1")) return false;
    boolean r;
    r = Instruction(b, l + 1);
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = MacroDefCall(b, l + 1);
    return r;
  }

  // NL*
  private static boolean Label_2_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_2_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "Label_2_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ID MacroParenthesis COLON NL*
  public static boolean LabelDefMacro(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelDefMacro")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    r = r && MacroParenthesis(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && LabelDefMacro_3(b, l + 1);
    exit_section_(b, m, LABEL_DEF_MACRO, r);
    return r;
  }

  // NL*
  private static boolean LabelDefMacro_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelDefMacro_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "LabelDefMacro_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // MACRO_TAG Identifier MacroParams MacroDefaultParam? NL* ((MacroLabel|Label|DataElement|Instruction|Directive|Preprocessor|Expr) NL*)* MACRO_END_TAG
  public static boolean Macro(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro")) return false;
    if (!nextTokenIs(b, MACRO_TAG)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MACRO, null);
    r = consumeToken(b, MACRO_TAG);
    p = r; // pin = 1
    r = r && report_error_(b, Identifier(b, l + 1));
    r = p && report_error_(b, MacroParams(b, l + 1)) && r;
    r = p && report_error_(b, Macro_3(b, l + 1)) && r;
    r = p && report_error_(b, Macro_4(b, l + 1)) && r;
    r = p && report_error_(b, Macro_5(b, l + 1)) && r;
    r = p && consumeToken(b, MACRO_END_TAG) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // MacroDefaultParam?
  private static boolean Macro_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro_3")) return false;
    MacroDefaultParam(b, l + 1);
    return true;
  }

  // NL*
  private static boolean Macro_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "Macro_4", c)) break;
    }
    return true;
  }

  // ((MacroLabel|Label|DataElement|Instruction|Directive|Preprocessor|Expr) NL*)*
  private static boolean Macro_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Macro_5_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Macro_5", c)) break;
    }
    return true;
  }

  // (MacroLabel|Label|DataElement|Instruction|Directive|Preprocessor|Expr) NL*
  private static boolean Macro_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Macro_5_0_0(b, l + 1);
    r = r && Macro_5_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // MacroLabel|Label|DataElement|Instruction|Directive|Preprocessor|Expr
  private static boolean Macro_5_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro_5_0_0")) return false;
    boolean r;
    r = MacroLabel(b, l + 1);
    if (!r) r = Label(b, l + 1);
    if (!r) r = DataElement(b, l + 1);
    if (!r) r = Instruction(b, l + 1);
    if (!r) r = Directive(b, l + 1);
    if (!r) r = Preprocessor(b, l + 1);
    if (!r) r = Expr(b, l + 1, -1);
    return r;
  }

  // NL*
  private static boolean Macro_5_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro_5_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "Macro_5_0_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // Identifier MacroParams MacroDefaultParam?
  static boolean MacroCondition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCondition")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Identifier(b, l + 1);
    r = r && MacroParams(b, l + 1);
    r = r && MacroCondition_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // MacroDefaultParam?
  private static boolean MacroCondition_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCondition_2")) return false;
    MacroDefaultParam(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // Instruction|STRING|REGISTER|NumericLiteral|MacroCall
  static boolean MacroDefaultParam(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroDefaultParam")) return false;
    boolean r;
    r = Instruction(b, l + 1);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, REGISTER);
    if (!r) r = NumericLiteral(b, l + 1);
    if (!r) r = MacroCall(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // MACRO_PARAM_LBL_DEF (Instruction|DataElement)? NL*
  public static boolean MacroLabel(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroLabel")) return false;
    if (!nextTokenIs(b, MACRO_PARAM_LBL_DEF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MACRO_PARAM_LBL_DEF);
    r = r && MacroLabel_1(b, l + 1);
    r = r && MacroLabel_2(b, l + 1);
    exit_section_(b, m, MACRO_LABEL, r);
    return r;
  }

  // (Instruction|DataElement)?
  private static boolean MacroLabel_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroLabel_1")) return false;
    MacroLabel_1_0(b, l + 1);
    return true;
  }

  // Instruction|DataElement
  private static boolean MacroLabel_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroLabel_1_0")) return false;
    boolean r;
    r = Instruction(b, l + 1);
    if (!r) r = DataElement(b, l + 1);
    return r;
  }

  // NL*
  private static boolean MacroLabel_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroLabel_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "MacroLabel_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ((NumericLiteral MINUS NumericLiteral (PLUS|TIMES)?)|(NumericLiteral MINUS TIMES)|(NumericLiteral PLUS?)) MACRO_NOLIST_QUAL?
  static boolean MacroParams(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MacroParams_0(b, l + 1);
    r = r && MacroParams_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (NumericLiteral MINUS NumericLiteral (PLUS|TIMES)?)|(NumericLiteral MINUS TIMES)|(NumericLiteral PLUS?)
  private static boolean MacroParams_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MacroParams_0_0(b, l + 1);
    if (!r) r = MacroParams_0_1(b, l + 1);
    if (!r) r = MacroParams_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NumericLiteral MINUS NumericLiteral (PLUS|TIMES)?
  private static boolean MacroParams_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral(b, l + 1);
    r = r && consumeToken(b, MINUS);
    r = r && NumericLiteral(b, l + 1);
    r = r && MacroParams_0_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (PLUS|TIMES)?
  private static boolean MacroParams_0_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams_0_0_3")) return false;
    MacroParams_0_0_3_0(b, l + 1);
    return true;
  }

  // PLUS|TIMES
  private static boolean MacroParams_0_0_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams_0_0_3_0")) return false;
    boolean r;
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, TIMES);
    return r;
  }

  // NumericLiteral MINUS TIMES
  private static boolean MacroParams_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral(b, l + 1);
    r = r && consumeTokens(b, 0, MINUS, TIMES);
    exit_section_(b, m, null, r);
    return r;
  }

  // NumericLiteral PLUS?
  private static boolean MacroParams_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral(b, l + 1);
    r = r && MacroParams_0_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PLUS?
  private static boolean MacroParams_0_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams_0_2_1")) return false;
    consumeToken(b, PLUS);
    return true;
  }

  // MACRO_NOLIST_QUAL?
  private static boolean MacroParams_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams_1")) return false;
    consumeToken(b, MACRO_NOLIST_QUAL);
    return true;
  }

  /* ********************************************************** */
  // ROUND_L (Expr (SEPARATOR Expr)*)? ROUND_R
  static boolean MacroParenthesis(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParenthesis")) return false;
    if (!nextTokenIs(b, ROUND_L)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, ROUND_L);
    p = r; // pin = 1
    r = r && report_error_(b, MacroParenthesis_1(b, l + 1));
    r = p && consumeToken(b, ROUND_R) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (Expr (SEPARATOR Expr)*)?
  private static boolean MacroParenthesis_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParenthesis_1")) return false;
    MacroParenthesis_1_0(b, l + 1);
    return true;
  }

  // Expr (SEPARATOR Expr)*
  private static boolean MacroParenthesis_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParenthesis_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = Expr(b, l + 1, -1);
    p = r; // pin = 1
    r = r && MacroParenthesis_1_0_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (SEPARATOR Expr)*
  private static boolean MacroParenthesis_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParenthesis_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!MacroParenthesis_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "MacroParenthesis_1_0_1", c)) break;
    }
    return true;
  }

  // SEPARATOR Expr
  private static boolean MacroParenthesis_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParenthesis_1_0_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, SEPARATOR);
    p = r; // pin = 1
    r = r && Expr(b, l + 1, -1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // SQUARE_L ('map'|'MAP') (('all'|'ALL')|('brief'|'BRIEF')|('sections'|'SECTIONS')|('segments'|'SEGMENTS')|('symbols'|'SYMBOLS'))? MAP_FILE SQUARE_R
  public static boolean MapOption(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapOption")) return false;
    if (!nextTokenIs(b, SQUARE_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SQUARE_L);
    r = r && MapOption_1(b, l + 1);
    r = r && MapOption_2(b, l + 1);
    r = r && consumeTokens(b, 0, MAP_FILE, SQUARE_R);
    exit_section_(b, m, MAP_OPTION, r);
    return r;
  }

  // 'map'|'MAP'
  private static boolean MapOption_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapOption_1")) return false;
    boolean r;
    r = consumeToken(b, "map");
    if (!r) r = consumeToken(b, "MAP");
    return r;
  }

  // (('all'|'ALL')|('brief'|'BRIEF')|('sections'|'SECTIONS')|('segments'|'SEGMENTS')|('symbols'|'SYMBOLS'))?
  private static boolean MapOption_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapOption_2")) return false;
    MapOption_2_0(b, l + 1);
    return true;
  }

  // ('all'|'ALL')|('brief'|'BRIEF')|('sections'|'SECTIONS')|('segments'|'SEGMENTS')|('symbols'|'SYMBOLS')
  private static boolean MapOption_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapOption_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MapOption_2_0_0(b, l + 1);
    if (!r) r = MapOption_2_0_1(b, l + 1);
    if (!r) r = MapOption_2_0_2(b, l + 1);
    if (!r) r = MapOption_2_0_3(b, l + 1);
    if (!r) r = MapOption_2_0_4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // 'all'|'ALL'
  private static boolean MapOption_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapOption_2_0_0")) return false;
    boolean r;
    r = consumeToken(b, "all");
    if (!r) r = consumeToken(b, "ALL");
    return r;
  }

  // 'brief'|'BRIEF'
  private static boolean MapOption_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapOption_2_0_1")) return false;
    boolean r;
    r = consumeToken(b, "brief");
    if (!r) r = consumeToken(b, "BRIEF");
    return r;
  }

  // 'sections'|'SECTIONS'
  private static boolean MapOption_2_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapOption_2_0_2")) return false;
    boolean r;
    r = consumeToken(b, "sections");
    if (!r) r = consumeToken(b, "SECTIONS");
    return r;
  }

  // 'segments'|'SEGMENTS'
  private static boolean MapOption_2_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapOption_2_0_3")) return false;
    boolean r;
    r = consumeToken(b, "segments");
    if (!r) r = consumeToken(b, "SEGMENTS");
    return r;
  }

  // 'symbols'|'SYMBOLS'
  private static boolean MapOption_2_0_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapOption_2_0_4")) return false;
    boolean r;
    r = consumeToken(b, "symbols");
    if (!r) r = consumeToken(b, "SYMBOLS");
    return r;
  }

  /* ********************************************************** */
  // Element*
  static boolean NASMFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NASMFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "NASMFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // OP_PREFIX NL*
  static boolean Prefix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Prefix")) return false;
    if (!nextTokenIs(b, OP_PREFIX)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_PREFIX);
    r = r && Prefix_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NL*
  private static boolean Prefix_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Prefix_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "Prefix_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // Include
  //                 | Define
  //                 | Assign
  //                 | Macro
  //                 | MacroDefCall
  //                 | MacroCall
  //                 | Strlen
  //                 | Error
  //                 | Conditional
  //                 | PreprocessorExpr
  public static boolean Preprocessor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Preprocessor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PREPROCESSOR, "<preprocessor>");
    r = Include(b, l + 1);
    if (!r) r = Define(b, l + 1);
    if (!r) r = Assign(b, l + 1);
    if (!r) r = Macro(b, l + 1);
    if (!r) r = MacroDefCall(b, l + 1);
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = Strlen(b, l + 1);
    if (!r) r = Error(b, l + 1);
    if (!r) r = Conditional(b, l + 1);
    if (!r) r = PreprocessorExpr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // PREPROCESSOR_OP Expr*
  static boolean PreprocessorExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PreprocessorExpr")) return false;
    if (!nextTokenIs(b, PREPROCESSOR_OP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PREPROCESSOR_OP);
    r = r && PreprocessorExpr_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Expr*
  private static boolean PreprocessorExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PreprocessorExpr_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Expr(b, l + 1, -1)) break;
      if (!empty_element_parsed_guard_(b, "PreprocessorExpr_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // (SQUARE_L SECTION SQUARE_R) | SECTION
  static boolean Section(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Section")) return false;
    if (!nextTokenIs(b, "", SECTION, SQUARE_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Section_0(b, l + 1);
    if (!r) r = consumeToken(b, SECTION);
    exit_section_(b, m, null, r);
    return r;
  }

  // SQUARE_L SECTION SQUARE_R
  private static boolean Section_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Section_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SQUARE_L, SECTION, SQUARE_R);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (SQUARE_L SEGMENT SQUARE_R) | SEGMENT
  static boolean Segment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Segment")) return false;
    if (!nextTokenIs(b, "", SEGMENT, SQUARE_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Segment_0(b, l + 1);
    if (!r) r = consumeToken(b, SEGMENT);
    exit_section_(b, m, null, r);
    return r;
  }

  // SQUARE_L SEGMENT SQUARE_R
  private static boolean Segment_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Segment_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SQUARE_L, SEGMENT, SQUARE_R);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // STRLEN_TAG Identifier (STRING|ID) NL*
  public static boolean Strlen(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Strlen")) return false;
    if (!nextTokenIs(b, STRLEN_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRLEN_TAG);
    r = r && Identifier(b, l + 1);
    r = r && Strlen_2(b, l + 1);
    r = r && Strlen_3(b, l + 1);
    exit_section_(b, m, STRLEN, r);
    return r;
  }

  // STRING|ID
  private static boolean Strlen_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Strlen_2")) return false;
    boolean r;
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, ID);
    return r;
  }

  // NL*
  private static boolean Strlen_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Strlen_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "Strlen_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // STRUC_TAG Identifier NL* ((((LBL_DEF|LabelIdentifier) DATA_OP ((MINUS|PLUS)? (Expr|Identifier)))|(LabelIdentifier|LBL_DEF)|Conditional) NL*)* ENDSTRUC_TAG
  public static boolean Struc(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc")) return false;
    if (!nextTokenIs(b, STRUC_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRUC_TAG);
    r = r && Identifier(b, l + 1);
    r = r && Struc_2(b, l + 1);
    r = r && Struc_3(b, l + 1);
    r = r && consumeToken(b, ENDSTRUC_TAG);
    exit_section_(b, m, STRUC, r);
    return r;
  }

  // NL*
  private static boolean Struc_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "Struc_2", c)) break;
    }
    return true;
  }

  // ((((LBL_DEF|LabelIdentifier) DATA_OP ((MINUS|PLUS)? (Expr|Identifier)))|(LabelIdentifier|LBL_DEF)|Conditional) NL*)*
  private static boolean Struc_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Struc_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Struc_3", c)) break;
    }
    return true;
  }

  // (((LBL_DEF|LabelIdentifier) DATA_OP ((MINUS|PLUS)? (Expr|Identifier)))|(LabelIdentifier|LBL_DEF)|Conditional) NL*
  private static boolean Struc_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Struc_3_0_0(b, l + 1);
    r = r && Struc_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((LBL_DEF|LabelIdentifier) DATA_OP ((MINUS|PLUS)? (Expr|Identifier)))|(LabelIdentifier|LBL_DEF)|Conditional
  private static boolean Struc_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Struc_3_0_0_0(b, l + 1);
    if (!r) r = Struc_3_0_0_1(b, l + 1);
    if (!r) r = Conditional(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LBL_DEF|LabelIdentifier) DATA_OP ((MINUS|PLUS)? (Expr|Identifier))
  private static boolean Struc_3_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Struc_3_0_0_0_0(b, l + 1);
    r = r && consumeToken(b, DATA_OP);
    r = r && Struc_3_0_0_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LBL_DEF|LabelIdentifier
  private static boolean Struc_3_0_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3_0_0_0_0")) return false;
    boolean r;
    r = consumeToken(b, LBL_DEF);
    if (!r) r = LabelIdentifier(b, l + 1);
    return r;
  }

  // (MINUS|PLUS)? (Expr|Identifier)
  private static boolean Struc_3_0_0_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3_0_0_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Struc_3_0_0_0_2_0(b, l + 1);
    r = r && Struc_3_0_0_0_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MINUS|PLUS)?
  private static boolean Struc_3_0_0_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3_0_0_0_2_0")) return false;
    Struc_3_0_0_0_2_0_0(b, l + 1);
    return true;
  }

  // MINUS|PLUS
  private static boolean Struc_3_0_0_0_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3_0_0_0_2_0_0")) return false;
    boolean r;
    r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, PLUS);
    return r;
  }

  // Expr|Identifier
  private static boolean Struc_3_0_0_0_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3_0_0_0_2_1")) return false;
    boolean r;
    r = Expr(b, l + 1, -1);
    if (!r) r = Identifier(b, l + 1);
    return r;
  }

  // LabelIdentifier|LBL_DEF
  private static boolean Struc_3_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3_0_0_1")) return false;
    boolean r;
    r = LabelIdentifier(b, l + 1);
    if (!r) r = consumeToken(b, LBL_DEF);
    return r;
  }

  // NL*
  private static boolean Struc_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "Struc_3_0_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // (Struc|IStruc) NL*
  public static boolean Structure(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Structure")) return false;
    if (!nextTokenIs(b, "<structure>", ISTRUC_TAG, STRUC_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STRUCTURE, "<structure>");
    r = Structure_0(b, l + 1);
    r = r && Structure_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Struc|IStruc
  private static boolean Structure_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Structure_0")) return false;
    boolean r;
    r = Struc(b, l + 1);
    if (!r) r = IStruc(b, l + 1);
    return r;
  }

  // NL*
  private static boolean Structure_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Structure_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, NL)) break;
      if (!empty_element_parsed_guard_(b, "Structure_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // Expression root: Expr
  // Operator priority table:
  // 0: ATOM(ParenthesisExpr)
  // 1: BINARY(TokenConcatExpr)
  // 2: BINARY(MulExpr)
  // 3: BINARY(DivExpr)
  // 4: BINARY(PlusExpr)
  // 5: BINARY(MinusExpr)
  // 6: BINARY(ModulusExpr)
  // 7: BINARY(BitShiftLExpr)
  // 8: BINARY(BitShiftRExpr)
  // 9: BINARY(BitwiseANDExpr)
  // 10: BINARY(BitwiseORExpr)
  // 11: BINARY(BitwiseXORExpr)
  // 12: PREFIX(BitwiseNOTExpr)
  // 13: BINARY(LogicalANDExpr)
  // 14: BINARY(LogicalORExpr)
  // 15: BINARY(LogicalXORExpr)
  // 16: ATOM(NumericLiteral)
  // 17: ATOM(SegmentAddress)
  // 18: ATOM(Str)
  // 19: ATOM(StructureField)
  // 20: ATOM(MacroDefCall)
  // 21: ATOM(MacroCall)
  // 22: ATOM(MacroParamCounter)
  // 23: ATOM(MacroParamReference)
  // 24: ATOM(MacroVarReference)
  // 25: ATOM(Address)
  // 26: ATOM(Reg)
  // 27: ATOM(Seg)
  // 28: ATOM(Mnemonic)
  // 29: ATOM(Identifier)
  // 30: ATOM(LabelIdentifier)
  // 31: ATOM(EndDirective)
  public static boolean Expr(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "Expr")) return false;
    addVariant(b, "<expr>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expr>");
    r = ParenthesisExpr(b, l + 1);
    if (!r) r = BitwiseNOTExpr(b, l + 1);
    if (!r) r = NumericLiteral(b, l + 1);
    if (!r) r = SegmentAddress(b, l + 1);
    if (!r) r = Str(b, l + 1);
    if (!r) r = StructureField(b, l + 1);
    if (!r) r = MacroDefCall(b, l + 1);
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = MacroParamCounter(b, l + 1);
    if (!r) r = MacroParamReference(b, l + 1);
    if (!r) r = MacroVarReference(b, l + 1);
    if (!r) r = Address(b, l + 1);
    if (!r) r = Reg(b, l + 1);
    if (!r) r = Seg(b, l + 1);
    if (!r) r = Mnemonic(b, l + 1);
    if (!r) r = Identifier(b, l + 1);
    if (!r) r = LabelIdentifier(b, l + 1);
    if (!r) r = EndDirective(b, l + 1);
    p = r;
    r = r && Expr_0(b, l + 1, g);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  public static boolean Expr_0(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "Expr_0")) return false;
    boolean r = true;
    while (true) {
      Marker m = enter_section_(b, l, _LEFT_, null);
      if (g < 1 && consumeTokenSmart(b, TOKEN_CONCAT)) {
        r = Expr(b, l, 1);
        exit_section_(b, l, m, TOKEN_CONCAT_EXPR, r, true, null);
      }
      else if (g < 2 && consumeTokenSmart(b, TIMES)) {
        r = Expr(b, l, 2);
        exit_section_(b, l, m, MUL_EXPR, r, true, null);
      }
      else if (g < 3 && consumeTokenSmart(b, DIVIDE)) {
        r = Expr(b, l, 3);
        exit_section_(b, l, m, DIV_EXPR, r, true, null);
      }
      else if (g < 4 && consumeTokenSmart(b, PLUS)) {
        r = Expr(b, l, 4);
        exit_section_(b, l, m, PLUS_EXPR, r, true, null);
      }
      else if (g < 5 && consumeTokenSmart(b, MINUS)) {
        r = Expr(b, l, 5);
        exit_section_(b, l, m, MINUS_EXPR, r, true, null);
      }
      else if (g < 6 && consumeTokenSmart(b, PERCENT)) {
        r = Expr(b, l, 6);
        exit_section_(b, l, m, MODULUS_EXPR, r, true, null);
      }
      else if (g < 7 && consumeTokenSmart(b, BITSHIFT_L)) {
        r = Expr(b, l, 7);
        exit_section_(b, l, m, BIT_SHIFT_L_EXPR, r, true, null);
      }
      else if (g < 8 && consumeTokenSmart(b, BITSHIFT_R)) {
        r = Expr(b, l, 8);
        exit_section_(b, l, m, BIT_SHIFT_R_EXPR, r, true, null);
      }
      else if (g < 9 && consumeTokenSmart(b, BITWISE_AND)) {
        r = Expr(b, l, 9);
        exit_section_(b, l, m, BITWISE_AND_EXPR, r, true, null);
      }
      else if (g < 10 && consumeTokenSmart(b, BITWISE_OR)) {
        r = Expr(b, l, 10);
        exit_section_(b, l, m, BITWISE_OR_EXPR, r, true, null);
      }
      else if (g < 11 && consumeTokenSmart(b, BITWISE_XOR)) {
        r = Expr(b, l, 11);
        exit_section_(b, l, m, BITWISE_XOR_EXPR, r, true, null);
      }
      else if (g < 13 && consumeTokenSmart(b, LOGICAL_AND)) {
        r = Expr(b, l, 13);
        exit_section_(b, l, m, LOGICAL_AND_EXPR, r, true, null);
      }
      else if (g < 14 && consumeTokenSmart(b, LOGICAL_OR)) {
        r = Expr(b, l, 14);
        exit_section_(b, l, m, LOGICAL_OR_EXPR, r, true, null);
      }
      else if (g < 15 && consumeTokenSmart(b, LOGICAL_XOR)) {
        r = Expr(b, l, 15);
        exit_section_(b, l, m, LOGICAL_XOR_EXPR, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  // ROUND_L Expr ROUND_R
  public static boolean ParenthesisExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParenthesisExpr")) return false;
    if (!nextTokenIsSmart(b, ROUND_L)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PARENTHESIS_EXPR, null);
    r = consumeTokenSmart(b, ROUND_L);
    p = r; // pin = 1
    r = r && report_error_(b, Expr(b, l + 1, -1));
    r = p && consumeToken(b, ROUND_R) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  public static boolean BitwiseNOTExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitwiseNOTExpr")) return false;
    if (!nextTokenIsSmart(b, BITWISE_NOT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, BITWISE_NOT);
    p = r;
    r = p && Expr(b, l, 12);
    exit_section_(b, l, m, BITWISE_NOT_EXPR, r, p, null);
    return r || p;
  }

  // SIZE_TYPE? (BITWISE_NOT|MINUS|PLUS)? (BINARY|ZEROES|HEXADECIMAL|DECIMAL|FLOAT_DECIMAL|CHARACTER)
  public static boolean NumericLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NUMERIC_LITERAL, "<numeric literal>");
    r = NumericLiteral_0(b, l + 1);
    r = r && NumericLiteral_1(b, l + 1);
    r = r && NumericLiteral_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SIZE_TYPE?
  private static boolean NumericLiteral_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral_0")) return false;
    consumeTokenSmart(b, SIZE_TYPE);
    return true;
  }

  // (BITWISE_NOT|MINUS|PLUS)?
  private static boolean NumericLiteral_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral_1")) return false;
    NumericLiteral_1_0(b, l + 1);
    return true;
  }

  // BITWISE_NOT|MINUS|PLUS
  private static boolean NumericLiteral_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral_1_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, BITWISE_NOT);
    if (!r) r = consumeTokenSmart(b, MINUS);
    if (!r) r = consumeTokenSmart(b, PLUS);
    return r;
  }

  // BINARY|ZEROES|HEXADECIMAL|DECIMAL|FLOAT_DECIMAL|CHARACTER
  private static boolean NumericLiteral_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral_2")) return false;
    boolean r;
    r = consumeTokenSmart(b, BINARY);
    if (!r) r = consumeTokenSmart(b, ZEROES);
    if (!r) r = consumeTokenSmart(b, HEXADECIMAL);
    if (!r) r = consumeTokenSmart(b, DECIMAL);
    if (!r) r = consumeTokenSmart(b, FLOAT_DECIMAL);
    if (!r) r = consumeTokenSmart(b, CHARACTER);
    return r;
  }

  // SIZE_TYPE? ((SEGMENT_ADDR_L (HEXADECIMAL|ZEROES|MacroCall|ID|LBL))|
  //                                 (LBL_DEF (SIZE_TYPE? (HEXADECIMAL|ZEROES|MacroCall|ID|LBL)))|
  //                                 (LabelDefMacro (SIZE_TYPE? (HEXADECIMAL|ZEROES|MacroCall|ID|LBL))))
  public static boolean SegmentAddress(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SegmentAddress")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, SEGMENT_ADDRESS, "<segment address>");
    r = SegmentAddress_0(b, l + 1);
    r = r && SegmentAddress_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SIZE_TYPE?
  private static boolean SegmentAddress_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SegmentAddress_0")) return false;
    consumeTokenSmart(b, SIZE_TYPE);
    return true;
  }

  // (SEGMENT_ADDR_L (HEXADECIMAL|ZEROES|MacroCall|ID|LBL))|
  //                                 (LBL_DEF (SIZE_TYPE? (HEXADECIMAL|ZEROES|MacroCall|ID|LBL)))|
  //                                 (LabelDefMacro (SIZE_TYPE? (HEXADECIMAL|ZEROES|MacroCall|ID|LBL)))
  private static boolean SegmentAddress_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SegmentAddress_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = SegmentAddress_1_0(b, l + 1);
    if (!r) r = SegmentAddress_1_1(b, l + 1);
    if (!r) r = SegmentAddress_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SEGMENT_ADDR_L (HEXADECIMAL|ZEROES|MacroCall|ID|LBL)
  private static boolean SegmentAddress_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SegmentAddress_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, SEGMENT_ADDR_L);
    r = r && SegmentAddress_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // HEXADECIMAL|ZEROES|MacroCall|ID|LBL
  private static boolean SegmentAddress_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SegmentAddress_1_0_1")) return false;
    boolean r;
    r = consumeTokenSmart(b, HEXADECIMAL);
    if (!r) r = consumeTokenSmart(b, ZEROES);
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = consumeTokenSmart(b, ID);
    if (!r) r = consumeTokenSmart(b, LBL);
    return r;
  }

  // LBL_DEF (SIZE_TYPE? (HEXADECIMAL|ZEROES|MacroCall|ID|LBL))
  private static boolean SegmentAddress_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SegmentAddress_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, LBL_DEF);
    r = r && SegmentAddress_1_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SIZE_TYPE? (HEXADECIMAL|ZEROES|MacroCall|ID|LBL)
  private static boolean SegmentAddress_1_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SegmentAddress_1_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = SegmentAddress_1_1_1_0(b, l + 1);
    r = r && SegmentAddress_1_1_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SIZE_TYPE?
  private static boolean SegmentAddress_1_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SegmentAddress_1_1_1_0")) return false;
    consumeTokenSmart(b, SIZE_TYPE);
    return true;
  }

  // HEXADECIMAL|ZEROES|MacroCall|ID|LBL
  private static boolean SegmentAddress_1_1_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SegmentAddress_1_1_1_1")) return false;
    boolean r;
    r = consumeTokenSmart(b, HEXADECIMAL);
    if (!r) r = consumeTokenSmart(b, ZEROES);
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = consumeTokenSmart(b, ID);
    if (!r) r = consumeTokenSmart(b, LBL);
    return r;
  }

  // LabelDefMacro (SIZE_TYPE? (HEXADECIMAL|ZEROES|MacroCall|ID|LBL))
  private static boolean SegmentAddress_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SegmentAddress_1_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LabelDefMacro(b, l + 1);
    r = r && SegmentAddress_1_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SIZE_TYPE? (HEXADECIMAL|ZEROES|MacroCall|ID|LBL)
  private static boolean SegmentAddress_1_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SegmentAddress_1_2_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = SegmentAddress_1_2_1_0(b, l + 1);
    r = r && SegmentAddress_1_2_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SIZE_TYPE?
  private static boolean SegmentAddress_1_2_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SegmentAddress_1_2_1_0")) return false;
    consumeTokenSmart(b, SIZE_TYPE);
    return true;
  }

  // HEXADECIMAL|ZEROES|MacroCall|ID|LBL
  private static boolean SegmentAddress_1_2_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SegmentAddress_1_2_1_1")) return false;
    boolean r;
    r = consumeTokenSmart(b, HEXADECIMAL);
    if (!r) r = consumeTokenSmart(b, ZEROES);
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = consumeTokenSmart(b, ID);
    if (!r) r = consumeTokenSmart(b, LBL);
    return r;
  }

  // STRING
  public static boolean Str(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Str")) return false;
    if (!nextTokenIsSmart(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, STRING);
    exit_section_(b, m, STR, r);
    return r;
  }

  // ID_EXTENSION
  public static boolean StructureField(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructureField")) return false;
    if (!nextTokenIsSmart(b, ID_EXTENSION)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, ID_EXTENSION);
    exit_section_(b, m, STRUCTURE_FIELD, r);
    return r;
  }

  // SIZE_TYPE? (BITWISE_NOT|MINUS|PLUS)? Identifier MacroParenthesis
  public static boolean MacroDefCall(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroDefCall")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MACRO_DEF_CALL, "<macro def call>");
    r = MacroDefCall_0(b, l + 1);
    r = r && MacroDefCall_1(b, l + 1);
    r = r && Identifier(b, l + 1);
    r = r && MacroParenthesis(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SIZE_TYPE?
  private static boolean MacroDefCall_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroDefCall_0")) return false;
    consumeTokenSmart(b, SIZE_TYPE);
    return true;
  }

  // (BITWISE_NOT|MINUS|PLUS)?
  private static boolean MacroDefCall_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroDefCall_1")) return false;
    MacroDefCall_1_0(b, l + 1);
    return true;
  }

  // BITWISE_NOT|MINUS|PLUS
  private static boolean MacroDefCall_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroDefCall_1_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, BITWISE_NOT);
    if (!r) r = consumeTokenSmart(b, MINUS);
    if (!r) r = consumeTokenSmart(b, PLUS);
    return r;
  }

  // SIZE_TYPE? Identifier Expr (SEPARATOR Expr)*
  public static boolean MacroCall(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, MACRO_CALL, "<macro call>");
    r = MacroCall_0(b, l + 1);
    r = r && Identifier(b, l + 1);
    r = r && Expr(b, l + 1, -1);
    r = r && MacroCall_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SIZE_TYPE?
  private static boolean MacroCall_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall_0")) return false;
    consumeTokenSmart(b, SIZE_TYPE);
    return true;
  }

  // (SEPARATOR Expr)*
  private static boolean MacroCall_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!MacroCall_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "MacroCall_3", c)) break;
    }
    return true;
  }

  // SEPARATOR Expr
  private static boolean MacroCall_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, SEPARATOR);
    r = r && Expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SIZE_TYPE? MACRO_PARAM_COUNT
  public static boolean MacroParamCounter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParamCounter")) return false;
    if (!nextTokenIsSmart(b, MACRO_PARAM_COUNT, SIZE_TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MACRO_PARAM_COUNTER, "<macro param counter>");
    r = MacroParamCounter_0(b, l + 1);
    r = r && consumeToken(b, MACRO_PARAM_COUNT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SIZE_TYPE?
  private static boolean MacroParamCounter_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParamCounter_0")) return false;
    consumeTokenSmart(b, SIZE_TYPE);
    return true;
  }

  // SIZE_TYPE? MACRO_PARAM_REF
  public static boolean MacroParamReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParamReference")) return false;
    if (!nextTokenIsSmart(b, MACRO_PARAM_REF, SIZE_TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MACRO_PARAM_REFERENCE, "<macro param reference>");
    r = MacroParamReference_0(b, l + 1);
    r = r && consumeToken(b, MACRO_PARAM_REF);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SIZE_TYPE?
  private static boolean MacroParamReference_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParamReference_0")) return false;
    consumeTokenSmart(b, SIZE_TYPE);
    return true;
  }

  // SIZE_TYPE? MACRO_VAR_REF
  public static boolean MacroVarReference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroVarReference")) return false;
    if (!nextTokenIsSmart(b, MACRO_VAR_REF, SIZE_TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MACRO_VAR_REFERENCE, "<macro var reference>");
    r = MacroVarReference_0(b, l + 1);
    r = r && consumeToken(b, MACRO_VAR_REF);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SIZE_TYPE?
  private static boolean MacroVarReference_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroVarReference_0")) return false;
    consumeTokenSmart(b, SIZE_TYPE);
    return true;
  }

  // (SIZE_TYPE? AddressInternal)|DOLLARSIGN|DOLLARSIGN2
  public static boolean Address(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Address")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ADDRESS, "<address>");
    r = Address_0(b, l + 1);
    if (!r) r = consumeTokenSmart(b, DOLLARSIGN);
    if (!r) r = consumeTokenSmart(b, DOLLARSIGN2);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SIZE_TYPE? AddressInternal
  private static boolean Address_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Address_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Address_0_0(b, l + 1);
    r = r && AddressInternal(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SIZE_TYPE?
  private static boolean Address_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Address_0_0")) return false;
    consumeTokenSmart(b, SIZE_TYPE);
    return true;
  }

  // SIZE_TYPE? REGISTER
  public static boolean Reg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Reg")) return false;
    if (!nextTokenIsSmart(b, REGISTER, SIZE_TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REG, "<reg>");
    r = Reg_0(b, l + 1);
    r = r && consumeToken(b, REGISTER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SIZE_TYPE?
  private static boolean Reg_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Reg_0")) return false;
    consumeTokenSmart(b, SIZE_TYPE);
    return true;
  }

  // SEGMENT_REGISTER
  public static boolean Seg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Seg")) return false;
    if (!nextTokenIsSmart(b, SEGMENT_REGISTER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, SEGMENT_REGISTER);
    exit_section_(b, m, SEG, r);
    return r;
  }

  // GENERAL_OP|SYSTEM_OP|VIRTUALIZATION_OP|X64_OP|FPU_OP|MMX_OP|SSE_OP|SSE2_OP|SSE3_OP|SSE4_OP|AVX_OP|AVX2_OP|AVX512_OP
  public static boolean Mnemonic(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Mnemonic")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MNEMONIC, "<mnemonic>");
    r = consumeTokenSmart(b, GENERAL_OP);
    if (!r) r = consumeTokenSmart(b, SYSTEM_OP);
    if (!r) r = consumeTokenSmart(b, VIRTUALIZATION_OP);
    if (!r) r = consumeTokenSmart(b, X64_OP);
    if (!r) r = consumeTokenSmart(b, FPU_OP);
    if (!r) r = consumeTokenSmart(b, MMX_OP);
    if (!r) r = consumeTokenSmart(b, SSE_OP);
    if (!r) r = consumeTokenSmart(b, SSE2_OP);
    if (!r) r = consumeTokenSmart(b, SSE3_OP);
    if (!r) r = consumeTokenSmart(b, SSE4_OP);
    if (!r) r = consumeTokenSmart(b, AVX_OP);
    if (!r) r = consumeTokenSmart(b, AVX2_OP);
    if (!r) r = consumeTokenSmart(b, AVX512_OP);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ID|MACRO_VAR_REF|MACRO_PARAM_REF
  public static boolean Identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Identifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IDENTIFIER, "<identifier>");
    r = consumeTokenSmart(b, ID);
    if (!r) r = consumeTokenSmart(b, MACRO_VAR_REF);
    if (!r) r = consumeTokenSmart(b, MACRO_PARAM_REF);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SIZE_TYPE? (LBL|ID|MACRO_PARAM_LBL_DEF)
  public static boolean LabelIdentifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelIdentifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LABEL_IDENTIFIER, "<label identifier>");
    r = LabelIdentifier_0(b, l + 1);
    r = r && LabelIdentifier_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SIZE_TYPE?
  private static boolean LabelIdentifier_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelIdentifier_0")) return false;
    consumeTokenSmart(b, SIZE_TYPE);
    return true;
  }

  // LBL|ID|MACRO_PARAM_LBL_DEF
  private static boolean LabelIdentifier_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelIdentifier_1")) return false;
    boolean r;
    r = consumeTokenSmart(b, LBL);
    if (!r) r = consumeTokenSmart(b, ID);
    if (!r) r = consumeTokenSmart(b, MACRO_PARAM_LBL_DEF);
    return r;
  }

  // END_DIRECTIVE_OP
  public static boolean EndDirective(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EndDirective")) return false;
    if (!nextTokenIsSmart(b, END_DIRECTIVE_OP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, END_DIRECTIVE_OP);
    exit_section_(b, m, END_DIRECTIVE, r);
    return r;
  }

}
