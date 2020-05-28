package org.moy.spring.http.client;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String url = "http://127.0.0.1:9000";
        System.err.println(HttpHelper.get(url + "/demo/indexGet"));
        System.err.println(HttpHelper.post(url + "/demo/index"));

        System.err.println(HttpHelper.postTextBody(url + "/demo/index", "id=1&name=2"));
        System.err.println(HttpHelper.postTextBody(url + "/demo/index?id=1&name=2", ""));
        System.err.println(HttpHelper.postTextBody(url + "/demo/indexStr?demo=demo", "demo=2"));

        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
        meBuilder.addPart("name", new StringBody("233", ContentType.TEXT_PLAIN));
        meBuilder.build();
        System.err.println(HttpHelper.postJsonBody(url + "/demo/indexBody", "{}"));
        System.err.println(HttpHelper.postJsonBody(url + "/demo/indexBody", "{\"name\":\"233\"}"));
    }
}
