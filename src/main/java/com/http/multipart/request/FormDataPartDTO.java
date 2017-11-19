package com.http.multipart.request;

import javax.validation.constraints.NotNull;

/**
 * Created by Vijay kiran on 019 19-Nov-2017.
 */
public class FormDataPartDTO {

    public FormDataPartDTO(@NotNull String keyName, @NotNull String data, String contentType) {
        this.keyName = keyName;
        this.data = data;
        this.contentType = contentType;
    }
    public FormDataPartDTO(@NotNull String keyName, @NotNull String data) {
        this.keyName = keyName;
        this.data = data;
    }

    @NotNull
    String keyName;

    public String getKeyName() {

        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @NotNull
    String data;

    String contentType="text/plain";
}
