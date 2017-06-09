MNEMONICS_GENERAL_PURPOSE=(mov([sz]x)?|cmov(n?[abceglopsz]|n?[abgl]e|p[eo]))|           // Data Transfer MOV
                          (xchg|bswap|xadd|cmpxchg(8b)?)|                               // Data Transfer XCHG
                          ((push|pop)(ad?)?|cwde?|cdq|cbw)|                             // Data Transfer Other
                          (adcx?|adox|add|sub|sbb|i?mul|i?div|inc|dec|neg|cmp)|         // Binary Arithmetic
                          (daa|das|aaa|aas|aam|aad)|                                    // Decimal Arithmetic
                          (and|x?or|not)|                                               // Logical
                          (s[ah][rl]|sh[rl]d|r[co][rl])|                                // Bits Rotate
                          (set(n?[abceglopsz]|n?[abgl]e|p[eo]))|                        // Bits and Bytes set
                          (bt[crs]?|bs[fr]|test|crc32|popcnt)|                          // Bits and Bytes other
                          (jmp|jn?[abceglopsz]|jn?[abgl]e|jp[eo]|j[er]?cxz)|            // Control Transfer
                          (loop(n?[ez])?|call|ret|iret[dq]?|into?|bound|enter|leave)|   // Control Transfer Other
                          ((mov|cmp|sca|lod|sto)(s[bdw]?)|rep(n?[ez])?)|                // Strings
                          ((in|out)(s[bdw]?)?)|                                         // IO
                          ((st|cl)[cdi]|cmc|[ls]ahf|(push|pop)f[dq]?)|                  // Flag Control
                          (l[defgs]s)|                                                  // Segment Registers
                          (lea|nop|ud2|xlatb?|cpuid|movbe)|                             // Misc
                          (rdrand|rdseed)|                                              // RNG
                          (andn|bextr|bls(i|r|msk)|bzhi|pdep|pext|[lt]zcnt|(mul|ror|sar|shl|shr)x)  // BMI

MNEMONICS_64BIT=\b(cdqe|cqo|(cmp|lod|mov|sto)sq|cmpxchg16b|mov(ntq|sxd)|scasq|swapgs|sys(call|ret))\b


MNEMONICS=MNEMONICS_GENERAL_PURPOSE|MNEMONICS_64BIT