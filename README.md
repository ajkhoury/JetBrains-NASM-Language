# JetBrains NASM Language Support

A plugin that adds NASM support for the IntelliJ Platform IDEs.

The latest release can be found in the [JetBrains Plugins](https://plugins.jetbrains.com/plugin/9759-nasm-assembly-language)

![NASM Language](http://i.imgur.com/0BW2jL7.png "NASM Language Preview")

# Features

Version v0.5-beta.1 pre-release

    FPU, MMX, SSE, SSE2, SSE3, SSE4, AVX, AVX2, AVX512, Virtualization, and General instruction support.
    Syntax highlighting.
    Single and multiline macro support.
    Conditional assembly directives support.
    Preprocessor directives support.
    Structure support.
    Brace matching for braces, macros, preprocessor, and structs.
    Improved macro highlighting.
    Goto declaration support for labels.

## TODO
- Code Completion
- Find Usages
- Goto Symbol
- Debugger Support

# v0.5-beta.1 Changes
    
    Fixed bug caused by an invalid index being used when getting references.
    Add plugin logo icon and now use svg for file icons.

# Complete Changelog

    Added macro syntax highlighting.
    Added support for conditional assembly directives.
    Fix for bug where blank comments would not match the comment regex.
    Added support for structures and improved highlighting.
    Fixed conditional assembly problems.
    Fixed number size prefixes, segment addresses, data/instruction labels.
    Added new operator expressions.
    Added pinning for some repeating rules.
    Added system instructions.
    Added highlighting for segment addresses, and structure references.
    Fix for blank labels in structs.
    Fixed istruc structure parsing.
    Added support for the RETF instruction.
    Added bitwise operations and fixed macro label definitions.
    Added BSS section.
    Now allow structs to have macros and identifiers.
    Fixed macro labels.
    Allow expressions in struc fields.
    Can now prefix expressions with bitwise NOT, minus, or plus symbol.
    Fixed segment addresses.
    Added AVX, AVX2, AVX512, and Virtualization instruction sets.
    Added brace matching for braces, macros, preprocessor, and structs.
    Fixed size type prefixes on expressions.
    Added identifiers to istruc structure instances.
    Added .RDATA section directive.
    Added improved macro highlighting.
    Fixed a null pointer exception that would happen with labels in segment addresses.
    Added highlighting for macros in segment part of segment addresses.
    Fixed section and segment directives so you can use any section/segment name.
    Can now use size prefixes for macro calls/references.
    Added the ability to use END tag in expressions.
    Conditional and other preprocessor directives now supported inside macro definitions.
    Nested macros now supported.
    Macros without parameters now properly supported.
    Single semicolon (comment) now allowed at end of file.
    Added a "ChooseByNameContributor" that can navigate to a symbol by name.
    Fixed bug where generic identifiers would error if a comment was after it.
    Fixed highlighting which broke in v0.4.1.
    Began adding more conditions for conditional expressions.
    Added ability to use non-local to macro labels.
    Macro parameters can now be any expression or mnemonic.
    Added size type prefix for registers.
    Added support for commas in directives. Thanks to github.com/snowwm.
    Added support for back-tick strings.
    Added initial references implementation for goto declaration support.
    Fixed bug caused by an invalid index being used when getting references.
    Add plugin logo icon and now use svg for file icons.

# License

Copyright (c) 2017-2019 Aidan Khoury

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.