// This is a generated file. Not intended for manual editing.
package com.nasmlanguage.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.nasmlanguage.psi.impl.*;

public interface NASMTypes {

  IElementType ADDRESS = new NASMElementType("ADDRESS");
  IElementType ASSIGN = new NASMElementType("ASSIGN");
  IElementType BITWISE_AND_EXPR = new NASMElementType("BITWISE_AND_EXPR");
  IElementType BITWISE_NOT_EXPR = new NASMElementType("BITWISE_NOT_EXPR");
  IElementType BITWISE_OR_EXPR = new NASMElementType("BITWISE_OR_EXPR");
  IElementType BITWISE_XOR_EXPR = new NASMElementType("BITWISE_XOR_EXPR");
  IElementType BIT_SHIFT_L_EXPR = new NASMElementType("BIT_SHIFT_L_EXPR");
  IElementType BIT_SHIFT_R_EXPR = new NASMElementType("BIT_SHIFT_R_EXPR");
  IElementType CONDITIONAL = new NASMElementType("CONDITIONAL");
  IElementType CONSTANT = new NASMElementType("CONSTANT");
  IElementType DATA_ELEMENT = new NASMElementType("DATA_ELEMENT");
  IElementType DEFINE = new NASMElementType("DEFINE");
  IElementType DIRECTIVE = new NASMElementType("DIRECTIVE");
  IElementType DIV_EXPR = new NASMElementType("DIV_EXPR");
  IElementType END_DIRECTIVE = new NASMElementType("END_DIRECTIVE");
  IElementType ERROR = new NASMElementType("ERROR");
  IElementType EXPR = new NASMElementType("EXPR");
  IElementType IDENTIFIER = new NASMElementType("IDENTIFIER");
  IElementType INCLUDE = new NASMElementType("INCLUDE");
  IElementType INSTRUCTION = new NASMElementType("INSTRUCTION");
  IElementType I_STRUC = new NASMElementType("I_STRUC");
  IElementType LABEL = new NASMElementType("LABEL");
  IElementType LABEL_DEF_MACRO = new NASMElementType("LABEL_DEF_MACRO");
  IElementType LABEL_IDENTIFIER = new NASMElementType("LABEL_IDENTIFIER");
  IElementType LOGICAL_AND_EXPR = new NASMElementType("LOGICAL_AND_EXPR");
  IElementType LOGICAL_OR_EXPR = new NASMElementType("LOGICAL_OR_EXPR");
  IElementType LOGICAL_XOR_EXPR = new NASMElementType("LOGICAL_XOR_EXPR");
  IElementType MACRO = new NASMElementType("MACRO");
  IElementType MACRO_CALL = new NASMElementType("MACRO_CALL");
  IElementType MACRO_DEF_CALL = new NASMElementType("MACRO_DEF_CALL");
  IElementType MACRO_LABEL = new NASMElementType("MACRO_LABEL");
  IElementType MACRO_PARAM_COUNTER = new NASMElementType("MACRO_PARAM_COUNTER");
  IElementType MACRO_PARAM_REFERENCE = new NASMElementType("MACRO_PARAM_REFERENCE");
  IElementType MACRO_VAR_REFERENCE = new NASMElementType("MACRO_VAR_REFERENCE");
  IElementType MAP_OPTION = new NASMElementType("MAP_OPTION");
  IElementType MINUS_EXPR = new NASMElementType("MINUS_EXPR");
  IElementType MNEMONIC = new NASMElementType("MNEMONIC");
  IElementType MODULUS_EXPR = new NASMElementType("MODULUS_EXPR");
  IElementType MUL_EXPR = new NASMElementType("MUL_EXPR");
  IElementType NUMERIC_LITERAL = new NASMElementType("NUMERIC_LITERAL");
  IElementType PARENTHESIS_EXPR = new NASMElementType("PARENTHESIS_EXPR");
  IElementType PLUS_EXPR = new NASMElementType("PLUS_EXPR");
  IElementType PREPROCESSOR = new NASMElementType("PREPROCESSOR");
  IElementType REG = new NASMElementType("REG");
  IElementType SEG = new NASMElementType("SEG");
  IElementType SEGMENT_ADDRESS = new NASMElementType("SEGMENT_ADDRESS");
  IElementType STR = new NASMElementType("STR");
  IElementType STRLEN = new NASMElementType("STRLEN");
  IElementType STRUC = new NASMElementType("STRUC");
  IElementType STRUCTURE = new NASMElementType("STRUCTURE");
  IElementType STRUCTURE_FIELD = new NASMElementType("STRUCTURE_FIELD");
  IElementType TOKEN_CONCAT_EXPR = new NASMElementType("TOKEN_CONCAT_EXPR");

