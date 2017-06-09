package com.nasmlanguage;

import com.intellij.openapi.fileTypes.*;
import org.jetbrains.annotations.NotNull;


public class NASMFileTypeFactory extends FileTypeFactory {
    @Override
    public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
        fileTypeConsumer.consume(NASMFileType.INSTANCE, "asm");
    }
}
