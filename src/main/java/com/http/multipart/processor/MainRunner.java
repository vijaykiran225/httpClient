package com.http.multipart.processor;

import com.http.multipart.request.FilePartDTO;
import com.http.multipart.request.FormDataPartDTO;
import com.http.multipart.request.MultipartRequestObject;
import com.http.multipart.response.MultipartResponseObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by Vijay kiran on 019 19-Nov-2017.
 */
public class MainRunner {

    public static void main(String[] args) throws URISyntaxException {

        URI URL = new URI("https://api.sandbox.paypal.com/v1/customer/disputes/PP-000-042-507-457/provide-evidence");

        String body = "{\"evidence_type\":\"OTHER\",\"notes\":\"Test\"}";
        FormDataPartDTO dataPartDTO=new FormDataPartDTO("input",body,"application/json");
        List<FormDataPartDTO> dataBody= Arrays.asList(dataPartDTO);


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer A21AAGv1Zr4kQOs7a_dkcqHHLqs-D37wA7lA2Uo5rfrMzpTYCi2Ib0UpDNGrjYJMmOhBt9pZQJN0vqvVNllJoPBqFCYBYQ57w");


        MultipartRequestObject request=new MultipartRequestObject();
        request.setContentType("multipart/related");
        request.setDataBody(dataBody);
        request.setHeaders(headers);
        request.setURL(URL);

        Optional<MultipartResponseObject> response = ConnectionManager.executeMultipartCall(request);

        if (response.isPresent()){
            MultipartResponseObject responseObject = response.get();

            System.out.println(responseObject.getStatusCode());
            System.out.println(responseObject.getResponseBody());
        }
    }
}
