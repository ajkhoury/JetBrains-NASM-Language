package com.nasmlanguage;

import com.intellij.lexer.FlexAdapter;

public class NASMLexer extends FlexAdapter {
    public NASMLexer() {
        super(new _NASMLexer());
    }
}
