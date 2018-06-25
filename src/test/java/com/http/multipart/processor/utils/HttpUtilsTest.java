package com.http.multipart.processor.utils;

import com.http.multipart.request.FilePartDTO;
import com.http.multipart.request.FormDataPartDTO;
import com.http.multipart.request.MultipartRequestObject;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vijay kiran on 019 19-Nov-2017.
 */
public class HttpUtilsTest {
    @Test
    public void displayCurl() throws Exception {


        URI URL = new URI("https://api.sandbox.paypal.com/v1/customer/disputes/PP-000-042-507-457/provide-evidence");

        String body = "{\"evidence_type\":\"OTHER\",\"notes\":\"Test\"}";
        FormDataPartDTO dataPartDTO=new FormDataPartDTO("input",body,"application/json");
        FormDataPartDTO dataPartDTO1=new FormDataPartDTO("input1",body);
        List<FormDataPartDTO> dataBody= Arrays.asList(dataPartDTO,dataPartDTO1);

        FilePartDTO filePartDTO=new FilePartDTO("file1",new File("C:\\Users\\Vijay kiran\\Downloads\\uploadFile.pdf"));
        FilePartDTO filePartDTO1=new FilePartDTO("file2",new File("C:\\Users\\Vijay kiran\\Downloads\\uploadFile2.pdf"));

        List<FilePartDTO> fileBody = Arrays.asList(filePartDTO,filePartDTO1);

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer A21AAGv1Zr4kQOs7a_dkcqHHLqs-D37wA7lA2Uo5rfrMzpTYCi2Ib0UpDNGrjYJMmOhBt9pZQJN0vqvVNllJoPBqFCYBYQ57w");


        MultipartRequestObject request=new MultipartRequestObject();
        request.setContentType("multipart/related");
        request.setDataBody(dataBody);
        request.setHeaders(headers);
        request.setURL(URL);
        request.setFileBody(fileBody);


        System.out.println(HttpUtils.displayCurl(request));
    }

    @Test
    public void getMultipartRawBody() throws URISyntaxException {
        URI URL = new URI("https://api.sandbox.paypal.com/v1/customer/disputes/PP-000-042-507-457/provide-evidence");

        String body = "{\"evidence_type\":\"OTHER\",\"notes\":\"Test\"}";
        FormDataPartDTO dataPartDTO=new FormDataPartDTO("input",body,"application/json");
        FormDataPartDTO dataPartDTO1=new FormDataPartDTO("input1",body);
        List<FormDataPartDTO> dataBody= Arrays.asList(dataPartDTO,dataPartDTO1);

        FilePartDTO filePartDTO=new FilePartDTO("file1",new File("C:\\Users\\Vijay kiran\\Downloads\\uploadFile.pdf"));
        FilePartDTO filePartDTO1=new FilePartDTO("file2",new File("C:\\Users\\Vijay kiran\\Downloads\\uploadFile2.pdf"));

        List<FilePartDTO> fileBody = Arrays.asList(filePartDTO,filePartDTO1);

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer A21AAGv1Zr4kQOs7a_dkcqHHLqs-D37wA7lA2Uo5rfrMzpTYCi2Ib0UpDNGrjYJMmOhBt9pZQJN0vqvVNllJoPBqFCYBYQ57w");


        MultipartRequestObject request=new MultipartRequestObject();
        request.setContentType("multipart/related");
        request.setDataBody(dataBody);
        request.setHeaders(headers);
        request.setURL(URL);
        request.setFileBody(fileBody);

        System.out.println(HttpUtils.getMultipartRawBody(request));
    }

}