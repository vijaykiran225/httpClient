package com.http.multipart.request;

import javax.validation.constraints.NotNull;
import java.io.File;

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

}