  IElementType ASSIGN_TAG = new NASMTokenType("ASSIGN_TAG");
  IElementType AT_TAG = new NASMTokenType("AT_TAG");
  IElementType AVX2_OP = new NASMTokenType("AVX2_OP");
  IElementType AVX512_OP = new NASMTokenType("AVX512_OP");
  IElementType AVX_OP = new NASMTokenType("AVX_OP");
  IElementType BINARY = new NASMTokenType("BINARY");
  IElementType BITSHIFT_L = new NASMTokenType("<<");
  IElementType BITSHIFT_R = new NASMTokenType(">>");
  IElementType BITWISE_AND = new NASMTokenType("&");
  IElementType BITWISE_NOT = new NASMTokenType("~");
  IElementType BITWISE_OR = new NASMTokenType("|");
  IElementType BITWISE_XOR = new NASMTokenType("^");
  IElementType CHARACTER = new NASMTokenType("CHARACTER");
  IElementType COLON = new NASMTokenType(":");
  IElementType COMMENT = new NASMTokenType("COMMENT");
  IElementType DATA_OP = new NASMTokenType("DATA_OP");
  IElementType DECIMAL = new NASMTokenType("DECIMAL");
  IElementType DEFINE_TAG = new NASMTokenType("DEFINE_TAG");
  IElementType DIRECTIVE_OP = new NASMTokenType("DIRECTIVE_OP");
  IElementType DIVIDE = new NASMTokenType("/");
  IElementType DOLLARSIGN = new NASMTokenType("$");
  IElementType DOLLARSIGN2 = new NASMTokenType("$$");
  IElementType DOT = new NASMTokenType(".");
  IElementType ELIF_TAG = new NASMTokenType("ELIF_TAG");
  IElementType ELSE_TAG = new NASMTokenType("ELSE_TAG");
  IElementType ENDIF_TAG = new NASMTokenType("ENDIF_TAG");
  IElementType ENDSTRUC_TAG = new NASMTokenType("ENDSTRUC_TAG");
  IElementType END_DIRECTIVE_OP = new NASMTokenType("END_DIRECTIVE_OP");
  IElementType EQU = new NASMTokenType("EQU");
  IElementType EQUAL = new NASMTokenType("=");
  IElementType EQUALEQUAL = new NASMTokenType("==");
  IElementType ERROR_TAG = new NASMTokenType("ERROR_TAG");
  IElementType FLOAT_DECIMAL = new NASMTokenType("FLOAT_DECIMAL");
  IElementType FPU_OP = new NASMTokenType("FPU_OP");
  IElementType GENERAL_OP = new NASMTokenType("GENERAL_OP");
  IElementType GREATERTHAN = new NASMTokenType(">");
  IElementType GREATERTHANOREQUAL = new NASMTokenType(">=");
  IElementType HEXADECIMAL = new NASMTokenType("HEXADECIMAL");
  IElementType HLE_OP = new NASMTokenType("HLE_OP");
  IElementType ID = new NASMTokenType("ID");
  IElementType ID_EXTENSION = new NASMTokenType("ID_EXTENSION");
  IElementType IEND_TAG = new NASMTokenType("IEND_TAG");
  IElementType IFCTX_TAG = new NASMTokenType("IFCTX_TAG");
  IElementType IFDEF_TAG = new NASMTokenType("IFDEF_TAG");
  IElementType IFIDN_TAG = new NASMTokenType("IFIDN_TAG");
  IElementType IFID_TAG = new NASMTokenType("IFID_TAG");
  IElementType IFMACRO_TAG = new NASMTokenType("IFMACRO_TAG");
  IElementType IFNUM_TAG = new NASMTokenType("IFNUM_TAG");
  IElementType IFSTR_TAG = new NASMTokenType("IFSTR_TAG");
  IElementType IF_TAG = new NASMTokenType("IF_TAG");
  IElementType INCLUDE_TAG = new NASMTokenType("INCLUDE_TAG");
  IElementType ISTRUC_TAG = new NASMTokenType("ISTRUC_TAG");
  IElementType LBL = new NASMTokenType("LBL");
  IElementType LBL_DEF = new NASMTokenType("LBL_DEF");
  IElementType LESSTHAN = new NASMTokenType("<");
  IElementType LESSTHANOREQUAL = new NASMTokenType("<=");
  IElementType LOGICAL_AND = new NASMTokenType("&&");
  IElementType LOGICAL_OR = new NASMTokenType("||");
  IElementType LOGICAL_XOR = new NASMTokenType("^^");
  IElementType MACRO_END_TAG = new NASMTokenType("MACRO_END_TAG");
  IElementType MACRO_NOLIST_QUAL = new NASMTokenType("MACRO_NOLIST_QUAL");
  IElementType MACRO_PARAM_COUNT = new NASMTokenType("MACRO_PARAM_COUNT");
  IElementType MACRO_PARAM_LBL_DEF = new NASMTokenType("MACRO_PARAM_LBL_DEF");
  IElementType MACRO_PARAM_REF = new NASMTokenType("MACRO_PARAM_REF");
  IElementType MACRO_TAG = new NASMTokenType("MACRO_TAG");
  IElementType MACRO_VAR_REF = new NASMTokenType("MACRO_VAR_REF");
  IElementType MAP_FILE = new NASMTokenType("MAP_FILE");
  IElementType MAP_OPTIONS = new NASMTokenType("MAP_OPTIONS");
  IElementType MINUS = new NASMTokenType("-");
  IElementType MMX_OP = new NASMTokenType("MMX_OP");
  IElementType NL = new NASMTokenType("NL");
  IElementType NOTEQUAL = new NASMTokenType("!=");
  IElementType OP_PREFIX = new NASMTokenType("OP_PREFIX");
  IElementType PERCENT = new NASMTokenType("%");
  IElementType PERCENT2 = new NASMTokenType("%%");
  IElementType PLUS = new NASMTokenType("+");
  IElementType PREPROCESSOR_OP = new NASMTokenType("PREPROCESSOR_OP");
  IElementType QUESTION = new NASMTokenType("?");
  IElementType REGISTER = new NASMTokenType("REGISTER");
  IElementType ROUND_L = new NASMTokenType("(");
  IElementType ROUND_R = new NASMTokenType(")");
  IElementType SECTION = new NASMTokenType("SECTION");
  IElementType SEGMENT = new NASMTokenType("SEGMENT");
  IElementType SEGMENT_ADDR_L = new NASMTokenType("SEGMENT_ADDR_L");
  IElementType SEGMENT_REGISTER = new NASMTokenType("SEGMENT_REGISTER");
  IElementType SEMICOLON = new NASMTokenType(";");
  IElementType SEPARATOR = new NASMTokenType(",");
  IElementType SIZE_TYPE = new NASMTokenType("SIZE_TYPE");
  IElementType SQUARE_L = new NASMTokenType("[");
  IElementType SQUARE_R = new NASMTokenType("]");
  IElementType SSE2_OP = new NASMTokenType("SSE2_OP");
  IElementType SSE3_OP = new NASMTokenType("SSE3_OP");
  IElementType SSE4_OP = new NASMTokenType("SSE4_OP");
  IElementType SSE_OP = new NASMTokenType("SSE_OP");
  IElementType STRING = new NASMTokenType("STRING");
  IElementType STRLEN_TAG = new NASMTokenType("STRLEN_TAG");
  IElementType STRUC_TAG = new NASMTokenType("STRUC_TAG");
  IElementType SYSTEM_OP = new NASMTokenType("SYSTEM_OP");
  IElementType TIMES = new NASMTokenType("*");
  IElementType TOKEN_CONCAT = new NASMTokenType("%+");
  IElementType VIRTUALIZATION_OP = new NASMTokenType("VIRTUALIZATION_OP");
  IElementType X64_OP = new NASMTokenType("X64_OP");
  IElementType ZEROES = new NASMTokenType("ZEROES");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ADDRESS) {
        return new NASMAddressImpl(node);
      }
      else if (type == ASSIGN) {
        return new NASMAssignImpl(node);
      }
      else if (type == BITWISE_AND_EXPR) {
        return new NASMBitwiseANDExprImpl(node);
      }
      else if (type == BITWISE_NOT_EXPR) {
        return new NASMBitwiseNOTExprImpl(node);
      }
      else if (type == BITWISE_OR_EXPR) {
        return new NASMBitwiseORExprImpl(node);
      }
      else if (type == BITWISE_XOR_EXPR) {
        return new NASMBitwiseXORExprImpl(node);
      }
      else if (type == BIT_SHIFT_L_EXPR) {
        return new NASMBitShiftLExprImpl(node);
      }
      else if (type == BIT_SHIFT_R_EXPR) {
        return new NASMBitShiftRExprImpl(node);
      }
      else if (type == CONDITIONAL) {
        return new NASMConditionalImpl(node);
      }
      else if (type == CONSTANT) {
        return new NASMConstantImpl(node);
      }
      else if (type == DATA_ELEMENT) {
        return new NASMDataElementImpl(node);
      }
      else if (type == DEFINE) {
        return new NASMDefineImpl(node);
      }
      else if (type == DIRECTIVE) {
        return new NASMDirectiveImpl(node);
      }
      else if (type == DIV_EXPR) {
        return new NASMDivExprImpl(node);
      }
      else if (type == END_DIRECTIVE) {
        return new NASMEndDirectiveImpl(node);
      }
      else if (type == ERROR) {
        return new NASMErrorImpl(node);
      }
      else if (type == IDENTIFIER) {
        return new NASMIdentifierImpl(node);
      }
      else if (type == INCLUDE) {
        return new NASMIncludeImpl(node);
      }
      else if (type == INSTRUCTION) {
        return new NASMInstructionImpl(node);
      }
      else if (type == I_STRUC) {
        return new NASMIStrucImpl(node);
      }
      else if (type == LABEL) {
        return new NASMLabelImpl(node);
      }
      else if (type == LABEL_DEF_MACRO) {
        return new NASMLabelDefMacroImpl(node);
      }
      else if (type == LABEL_IDENTIFIER) {
        return new NASMLabelIdentifierImpl(node);
      }
      else if (type == LOGICAL_AND_EXPR) {
        return new NASMLogicalANDExprImpl(node);
      }
      else if (type == LOGICAL_OR_EXPR) {
        return new NASMLogicalORExprImpl(node);
      }
      else if (type == LOGICAL_XOR_EXPR) {
        return new NASMLogicalXORExprImpl(node);
      }
      else if (type == MACRO) {
        return new NASMMacroImpl(node);
      }
      else if (type == MACRO_CALL) {
        return new NASMMacroCallImpl(node);
      }
      else if (type == MACRO_DEF_CALL) {
        return new NASMMacroDefCallImpl(node);
      }
      else if (type == MACRO_LABEL) {
        return new NASMMacroLabelImpl(node);
      }
      else if (type == MACRO_PARAM_COUNTER) {
        return new NASMMacroParamCounterImpl(node);
      }
      else if (type == MACRO_PARAM_REFERENCE) {
        return new NASMMacroParamReferenceImpl(node);
      }
      else if (type == MACRO_VAR_REFERENCE) {
        return new NASMMacroVarReferenceImpl(node);
      }
      else if (type == MAP_OPTION) {
        return new NASMMapOptionImpl(node);
      }
      else if (type == MINUS_EXPR) {
        return new NASMMinusExprImpl(node);
      }
      else if (type == MNEMONIC) {
        return new NASMMnemonicImpl(node);
      }
      else if (type == MODULUS_EXPR) {
        return new NASMModulusExprImpl(node);
      }
      else if (type == MUL_EXPR) {
        return new NASMMulExprImpl(node);
      }
      else if (type == NUMERIC_LITERAL) {
        return new NASMNumericLiteralImpl(node);
      }
      else if (type == PARENTHESIS_EXPR) {
        return new NASMParenthesisExprImpl(node);
      }
      else if (type == PLUS_EXPR) {
        return new NASMPlusExprImpl(node);
      }
      else if (type == PREPROCESSOR) {
        return new NASMPreprocessorImpl(node);
      }
      else if (type == REG) {
        return new NASMRegImpl(node);
      }
      else if (type == SEG) {
        return new NASMSegImpl(node);
      }
      else if (type == SEGMENT_ADDRESS) {
        return new NASMSegmentAddressImpl(node);
      }
      else if (type == STR) {
        return new NASMStrImpl(node);
      }
      else if (type == STRLEN) {
        return new NASMStrlenImpl(node);
      }
      else if (type == STRUC) {
        return new NASMStrucImpl(node);
      }
      else if (type == STRUCTURE) {
        return new NASMStructureImpl(node);
      }
      else if (type == STRUCTURE_FIELD) {
        return new NASMStructureFieldImpl(node);
      }
      else if (type == TOKEN_CONCAT_EXPR) {
        return new NASMTokenConcatExprImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
