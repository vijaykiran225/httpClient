package com.http.multipart.response;

import org.apache.http.impl.client.BasicResponseHandler;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vijay kiran on 019 19-Nov-2017.
 */
public class MultipartResponseObject {


    private Map<String,String> headers=new HashMap<>();
    private Response.Status status;
    private int statusCode;
    private String responseBody;

    public MultipartResponseObject() {}

    public MultipartResponseObject(Map<String, String> headers, Response.Status status, int statusCode, String responseBody) {
        this.headers = headers;
        this.status = status;
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Response.Status getStatus() {
        return status;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}

