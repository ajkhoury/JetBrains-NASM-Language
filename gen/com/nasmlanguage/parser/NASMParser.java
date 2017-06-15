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
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == ADDRESS) {
      r = Address(b, 0);
    }
    else if (t == CODE_SECTION) {
      r = CodeSection(b, 0);
    }
    else if (t == CONDITIONAL) {
      r = Conditional(b, 0);
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
    else if (t == INCLUDE) {
      r = Include(b, 0);
    }
    else if (t == INSTRUCTION) {
      r = Instruction(b, 0);
    }
    else if (t == MACRO) {
      r = Macro(b, 0);
    }
    else if (t == MACRO_CALL) {
      r = MacroCall(b, 0);
    }
    else if (t == PREPROCESSOR) {
      r = Preprocessor(b, 0);
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
  // SIZE_TYPE? SQUARE_L AddressInternal SQUARE_R
  public static boolean Address(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Address")) return false;
    if (!nextTokenIs(b, "<address>", SIZE_TYPE, SQUARE_L)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ADDRESS, "<address>");
    r = Address_0(b, l + 1);
    r = r && consumeToken(b, SQUARE_L);
    r = r && AddressInternal(b, l + 1);
    r = r && consumeToken(b, SQUARE_R);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SIZE_TYPE?
  private static boolean Address_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Address_0")) return false;
    consumeToken(b, SIZE_TYPE);
    return true;
  }

  /* ********************************************************** */
  // (REGISTER|MacroCall|Identifier) ((PLUS|MINUS|TIMES) (((NumericLiteral|MacroCall|Identifier) (PLUS|MINUS|TIMES))*)? (NumericLiteral|REGISTER|MacroCall|Identifier))? ((PLUS|MINUS|TIMES) (NumericLiteral|MacroCall|Identifier))?
  static boolean AddressInternal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AddressInternal_0(b, l + 1);
    r = r && AddressInternal_1(b, l + 1);
    r = r && AddressInternal_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // REGISTER|MacroCall|Identifier
  private static boolean AddressInternal_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, REGISTER);
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = Identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((PLUS|MINUS|TIMES) (((NumericLiteral|MacroCall|Identifier) (PLUS|MINUS|TIMES))*)? (NumericLiteral|REGISTER|MacroCall|Identifier))?
  private static boolean AddressInternal_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_1")) return false;
    AddressInternal_1_0(b, l + 1);
    return true;
  }

  // (PLUS|MINUS|TIMES) (((NumericLiteral|MacroCall|Identifier) (PLUS|MINUS|TIMES))*)? (NumericLiteral|REGISTER|MacroCall|Identifier)
  private static boolean AddressInternal_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AddressInternal_1_0_0(b, l + 1);
    r = r && AddressInternal_1_0_1(b, l + 1);
    r = r && AddressInternal_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PLUS|MINUS|TIMES
  private static boolean AddressInternal_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, TIMES);
    exit_section_(b, m, null, r);
    return r;
  }

  // (((NumericLiteral|MacroCall|Identifier) (PLUS|MINUS|TIMES))*)?
  private static boolean AddressInternal_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_1_0_1")) return false;
    AddressInternal_1_0_1_0(b, l + 1);
    return true;
  }

  // ((NumericLiteral|MacroCall|Identifier) (PLUS|MINUS|TIMES))*
  private static boolean AddressInternal_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_1_0_1_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!AddressInternal_1_0_1_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "AddressInternal_1_0_1_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (NumericLiteral|MacroCall|Identifier) (PLUS|MINUS|TIMES)
  private static boolean AddressInternal_1_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_1_0_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AddressInternal_1_0_1_0_0_0(b, l + 1);
    r = r && AddressInternal_1_0_1_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NumericLiteral|MacroCall|Identifier
  private static boolean AddressInternal_1_0_1_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_1_0_1_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral(b, l + 1);
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = Identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PLUS|MINUS|TIMES
  private static boolean AddressInternal_1_0_1_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_1_0_1_0_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, TIMES);
    exit_section_(b, m, null, r);
    return r;
  }

  // NumericLiteral|REGISTER|MacroCall|Identifier
  private static boolean AddressInternal_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_1_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral(b, l + 1);
    if (!r) r = consumeToken(b, REGISTER);
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = Identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((PLUS|MINUS|TIMES) (NumericLiteral|MacroCall|Identifier))?
  private static boolean AddressInternal_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_2")) return false;
    AddressInternal_2_0(b, l + 1);
    return true;
  }

  // (PLUS|MINUS|TIMES) (NumericLiteral|MacroCall|Identifier)
  private static boolean AddressInternal_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = AddressInternal_2_0_0(b, l + 1);
    r = r && AddressInternal_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PLUS|MINUS|TIMES
  private static boolean AddressInternal_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, TIMES);
    exit_section_(b, m, null, r);
    return r;
  }

  // NumericLiteral|MacroCall|Identifier
  private static boolean AddressInternal_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddressInternal_2_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral(b, l + 1);
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = Identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // SECTION_TAG CODE_SECTION_NAME CRLF* (Instruction | Directive)*
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

  // CRLF*
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
  // (Expression EQUALEQUAL Expression)|Expression
  static boolean Condition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Condition")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Condition_0(b, l + 1);
    if (!r) r = Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Expression EQUALEQUAL Expression
  private static boolean Condition_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Condition_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression(b, l + 1);
    r = r && consumeToken(b, EQUALEQUAL);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (IF_TAG Condition CRLF* (Preprocessor|Directive|Data|Instruction)* ((ELIF_TAG Condition|ELSE_TAG) CRLF* (Preprocessor|Directive|Data|Instruction)*)*  ENDIF_TAG)
  //                 | (IFMACRO_TAG IDENTIFIER MacroParams MacroDefaultParam? CRLF* (Preprocessor|Directive|Data|Instruction)* ENDIF_TAG)
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

  // IFMACRO_TAG IDENTIFIER MacroParams MacroDefaultParam? CRLF* (Preprocessor|Directive|Data|Instruction)* ENDIF_TAG
  private static boolean Conditional_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Conditional_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IFMACRO_TAG, IDENTIFIER);
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
  // (LABEL_DEF? DATA_OP DataValue?) | (Identifier EQU NumericExpression)
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

  // Identifier EQU NumericExpression
  private static boolean Data_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Data_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Identifier(b, l + 1);
    r = r && consumeToken(b, EQU);
    r = r && NumericExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // SECTION_TAG DATA_SECTION_NAME CRLF* (Data | Directive)*
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

  // CRLF*
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
  // ((NumericLiteral|STRING) SEPARATOR?)*
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

  // (NumericLiteral|STRING) SEPARATOR?
  private static boolean DataValue_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DataValue_0_0(b, l + 1);
    r = r && DataValue_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NumericLiteral|STRING
  private static boolean DataValue_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral(b, l + 1);
    if (!r) r = consumeToken(b, STRING);
    exit_section_(b, m, null, r);
    return r;
  }

  // SEPARATOR?
  private static boolean DataValue_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DataValue_0_1")) return false;
    consumeToken(b, SEPARATOR);
    return true;
  }

  /* ********************************************************** */
  // (DEFINE_TAG IDENTIFIER (NumericLiteral|((PERCENT SQUARE_L)? IDENTIFIER SQUARE_R?)))
  //         | (DEFINE_TAG (IDENTIFIER ROUND_L ((IDENTIFIER SEPARATOR)* IDENTIFIER)? ROUND_R) Expression)
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

  // DEFINE_TAG IDENTIFIER (NumericLiteral|((PERCENT SQUARE_L)? IDENTIFIER SQUARE_R?))
  private static boolean Define_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DEFINE_TAG, IDENTIFIER);
    r = r && Define_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NumericLiteral|((PERCENT SQUARE_L)? IDENTIFIER SQUARE_R?)
  private static boolean Define_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral(b, l + 1);
    if (!r) r = Define_0_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (PERCENT SQUARE_L)? IDENTIFIER SQUARE_R?
  private static boolean Define_0_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_2_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Define_0_2_1_0(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    r = r && Define_0_2_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (PERCENT SQUARE_L)?
  private static boolean Define_0_2_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_2_1_0")) return false;
    Define_0_2_1_0_0(b, l + 1);
    return true;
  }

  // PERCENT SQUARE_L
  private static boolean Define_0_2_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_2_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PERCENT, SQUARE_L);
    exit_section_(b, m, null, r);
    return r;
  }

  // SQUARE_R?
  private static boolean Define_0_2_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_0_2_1_2")) return false;
    consumeToken(b, SQUARE_R);
    return true;
  }

  // DEFINE_TAG (IDENTIFIER ROUND_L ((IDENTIFIER SEPARATOR)* IDENTIFIER)? ROUND_R) Expression
  private static boolean Define_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DEFINE_TAG);
    r = r && Define_1_1(b, l + 1);
    r = r && Expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENTIFIER ROUND_L ((IDENTIFIER SEPARATOR)* IDENTIFIER)? ROUND_R
  private static boolean Define_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, ROUND_L);
    r = r && Define_1_1_2(b, l + 1);
    r = r && consumeToken(b, ROUND_R);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((IDENTIFIER SEPARATOR)* IDENTIFIER)?
  private static boolean Define_1_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_1_1_2")) return false;
    Define_1_1_2_0(b, l + 1);
    return true;
  }

  // (IDENTIFIER SEPARATOR)* IDENTIFIER
  private static boolean Define_1_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_1_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Define_1_1_2_0_0(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // (IDENTIFIER SEPARATOR)*
  private static boolean Define_1_1_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_1_1_2_0_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Define_1_1_2_0_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Define_1_1_2_0_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // IDENTIFIER SEPARATOR
  private static boolean Define_1_1_2_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Define_1_1_2_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, SEPARATOR);
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
  // NumericLiteral | Address | Identifier
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
  //                 | Section
  //                 | Preprocessor
  //                 | Directive
  //                 | SQUARE_L Section SQUARE_R
  //                 | SQUARE_L Directive SQUARE_R
  //                 | Data
  //                 | Instruction
  static boolean Element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Element")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, CRLF);
    if (!r) r = parseTokens(b, 0, COMMENT, CRLF);
    if (!r) r = Section(b, l + 1);
    if (!r) r = Preprocessor(b, l + 1);
    if (!r) r = Directive(b, l + 1);
    if (!r) r = Element_6(b, l + 1);
    if (!r) r = Element_7(b, l + 1);
    if (!r) r = Data(b, l + 1);
    if (!r) r = Instruction(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SQUARE_L Section SQUARE_R
  private static boolean Element_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Element_6")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SQUARE_L);
    r = r && Section(b, l + 1);
    r = r && consumeToken(b, SQUARE_R);
    exit_section_(b, m, null, r);
    return r;
  }

  // SQUARE_L Directive SQUARE_R
  private static boolean Element_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Element_7")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SQUARE_L);
    r = r && Directive(b, l + 1);
    r = r && consumeToken(b, SQUARE_R);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ERROR_TAG ((IDENTIFIER|PUNCTUATION) '\s+'?)*
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

  // ((IDENTIFIER|PUNCTUATION) '\s+'?)*
  private static boolean Error_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Error_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Error_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Error_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (IDENTIFIER|PUNCTUATION) '\s+'?
  private static boolean Error_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Error_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Error_1_0_0(b, l + 1);
    r = r && Error_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENTIFIER|PUNCTUATION
  private static boolean Error_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Error_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, PUNCTUATION);
    exit_section_(b, m, null, r);
    return r;
  }

  // '\s+'?
  private static boolean Error_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Error_1_0_1")) return false;
    consumeToken(b, "\\s+");
    return true;
  }

  /* ********************************************************** */
  // ((NumericLiteral|REGISTER|MacroCall|IDENTIFIER)(PLUS|MINUS|TIMES))* (NumericLiteral|REGISTER|MacroCall|IDENTIFIER)
  static boolean Expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression_0(b, l + 1);
    r = r && Expression_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((NumericLiteral|REGISTER|MacroCall|IDENTIFIER)(PLUS|MINUS|TIMES))*
  private static boolean Expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Expression_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Expression_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (NumericLiteral|REGISTER|MacroCall|IDENTIFIER)(PLUS|MINUS|TIMES)
  private static boolean Expression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Expression_0_0_0(b, l + 1);
    r = r && Expression_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NumericLiteral|REGISTER|MacroCall|IDENTIFIER
  private static boolean Expression_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral(b, l + 1);
    if (!r) r = consumeToken(b, REGISTER);
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // PLUS|MINUS|TIMES
  private static boolean Expression_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression_0_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, TIMES);
    exit_section_(b, m, null, r);
    return r;
  }

  // NumericLiteral|REGISTER|MacroCall|IDENTIFIER
  private static boolean Expression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Expression_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral(b, l + 1);
    if (!r) r = consumeToken(b, REGISTER);
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // SIZE_TYPE? (LABEL|IDENTIFIER)
  static boolean Identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Identifier")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Identifier_0(b, l + 1);
    r = r && Identifier_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SIZE_TYPE?
  private static boolean Identifier_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Identifier_0")) return false;
    consumeToken(b, SIZE_TYPE);
    return true;
  }

  // LABEL|IDENTIFIER
  private static boolean Identifier_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Identifier_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LABEL);
    if (!r) r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
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
  // LABEL_DEF? ((MnemonicOperation ((MnemonicOperationArg SEPARATOR)* MnemonicOperationArg)? COMMENT?) | Directive | MacroCall)
  public static boolean Instruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INSTRUCTION, "<instruction>");
    r = Instruction_0(b, l + 1);
    r = r && Instruction_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LABEL_DEF?
  private static boolean Instruction_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_0")) return false;
    consumeToken(b, LABEL_DEF);
    return true;
  }

  // (MnemonicOperation ((MnemonicOperationArg SEPARATOR)* MnemonicOperationArg)? COMMENT?) | Directive | MacroCall
  private static boolean Instruction_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_1_0(b, l + 1);
    if (!r) r = Directive(b, l + 1);
    if (!r) r = MacroCall(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // MnemonicOperation ((MnemonicOperationArg SEPARATOR)* MnemonicOperationArg)? COMMENT?
  private static boolean Instruction_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MnemonicOperation(b, l + 1);
    r = r && Instruction_1_0_1(b, l + 1);
    r = r && Instruction_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((MnemonicOperationArg SEPARATOR)* MnemonicOperationArg)?
  private static boolean Instruction_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_1_0_1")) return false;
    Instruction_1_0_1_0(b, l + 1);
    return true;
  }

  // (MnemonicOperationArg SEPARATOR)* MnemonicOperationArg
  private static boolean Instruction_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction_1_0_1_0_0(b, l + 1);
    r = r && MnemonicOperationArg(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MnemonicOperationArg SEPARATOR)*
  private static boolean Instruction_1_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_1_0_1_0_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!Instruction_1_0_1_0_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Instruction_1_0_1_0_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // MnemonicOperationArg SEPARATOR
  private static boolean Instruction_1_0_1_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_1_0_1_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MnemonicOperationArg(b, l + 1);
    r = r && consumeToken(b, SEPARATOR);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMENT?
  private static boolean Instruction_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_1_0_2")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // MACRO_TAG IDENTIFIER MacroParams MacroDefaultParam? CRLF* (Data|Instruction)* MACRO_END_TAG
  public static boolean Macro(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Macro")) return false;
    if (!nextTokenIs(b, MACRO_TAG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, MACRO_TAG, IDENTIFIER);
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
  // IDENTIFIER ROUND_L ((NumericExpression SEPARATOR)* NumericExpression)? ROUND_R
  public static boolean MacroCall(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, ROUND_L);
    r = r && MacroCall_2(b, l + 1);
    r = r && consumeToken(b, ROUND_R);
    exit_section_(b, m, MACRO_CALL, r);
    return r;
  }

  // ((NumericExpression SEPARATOR)* NumericExpression)?
  private static boolean MacroCall_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall_2")) return false;
    MacroCall_2_0(b, l + 1);
    return true;
  }

  // (NumericExpression SEPARATOR)* NumericExpression
  private static boolean MacroCall_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MacroCall_2_0_0(b, l + 1);
    r = r && NumericExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (NumericExpression SEPARATOR)*
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

  // NumericExpression SEPARATOR
  private static boolean MacroCall_2_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroCall_2_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericExpression(b, l + 1);
    r = r && consumeToken(b, SEPARATOR);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Instruction|STRING|REGISTER|NumericLiteral
  static boolean MacroDefaultParam(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroDefaultParam")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Instruction(b, l + 1);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, REGISTER);
    if (!r) r = NumericLiteral(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (NumericLiteral MINUS ((NumericLiteral TIMES)|NumericLiteral|TIMES)) | NumericLiteral PLUS
  static boolean MacroParams(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MacroParams_0(b, l + 1);
    if (!r) r = MacroParams_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NumericLiteral MINUS ((NumericLiteral TIMES)|NumericLiteral|TIMES)
  private static boolean MacroParams_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral(b, l + 1);
    r = r && consumeToken(b, MINUS);
    r = r && MacroParams_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (NumericLiteral TIMES)|NumericLiteral|TIMES
  private static boolean MacroParams_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MacroParams_0_2_0(b, l + 1);
    if (!r) r = NumericLiteral(b, l + 1);
    if (!r) r = consumeToken(b, TIMES);
    exit_section_(b, m, null, r);
    return r;
  }

  // NumericLiteral TIMES
  private static boolean MacroParams_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral(b, l + 1);
    r = r && consumeToken(b, TIMES);
    exit_section_(b, m, null, r);
    return r;
  }

  // NumericLiteral PLUS
  private static boolean MacroParams_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MacroParams_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral(b, l + 1);
    r = r && consumeToken(b, PLUS);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // OP_PREFIX? (GENERAL_OP|X64_OP|FPU_OP|MMX_OP|SSE_OP|SSE2_OP)
  static boolean MnemonicOperation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MnemonicOperation")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = MnemonicOperation_0(b, l + 1);
    r = r && MnemonicOperation_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // OP_PREFIX?
  private static boolean MnemonicOperation_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MnemonicOperation_0")) return false;
    consumeToken(b, OP_PREFIX);
    return true;
  }

  // GENERAL_OP|X64_OP|FPU_OP|MMX_OP|SSE_OP|SSE2_OP
  private static boolean MnemonicOperation_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MnemonicOperation_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GENERAL_OP);
    if (!r) r = consumeToken(b, X64_OP);
    if (!r) r = consumeToken(b, FPU_OP);
    if (!r) r = consumeToken(b, MMX_OP);
    if (!r) r = consumeToken(b, SSE_OP);
    if (!r) r = consumeToken(b, SSE2_OP);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // REGISTER | NumericLiteral | Address | MacroCall | Identifier
  static boolean MnemonicOperationArg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MnemonicOperationArg")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, REGISTER);
    if (!r) r = NumericLiteral(b, l + 1);
    if (!r) r = Address(b, l + 1);
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = Identifier(b, l + 1);
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
  // ((NumericLiteral|MacroCall|IDENTIFIER)(PLUS|MINUS|TIMES))* (NumericLiteral|MacroCall|IDENTIFIER)
  static boolean NumericExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericExpression")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericExpression_0(b, l + 1);
    r = r && NumericExpression_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ((NumericLiteral|MacroCall|IDENTIFIER)(PLUS|MINUS|TIMES))*
  private static boolean NumericExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericExpression_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!NumericExpression_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "NumericExpression_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (NumericLiteral|MacroCall|IDENTIFIER)(PLUS|MINUS|TIMES)
  private static boolean NumericExpression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericExpression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericExpression_0_0_0(b, l + 1);
    r = r && NumericExpression_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // NumericLiteral|MacroCall|IDENTIFIER
  private static boolean NumericExpression_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericExpression_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral(b, l + 1);
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // PLUS|MINUS|TIMES
  private static boolean NumericExpression_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericExpression_0_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, TIMES);
    exit_section_(b, m, null, r);
    return r;
  }

  // NumericLiteral|MacroCall|IDENTIFIER
  private static boolean NumericExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericExpression_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral(b, l + 1);
    if (!r) r = MacroCall(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (PLUS|MINUS)? NUMBER
  static boolean NumericLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumericLiteral_0(b, l + 1);
    r = r && consumeToken(b, NUMBER);
    exit_section_(b, m, null, r);
    return r;
  }

  // (PLUS|MINUS)?
  private static boolean NumericLiteral_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral_0")) return false;
    NumericLiteral_0_0(b, l + 1);
    return true;
  }

  // PLUS|MINUS
  private static boolean NumericLiteral_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumericLiteral_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Include | Define | Macro | Error | Conditional | PREPROCESSOR_OP
  public static boolean Preprocessor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Preprocessor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PREPROCESSOR, "<preprocessor>");
    r = Include(b, l + 1);
    if (!r) r = Define(b, l + 1);
    if (!r) r = Macro(b, l + 1);
    if (!r) r = Error(b, l + 1);
    if (!r) r = Conditional(b, l + 1);
    if (!r) r = consumeToken(b, PREPROCESSOR_OP);
    exit_section_(b, l, m, r, false, null);
    return r;
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
