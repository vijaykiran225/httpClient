package com.http.multipart.request;

import java.net.URI;
import java.util.*;
import java.util.function.Predicate;

/**
 * Created by Vijay kiran on 019 19-Nov-2017.
 */
public class MultipartRequestObject {

    private URI URL;
    private Map<String,String> headers=new HashMap<>();
    private String contentType;

    private List<FormDataPartDTO> dataBody=new ArrayList<>();
    private List<FilePartDTO> fileBody=new ArrayList<>();

    public MultipartRequestObject() {}

    public MultipartRequestObject(URI URL, Map<String, String> headers, String contentType, List<FormDataPartDTO> dataBody, List<FilePartDTO> fileBody) {
        this.URL = URL;
        this.headers = headers;
        this.contentType = contentType;
        Predicate<List> listNotEmpty = e->  e!= null && e.size() > 0;
        if(listNotEmpty.test(dataBody))
            this.dataBody = dataBody;
        if(listNotEmpty.test(fileBody))
            this.fileBody = fileBody;
    }

    public URI getURL() {
        return URL;

    }

    public void setURL(URI URL) {
        this.URL = URL;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public List<FormDataPartDTO> getDataBody() {
        return dataBody;
    }

    public void setDataBody(List<FormDataPartDTO> dataBody) {
        this.dataBody = dataBody;
    }

    public List<FilePartDTO> getFileBody() {
        return fileBody;
    }

    public void setFileBody(List<FilePartDTO> fileBody) {
        this.fileBody = fileBody;
    }


}
