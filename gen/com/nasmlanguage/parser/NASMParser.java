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
    if (t == ASSIGN) {
      r = Assign(b, 0);
    }
    else if (t == CONDITIONAL) {
      r = Conditional(b, 0);
    }
    else if (t == CONSTANT) {
      r = Constant(b, 0);
    }
    else if (t == DATA) {
      r = Data(b, 0);
    }
    else if (t == DEFINE) {
      r = Define(b, 0);
    }
    else if (t == DIRECTIVE) {
      r = Directive(b, 0);
    }
    else if (t == DIRECTIVE_ARG) {
      r = DirectiveArg(b, 0);
    }
    else if (t == ERROR) {
      r = Error(b, 0);
    }
    else if (t == EXPR) {
      r = Expr(b, 0, -1);
    }
    else if (t == I_STRUC) {
      r = IStruc(b, 0);
    }
    else if (t == INCLUDE) {
      r = Include(b, 0);
    }
    else if (t == INSTRUCTION) {
      r = Instruction(b, 0);
    }
    else if (t == LABEL) {
      r = Label(b, 0);
    }
    else if (t == LABEL_DEF_MACRO) {
      r = LabelDefMacro(b, 0);
    }
    else if (t == MACRO) {
      r = Macro(b, 0);
    }
    else if (t == MACRO_LABEL) {
      r = MacroLabel(b, 0);
    }
    else if (t == MACRO_PARENTHESIS) {
      r = MacroParenthesis(b, 0);
    }
    else if (t == MAP_OPTION) {
      r = MapOption(b, 0);
    }
    else if (t == NUMERIC_EXPR) {
      r = NumericExpr(b, 0);
    }
    else if (t == PARENTHESIS_NUMERIC_EXPR) {
      r = ParenthesisNumericExpr(b, 0);
    }
    else if (t == PREPROCESSOR) {
      r = Preprocessor(b, 0);
    }
    else if (t == STRLEN) {
      r = Strlen(b, 0);
    }
    else if (t == STRUC) {
      r = Struc(b, 0);
    }
    else if (t == STRUCTURE) {
      r = Structure(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return NASMFile(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(NUMERIC_EXPR, PARENTHESIS_NUMERIC_EXPR),
    create_token_set_(ADDRESS, BITWISE_AND_EXPR, BITWISE_NOT_EXPR, BITWISE_OR_EXPR,
      BITWISE_XOR_EXPR, BIT_SHIFT_L_EXPR, BIT_SHIFT_R_EXPR, DIV_EXPR,
      END_DIRECTIVE, EXPR, IDENTIFIER, LABEL_IDENTIFIER,
      LOGICAL_AND_EXPR, LOGICAL_OR_EXPR, LOGICAL_XOR_EXPR, MACRO_CALL,
      MACRO_PARAM_REFERENCE, MACRO_VAR_REFERENCE, MINUS_EXPR, MODULUS_EXPR,
      MUL_EXPR, NUMERIC_LITERAL, PARENTHESIS_EXPR, PLUS_EXPR,
      REG, SEG, SEGMENT_ADDRESS, STR,
      STRUCTURE_FIELD),
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
  // Expr (EQUALEQUAL|NOTEQUAL|GREATERTHAN|LESSTHAN|GREATERTHANOREQUAL|LESSTHANOREQUAL) Expr
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

  // EQUALEQUAL|NOTEQUAL|GREATERTHAN|LESSTHAN|GREATERTHANOREQUAL|LESSTHANOREQUAL
  private static boolean Condition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Condition_1")) return false;
    boolean r;
    r = consumeToken(b, EQUALEQUAL);
    if (!r) r = consumeToken(b, NOTEQUAL);
    if (!r) r = consumeToken(b, GREATERTHAN);
    if (!r) r = consumeToken(b, LESSTHAN);
    if (!r) r = consumeToken(b, GREATERTHANOREQUAL);
    if (!r) r = consumeToken(b, LESSTHANOREQUAL);
    return r;
  }

  /* ********************************************************** */
  // (IF_TAG (Condition|Expr) CRLF* Element* ((ELIF_TAG Condition|ELSE_TAG) CRLF* Element*)* ENDIF_TAG CRLF*)
  //             | (IFMACRO_TAG ID MacroParams MacroDefaultParam? CRLF* (Preprocessor|Directive|Data|Instruction)* ENDIF_TAG)
  public static boolean Conditional(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional")) return false;
    if (!nextTokenIs(b, "<conditional>", IFMACRO_TAG, IF_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONDITIONAL, "<conditional>");
    r = Conditional_0(b, l + 1);
    if (!r) r = Conditional_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // IF_TAG (Condition|Expr) CRLF* Element* ((ELIF_TAG Condition|ELSE_TAG) CRLF* Element*)* ENDIF_TAG CRLF*
  private static boolean Conditional_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IF_TAG);
    r = r && Conditional_0_1(b, l + 1);
    r = r && Conditional_0_2(b, l + 1);
    r = r && Conditional_0_3(b, l + 1);
    r = r && Conditional_0_4(b, l + 1);
    r = r && consumeToken(b, ENDIF_TAG);
    r = r && Conditional_0_6(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Condition|Expr
  private static boolean Conditional_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_1")) return false;
    boolean r;
    r = Condition(b, l + 1);
    if (!r) r = Expr(b, l + 1, -1);
    return r;
  }

  // CRLF*
  private static boolean Conditional_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
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

  // ((ELIF_TAG Condition|ELSE_TAG) CRLF* Element*)*
  private static boolean Conditional_0_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Conditional_0_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Conditional_0_4", c)) break;
    }
    return true;
  }

  // (ELIF_TAG Condition|ELSE_TAG) CRLF* Element*
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

  // ELIF_TAG Condition|ELSE_TAG
  private static boolean Conditional_0_4_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_4_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Conditional_0_4_0_0_0(b, l + 1);
    if (!r) r = consumeToken(b, ELSE_TAG);
    exit_section_(b, m, null, r);
    return r;
  }

  // ELIF_TAG Condition
  private static boolean Conditional_0_4_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_4_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELIF_TAG);
    r = r && Condition(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // CRLF*
  private static boolean Conditional_0_4_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_4_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
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

  // CRLF*
  private static boolean Conditional_0_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_6")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Conditional_0_6", c)) break;
    }
    return true;
  }

  // IFMACRO_TAG ID MacroParams MacroDefaultParam? CRLF* (Preprocessor|Directive|Data|Instruction)* ENDIF_TAG
  private static boolean Conditional_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IFMACRO_TAG, ID);
    r = r && MacroParams(b, l + 1);
    r = r && Conditional_1_3(b, l + 1);
    r = r && Conditional_1_4(b, l + 1);
    r = r && Conditional_1_5(b, l + 1);
    r = r && consumeToken(b, ENDIF_TAG);
    exit_section_(b, m, null, r);
    return r;
  }

  // MacroDefaultParam?
  private static boolean Conditional_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_1_3")) return false;
    MacroDefaultParam(b, l + 1);
    return true;
  }

  // CRLF*
  private static boolean Conditional_1_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_1_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Conditional_1_4", c)) break;
    }
    return true;
  }

  // (Preprocessor|Directive|Data|Instruction)*
  private static boolean Conditional_1_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_1_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Conditional_1_5_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Conditional_1_5", c)) break;
    }
    return true;
  }

  // Preprocessor|Directive|Data|Instruction
  private static boolean Conditional_1_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_1_5_0")) return false;
    boolean r;
    r = Preprocessor(b, l + 1);
    if (!r) r = Directive(b, l + 1);
    if (!r) r = Data(b, l + 1);
    if (!r) r = Instruction(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // Identifier EQU ((MINUS|PLUS|BITWISE_NOT)? NumericExpr)* CRLF*
  public static boolean Constant(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Constant")) return false;
    if (!nextTokenIsSmart(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Identifier(b, l + 1);
    r = r && consumeToken(b, EQU);
    r = r && Constant_2(b, l + 1);
    r = r && Constant_3(b, l + 1);
    exit_section_(b, m, CONSTANT, r);
    return r;
  }

  // ((MINUS|PLUS|BITWISE_NOT)? NumericExpr)*
  private static boolean Constant_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Constant_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Constant_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Constant_2", c)) break;
    }
    return true;
  }

  // (MINUS|PLUS|BITWISE_NOT)? NumericExpr
  private static boolean Constant_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Constant_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Constant_2_0_0(b, l + 1);
    r = r && NumericExpr(b, l + 1);
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

  // CRLF*
  private static boolean Constant_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Constant_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Constant_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ID? DATA_OP DataValue CRLF*
  public static boolean Data(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Data")) return false;
    if (!nextTokenIs(b, "<data>", DATA_OP, ID)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DATA, "<data>");
    r = Data_0(b, l + 1);
    r = r && consumeToken(b, DATA_OP);
    r = r && DataValue(b, l + 1);
    r = r && Data_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ID?
  private static boolean Data_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Data_0")) return false;
    consumeToken(b, ID);
    return true;
  }

  // CRLF*
  private static boolean Data_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Data_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Data_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // (MINUS|PLUS|BITWISE_NOT)? (NumericExpr|STRING|LabelIdentifier) (SEPARATOR ((MINUS|PLUS)? (NumericExpr|STRING|LabelIdentifier)))*
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

  // NumericExpr|STRING|LabelIdentifier
  private static boolean DataValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_1")) return false;
    boolean r;
    r = NumericExpr(b, l + 1);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = LabelIdentifier(b, l + 1);
    return r;
  }

  // (SEPARATOR ((MINUS|PLUS)? (NumericExpr|STRING|LabelIdentifier)))*
  private static boolean DataValue_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!DataValue_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "DataValue_2", c)) break;
    }
    return true;
  }

  // SEPARATOR ((MINUS|PLUS)? (NumericExpr|STRING|LabelIdentifier))
  private static boolean DataValue_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEPARATOR);
    r = r && DataValue_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MINUS|PLUS)? (NumericExpr|STRING|LabelIdentifier)
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

  // NumericExpr|STRING|LabelIdentifier
  private static boolean DataValue_2_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_2_0_1_1")) return false;
    boolean r;
    r = NumericExpr(b, l + 1);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = LabelIdentifier(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // (DEFINE_TAG Identifier ROUND_L (Identifier (SEPARATOR Identifier)*)? ROUND_R Expr CRLF*)
  //         | (DEFINE_TAG Identifier (Expr|(PERCENT SQUARE_L Expr SQUARE_R)) CRLF*)
  public static boolean Define(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define")) return false;
    if (!nextTokenIs(b, DEFINE_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Define_0(b, l + 1);
    if (!r) r = Define_1(b, l + 1);
    exit_section_(b, m, DEFINE, r);
    return r;
  }

  // DEFINE_TAG Identifier ROUND_L (Identifier (SEPARATOR Identifier)*)? ROUND_R Expr CRLF*
  private static boolean Define_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DEFINE_TAG);
    r = r && Identifier(b, l + 1);
    r = r && consumeToken(b, ROUND_L);
    r = r && Define_0_3(b, l + 1);
    r = r && consumeToken(b, ROUND_R);
    r = r && Expr(b, l + 1, -1);
    r = r && Define_0_6(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (Identifier (SEPARATOR Identifier)*)?
  private static boolean Define_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_3")) return false;
    Define_0_3_0(b, l + 1);
    return true;
  }

  // Identifier (SEPARATOR Identifier)*
  private static boolean Define_0_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Identifier(b, l + 1);
    r = r && Define_0_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (SEPARATOR Identifier)*
  private static boolean Define_0_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_3_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Define_0_3_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Define_0_3_0_1", c)) break;
    }
    return true;
  }

  // SEPARATOR Identifier
  private static boolean Define_0_3_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_3_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEPARATOR);
    r = r && Identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // CRLF*
  private static boolean Define_0_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_6")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Define_0_6", c)) break;
    }
    return true;
  }

  // DEFINE_TAG Identifier (Expr|(PERCENT SQUARE_L Expr SQUARE_R)) CRLF*
  private static boolean Define_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DEFINE_TAG);
    r = r && Identifier(b, l + 1);
    r = r && Define_1_2(b, l + 1);
    r = r && Define_1_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Expr|(PERCENT SQUARE_L Expr SQUARE_R)
  private static boolean Define_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_1_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expr(b, l + 1, -1);
    if (!r) r = Define_1_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PERCENT SQUARE_L Expr SQUARE_R
  private static boolean Define_1_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_1_2_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PERCENT, SQUARE_L);
    r = r && Expr(b, l + 1, -1);
    r = r && consumeToken(b, SQUARE_R);
    exit_section_(b, m, null, r);
    return r;
  }

  // CRLF*
  private static boolean Define_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_1_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Define_1_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // (DirectiveDecl|DirectiveDeclBrackets|MapOption) CRLF*
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

  // CRLF*
  private static boolean Directive_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Directive_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Directive_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // (MINUS|PLUS)? (MacroCall|NumericLiteral|Address|SegmentAddress|Identifier)
  public static boolean DirectiveArg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveArg")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DIRECTIVE_ARG, "<directive arg>");
    r = DirectiveArg_0(b, l + 1);
    r = r && DirectiveArg_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (MINUS|PLUS)?
  private static boolean DirectiveArg_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveArg_0")) return false;
    DirectiveArg_0_0(b, l + 1);
    return true;
  }

  // MINUS|PLUS
  private static boolean DirectiveArg_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveArg_0_0")) return false;
    boolean r;
    r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, PLUS);
    return r;
  }

  // MacroCall|NumericLiteral|Address|SegmentAddress|Identifier
  private static boolean DirectiveArg_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveArg_1")) return false;
    boolean r;
    r = MacroCall(b, l + 1);
    if (!r) r = NumericLiteral(b, l + 1);
    if (!r) r = Address(b, l + 1);
    if (!r) r = SegmentAddress(b, l + 1);
    if (!r) r = Identifier(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // DIRECTIVE_OP (DirectiveArg SEPARATOR?)*
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

  // (DirectiveArg SEPARATOR?)*
  private static boolean DirectiveDecl_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveDecl_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!DirectiveDecl_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "DirectiveDecl_1", c)) break;
    }
    return true;
  }

  // DirectiveArg SEPARATOR?
  private static boolean DirectiveDecl_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveDecl_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DirectiveArg(b, l + 1);
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
  // SQUARE_L DIRECTIVE_OP DirectiveArg* SQUARE_R
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

  // DirectiveArg*
  private static boolean DirectiveDeclBrackets_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveDeclBrackets_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!DirectiveArg(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "DirectiveDeclBrackets_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // Section
  //                 | Segment
  //                 | Label
  //                 | Preprocessor
  //                 | Directive
  //                 | EndDirective
  //                 | Constant
  //                 | Structure
  //                 | Instruction
  //                 | Data
  //                 | COMMENT
  //                 | CRLF
  //                 | ID
  static boolean Element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Element")) return false;
    boolean r;
    r = Section(b, l + 1);
    if (!r) r = Segment(b, l + 1);
    if (!r) r = Label(b, l + 1);
    if (!r) r = Preprocessor(b, l + 1);
    if (!r) r = Directive(b, l + 1);
    if (!r) r = EndDirective(b, l + 1);
    if (!r) r = Constant(b, l + 1);
    if (!r) r = Structure(b, l + 1);
    if (!r) r = Instruction(b, l + 1);
    if (!r) r = Data(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, CRLF);
    if (!r) r = consumeToken(b, ID);
    return r;
  }

  /* ********************************************************** */
  // ERROR_TAG CRLF*
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

  // CRLF*
  private static boolean Error_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Error_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Error_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ISTRUC_TAG Identifier CRLF* (AT_TAG (StructureField|LabelIdentifier|Identifier) SEPARATOR DATA_OP DataValue CRLF*)* IEND_TAG
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

  // CRLF*
  private static boolean IStruc_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStruc_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "IStruc_2", c)) break;
    }
    return true;
  }

  // (AT_TAG (StructureField|LabelIdentifier|Identifier) SEPARATOR DATA_OP DataValue CRLF*)*
  private static boolean IStruc_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStruc_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!IStruc_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "IStruc_3", c)) break;
    }
    return true;
  }

  // AT_TAG (StructureField|LabelIdentifier|Identifier) SEPARATOR DATA_OP DataValue CRLF*
  private static boolean IStruc_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStruc_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AT_TAG);
    r = r && IStruc_3_0_1(b, l + 1);
    r = r && consumeTokens(b, 0, SEPARATOR, DATA_OP);
    r = r && DataValue(b, l + 1);
    r = r && IStruc_3_0_5(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // StructureField|LabelIdentifier|Identifier
  private static boolean IStruc_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStruc_3_0_1")) return false;
    boolean r;
    r = StructureField(b, l + 1);
    if (!r) r = LabelIdentifier(b, l + 1);
    if (!r) r = Identifier(b, l + 1);
    return r;
  }

  // CRLF*
  private static boolean IStruc_3_0_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStruc_3_0_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "IStruc_3_0_5", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // INCLUDE_TAG STRING
  public static boolean Include(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Include")) return false;
    if (!nextTokenIs(b, INCLUDE_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, INCLUDE_TAG, STRING);
    exit_section_(b, m, INCLUDE, r);
    return r;
  }

  /* ********************************************************** */
  // (OP_PREFIX? (Mnemonic|(ID COMMENT)) (((((BITWISE_NOT|MINUS|PLUS)? Expr) SEPARATOR)* ((BITWISE_NOT|MINUS|PLUS)? Expr))|(SIZE_TYPE (BITWISE_NOT|MINUS|PLUS)? ROUND_L Expr ROUND_R))?) CRLF*
  public static boolean Instruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INSTRUCTION, "<instruction>");
    r = Instruction_0(b, l + 1);
    r = r && Instruction_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // OP_PREFIX? (Mnemonic|(ID COMMENT)) (((((BITWISE_NOT|MINUS|PLUS)? Expr) SEPARATOR)* ((BITWISE_NOT|MINUS|PLUS)? Expr))|(SIZE_TYPE (BITWISE_NOT|MINUS|PLUS)? ROUND_L Expr ROUND_R))?
  private static boolean Instruction_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_0_0(b, l + 1);
    r = r && Instruction_0_1(b, l + 1);
    r = r && Instruction_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // OP_PREFIX?
  private static boolean Instruction_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_0")) return false;
    consumeToken(b, OP_PREFIX);
    return true;
  }

  // Mnemonic|(ID COMMENT)
  private static boolean Instruction_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Mnemonic(b, l + 1);
    if (!r) r = Instruction_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ID COMMENT
  private static boolean Instruction_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ID, COMMENT);
    exit_section_(b, m, null, r);
    return r;
  }

  // (((((BITWISE_NOT|MINUS|PLUS)? Expr) SEPARATOR)* ((BITWISE_NOT|MINUS|PLUS)? Expr))|(SIZE_TYPE (BITWISE_NOT|MINUS|PLUS)? ROUND_L Expr ROUND_R))?
  private static boolean Instruction_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_2")) return false;
    Instruction_0_2_0(b, l + 1);
    return true;
  }

  // ((((BITWISE_NOT|MINUS|PLUS)? Expr) SEPARATOR)* ((BITWISE_NOT|MINUS|PLUS)? Expr))|(SIZE_TYPE (BITWISE_NOT|MINUS|PLUS)? ROUND_L Expr ROUND_R)
  private static boolean Instruction_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_0_2_0_0(b, l + 1);
    if (!r) r = Instruction_0_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (((BITWISE_NOT|MINUS|PLUS)? Expr) SEPARATOR)* ((BITWISE_NOT|MINUS|PLUS)? Expr)
  private static boolean Instruction_0_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_0_2_0_0_0(b, l + 1);
    r = r && Instruction_0_2_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (((BITWISE_NOT|MINUS|PLUS)? Expr) SEPARATOR)*
  private static boolean Instruction_0_2_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_2_0_0_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Instruction_0_2_0_0_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Instruction_0_2_0_0_0", c)) break;
    }
    return true;
  }

  // ((BITWISE_NOT|MINUS|PLUS)? Expr) SEPARATOR
  private static boolean Instruction_0_2_0_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_2_0_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_0_2_0_0_0_0_0(b, l + 1);
    r = r && consumeToken(b, SEPARATOR);
    exit_section_(b, m, null, r);
    return r;
  }

  // (BITWISE_NOT|MINUS|PLUS)? Expr
  private static boolean Instruction_0_2_0_0_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_2_0_0_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_0_2_0_0_0_0_0_0(b, l + 1);
    r = r && Expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (BITWISE_NOT|MINUS|PLUS)?
  private static boolean Instruction_0_2_0_0_0_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_2_0_0_0_0_0_0")) return false;
    Instruction_0_2_0_0_0_0_0_0_0(b, l + 1);
    return true;
  }

  // BITWISE_NOT|MINUS|PLUS
  private static boolean Instruction_0_2_0_0_0_0_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_2_0_0_0_0_0_0_0")) return false;
    boolean r;
    r = consumeToken(b, BITWISE_NOT);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, PLUS);
    return r;
  }

  // (BITWISE_NOT|MINUS|PLUS)? Expr
  private static boolean Instruction_0_2_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_2_0_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_0_2_0_0_1_0(b, l + 1);
    r = r && Expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (BITWISE_NOT|MINUS|PLUS)?
  private static boolean Instruction_0_2_0_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_2_0_0_1_0")) return false;
    Instruction_0_2_0_0_1_0_0(b, l + 1);
    return true;
  }

  // BITWISE_NOT|MINUS|PLUS
  private static boolean Instruction_0_2_0_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_2_0_0_1_0_0")) return false;
    boolean r;
    r = consumeToken(b, BITWISE_NOT);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, PLUS);
    return r;
  }

  // SIZE_TYPE (BITWISE_NOT|MINUS|PLUS)? ROUND_L Expr ROUND_R
  private static boolean Instruction_0_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_2_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SIZE_TYPE);
    r = r && Instruction_0_2_0_1_1(b, l + 1);
    r = r && consumeToken(b, ROUND_L);
    r = r && Expr(b, l + 1, -1);
    r = r && consumeToken(b, ROUND_R);
    exit_section_(b, m, null, r);
    return r;
  }

  // (BITWISE_NOT|MINUS|PLUS)?
  private static boolean Instruction_0_2_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_2_0_1_1")) return false;
    Instruction_0_2_0_1_1_0(b, l + 1);
    return true;
  }

  // BITWISE_NOT|MINUS|PLUS
  private static boolean Instruction_0_2_0_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_2_0_1_1_0")) return false;
    boolean r;
    r = consumeToken(b, BITWISE_NOT);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, PLUS);
    return r;
  }

  // CRLF*
  private static boolean Instruction_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Instruction_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // (LBL_DEF (Instruction|Data|Structure)? CRLF*)|(LabelDefMacro (Instruction|Data|Structure)? CRLF*)
  public static boolean Label(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label")) return false;
    if (!nextTokenIs(b, "<label>", ID, LBL_DEF)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LABEL, "<label>");
    r = Label_0(b, l + 1);
    if (!r) r = Label_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LBL_DEF (Instruction|Data|Structure)? CRLF*
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

  // (Instruction|Data|Structure)?
  private static boolean Label_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_0_1")) return false;
    Label_0_1_0(b, l + 1);
    return true;
  }

  // Instruction|Data|Structure
  private static boolean Label_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_0_1_0")) return false;
    boolean r;
    r = Instruction(b, l + 1);
    if (!r) r = Data(b, l + 1);
    if (!r) r = Structure(b, l + 1);
    return r;
  }

  // CRLF*
  private static boolean Label_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Label_0_2", c)) break;
    }
    return true;
  }

  // LabelDefMacro (Instruction|Data|Structure)? CRLF*
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

  // (Instruction|Data|Structure)?
  private static boolean Label_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_1_1")) return false;
    Label_1_1_0(b, l + 1);
    return true;
  }

  // Instruction|Data|Structure
  private static boolean Label_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_1_1_0")) return false;
    boolean r;
    r = Instruction(b, l + 1);
    if (!r) r = Data(b, l + 1);
    if (!r) r = Structure(b, l + 1);
    return r;
  }

  // CRLF*
  private static boolean Label_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_1_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Label_1_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ID (ROUND_L (NumericExpr (SEPARATOR NumericExpr)*)? ROUND_R) COLON CRLF*
  public static boolean LabelDefMacro(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelDefMacro")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    r = r && LabelDefMacro_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && LabelDefMacro_3(b, l + 1);
    exit_section_(b, m, LABEL_DEF_MACRO, r);
    return r;
  }

  // ROUND_L (NumericExpr (SEPARATOR NumericExpr)*)? ROUND_R
  private static boolean LabelDefMacro_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelDefMacro_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ROUND_L);
    r = r && LabelDefMacro_1_1(b, l + 1);
    r = r && consumeToken(b, ROUND_R);
    exit_section_(b, m, null, r);
    return r;
  }

  // (NumericExpr (SEPARATOR NumericExpr)*)?
  private static boolean LabelDefMacro_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelDefMacro_1_1")) return false;
    LabelDefMacro_1_1_0(b, l + 1);
    return true;
  }

  // NumericExpr (SEPARATOR NumericExpr)*
  private static boolean LabelDefMacro_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelDefMacro_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericExpr(b, l + 1);
    r = r && LabelDefMacro_1_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (SEPARATOR NumericExpr)*
  private static boolean LabelDefMacro_1_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelDefMacro_1_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!LabelDefMacro_1_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "LabelDefMacro_1_1_0_1", c)) break;
    }
    return true;
  }

  // SEPARATOR NumericExpr
  private static boolean LabelDefMacro_1_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelDefMacro_1_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEPARATOR);
    r = r && NumericExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // CRLF*
  private static boolean LabelDefMacro_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelDefMacro_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "LabelDefMacro_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // MACRO_TAG (Identifier MacroParams MacroDefaultParam? CRLF* ((MacroLabel|Data|Instruction|Directive|Preprocessor|Label|ID) CRLF*)*) MACRO_END_TAG
  public static boolean Macro(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro")) return false;
    if (!nextTokenIs(b, MACRO_TAG)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MACRO, null);
    r = consumeToken(b, MACRO_TAG);
    p = r; // pin = 1
    r = r && report_error_(b, Macro_1(b, l + 1));
    r = p && consumeToken(b, MACRO_END_TAG) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // Identifier MacroParams MacroDefaultParam? CRLF* ((MacroLabel|Data|Instruction|Directive|Preprocessor|Label|ID) CRLF*)*
  private static boolean Macro_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Identifier(b, l + 1);
    r = r && MacroParams(b, l + 1);
    r = r && Macro_1_2(b, l + 1);
    r = r && Macro_1_3(b, l + 1);
    r = r && Macro_1_4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // MacroDefaultParam?
  private static boolean Macro_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro_1_2")) return false;
    MacroDefaultParam(b, l + 1);
    return true;
  }

  // CRLF*
  private static boolean Macro_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro_1_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Macro_1_3", c)) break;
    }
    return true;
  }

  // ((MacroLabel|Data|Instruction|Directive|Preprocessor|Label|ID) CRLF*)*
  private static boolean Macro_1_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro_1_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Macro_1_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Macro_1_4", c)) break;
    }
    return true;
  }

  // (MacroLabel|Data|Instruction|Directive|Preprocessor|Label|ID) CRLF*
  private static boolean Macro_1_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro_1_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Macro_1_4_0_0(b, l + 1);
    r = r && Macro_1_4_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // MacroLabel|Data|Instruction|Directive|Preprocessor|Label|ID
  private static boolean Macro_1_4_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro_1_4_0_0")) return false;
    boolean r;
    r = MacroLabel(b, l + 1);
    if (!r) r = Data(b, l + 1);
    if (!r) r = Instruction(b, l + 1);
    if (!r) r = Directive(b, l + 1);
    if (!r) r = Preprocessor(b, l + 1);
    if (!r) r = Label(b, l + 1);
    if (!r) r = consumeToken(b, ID);
    return r;
  }

  // CRLF*
  private static boolean Macro_1_4_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro_1_4_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Macro_1_4_0_1", c)) break;
    }
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
  // MACRO_PARAM_LBL_DEF (Instruction|Data)? CRLF*
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

  // (Instruction|Data)?
  private static boolean MacroLabel_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroLabel_1")) return false;
    MacroLabel_1_0(b, l + 1);
    return true;
  }

  // Instruction|Data
  private static boolean MacroLabel_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroLabel_1_0")) return false;
    boolean r;
    r = Instruction(b, l + 1);
    if (!r) r = Data(b, l + 1);
    return r;
  }

  // CRLF*
  private static boolean MacroLabel_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroLabel_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "MacroLabel_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // (NumericLiteral MINUS NumericLiteral TIMES?)|(NumericLiteral MINUS TIMES)|(NumericLiteral PLUS?)
  static boolean MacroParams(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MacroParams_0(b, l + 1);
    if (!r) r = MacroParams_1(b, l + 1);
    if (!r) r = MacroParams_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NumericLiteral MINUS NumericLiteral TIMES?
  private static boolean MacroParams_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral(b, l + 1);
    r = r && consumeToken(b, MINUS);
    r = r && NumericLiteral(b, l + 1);
    r = r && MacroParams_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // TIMES?
  private static boolean MacroParams_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams_0_3")) return false;
    consumeToken(b, TIMES);
    return true;
  }

  // NumericLiteral MINUS TIMES
  private static boolean MacroParams_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral(b, l + 1);
    r = r && consumeTokens(b, 0, MINUS, TIMES);
    exit_section_(b, m, null, r);
    return r;
  }

  // NumericLiteral PLUS?
  private static boolean MacroParams_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral(b, l + 1);
    r = r && MacroParams_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PLUS?
  private static boolean MacroParams_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams_2_1")) return false;
    consumeToken(b, PLUS);
    return true;
  }

  /* ********************************************************** */
  // ROUND_L ((Expr|Mnemonic) (SEPARATOR (Expr|Mnemonic))*)? ROUND_R
  public static boolean MacroParenthesis(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParenthesis")) return false;
    if (!nextTokenIs(b, ROUND_L)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MACRO_PARENTHESIS, null);
    r = consumeToken(b, ROUND_L);
    p = r; // pin = 1
    r = r && report_error_(b, MacroParenthesis_1(b, l + 1));
    r = p && consumeToken(b, ROUND_R) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ((Expr|Mnemonic) (SEPARATOR (Expr|Mnemonic))*)?
  private static boolean MacroParenthesis_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParenthesis_1")) return false;
    MacroParenthesis_1_0(b, l + 1);
    return true;
  }

  // (Expr|Mnemonic) (SEPARATOR (Expr|Mnemonic))*
  private static boolean MacroParenthesis_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParenthesis_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = MacroParenthesis_1_0_0(b, l + 1);
    p = r; // pin = 1
    r = r && MacroParenthesis_1_0_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // Expr|Mnemonic
  private static boolean MacroParenthesis_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParenthesis_1_0_0")) return false;
    boolean r;
    r = Expr(b, l + 1, -1);
    if (!r) r = Mnemonic(b, l + 1);
    return r;
  }

  // (SEPARATOR (Expr|Mnemonic))*
  private static boolean MacroParenthesis_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParenthesis_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!MacroParenthesis_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "MacroParenthesis_1_0_1", c)) break;
    }
    return true;
  }

  // SEPARATOR (Expr|Mnemonic)
  private static boolean MacroParenthesis_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParenthesis_1_0_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, SEPARATOR);
    p = r; // pin = 1
    r = r && MacroParenthesis_1_0_1_0_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // Expr|Mnemonic
  private static boolean MacroParenthesis_1_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParenthesis_1_0_1_0_1")) return false;
    boolean r;
    r = Expr(b, l + 1, -1);
    if (!r) r = Mnemonic(b, l + 1);
    return r;
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
    Marker m = enter_section_(b);
    r = consumeToken(b, "map");
    if (!r) r = consumeToken(b, "MAP");
    exit_section_(b, m, null, r);
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
    Marker m = enter_section_(b);
    r = consumeToken(b, "all");
    if (!r) r = consumeToken(b, "ALL");
    exit_section_(b, m, null, r);
    return r;
  }

  // 'brief'|'BRIEF'
  private static boolean MapOption_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapOption_2_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "brief");
    if (!r) r = consumeToken(b, "BRIEF");
    exit_section_(b, m, null, r);
    return r;
  }

  // 'sections'|'SECTIONS'
  private static boolean MapOption_2_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapOption_2_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "sections");
    if (!r) r = consumeToken(b, "SECTIONS");
    exit_section_(b, m, null, r);
    return r;
  }

  // 'segments'|'SEGMENTS'
  private static boolean MapOption_2_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapOption_2_0_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "segments");
    if (!r) r = consumeToken(b, "SEGMENTS");
    exit_section_(b, m, null, r);
    return r;
  }

  // 'symbols'|'SYMBOLS'
  private static boolean MapOption_2_0_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapOption_2_0_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "symbols");
    if (!r) r = consumeToken(b, "SYMBOLS");
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // GENERAL_OP|SYSTEM_OP|VIRTUALIZATION_OP|X64_OP|FPU_OP|MMX_OP|SSE_OP|SSE2_OP|SSE3_OP|SSE4_OP|AVX_OP|AVX2_OP|AVX512_OP
  static boolean Mnemonic(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Mnemonic")) return false;
    boolean r;
    r = consumeToken(b, GENERAL_OP);
    if (!r) r = consumeToken(b, SYSTEM_OP);
    if (!r) r = consumeToken(b, VIRTUALIZATION_OP);
    if (!r) r = consumeToken(b, X64_OP);
    if (!r) r = consumeToken(b, FPU_OP);
    if (!r) r = consumeToken(b, MMX_OP);
    if (!r) r = consumeToken(b, SSE_OP);
    if (!r) r = consumeToken(b, SSE2_OP);
    if (!r) r = consumeToken(b, SSE3_OP);
    if (!r) r = consumeToken(b, SSE4_OP);
    if (!r) r = consumeToken(b, AVX_OP);
    if (!r) r = consumeToken(b, AVX2_OP);
    if (!r) r = consumeToken(b, AVX512_OP);
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
  // ParenthesisNumericExpr
  //         | MulExpr
  //         | DivExpr
  //         | PlusExpr
  //         | MinusExpr
  //         | ModulusExpr
  //         | BitShiftLExpr
  //         | BitShiftRExpr
  //         | BitwiseANDExpr
  //         | BitwiseORExpr
  //         | BitwiseXORExpr
  //         | BitwiseNOTExpr
  //         | LogicalANDExpr
  //         | LogicalORExpr
  //         | LogicalXORExpr
  //         | NumericLiteral
  //         | SegmentAddress
  //         | Str
  //         | StructureField
  //         | MacroCall
  //         | MacroParamReference
  //         | MacroVarReference
  //         | Address
  //         | Identifier
  //         | LabelIdentifier
  public static boolean NumericExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, NUMERIC_EXPR, "<numeric expr>");
    r = ParenthesisNumericExpr(b, l + 1);
    if (!r) r = Expr(b, l + 1, 0);
    if (!r) r = Expr(b, l + 1, 1);
    if (!r) r = Expr(b, l + 1, 2);
    if (!r) r = Expr(b, l + 1, 3);
    if (!r) r = Expr(b, l + 1, 4);
    if (!r) r = Expr(b, l + 1, 5);
    if (!r) r = Expr(b, l + 1, 6);
    if (!r) r = Expr(b, l + 1, 7);
    if (!r) r = Expr(b, l + 1, 8);
    if (!r) r = Expr(b, l + 1, 9);
    if (!r) r = BitwiseNOTExpr(b, l + 1);
    if (!r) r = Expr(b, l + 1, 11);
    if (!r) r = Expr(b, l + 1, 12);
    if (!r) r = Expr(b, l + 1, 13);
    if (!r) r = NumericLiteral(b, l + 1);
    if (!r) r = SegmentAddress(b, l + 1);
    if (!r) r = Str(b, l + 1);
    if (!r) r = StructureField(b, l + 1);
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = MacroParamReference(b, l + 1);
    if (!r) r = MacroVarReference(b, l + 1);
    if (!r) r = Address(b, l + 1);
    if (!r) r = Identifier(b, l + 1);
    if (!r) r = LabelIdentifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ROUND_L NumericExpr ROUND_R
  public static boolean ParenthesisNumericExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParenthesisNumericExpr")) return false;
    if (!nextTokenIs(b, ROUND_L)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PARENTHESIS_NUMERIC_EXPR, null);
    r = consumeToken(b, ROUND_L);
    p = r; // pin = 1
    r = r && report_error_(b, NumericExpr(b, l + 1));
    r = p && consumeToken(b, ROUND_R) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // Include
  //                 | Define
  //                 | Assign
  //                 | Macro
  //                 | MacroCall
  //                 | Strlen
  //                 | Error
  //                 | Conditional
  //                 | (PREPROCESSOR_OP Identifier Expr)
  public static boolean Preprocessor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Preprocessor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PREPROCESSOR, "<preprocessor>");
    r = Include(b, l + 1);
    if (!r) r = Define(b, l + 1);
    if (!r) r = Assign(b, l + 1);
    if (!r) r = Macro(b, l + 1);
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = Strlen(b, l + 1);
    if (!r) r = Error(b, l + 1);
    if (!r) r = Conditional(b, l + 1);
    if (!r) r = Preprocessor_8(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // PREPROCESSOR_OP Identifier Expr
  private static boolean Preprocessor_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Preprocessor_8")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PREPROCESSOR_OP);
    r = r && Identifier(b, l + 1);
    r = r && Expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ((SQUARE_L SECTION SQUARE_R) | SECTION) CRLF*
  static boolean Section(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Section")) return false;
    if (!nextTokenIs(b, "", SECTION, SQUARE_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Section_0(b, l + 1);
    r = r && Section_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (SQUARE_L SECTION SQUARE_R) | SECTION
  private static boolean Section_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Section_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Section_0_0(b, l + 1);
    if (!r) r = consumeToken(b, SECTION);
    exit_section_(b, m, null, r);
    return r;
  }

  // SQUARE_L SECTION SQUARE_R
  private static boolean Section_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Section_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SQUARE_L, SECTION, SQUARE_R);
    exit_section_(b, m, null, r);
    return r;
  }

  // CRLF*
  private static boolean Section_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Section_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Section_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ((SQUARE_L SEGMENT SQUARE_R) | SEGMENT) CRLF*
  static boolean Segment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Segment")) return false;
    if (!nextTokenIs(b, "", SEGMENT, SQUARE_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Segment_0(b, l + 1);
    r = r && Segment_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (SQUARE_L SEGMENT SQUARE_R) | SEGMENT
  private static boolean Segment_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Segment_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Segment_0_0(b, l + 1);
    if (!r) r = consumeToken(b, SEGMENT);
    exit_section_(b, m, null, r);
    return r;
  }

  // SQUARE_L SEGMENT SQUARE_R
  private static boolean Segment_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Segment_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SQUARE_L, SEGMENT, SQUARE_R);
    exit_section_(b, m, null, r);
    return r;
  }

  // CRLF*
  private static boolean Segment_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Segment_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Segment_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // STRLEN_TAG Identifier (STRING|ID) CRLF*
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

  // CRLF*
  private static boolean Strlen_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Strlen_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Strlen_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // STRUC_TAG Identifier CRLF* ((((LBL_DEF|LabelIdentifier) DATA_OP ((MINUS|PLUS)? (NumericExpr|Identifier)))|(LabelIdentifier|LBL_DEF)) CRLF*)* ENDSTRUC_TAG
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

  // CRLF*
  private static boolean Struc_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Struc_2", c)) break;
    }
    return true;
  }

  // ((((LBL_DEF|LabelIdentifier) DATA_OP ((MINUS|PLUS)? (NumericExpr|Identifier)))|(LabelIdentifier|LBL_DEF)) CRLF*)*
  private static boolean Struc_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Struc_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Struc_3", c)) break;
    }
    return true;
  }

  // (((LBL_DEF|LabelIdentifier) DATA_OP ((MINUS|PLUS)? (NumericExpr|Identifier)))|(LabelIdentifier|LBL_DEF)) CRLF*
  private static boolean Struc_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Struc_3_0_0(b, l + 1);
    r = r && Struc_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((LBL_DEF|LabelIdentifier) DATA_OP ((MINUS|PLUS)? (NumericExpr|Identifier)))|(LabelIdentifier|LBL_DEF)
  private static boolean Struc_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Struc_3_0_0_0(b, l + 1);
    if (!r) r = Struc_3_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LBL_DEF|LabelIdentifier) DATA_OP ((MINUS|PLUS)? (NumericExpr|Identifier))
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

  // (MINUS|PLUS)? (NumericExpr|Identifier)
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

  // NumericExpr|Identifier
  private static boolean Struc_3_0_0_0_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3_0_0_0_2_1")) return false;
    boolean r;
    r = NumericExpr(b, l + 1);
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

  // CRLF*
  private static boolean Struc_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Struc_3_0_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // (Struc|IStruc) CRLF*
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

  // CRLF*
  private static boolean Structure_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Structure_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Structure_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // Expression root: Expr
  // Operator priority table:
  // 0: ATOM(ParenthesisExpr)
  // 1: BINARY(MulExpr)
  // 2: BINARY(DivExpr)
  // 3: BINARY(PlusExpr)
  // 4: BINARY(MinusExpr)
  // 5: BINARY(ModulusExpr)
  // 6: BINARY(BitShiftLExpr)
  // 7: BINARY(BitShiftRExpr)
  // 8: BINARY(BitwiseANDExpr)
  // 9: BINARY(BitwiseORExpr)
  // 10: BINARY(BitwiseXORExpr)
  // 11: ATOM(BitwiseNOTExpr)
  // 12: BINARY(LogicalANDExpr)
  // 13: BINARY(LogicalORExpr)
  // 14: BINARY(LogicalXORExpr)
  // 15: ATOM(NumericLiteral)
  // 16: ATOM(SegmentAddress)
  // 17: ATOM(Str)
  // 18: ATOM(StructureField)
  // 19: ATOM(MacroCall)
  // 20: ATOM(MacroParamReference)
  // 21: ATOM(MacroVarReference)
  // 22: ATOM(Address)
  // 23: ATOM(Reg)
  // 24: ATOM(Seg)
  // 25: ATOM(Identifier)
  // 26: ATOM(LabelIdentifier)
  // 27: ATOM(EndDirective)
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
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = MacroParamReference(b, l + 1);
    if (!r) r = MacroVarReference(b, l + 1);
    if (!r) r = Address(b, l + 1);
    if (!r) r = Reg(b, l + 1);
    if (!r) r = Seg(b, l + 1);
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
      if (g < 1 && consumeTokenSmart(b, TIMES)) {
        r = Expr(b, l, 1);
        exit_section_(b, l, m, MUL_EXPR, r, true, null);
      }
      else if (g < 2 && consumeTokenSmart(b, DIVIDE)) {
        r = Expr(b, l, 2);
        exit_section_(b, l, m, DIV_EXPR, r, true, null);
      }
      else if (g < 3 && consumeTokenSmart(b, PLUS)) {
        r = Expr(b, l, 3);
        exit_section_(b, l, m, PLUS_EXPR, r, true, null);
      }
      else if (g < 4 && consumeTokenSmart(b, MINUS)) {
        r = Expr(b, l, 4);
        exit_section_(b, l, m, MINUS_EXPR, r, true, null);
      }
      else if (g < 5 && consumeTokenSmart(b, PERCENT)) {
        r = Expr(b, l, 5);
        exit_section_(b, l, m, MODULUS_EXPR, r, true, null);
      }
      else if (g < 6 && consumeTokenSmart(b, BITSHIFT_L)) {
        r = Expr(b, l, 6);
        exit_section_(b, l, m, BIT_SHIFT_L_EXPR, r, true, null);
      }
      else if (g < 7 && consumeTokenSmart(b, BITSHIFT_R)) {
        r = Expr(b, l, 7);
        exit_section_(b, l, m, BIT_SHIFT_R_EXPR, r, true, null);
      }
      else if (g < 8 && consumeTokenSmart(b, BITWISE_AND)) {
        r = Expr(b, l, 8);
        exit_section_(b, l, m, BITWISE_AND_EXPR, r, true, null);
      }
      else if (g < 9 && consumeTokenSmart(b, BITWISE_OR)) {
        r = Expr(b, l, 9);
        exit_section_(b, l, m, BITWISE_OR_EXPR, r, true, null);
      }
      else if (g < 10 && consumeTokenSmart(b, BITWISE_XOR)) {
        r = Expr(b, l, 10);
        exit_section_(b, l, m, BITWISE_XOR_EXPR, r, true, null);
      }
      else if (g < 12 && consumeTokenSmart(b, LOGICAL_AND)) {
        r = Expr(b, l, 12);
        exit_section_(b, l, m, LOGICAL_AND_EXPR, r, true, null);
      }
      else if (g < 13 && consumeTokenSmart(b, LOGICAL_OR)) {
        r = Expr(b, l, 13);
        exit_section_(b, l, m, LOGICAL_OR_EXPR, r, true, null);
      }
      else if (g < 14 && consumeTokenSmart(b, LOGICAL_XOR)) {
        r = Expr(b, l, 14);
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

  // BITWISE_NOT NumericExpr
  public static boolean BitwiseNOTExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BitwiseNOTExpr")) return false;
    if (!nextTokenIsSmart(b, BITWISE_NOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, BITWISE_NOT);
    r = r && NumericExpr(b, l + 1);
    exit_section_(b, m, BITWISE_NOT_EXPR, r);
    return r;
  }

  // (SIZE_TYPE (BITWISE_NOT|MINUS|PLUS)?)? (BINARY|ZEROES|HEXADECIMAL|DECIMAL|CHARACTER)
  public static boolean NumericLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NUMERIC_LITERAL, "<numeric literal>");
    r = NumericLiteral_0(b, l + 1);
    r = r && NumericLiteral_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (SIZE_TYPE (BITWISE_NOT|MINUS|PLUS)?)?
  private static boolean NumericLiteral_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral_0")) return false;
    NumericLiteral_0_0(b, l + 1);
    return true;
  }

  // SIZE_TYPE (BITWISE_NOT|MINUS|PLUS)?
  private static boolean NumericLiteral_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, SIZE_TYPE);
    r = r && NumericLiteral_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (BITWISE_NOT|MINUS|PLUS)?
  private static boolean NumericLiteral_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral_0_0_1")) return false;
    NumericLiteral_0_0_1_0(b, l + 1);
    return true;
  }

  // BITWISE_NOT|MINUS|PLUS
  private static boolean NumericLiteral_0_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral_0_0_1_0")) return false;
    boolean r;
    r = consumeTokenSmart(b, BITWISE_NOT);
    if (!r) r = consumeTokenSmart(b, MINUS);
    if (!r) r = consumeTokenSmart(b, PLUS);
    return r;
  }

  // BINARY|ZEROES|HEXADECIMAL|DECIMAL|CHARACTER
  private static boolean NumericLiteral_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral_1")) return false;
    boolean r;
    r = consumeTokenSmart(b, BINARY);
    if (!r) r = consumeTokenSmart(b, ZEROES);
    if (!r) r = consumeTokenSmart(b, HEXADECIMAL);
    if (!r) r = consumeTokenSmart(b, DECIMAL);
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

  // STRUCT_FIELD
  public static boolean StructureField(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StructureField")) return false;
    if (!nextTokenIsSmart(b, STRUCT_FIELD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, STRUCT_FIELD);
    exit_section_(b, m, STRUCTURE_FIELD, r);
    return r;
  }

  // SIZE_TYPE? (Identifier (MacroParenthesis | ((Expr|Mnemonic) (SEPARATOR (Expr|Mnemonic))*)))
  public static boolean MacroCall(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall")) return false;
    if (!nextTokenIsSmart(b, ID, SIZE_TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, MACRO_CALL, "<macro call>");
    r = MacroCall_0(b, l + 1);
    r = r && MacroCall_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SIZE_TYPE?
  private static boolean MacroCall_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall_0")) return false;
    consumeTokenSmart(b, SIZE_TYPE);
    return true;
  }

  // Identifier (MacroParenthesis | ((Expr|Mnemonic) (SEPARATOR (Expr|Mnemonic))*))
  private static boolean MacroCall_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Identifier(b, l + 1);
    r = r && MacroCall_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // MacroParenthesis | ((Expr|Mnemonic) (SEPARATOR (Expr|Mnemonic))*)
  private static boolean MacroCall_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MacroParenthesis(b, l + 1);
    if (!r) r = MacroCall_1_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (Expr|Mnemonic) (SEPARATOR (Expr|Mnemonic))*
  private static boolean MacroCall_1_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall_1_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MacroCall_1_1_1_0(b, l + 1);
    r = r && MacroCall_1_1_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Expr|Mnemonic
  private static boolean MacroCall_1_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall_1_1_1_0")) return false;
    boolean r;
    r = Expr(b, l + 1, -1);
    if (!r) r = Mnemonic(b, l + 1);
    return r;
  }

  // (SEPARATOR (Expr|Mnemonic))*
  private static boolean MacroCall_1_1_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall_1_1_1_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!MacroCall_1_1_1_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "MacroCall_1_1_1_1", c)) break;
    }
    return true;
  }

  // SEPARATOR (Expr|Mnemonic)
  private static boolean MacroCall_1_1_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall_1_1_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, SEPARATOR);
    r = r && MacroCall_1_1_1_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Expr|Mnemonic
  private static boolean MacroCall_1_1_1_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall_1_1_1_1_0_1")) return false;
    boolean r;
    r = Expr(b, l + 1, -1);
    if (!r) r = Mnemonic(b, l + 1);
    return r;
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

  // ID
  public static boolean Identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Identifier")) return false;
    if (!nextTokenIsSmart(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, ID);
    exit_section_(b, m, IDENTIFIER, r);
    return r;
  }

  // SIZE_TYPE? (LBL|ID)
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

  // LBL|ID
  private static boolean LabelIdentifier_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelIdentifier_1")) return false;
    boolean r;
    r = consumeTokenSmart(b, LBL);
    if (!r) r = consumeTokenSmart(b, ID);
    return r;
  }

  // (END_DIRECTIVE_OP)
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
