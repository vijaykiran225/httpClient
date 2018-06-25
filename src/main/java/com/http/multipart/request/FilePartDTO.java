package com.http.multipart.request;

import io.vertx.core.cli.Option;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Vijay kiran on 019 19-Nov-2017.
 */
public class FilePartDTO  {
    public String getKeyName() {
        return keyName;
    }

    public FilePartDTO(@NotNull String keyName, @NotNull File file) {
        this.keyName = keyName;
        this.file = file;
        this.fileName=Optional
                .of(file.getName())
                .orElse(UUID.randomUUID().toString());
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @NotNull

    String keyName;

    @NotNull
    File file;

    String fileName=UUID.randomUUID().toString();

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
