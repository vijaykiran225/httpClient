package com.http.multipart.processor;

import com.http.multipart.processor.utils.HttpUtils;
import com.http.multipart.request.MultipartRequestObject;
import com.http.multipart.response.MultipartResponseObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;


import java.io.IOException;
import java.util.Optional;

public class ConnectionManager {

    public static Optional<MultipartResponseObject> executeMultipartCall(MultipartRequestObject requestObject){

        HttpClientBuilder hcBuilder = HttpClientBuilder.create();
        RequestBuilder reqBuilder = RequestBuilder.post();

        HttpEntity entity = HttpUtils.getHttpEntity(requestObject);
        reqBuilder.setEntity(entity);

        reqBuilder.setUri(requestObject.getURL());

        requestObject.getHeaders().forEach(reqBuilder::setHeader);

        CloseableHttpClient httpClient = hcBuilder.build();
        MultipartResponseObject responseObject=null;
        try {
            CloseableHttpResponse response = httpClient.execute(reqBuilder.build());

            responseObject=new MultipartResponseObject();
            responseObject.setStatusCode(response.getStatusLine().getStatusCode());
            HttpUtils.extractHeaders(responseObject, response);
            HttpUtils.extractResponseBody(responseObject, response);

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Optional.ofNullable(responseObject);
    }


}
