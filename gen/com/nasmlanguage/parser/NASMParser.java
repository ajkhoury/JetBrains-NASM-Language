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
    else if (t == CODE_SECTION) {
      r = CodeSection(b, 0);
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
    else if (t == DATA_SECTION) {
      r = DataSection(b, 0);
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
    else if (t == MACRO) {
      r = Macro(b, 0);
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
    create_token_set_(ADDRESS, DIV_EXPR, EXPR, IDENTIFIER,
      LABEL_IDENTIFIER, MACRO_CALL, MINUS_EXPR, MUL_EXPR,
      NUMERIC_LITERAL, PARENTHESIS_EXPR, PLUS_EXPR, REG,
      SEG, STR),
  };

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
  // ((SQUARE_L SECTION_TAG CODE_SECTION_NAME SQUARE_R)|(SECTION_TAG CODE_SECTION_NAME)) CRLF*
  public static boolean CodeSection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CodeSection")) return false;
    if (!nextTokenIs(b, "<code section>", SECTION_TAG, SQUARE_L)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CODE_SECTION, "<code section>");
    r = CodeSection_0(b, l + 1);
    r = r && CodeSection_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (SQUARE_L SECTION_TAG CODE_SECTION_NAME SQUARE_R)|(SECTION_TAG CODE_SECTION_NAME)
  private static boolean CodeSection_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CodeSection_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CodeSection_0_0(b, l + 1);
    if (!r) r = CodeSection_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SQUARE_L SECTION_TAG CODE_SECTION_NAME SQUARE_R
  private static boolean CodeSection_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CodeSection_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SQUARE_L, SECTION_TAG, CODE_SECTION_NAME, SQUARE_R);
    exit_section_(b, m, null, r);
    return r;
  }

  // SECTION_TAG CODE_SECTION_NAME
  private static boolean CodeSection_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CodeSection_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SECTION_TAG, CODE_SECTION_NAME);
    exit_section_(b, m, null, r);
    return r;
  }

  // CRLF*
  private static boolean CodeSection_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CodeSection_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "CodeSection_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // (Expr EQUALEQUAL Expr)|Expr
  static boolean Condition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Condition")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Condition_0(b, l + 1);
    if (!r) r = Expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Expr EQUALEQUAL Expr
  private static boolean Condition_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Condition_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expr(b, l + 1, -1);
    r = r && consumeToken(b, EQUALEQUAL);
    r = r && Expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (IF_TAG Condition CRLF* (Preprocessor|Directive|Data|Instruction)* ((ELIF_TAG Condition|ELSE_TAG) CRLF* (Preprocessor|Directive|Data|Instruction)*)*  ENDIF_TAG)
  //             | (IFMACRO_TAG Identifier MacroParams MacroDefaultParam? CRLF* (Preprocessor|Directive|Data|Instruction)* ENDIF_TAG)
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

  // IF_TAG Condition CRLF* (Preprocessor|Directive|Data|Instruction)* ((ELIF_TAG Condition|ELSE_TAG) CRLF* (Preprocessor|Directive|Data|Instruction)*)*  ENDIF_TAG
  private static boolean Conditional_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IF_TAG);
    r = r && Condition(b, l + 1);
    r = r && Conditional_0_2(b, l + 1);
    r = r && Conditional_0_3(b, l + 1);
    r = r && Conditional_0_4(b, l + 1);
    r = r && consumeToken(b, ENDIF_TAG);
    exit_section_(b, m, null, r);
    return r;
  }

  // CRLF*
  private static boolean Conditional_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Conditional_0_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (Preprocessor|Directive|Data|Instruction)*
  private static boolean Conditional_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Conditional_0_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Conditional_0_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // Preprocessor|Directive|Data|Instruction
  private static boolean Conditional_0_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Preprocessor(b, l + 1);
    if (!r) r = Directive(b, l + 1);
    if (!r) r = Data(b, l + 1);
    if (!r) r = Instruction(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((ELIF_TAG Condition|ELSE_TAG) CRLF* (Preprocessor|Directive|Data|Instruction)*)*
  private static boolean Conditional_0_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_4")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Conditional_0_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Conditional_0_4", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (ELIF_TAG Condition|ELSE_TAG) CRLF* (Preprocessor|Directive|Data|Instruction)*
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
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Conditional_0_4_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (Preprocessor|Directive|Data|Instruction)*
  private static boolean Conditional_0_4_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_4_0_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Conditional_0_4_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Conditional_0_4_0_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // Preprocessor|Directive|Data|Instruction
  private static boolean Conditional_0_4_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_0_4_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Preprocessor(b, l + 1);
    if (!r) r = Directive(b, l + 1);
    if (!r) r = Data(b, l + 1);
    if (!r) r = Instruction(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IFMACRO_TAG Identifier MacroParams MacroDefaultParam? CRLF* (Preprocessor|Directive|Data|Instruction)* ENDIF_TAG
  private static boolean Conditional_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IFMACRO_TAG);
    r = r && Identifier(b, l + 1);
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
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Conditional_1_4", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (Preprocessor|Directive|Data|Instruction)*
  private static boolean Conditional_1_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_1_5")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Conditional_1_5_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Conditional_1_5", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // Preprocessor|Directive|Data|Instruction
  private static boolean Conditional_1_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_1_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Preprocessor(b, l + 1);
    if (!r) r = Directive(b, l + 1);
    if (!r) r = Data(b, l + 1);
    if (!r) r = Instruction(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Identifier EQU NumericExpr
  public static boolean Constant(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Constant")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Identifier(b, l + 1);
    r = r && consumeToken(b, EQU);
    r = r && NumericExpr(b, l + 1);
    exit_section_(b, m, CONSTANT, r);
    return r;
  }

  /* ********************************************************** */
  // DATA_OP DataValue?
  public static boolean Data(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Data")) return false;
    if (!nextTokenIs(b, DATA_OP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DATA_OP);
    r = r && Data_1(b, l + 1);
    exit_section_(b, m, DATA, r);
    return r;
  }

  // DataValue?
  private static boolean Data_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Data_1")) return false;
    DataValue(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ((SQUARE_L SECTION_TAG DATA_SECTION_NAME SQUARE_R)|(SECTION_TAG DATA_SECTION_NAME)) CRLF*
  public static boolean DataSection(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataSection")) return false;
    if (!nextTokenIs(b, "<data section>", SECTION_TAG, SQUARE_L)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DATA_SECTION, "<data section>");
    r = DataSection_0(b, l + 1);
    r = r && DataSection_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (SQUARE_L SECTION_TAG DATA_SECTION_NAME SQUARE_R)|(SECTION_TAG DATA_SECTION_NAME)
  private static boolean DataSection_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataSection_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DataSection_0_0(b, l + 1);
    if (!r) r = DataSection_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SQUARE_L SECTION_TAG DATA_SECTION_NAME SQUARE_R
  private static boolean DataSection_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataSection_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SQUARE_L, SECTION_TAG, DATA_SECTION_NAME, SQUARE_R);
    exit_section_(b, m, null, r);
    return r;
  }

  // SECTION_TAG DATA_SECTION_NAME
  private static boolean DataSection_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataSection_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SECTION_TAG, DATA_SECTION_NAME);
    exit_section_(b, m, null, r);
    return r;
  }

  // CRLF*
  private static boolean DataSection_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataSection_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "DataSection_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // (NumericExpr|STRING|LabelIdentifier) (SEPARATOR (NumericExpr|STRING|LabelIdentifier))* COMMENT?
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

  // NumericExpr|STRING|LabelIdentifier
  private static boolean DataValue_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericExpr(b, l + 1);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = LabelIdentifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (SEPARATOR (NumericExpr|STRING|LabelIdentifier))*
  private static boolean DataValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!DataValue_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "DataValue_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // SEPARATOR (NumericExpr|STRING|LabelIdentifier)
  private static boolean DataValue_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEPARATOR);
    r = r && DataValue_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NumericExpr|STRING|LabelIdentifier
  private static boolean DataValue_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericExpr(b, l + 1);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = LabelIdentifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMENT?
  private static boolean DataValue_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_2")) return false;
    consumeToken(b, COMMENT);
    return true;
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
    int c = current_position_(b);
    while (true) {
      if (!Define_0_3_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Define_0_3_0_1", c)) break;
      c = current_position_(b);
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
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Define_0_6", c)) break;
      c = current_position_(b);
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
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Define_1_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // ((DIRECTIVE_OP DirectiveArg*)|(SQUARE_L DIRECTIVE_OP DirectiveArg* SQUARE_R)|MapOption) CRLF*
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

  // (DIRECTIVE_OP DirectiveArg*)|(SQUARE_L DIRECTIVE_OP DirectiveArg* SQUARE_R)|MapOption
  private static boolean Directive_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Directive_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Directive_0_0(b, l + 1);
    if (!r) r = Directive_0_1(b, l + 1);
    if (!r) r = MapOption(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DIRECTIVE_OP DirectiveArg*
  private static boolean Directive_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Directive_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DIRECTIVE_OP);
    r = r && Directive_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DirectiveArg*
  private static boolean Directive_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Directive_0_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!DirectiveArg(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Directive_0_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // SQUARE_L DIRECTIVE_OP DirectiveArg* SQUARE_R
  private static boolean Directive_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Directive_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SQUARE_L, DIRECTIVE_OP);
    r = r && Directive_0_1_2(b, l + 1);
    r = r && consumeToken(b, SQUARE_R);
    exit_section_(b, m, null, r);
    return r;
  }

  // DirectiveArg*
  private static boolean Directive_0_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Directive_0_1_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!DirectiveArg(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Directive_0_1_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // CRLF*
  private static boolean Directive_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Directive_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Directive_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // NumericLiteral|Address|Identifier
  public static boolean DirectiveArg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveArg")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DIRECTIVE_ARG, "<directive arg>");
    r = NumericLiteral(b, l + 1);
    if (!r) r = Address(b, l + 1);
    if (!r) r = Identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // COMMENT
  //                 | CRLF
  //                 | COMMENT CRLF
  //                 | Constant
  //                 | Structure
  //                 | Data
  //                 | Instruction
  //                 | Label
  //                 | Preprocessor
  //                 | Section
  //                 | Directive
  static boolean Element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Element")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, CRLF);
    if (!r) r = parseTokens(b, 0, COMMENT, CRLF);
    if (!r) r = Constant(b, l + 1);
    if (!r) r = Structure(b, l + 1);
    if (!r) r = Data(b, l + 1);
    if (!r) r = Instruction(b, l + 1);
    if (!r) r = Label(b, l + 1);
    if (!r) r = Preprocessor(b, l + 1);
    if (!r) r = Section(b, l + 1);
    if (!r) r = Directive(b, l + 1);
    exit_section_(b, m, null, r);
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
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Error_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // ISTRUC_TAG Identifier CRLF* (AT_TAG LabelIdentifier SEPARATOR DATA_OP DataValue CRLF*)* IEND_TAG
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
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "IStruc_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (AT_TAG LabelIdentifier SEPARATOR DATA_OP DataValue CRLF*)*
  private static boolean IStruc_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStruc_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!IStruc_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "IStruc_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // AT_TAG LabelIdentifier SEPARATOR DATA_OP DataValue CRLF*
  private static boolean IStruc_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStruc_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AT_TAG);
    r = r && LabelIdentifier(b, l + 1);
    r = r && consumeTokens(b, 0, SEPARATOR, DATA_OP);
    r = r && DataValue(b, l + 1);
    r = r && IStruc_3_0_5(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // CRLF*
  private static boolean IStruc_3_0_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IStruc_3_0_5")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "IStruc_3_0_5", c)) break;
      c = current_position_(b);
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
  // ((((OP_PREFIX MnemonicOperation)|MnemonicOperation) ((Expr SEPARATOR)* Expr)? COMMENT? CRLF*) | Directive | MacroCall) CRLF*
  public static boolean Instruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INSTRUCTION, "<instruction>");
    r = Instruction_0(b, l + 1);
    r = r && Instruction_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (((OP_PREFIX MnemonicOperation)|MnemonicOperation) ((Expr SEPARATOR)* Expr)? COMMENT? CRLF*) | Directive | MacroCall
  private static boolean Instruction_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_0_0(b, l + 1);
    if (!r) r = Directive(b, l + 1);
    if (!r) r = MacroCall(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((OP_PREFIX MnemonicOperation)|MnemonicOperation) ((Expr SEPARATOR)* Expr)? COMMENT? CRLF*
  private static boolean Instruction_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_0_0_0(b, l + 1);
    r = r && Instruction_0_0_1(b, l + 1);
    r = r && Instruction_0_0_2(b, l + 1);
    r = r && Instruction_0_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (OP_PREFIX MnemonicOperation)|MnemonicOperation
  private static boolean Instruction_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_0_0_0_0(b, l + 1);
    if (!r) r = MnemonicOperation(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // OP_PREFIX MnemonicOperation
  private static boolean Instruction_0_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OP_PREFIX);
    r = r && MnemonicOperation(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((Expr SEPARATOR)* Expr)?
  private static boolean Instruction_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_0_1")) return false;
    Instruction_0_0_1_0(b, l + 1);
    return true;
  }

  // (Expr SEPARATOR)* Expr
  private static boolean Instruction_0_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_0_0_1_0_0(b, l + 1);
    r = r && Expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (Expr SEPARATOR)*
  private static boolean Instruction_0_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_0_1_0_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Instruction_0_0_1_0_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Instruction_0_0_1_0_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // Expr SEPARATOR
  private static boolean Instruction_0_0_1_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_0_1_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expr(b, l + 1, -1);
    r = r && consumeToken(b, SEPARATOR);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMENT?
  private static boolean Instruction_0_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_0_2")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  // CRLF*
  private static boolean Instruction_0_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0_0_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Instruction_0_0_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // CRLF*
  private static boolean Instruction_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Instruction_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // LBL_DEF (Instruction|Data|Structure)? CRLF*
  public static boolean Label(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label")) return false;
    if (!nextTokenIs(b, LBL_DEF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBL_DEF);
    r = r && Label_1(b, l + 1);
    r = r && Label_2(b, l + 1);
    exit_section_(b, m, LABEL, r);
    return r;
  }

  // (Instruction|Data|Structure)?
  private static boolean Label_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_1")) return false;
    Label_1_0(b, l + 1);
    return true;
  }

  // Instruction|Data|Structure
  private static boolean Label_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction(b, l + 1);
    if (!r) r = Data(b, l + 1);
    if (!r) r = Structure(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // CRLF*
  private static boolean Label_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Label_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // MACRO_TAG Identifier MacroParams MacroDefaultParam? CRLF* (Data|Instruction)* MACRO_END_TAG
  public static boolean Macro(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro")) return false;
    if (!nextTokenIs(b, MACRO_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MACRO_TAG);
    r = r && Identifier(b, l + 1);
    r = r && MacroParams(b, l + 1);
    r = r && Macro_3(b, l + 1);
    r = r && Macro_4(b, l + 1);
    r = r && Macro_5(b, l + 1);
    r = r && consumeToken(b, MACRO_END_TAG);
    exit_section_(b, m, MACRO, r);
    return r;
  }

  // MacroDefaultParam?
  private static boolean Macro_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro_3")) return false;
    MacroDefaultParam(b, l + 1);
    return true;
  }

  // CRLF*
  private static boolean Macro_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro_4")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Macro_4", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (Data|Instruction)*
  private static boolean Macro_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro_5")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Macro_5_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Macro_5", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // Data|Instruction
  private static boolean Macro_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Data(b, l + 1);
    if (!r) r = Instruction(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Instruction|STRING|REGISTER|NumericLiteral|MacroCall
  static boolean MacroDefaultParam(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroDefaultParam")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction(b, l + 1);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, REGISTER);
    if (!r) r = NumericLiteral(b, l + 1);
    if (!r) r = MacroCall(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
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
  // SQUARE_L 'map' ('all'|'brief'|'sections'|'segments'|'symbols')? MAP_FILE SQUARE_R
  public static boolean MapOption(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapOption")) return false;
    if (!nextTokenIs(b, SQUARE_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SQUARE_L);
    r = r && consumeToken(b, "map");
    r = r && MapOption_2(b, l + 1);
    r = r && consumeTokens(b, 0, MAP_FILE, SQUARE_R);
    exit_section_(b, m, MAP_OPTION, r);
    return r;
  }

  // ('all'|'brief'|'sections'|'segments'|'symbols')?
  private static boolean MapOption_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapOption_2")) return false;
    MapOption_2_0(b, l + 1);
    return true;
  }

  // 'all'|'brief'|'sections'|'segments'|'symbols'
  private static boolean MapOption_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MapOption_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "all");
    if (!r) r = consumeToken(b, "brief");
    if (!r) r = consumeToken(b, "sections");
    if (!r) r = consumeToken(b, "segments");
    if (!r) r = consumeToken(b, "symbols");
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // GENERAL_OP | X64_OP | FPU_OP | MMX_OP | SSE_OP | SSE2_OP | SSE3_OP | SSE4_OP
  static boolean MnemonicOperation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MnemonicOperation")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GENERAL_OP);
    if (!r) r = consumeToken(b, X64_OP);
    if (!r) r = consumeToken(b, FPU_OP);
    if (!r) r = consumeToken(b, MMX_OP);
    if (!r) r = consumeToken(b, SSE_OP);
    if (!r) r = consumeToken(b, SSE2_OP);
    if (!r) r = consumeToken(b, SSE3_OP);
    if (!r) r = consumeToken(b, SSE4_OP);
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
  // ParenthesisNumericExpr
  //         | MulExpr
  //         | DivExpr
  //         | PlusExpr
  //         | MinusExpr
  //         | NumericLiteral
  //         | Str
  //         | MacroCall
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
    if (!r) r = NumericLiteral(b, l + 1);
    if (!r) r = Str(b, l + 1);
    if (!r) r = MacroCall(b, l + 1);
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
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ROUND_L);
    r = r && NumericExpr(b, l + 1);
    r = r && consumeToken(b, ROUND_R);
    exit_section_(b, m, PARENTHESIS_NUMERIC_EXPR, r);
    return r;
  }

  /* ********************************************************** */
  // Include | Define | Assign | Macro | Strlen | Error | Conditional | (PREPROCESSOR_OP Identifier Expr)
  public static boolean Preprocessor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Preprocessor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PREPROCESSOR, "<preprocessor>");
    r = Include(b, l + 1);
    if (!r) r = Define(b, l + 1);
    if (!r) r = Assign(b, l + 1);
    if (!r) r = Macro(b, l + 1);
    if (!r) r = Strlen(b, l + 1);
    if (!r) r = Error(b, l + 1);
    if (!r) r = Conditional(b, l + 1);
    if (!r) r = Preprocessor_7(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // PREPROCESSOR_OP Identifier Expr
  private static boolean Preprocessor_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Preprocessor_7")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PREPROCESSOR_OP);
    r = r && Identifier(b, l + 1);
    r = r && Expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DataSection | CodeSection
  static boolean Section(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Section")) return false;
    if (!nextTokenIs(b, "", SECTION_TAG, SQUARE_L)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DataSection(b, l + 1);
    if (!r) r = CodeSection(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // STRLEN_TAG Identifier (STRING|Identifier) CRLF*
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

  // STRING|Identifier
  private static boolean Strlen_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Strlen_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING);
    if (!r) r = Identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // CRLF*
  private static boolean Strlen_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Strlen_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Strlen_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // STRUC_TAG Identifier CRLF* ((LBL_DEF|LabelIdentifier) DATA_OP NumericLiteral CRLF*)* ENDSTRUC_TAG
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
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Struc_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ((LBL_DEF|LabelIdentifier) DATA_OP NumericLiteral CRLF*)*
  private static boolean Struc_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Struc_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Struc_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (LBL_DEF|LabelIdentifier) DATA_OP NumericLiteral CRLF*
  private static boolean Struc_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Struc_3_0_0(b, l + 1);
    r = r && consumeToken(b, DATA_OP);
    r = r && NumericLiteral(b, l + 1);
    r = r && Struc_3_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LBL_DEF|LabelIdentifier
  private static boolean Struc_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBL_DEF);
    if (!r) r = LabelIdentifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // CRLF*
  private static boolean Struc_3_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Struc_3_0_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Struc_3_0_3", c)) break;
      c = current_position_(b);
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
    Marker m = enter_section_(b);
    r = Struc(b, l + 1);
    if (!r) r = IStruc(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // CRLF*
  private static boolean Structure_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Structure_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, CRLF)) break;
      if (!empty_element_parsed_guard_(b, "Structure_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // Expression root: Expr
  // Operator priority table:
  // 0: PREFIX(ParenthesisExpr)
  // 1: BINARY(MulExpr)
  // 2: BINARY(DivExpr)
  // 3: BINARY(PlusExpr)
  // 4: BINARY(MinusExpr)
  // 5: ATOM(NumericLiteral)
  // 6: ATOM(Str)
  // 7: ATOM(MacroCall)
  // 8: ATOM(Address)
  // 9: ATOM(Reg)
  // 10: ATOM(Seg)
  // 11: ATOM(Identifier)
  // 12: ATOM(LabelIdentifier)
  public static boolean Expr(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "Expr")) return false;
    addVariant(b, "<expr>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expr>");
    r = ParenthesisExpr(b, l + 1);
    if (!r) r = NumericLiteral(b, l + 1);
    if (!r) r = Str(b, l + 1);
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = Address(b, l + 1);
    if (!r) r = Reg(b, l + 1);
    if (!r) r = Seg(b, l + 1);
    if (!r) r = Identifier(b, l + 1);
    if (!r) r = LabelIdentifier(b, l + 1);
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
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  public static boolean ParenthesisExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParenthesisExpr")) return false;
    if (!nextTokenIsSmart(b, ROUND_L)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, ROUND_L);
    p = r;
    r = p && Expr(b, l, 0);
    r = p && report_error_(b, consumeToken(b, ROUND_R)) && r;
    exit_section_(b, l, m, PARENTHESIS_EXPR, r, p, null);
    return r || p;
  }

  // ((HEXADECIMAL|ID) COLON (HEXADECIMAL|ID))|BINARY|HEXADECIMAL|DECIMAL|CHARACTER
  public static boolean NumericLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NUMERIC_LITERAL, "<numeric literal>");
    r = NumericLiteral_0(b, l + 1);
    if (!r) r = consumeTokenSmart(b, BINARY);
    if (!r) r = consumeTokenSmart(b, HEXADECIMAL);
    if (!r) r = consumeTokenSmart(b, DECIMAL);
    if (!r) r = consumeTokenSmart(b, CHARACTER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (HEXADECIMAL|ID) COLON (HEXADECIMAL|ID)
  private static boolean NumericLiteral_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral_0_0(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && NumericLiteral_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // HEXADECIMAL|ID
  private static boolean NumericLiteral_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, HEXADECIMAL);
    if (!r) r = consumeTokenSmart(b, ID);
    exit_section_(b, m, null, r);
    return r;
  }

  // HEXADECIMAL|ID
  private static boolean NumericLiteral_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, HEXADECIMAL);
    if (!r) r = consumeTokenSmart(b, ID);
    exit_section_(b, m, null, r);
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

  // Identifier ROUND_L ((NumericExpr SEPARATOR)* NumericExpr)? ROUND_R
  public static boolean MacroCall(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall")) return false;
    if (!nextTokenIsSmart(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Identifier(b, l + 1);
    r = r && consumeToken(b, ROUND_L);
    r = r && MacroCall_2(b, l + 1);
    r = r && consumeToken(b, ROUND_R);
    exit_section_(b, m, MACRO_CALL, r);
    return r;
  }

  // ((NumericExpr SEPARATOR)* NumericExpr)?
  private static boolean MacroCall_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall_2")) return false;
    MacroCall_2_0(b, l + 1);
    return true;
  }

  // (NumericExpr SEPARATOR)* NumericExpr
  private static boolean MacroCall_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MacroCall_2_0_0(b, l + 1);
    r = r && NumericExpr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (NumericExpr SEPARATOR)*
  private static boolean MacroCall_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall_2_0_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!MacroCall_2_0_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "MacroCall_2_0_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // NumericExpr SEPARATOR
  private static boolean MacroCall_2_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall_2_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericExpr(b, l + 1);
    r = r && consumeToken(b, SEPARATOR);
    exit_section_(b, m, null, r);
    return r;
  }

  // (SIZE_TYPE? SQUARE_L Expr SQUARE_R)|DOLLARSIGN|DOLLARSIGN2
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

  // SIZE_TYPE? SQUARE_L Expr SQUARE_R
  private static boolean Address_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Address_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Address_0_0(b, l + 1);
    r = r && consumeToken(b, SQUARE_L);
    r = r && Expr(b, l + 1, -1);
    r = r && consumeToken(b, SQUARE_R);
    exit_section_(b, m, null, r);
    return r;
  }

  // SIZE_TYPE?
  private static boolean Address_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Address_0_0")) return false;
    consumeTokenSmart(b, SIZE_TYPE);
    return true;
  }

  // REGISTER
  public static boolean Reg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Reg")) return false;
    if (!nextTokenIsSmart(b, REGISTER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, REGISTER);
    exit_section_(b, m, REG, r);
    return r;
  }

  // SEGMENT
  public static boolean Seg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Seg")) return false;
    if (!nextTokenIsSmart(b, SEGMENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, SEGMENT);
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
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, LBL);
    if (!r) r = consumeTokenSmart(b, ID);
    exit_section_(b, m, null, r);
    return r;
  }

}
