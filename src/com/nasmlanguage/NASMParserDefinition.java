/*++

NASM Assembly Language Plugin
Copyright (c) 2017-2019 Aidan Khoury. All rights reserved.

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

import com.intellij.lang.*;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.tree.*;
import com.nasmlanguage.parser.NASMParser;
import com.nasmlanguage.psi.*;
import org.jetbrains.annotations.*;

public class NASMParserDefinition implements ParserDefinition {
    public static final TokenSet WHITESPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet STRING_LITERALS = TokenSet.create(NASMTypes.STRING);
    public static final TokenSet COMMENTS = TokenSet.create(NASMTypes.COMMENT);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new NASMLexer();
    }

    @NotNull
    @Override
    public PsiParser createParser(final Project project) {
        return new NASMParser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return NASMFileElementType.INSTANCE;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return WHITESPACES;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return STRING_LITERALS;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return NASMTypes.Factory.createElement(node);
    }

    @NotNull
    @Override
    public PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new NASMFile(viewProvider);
    }
}
