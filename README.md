# JetBrains-NASM-Language

A plugin that adds NASM support to various JetBrains IDEs, such as CLion,  Gogland, and IntelliJ.

There is a plugin in development [here](https://plugins.jetbrains.com/plugin/9386-nasm-assembly-support), however it is not open source and is lacking full NASM support.

![NASM Language](http://i.imgur.com/0BW2jL7.png "NASM Language Preview")

# Features

Version 0.1.6 pre-release
- FPU, MMX, SSE, SSE2, SSE3, SSE4, and general instruction support
- Syntax highlighting
- Single and multiline macro support
- Conditional assembly directives support
- Preprocessor directives support
- Structure support

## Todo
- Code completion
- Goto symbol
- ~Improved highlighting~

# Changes

- Added macro syntax highlighting
- Added support for conditional assembly directives
- Fix for bug where blank comments would not match the comment regex
- Added support for structures and improved highlighting
- Fixed conditional assembly problems
- Fixed number size prefixes, segment addresses, data/instruction labels
- Added new operator expressions
- Added pinning for some repeating rules
- Added system instructions
- Added highlighting for segment addresses, and structure references
- Fix for blank labels in structs
- Fixed istruc structure parsing

# License

Copyright (C) 2016 Aidan Khoury (dude719)

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/.
