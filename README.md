# JetBrains NASM Language Support

A plugin that adds NASM support for the IntelliJ Platform IDEs.

![NASM Language](http://i.imgur.com/0BW2jL7.png "NASM Language Preview")

# Features

Version v0.4-beta.1 pre-release

    FPU, MMX, SSE, SSE2, SSE3, SSE4, AVX, AVX2, AVX512, Virtualization, and General instruction support.
    Syntax highlighting.
    Single and multiline macro support.
    Conditional assembly directives support.
    Preprocessor directives support.
    Structure support.
    Brace matching for braces, macros, preprocessor, and structs.
    Improved macro highlighting.

## TODO
- Code Completion
- Find Usages
- Goto Symbol
- Debugger Support

# v0.4-beta.1 Changes
    
    Fixed bug where generic identifiers would error if a comment was after it.

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

# License

Copyright (C) 2017-2018 Aidan Khoury (ajkhoury)

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/.
