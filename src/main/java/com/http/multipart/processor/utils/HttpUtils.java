package com.http.multipart.processor.utils;

import com.http.multipart.request.FilePartDTO;
import com.http.multipart.request.FormDataPartDTO;
import com.http.multipart.request.MultipartRequestObject;
import com.http.multipart.response.MultipartResponseObject;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.FormBodyPartBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Vijay kiran on 019 19-Nov-2017.
 */
public class HttpUtils {
    public static void extractHeaders(MultipartResponseObject responseObject, CloseableHttpResponse response) {
        Map<String,String> responseHeaders=new HashMap<>();
        Arrays.stream(response.getAllHeaders()).forEach(header ->
                responseHeaders.put(header.getName(),header.getValue()));
        responseObject.setHeaders(responseHeaders);
    }

    public static void extractResponseBody(MultipartResponseObject responseObject, CloseableHttpResponse response) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder stringBuilder=new StringBuilder();
        reader.lines().forEach(stringBuilder::append);
        responseObject.setResponseBody(stringBuilder.toString());
    }

    public static FormBodyPart mapStringBodyPart(@Valid FormDataPartDTO dataPart) {
        return FormBodyPartBuilder.create()
                    .setName(dataPart.getKeyName())
                    .setField("Content-Type",dataPart.getContentType())
                    .setBody(new StringBody(dataPart.getData(), ContentType.parse(dataPart.getContentType())))
                    .build();
    }

    public static FormBodyPart mapFormDataPart(@Valid FilePartDTO filePart) {
        return FormBodyPartBuilder.create()
                    .setName(filePart.getKeyName())
                    .setBody(new FileBody(filePart.getFile()))
                    .build();
    }

    public static HttpEntity getHttpEntity(MultipartRequestObject requestObject) {
        List<FormBodyPart> fileParts = requestObject.getFileBody().stream().map(HttpUtils::mapFormDataPart).collect(Collectors.toList());

        List<FormBodyPart> stringParts = requestObject.getDataBody().stream().map(HttpUtils::mapStringBodyPart).collect(Collectors.toList());

        MultipartEntityBuilder entitybuilder = MultipartEntityBuilder.create()
                .setContentType(ContentType.create(requestObject.getContentType()));
        stringParts.forEach(entitybuilder::addPart);
        fileParts.forEach(entitybuilder::addPart);
        return entitybuilder.build();
    }

    public static String displayCurl(MultipartRequestObject requestObject) {
        StringBuilder curl=new StringBuilder("curl -v -k ");
        curl.append(requestObject.getURL());

        curl.append(" -H \"Content-type:"+requestObject.getContentType()+"\" ");

        requestObject.getHeaders().forEach((key,value) -> {
            curl.append(" -H \""+key+":"+value+"\" ");
        });

        requestObject.getDataBody().forEach(value -> {
            curl.append(" -F \""+value.getKeyName()+"="+value.getData()+";type="+value.getContentType()+"\" ");
        });

        requestObject.getFileBody().forEach(value -> {
            curl.append(" -F \""+value.getKeyName()+"=@"+value.getFile().getAbsolutePath()+"\" ");
        });

        return curl.toString();
    }

    public static String getMultipartRawBody(MultipartRequestObject requestObject) {


        final String boundary = UUID.randomUUID().toString();
        StringBuilder request = new StringBuilder();

        request.append("--");
        request.append(boundary);
        requestObject.getDataBody().forEach(aPart -> {
            request.append("\r\n");
            request.append("Content-Disposition: form-data; ");
            request.append("name=");
            request.append(aPart.getKeyName());
            request.append(";");
            request.append("\r\n");
            request.append("Content-Type: ");
            request.append(aPart.getContentType());
            request.append("\r\n");
            request.append("\r\n");
            request.append(aPart.getData());
            request.append("\r\n");
            request.append("--");
            request.append(boundary);
        });
        requestObject.getFileBody().forEach(aPart -> {
            request.append("\r\n");
            request.append("Content-Disposition: form-data; ");
            request.append("name=");
            request.append(aPart.getKeyName());
            request.append(";");
            request.append("filename=");
            request.append(aPart.getFileName());
            request.append(";");
            request.append("\r\n");
            request.append("Content-Type: application/octet-stream");
            request.append("\r\n");
            request.append("\r\n");
            try {
                request.append(FileUtils.readFileToString(aPart.getFile()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            request.append("\r\n");
            request.append("--");
            request.append(boundary);
        });

        request.append("--\r\n");
        final String body = request.toString();
        System.out.println(body);
        return body;
    }
}
