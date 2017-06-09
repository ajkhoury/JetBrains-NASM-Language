package com.nasmlanguage;

import com.intellij.lexer.FlexAdapter;

public class NASMLexerAdapter extends FlexAdapter {
    public NASMLexerAdapter() {
        super(new _NASMLexer());
    }
}
